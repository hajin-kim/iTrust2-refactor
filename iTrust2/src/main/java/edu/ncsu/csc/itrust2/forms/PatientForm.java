package edu.ncsu.csc.itrust2.forms;

import edu.ncsu.csc.itrust2.models.Patient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Form for user to fill out to add a Patient to the system.
 *
 * @author Kai Presler-Marshall
 *
 */
@Setter
@Getter
@NoArgsConstructor
public class PatientForm {
    /**
     * Populate the patient form from a patient object
     *
     * @param patient
     *            the patient object to set the form with
     */
    public PatientForm ( final Patient patient ) {
        if ( null == patient ) {
            return; /* Nothing to do here */
        }
        setFirstName( patient.getFirstName() );
        setLastName( patient.getLastName() );
        setPreferredName( patient.getPreferredName() );
        setEmail( patient.getEmail() );
        setAddress1( patient.getAddress1() );
        setAddress2( patient.getAddress2() );
        setCity( patient.getCity() );
        if ( null != patient.getState() ) {
            setState( patient.getState().toString() );
        }
        setZip( patient.getZip() );
        setPhone( patient.getPhone() );

        if ( null != patient.getDateOfBirth() ) {
            setDateOfBirth( patient.getDateOfBirth().toString() );
        }
        if ( null != patient.getDateOfDeath() ) {
            setDateOfDeath( patient.getDateOfDeath().toString() );
        }

        setCauseOfDeath( patient.getCauseOfDeath() );

        if ( null != patient.getBloodType() ) {
            setBloodType( patient.getBloodType().toString() );
        }

        if ( null != patient.getEthnicity() ) {
            setEthnicity( patient.getEthnicity().toString() );
        }

        if ( null != patient.getGender() ) {
            setGender( patient.getGender().toString() );
        }

    }

    @Length ( max = 20 )
    private String  username;

    /** The first name of the patient **/
    @Length ( max = 20 )
    private String  firstName;

    /** The preferred name of the patient **/
    @Length ( max = 20 )
    private String  preferredName;

    /** The last name of the patient **/
    @Length ( max = 30 )
    private String  lastName;

    /** The email of the patient **/
    @Length ( max = 30 )
    private String  email;

    /** The address line 1 of the patient **/
    @Length ( max = 50 )
    private String  address1;

    /** The address line 2 of the patient **/
    @Length ( max = 50 )
    private String  address2;

    /** The city of residence of the patient **/
    @Length ( max = 15 )
    private String  city;

    /** The state of residence of the patient **/
    @Length ( min = 2, max = 2 )
    private String  state;

    /** The zipcode of the patient **/
    @Length ( min = 5, max = 10 )
    private String  zip;

    /** The phone number of the patient **/
    @Pattern ( regexp = "(^[0-9]{3}-[0-9]{3}-[0-9]{4}$)", message = "Phone number must be formatted as xxx-xxx-xxxx" )
    private String  phone;

    /** The date of birth of the patient **/
    @Length ( min = 10, max = 10 )
    private String  dateOfBirth;

    /** The date of death of the patient **/
    private String  dateOfDeath;

    /** The cause of death of the patient **/
    @Length ( max = 50 )
    private String  causeOfDeath;

    /** The blood type of the patient **/
    @NotEmpty
    private String  bloodType;

    /** The ethnicity of the patient **/
    @NotEmpty
    private String  ethnicity;

    /** The gender of the patient **/
    @NotEmpty
    private String  gender;

    /**
     * Whether the patient is diabetic/pre-diabetic or not
     */
    @NotNull
    private boolean isDiabetic;

    /**
     * Blood sugar limit for fasting
     */
    @Min ( 80 )
    @Max ( 130 )
    private int     fastingLimit;

    /**
     * Blood sugar limit for meals
     */
    @Min ( 120 )
    @Max ( 180 )
    private int     mealLimit;

}
