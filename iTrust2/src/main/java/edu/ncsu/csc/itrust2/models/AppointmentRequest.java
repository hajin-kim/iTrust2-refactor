package edu.ncsu.csc.itrust2.models;

import edu.ncsu.csc.itrust2.adapters.ZonedDateTimeAdapter;
import edu.ncsu.csc.itrust2.adapters.ZonedDateTimeAttributeConverter;
import edu.ncsu.csc.itrust2.models.enums.AppointmentType;
import edu.ncsu.csc.itrust2.models.enums.Status;

import java.time.ZonedDateTime;
import javax.persistence.Basic;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.google.gson.annotations.JsonAdapter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Backing object for the Appointment Request system. This is the object that is actually stored in
 * the database and reflects the persistent information we have on the appointment request.
 *
 * @author Kai Presler-Marshall
 */
@NoArgsConstructor
@Getter
@Entity
public class AppointmentRequest extends DomainObject {
    /** ID of the AppointmentRequest */
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** The Patient who is associated with this AppointmentRequest */
    @Setter
    @NotNull @ManyToOne
    @JoinColumn(name = "patient_id", columnDefinition = "varchar(100)")
    private User patient;

    /** The HCP who is associated with this AppointmentRequest */
    @Setter
    @NotNull @ManyToOne
    @JoinColumn(name = "hcp_id", columnDefinition = "varchar(100)")
    private User hcp;

    /** When this AppointmentRequest has been scheduled to take place */
    @Setter
    @NotNull @Basic
    // Allows the field to show up nicely in the database
    @Convert(converter = ZonedDateTimeAttributeConverter.class)
    @JsonAdapter(ZonedDateTimeAdapter.class)
    private ZonedDateTime date;

    /**
     * Store the Enum in the DB as a string as it then makes the DB info more legible if it needs to
     * be read manually.
     */
    @Setter
    @NotNull @Enumerated(EnumType.STRING)
    private AppointmentType type;

    /** Any (optional) comments on the AppointmentRequest */
    @Setter private String comments;

    /** The Status of the AppointmentRequest */
    @Setter
    @NotNull @Enumerated(EnumType.STRING)
    private Status status;
}
