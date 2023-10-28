package edu.ncsu.csc.itrust2.models;

import edu.ncsu.csc.itrust2.forms.PersonnelForm;
import edu.ncsu.csc.itrust2.forms.UserForm;
import edu.ncsu.csc.itrust2.models.enums.Role;
import edu.ncsu.csc.itrust2.models.enums.State;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@Getter
@Entity
public class Personnel extends User {

    public Personnel(final UserForm uf) {
        super(uf);
        if (getRoles().contains(Role.ROLE_PATIENT)) {
            throw new IllegalArgumentException(
                    "Attempted to create a Personnel record for a non-Patient user!");
        }
    }

    /** The first name of the personnel */
    @Setter
    @Length(max = 20) private String firstName;

    /** The last name of the personnel */
    @Setter
    @Length(max = 30) private String lastName;

    /** The address line 1 of the personnel */
    @Setter
    @Length(max = 50) private String address1;

    /** The address line 2 of the personnel */
    @Setter
    @Length(max = 50) private String address2;

    /** The city of residence of the personnel */
    @Setter
    @Length(max = 15) private String city;

    /** The state of residence of the personnel */
    @Setter
    @Enumerated(EnumType.STRING)
    private State state;

    /** The zipcode of the personnel */
    @Setter
    @Length(min = 5, max = 10) private String zip;

    /** The phone number of the personnel */
    @Setter
    @Length(min = 12, max = 12) private String phone;

    /** The email of the personnel */
    @Setter
    @Length(max = 30) private String email;

    /** The id of the hospital the personnel works at */
    @Setter private String hospitalId;

    /**
     * Create a new personnel based off of the PersonnelForm
     *
     * @param form the filled-in personnel form with personnel information
     * @return `this` Personnel, after updating from form
     */
    public Personnel update(final PersonnelForm form) {

        setFirstName(form.getFirstName());
        setLastName(form.getLastName());
        setAddress1(form.getAddress1());
        setAddress2(form.getAddress2());
        setCity(form.getCity());
        setState(State.valueOf(form.getState()));
        setZip(form.getZip());
        setPhone(form.getPhone());
        setEmail(form.getEmail());

        return this;
    }

    /**
     * To string method
     *
     * @return string rep. of Personnel.
     */
    @Override
    public String toString() {
        return this.firstName + " " + this.lastName + " " + this.email;
    }
}
