package edu.ncsu.csc.itrust2.controllers.api;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ncsu.csc.itrust2.controllers.api.comm.LogEntryRequestBody;
import edu.ncsu.csc.itrust2.controllers.api.comm.LogEntryTableRow;
import edu.ncsu.csc.itrust2.models.User;
import edu.ncsu.csc.itrust2.models.enums.Role;
import edu.ncsu.csc.itrust2.models.enums.TransactionType;
import edu.ncsu.csc.itrust2.models.security.LogEntry;
import edu.ncsu.csc.itrust2.services.UserService;
import edu.ncsu.csc.itrust2.services.security.LogEntryService;
import edu.ncsu.csc.itrust2.utils.LoggerUtil;

/**
 * REST controller for interacting with Log Entry-related endpoints This will
 * have somewhat reduced functionality compared to the other controllers because
 * we don't want users to be able to delete logged events (_even_ if they are
 * Personnel/an admin)
 *
 * @author Kai Presler-Marshall
 *
 */
@RestController
@SuppressWarnings ( { "unchecked", "rawtypes" } )
public class APILogEntryController extends APIController {

    private final LogEntryService leservice;

    private final UserService     userService;

    private final LoggerUtil      loggerUtil;

    public APILogEntryController(LogEntryService leservice, UserService userService, LoggerUtil loggerUtil) {
        this.leservice = leservice;
        this.userService = userService;
        this.loggerUtil = loggerUtil;
    }

    /**
     * Handles GET requests for the current user's log entries when searching by
     * date and using a page system.
     *
     * @param body
     *            the request body of the GET request
     * @return ResponseEntity with an error or list of LogEntries
     */
    @PostMapping ( BASE_PATH + "/logentries/range" )
    public ResponseEntity getEntryByDateRange ( @RequestBody final LogEntryRequestBody body ) {
        // If no dates are specified, get all entries, otherwise use the date
        // range
        List<LogEntry> entries = null;
        try {
            if (body.getStartDate().isEmpty() || body.getEndDate().isEmpty()) {
                throw new ParseException( "Date", 1 );
            }

            // Parse in start/end dates as ZonedDateTimes
            // from ISO date/time or ISO date strings
            ZonedDateTime start;
            try {
                start = ZonedDateTime.parse( body.getStartDate() );
            }
            catch ( final DateTimeParseException ex ) {
                start = LocalDate.parse( body.getStartDate() ).atStartOfDay( ZoneId.systemDefault() );
            }

            ZonedDateTime end;
            try {
                end = ZonedDateTime.parse( body.getEndDate() ).plusDays( 1 );
            }
            catch ( final DateTimeParseException ex ) {
                end = LocalDate.parse( body.getEndDate() ).atStartOfDay( ZoneId.systemDefault() ).plusDays( 1 );
            }

            if ( start.isAfter( end ) ) {
                return new ResponseEntity( errorResponse( "Start Date is after End Date" ), HttpStatus.NOT_ACCEPTABLE );
            }
            entries = leservice.findByDateRange( LoggerUtil.currentUser(), start, end );
        }
        catch ( final ParseException ex ) {
            entries = leservice.findAllForUser( LoggerUtil.currentUser() );
        }

        // If the entries array is null give an error response
        if ( entries == null ) {
            return new ResponseEntity( errorResponse( "Error retrieving Log Entries" ),
                    HttpStatus.INTERNAL_SERVER_ERROR );
        }

        // Use only log entries that are viewable by the user
        List<LogEntry> visible;
        final User user = userService.findByName( LoggerUtil.currentUser() );
        if ( user.getRoles().contains( Role.ROLE_PATIENT ) ) {
            visible = new ArrayList<>();

            for (final LogEntry le : entries) {
                if (le.getLogCode().isPatientViewable()) {
                    visible.add(le);
                }
            }
        }
        else {
            visible = entries;
        }

        final int numPages = 1 + visible.size() / body.getPageLength();

        Collections.reverse( visible );

        // Find only the entries that should show up on the page given the page
        // and page length
        final List<LogEntry> page = new ArrayList<>();
        for ( int i = 0; i < body.getPageLength(); i++ ) {
            final int idx = ( body.getPage() - 1 ) * body.getPageLength() + i;
            if ( idx >= 0 && visible.size() > idx ) {
                page.add( visible.get( idx ) );
            }
        }

        // Turn these log entries into proper table rows for the application to
        // display
        final List<LogEntryTableRow> table = new ArrayList<>();
        for (final LogEntry le : page) {
            final LogEntryTableRow row = new LogEntryTableRow();

            row.setPrimary( le.getPrimaryUser() );
            row.setSecondary( le.getSecondaryUser() );

            // Convert to OffsetDateTime String so that
            // text-based timezone is not included
            row.setDateTime( le.getTime().toOffsetDateTime().toString() );
            row.setTransactionType( le.getLogCode().getDescription() );
            row.setNumPages( numPages );

            if ( user.getRoles().contains( Role.ROLE_PATIENT ) ) {
                row.setPatient( true );

                if ( le.getPrimaryUser().equals( LoggerUtil.currentUser() ) ) {
                    final User secondary = userService.findByName( le.getSecondaryUser() );
                    if ( secondary != null ) {
                        row.setRole( secondary.getRoles().toString() );
                    }
                }
                else {
                    final User primary = userService.findByName( le.getPrimaryUser() );
                    row.setRole( primary.getRoles().toString() );
                }
            }

            table.add( row );
        }

        // Create a log entry as long as the user is on the first page
        if ( body.page == 1 ) {
            loggerUtil.log( TransactionType.VIEW_USER_LOG, LoggerUtil.currentUser() );
        }
        return new ResponseEntity( table, HttpStatus.OK );
    }

}
