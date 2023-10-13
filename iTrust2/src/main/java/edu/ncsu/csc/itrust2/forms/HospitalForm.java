package edu.ncsu.csc.itrust2.forms;

import edu.ncsu.csc.itrust2.models.Hospital;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;

/**
 * Form used for creating a new Hospital. Will be parsed into an actual Hospital
 * object to be saved.
 *
 * @author Kai Presler-Marshall
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class HospitalForm {

    /**
     * Name of the Hospital
     */
    @NotEmpty
    @Length ( max = 255 )
    private String name;

    /**
     * Address of the Hospital
     */
    @NotEmpty
    @Length ( max = 255 )
    private String address;

    /**
     * ZIP Code of the Hospital
     */
    @NotEmpty
    @Length ( min = 5, max = 10 )
    private String zip;

    /**
     * State of the Hospital
     */
    @NotEmpty
    @Length ( max = 255 )
    private String state;

    /**
     * Creates a HospitalForm from the Hospital provided. Used to convert a
     * Hospital to a form that can be edited.
     *
     * @param h
     *            Hospital to convert to its Form.
     */
    public HospitalForm (@NotNull final Hospital h ) {
        setName( h.getName() );
        setAddress( h.getAddress() );
        setZip( h.getZip() );
        setState( h.getState().getName() );
    }

}
