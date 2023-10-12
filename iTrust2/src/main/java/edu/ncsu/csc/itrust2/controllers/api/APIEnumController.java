package edu.ncsu.csc.itrust2.controllers.api;

import edu.ncsu.csc.itrust2.models.User;
import edu.ncsu.csc.itrust2.models.enums.AppointmentType;
import edu.ncsu.csc.itrust2.models.enums.BloodType;
import edu.ncsu.csc.itrust2.models.enums.Ethnicity;
import edu.ncsu.csc.itrust2.models.enums.Gender;
import edu.ncsu.csc.itrust2.models.enums.HouseholdSmokingStatus;
import edu.ncsu.csc.itrust2.models.enums.PatientSmokingStatus;
import edu.ncsu.csc.itrust2.models.enums.Role;
import edu.ncsu.csc.itrust2.models.enums.State;
import edu.ncsu.csc.itrust2.models.enums.Status;
import edu.ncsu.csc.itrust2.services.UserService;
import edu.ncsu.csc.itrust2.utils.LoggerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class provides GET endpoints for all of the Enums, so that they can be
 * used for creating proper DomainObjects
 *
 * @author Kai Presler-Marshall
 */
@RestController
@RequiredArgsConstructor
public class APIEnumController extends APIController {

    private final LoggerUtil  loggerUtil;

    private final UserService userService;

    /**
     * Get the blood types
     *
     * @return blood types
     */
    @GetMapping ( "/bloodtype" )
    public List<Map<String, Object>> getBloodTypes () {
        return Arrays.stream( BloodType.values() ).map(BloodType::getInfo).collect( Collectors.toList() );
    }

    /**
     * Get ethnicity
     *
     * @return ethnicity
     */
    @GetMapping ( "/ethnicity" )
    public List<Map<String, Object>> getEthnicity () {
        return Arrays.stream( Ethnicity.values() ).map(Ethnicity::getInfo).collect( Collectors.toList() );
    }

    /**
     * Get genders
     *
     * @return genders
     */
    @GetMapping ( "/gender" )
    public List<Map<String, Object>> getGenders () {
        return Arrays.stream( Gender.values() ).map(Gender::getInfo).collect( Collectors.toList() );
    }

    /**
     * Get states
     *
     * @return states
     */
    @GetMapping ( "/state" )
    public List<Map<String, Object>> getStates () {
        return Arrays.stream( State.values() ).map(State::getInfo).collect( Collectors.toList() );
    }

    /**
     * Gets appointment types
     *
     * @return appointment types
     */
    @GetMapping ( "/appointmenttype" )
    public List<AppointmentType> getAppointmentTypes () {
        final User user = userService.findByName( LoggerUtil.currentUser() );
        final Collection<Role> role = user.getRoles();
        if ( role.contains( Role.ROLE_OD ) ) {
            return List.of( AppointmentType.GENERAL_CHECKUP, AppointmentType.GENERAL_OPHTHALMOLOGY );
        }

        if ( role.contains( Role.ROLE_OPH ) ) {
            return List.of( AppointmentType.GENERAL_CHECKUP, AppointmentType.GENERAL_OPHTHALMOLOGY,
                    AppointmentType.OPHTHALMOLOGY_SURGERY );
        }

        if ( role.contains( Role.ROLE_HCP ) ) {
            return List.of( AppointmentType.GENERAL_CHECKUP );
        }

        return Arrays.asList( AppointmentType.values() );
    }

    /**
     * Gets appointment statuses
     *
     * @return appointment statuses
     */
    @GetMapping ( "/appointmentstatus" )
    public List<Status> getAppointmentStatuses () {
        return Arrays.asList( Status.values() );
    }

    /**
     * Get house smoking statuses
     *
     * @return house smoking statuses
     */
    @GetMapping ( "/housesmoking" )
    public List<HouseholdSmokingStatus> getHouseSmokingStatuses () {
        return Arrays.asList( HouseholdSmokingStatus.values() ).subList( 1,
                HouseholdSmokingStatus.values().length );
    }

    /**
     * Get patient smoking statuses
     *
     * @return patient smoking statuses
     */
    @GetMapping ( "/patientsmoking" )
    public List<PatientSmokingStatus> getPatientSmokingStatuses () {
        return Arrays.asList( PatientSmokingStatus.values() ).subList( 1,
                PatientSmokingStatus.values().length );
    }

}
