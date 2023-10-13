package edu.ncsu.csc.itrust2.forms;

import edu.ncsu.csc.itrust2.models.OfficeVisit;
import edu.ncsu.csc.itrust2.models.enums.HouseholdSmokingStatus;
import edu.ncsu.csc.itrust2.models.enums.PatientSmokingStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Office Visit form used to document an Office Visit by the HCP. This will be
 * validated and converted to a OfficeVisit to be stored in the database.
 *
 * @author Kai Presler-Marshall
 * @author Elizabeth Gilbert
 *
 */
@Setter
@Getter
@NoArgsConstructor
public class OfficeVisitForm implements Serializable {
    /**
     * Serial Version of the Form. For the Serializable
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Name of the Patient involved in the OfficeVisit
     */
    @NotEmpty
    private String                 patient;

    /**
     * Name of the HCP involved in the OfficeVisit
     */
    @NotEmpty
    private String                 hcp;

    /**
     * Date at which the OfficeVisit occurred
     */
    @NotEmpty
    private String                 date;

    /**
     * ID of the OfficeVisit
     */
    private String                 id;

    /**
     * Type of the OfficeVisit.
     */
    @NotEmpty
    private String                 type;

    /**
     * Hospital where the OfficeVisit occurred
     */
    @NotEmpty
    private String                 hospital;

    /**
     * Doctor's Notes on the OfficeVisit
     */
    @Length ( max = 255 )
    private String                 notes;

    /**
     * Whether the OfficeVisit was prescheduled or not
     */
    public String                  preScheduled;

    /**
     * Height or length of the person. Up to a 3-digit number and potentially
     * one digit of decimal precision. > 0
     */
    private Float                  height;

    /**
     * Weight of the person. Up to a 3-digit number and potentially one digit of
     * decimal precision. > 0
     */
    private Float                  weight;

    /**
     * Head circumference of the person. Up to a 3-digit number and potentially
     * one digit of decimal precision. > 0
     */
    private Float                  headCircumference;

    /**
     * Systolic blood pressure. 3-digit positive number.
     */
    private Integer                systolic;

    /**
     * Diastolic blood pressure. 3-digit positive number.
     */
    private Integer                diastolic;

    /**
     * HDL cholesterol. Between 0 and 90 inclusive.
     */
    private Integer                hdl;

    /**
     * LDL cholesterol. Between 0 and 600 inclusive.
     */
    private Integer                ldl;

    /**
     * Triglycerides cholesterol. Between 100 and 600 inclusive.
     */
    private Integer                tri;

    /**
     * Smoking status of the patient's household.
     */
    private HouseholdSmokingStatus houseSmokingStatus;

    /**
     * Smoking status of the patient.
     */
    private PatientSmokingStatus   patientSmokingStatus;

    /**
     * Diagnoses associated with this visit
     */
    private List<DiagnosisForm>    diagnoses;

    private List<PrescriptionForm> prescriptions;

    /**
     * Creates an OfficeVisitForm from the OfficeVisit provided
     *
     * @param ov
     *            OfficeVisit to turn into an OfficeVisitForm
     */
    public OfficeVisitForm (@NotNull final OfficeVisit ov ) {
        setPatient( ov.getPatient().getUsername() );
        setHcp( ov.getHcp().getUsername() );
        setDate( ov.getDate().toString() );
        setNotes( ov.getNotes() );
        setId( ov.getId().toString() );
        setPreScheduled( ( (Boolean) ( ov.getAppointment() != null ) ).toString() );
        setDiagnoses( new ArrayList<>() );
        setPrescriptions( ov.getPrescriptions().stream().map( PrescriptionForm::new ).collect( Collectors.toList() ) );
    }

}
