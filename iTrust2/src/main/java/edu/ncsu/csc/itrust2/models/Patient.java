package edu.ncsu.csc.itrust2.models;

import edu.ncsu.csc.itrust2.adapters.LocalDateAdapter;
import edu.ncsu.csc.itrust2.forms.PatientForm;
import edu.ncsu.csc.itrust2.forms.UserForm;
import edu.ncsu.csc.itrust2.models.enums.BloodType;
import edu.ncsu.csc.itrust2.models.enums.Ethnicity;
import edu.ncsu.csc.itrust2.models.enums.Gender;
import edu.ncsu.csc.itrust2.models.enums.Role;
import edu.ncsu.csc.itrust2.models.enums.State;

import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.google.gson.annotations.JsonAdapter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateConverter;

@NoArgsConstructor
@Getter
@Entity
public class Patient extends User {

    /** The first name of this patient */
    @Setter
    @Length(min = 1) private String firstName;

    /** The preferred name of this patient */
    @Setter
    @Length(max = 20) private String preferredName;

    /** The last name of this patient */
    @Setter
    @Length(min = 1) private String lastName;

    /** The email address of this patient */
    @Setter
    @Length(max = 30) private String email;

    /** The address line 1 of this patient */
    @Setter
    @Length(max = 50) private String address1;

    /** The address line 2 of this patient */
    @Setter
    @Length(max = 50) private String address2;

    /** The city of residence of this patient */
    @Setter
    @Length(max = 15) private String city;

    /** The state of residence of this patient */
    @Setter
    @Enumerated(EnumType.STRING)
    private State state;

    /** The zip code of this patient */
    @Setter
    @Length(min = 5, max = 10) private String zip;

    /** The phone number of this patient */
    @Setter
    @Length(min = 12, max = 12) private String phone;

    /** The birthday of this patient */
    @Setter
    @Basic
    // Allows the field to show up nicely in the database
    @Convert(converter = LocalDateConverter.class)
    @JsonAdapter(LocalDateAdapter.class)
    private LocalDate dateOfBirth;

    /** The date of death of this patient */
    @Setter
    @Basic
    // Allows the field to show up nicely in the database
    @Convert(converter = LocalDateConverter.class)
    @JsonAdapter(LocalDateAdapter.class)
    private LocalDate dateOfDeath;

    /** The cause of death of this patient */
    @Setter private String causeOfDeath;

    /** The blood type of this patient */
    @Setter
    @Enumerated(EnumType.STRING)
    private BloodType bloodType;

    /** The ethnicity of this patient */
    @Setter
    @Enumerated(EnumType.STRING)
    private Ethnicity ethnicity;

    /** The gender of this patient */
    @Setter
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public Patient(final UserForm uf) {
        super(uf);
        if (!getRoles().contains(Role.ROLE_PATIENT)) {
            throw new IllegalArgumentException(
                    "Attempted to create a Patient record for a non-Patient user!");
        }
    }

    public Patient update(final PatientForm form) {
        setFirstName(form.getFirstName());
        setPreferredName(form.getPreferredName());
        setLastName(form.getLastName());
        setEmail(form.getEmail());
        setAddress1(form.getAddress1());
        setAddress2(form.getAddress2());
        setCity(form.getCity());
        setState(State.parse(form.getState()));
        setZip(form.getZip());
        setPhone(form.getPhone());

        setDateOfBirth(LocalDate.parse(form.getDateOfBirth()));

        // Patient can't set their date of death
        if (form.getDateOfDeath() != null) {
            setDateOfDeath(LocalDate.parse(form.getDateOfDeath()));
            setCauseOfDeath(form.getCauseOfDeath());
        }

        setBloodType(BloodType.parse(form.getBloodType()));

        setEthnicity(Ethnicity.parse(form.getEthnicity()));

        setGender(Gender.parse(form.getGender()));

        return this;
    }
}
