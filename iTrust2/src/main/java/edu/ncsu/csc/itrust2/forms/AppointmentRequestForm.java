package edu.ncsu.csc.itrust2.forms;

import edu.ncsu.csc.itrust2.models.AppointmentRequest;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This is the in-memory object that is used for requesting an appointment. It is validated and
 * converted into an AppointmentRequest object for persistence.
 *
 * @author Kai Presler-Marshall
 */
@Setter
@NoArgsConstructor
@Getter
public class AppointmentRequestForm {

    /** The status of the appt request * */
    private String status;

    /** The patient of the appt request */
    private String patient;

    /** The hcp of the appt request */
    @NotNull(message = "Invalid HCP") private String hcp;

    /** The date of the appt request */
    @NotEmpty(message = "Date cannot be empty")
    private String date;

    /** The id of the appt request */
    private String id;

    /** The type of the appt request */
    private String type;

    /** The comments of the appt request */
    private String comments;

    /**
     * Populate the appt request form from the Appointment request object
     *
     * @param request the appointment request to populate the form from
     */
    public AppointmentRequestForm(@NotNull final AppointmentRequest request) {
        setPatient(request.getPatient().getUsername());
        setHcp(request.getHcp().getUsername());
        setDate(request.getDate().toString());
        setType(request.getType().toString());
        setComments(request.getComments());

        if (request.getId() != null) {
            setId(request.getId().toString());
        }

        setStatus(request.getStatus().toString());
    }
}
