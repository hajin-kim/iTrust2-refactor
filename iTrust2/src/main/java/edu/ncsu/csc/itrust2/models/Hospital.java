package edu.ncsu.csc.itrust2.models;

import edu.ncsu.csc.itrust2.forms.HospitalForm;
import edu.ncsu.csc.itrust2.models.enums.State;

import java.io.Serial;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 * Class representing a Hospital object, as stored in the DB
 *
 * @author Kai Presler-Marshall
 */
@NoArgsConstructor
@Getter
@Entity
public class Hospital extends DomainObject implements Serializable {
    /** Name of the Hospital */
    @NotEmpty
    @Length(max = 100) @Id
    private String name;

    /** Address of the Hospital */
    @Setter
    @NotEmpty
    @Length(max = 100) private String address;

    /** ZIP code of the Hospital */
    @Setter
    @NotEmpty
    @Length(min = 5, max = 10) private String zip;

    /** State of the hospital */
    @Setter
    @NotNull @Enumerated(EnumType.STRING)
    private State state;

    /** Used for serializing the object. */
    @Serial private static final long serialVersionUID = 1L;

    /**
     * Construct a Hospital object from all of its individual fields.
     *
     * @param name Name of the Hospital
     * @param address Address of the Hospital
     * @param zip ZIP of the Hospital
     * @param state State of the Hospital
     */
    public Hospital(final String name, final String address, final String zip, final String state) {
        this.name = name;
        setAddress(address);
        setZip(zip);
        setState(State.parse(state));
    }

    /**
     * Construct a Hospital object from the HospitalForm object provided
     *
     * @param hf A HospitalForm to convert to a Hospital
     */
    public Hospital(final HospitalForm hf) {
        name = hf.getName();
        setAddress(hf.getAddress());
        setZip(hf.getZip());
        setState(State.parse(hf.getState()));
    }

    public Hospital(String name, String address, String zip, State state) {
        this.name = name;
        this.address = address;
        this.zip = zip;
        this.state = state;
    }

    /**
     * Update this Hospital object from the HospitalForm object provided
     *
     * @param hf A HospitalForm to convert to a Hospital
     * @return `this` Hospital object, after updates
     */
    public Hospital update(final HospitalForm hf) {
        name = hf.getName();
        setAddress(hf.getAddress());
        setZip(hf.getZip());
        setState(State.parse(hf.getState()));
        return this;
    }

    @Override
    public String toString() {
        return this.name + "  " + this.address;
    }

    @Override
    public Serializable getId() {
        return getName();
    }
}
