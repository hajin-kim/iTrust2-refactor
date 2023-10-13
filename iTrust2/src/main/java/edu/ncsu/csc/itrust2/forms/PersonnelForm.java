package edu.ncsu.csc.itrust2.forms;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

/**
 * Form for registering a user as an iTrust2 personnel or for editing their
 * existing information. Used for all non-patient types of users
 *
 * @author Kai Presler-Marshall
 *
 */
@Setter
@Getter
@NoArgsConstructor
public class PersonnelForm {

    /**
     * Username of the iTrust2 personnel to make a Personnel object for
     */
    private String username;

    /**
     * First name of the Personnel
     */
    @Length ( max = 20 )
    private String firstName;

    /**
     * Last name of the Personnel
     */
    @Length ( max = 30 )
    private String lastName;

    /**
     * Address1 of the Personnel
     */
    @Length ( max = 50 )
    private String address1;

    /**
     * Address2 of the Personnel
     */
    @Length ( max = 50 )
    private String address2;

    /**
     * City of the Personnel
     */
    @Length ( max = 15 )
    private String city;

    /**
     * State of the Personnel
     */
    @Length ( min = 2, max = 2 )
    private String state;

    /**
     * Zip of the Personnel
     */
    @Length ( min = 5, max = 10 )
    private String zip;

    /**
     * Phone of the Personnel
     */
    @Pattern ( regexp = "(^[0-9]{3}-[0-9]{3}-[0-9]{4}$)", message = "Phone number must be formatted as xxx-xxx-xxxx" )
    private String phone;

    /**
     * Email of the Personnel
     */
    @Length ( max = 30 )
    private String email;

}
