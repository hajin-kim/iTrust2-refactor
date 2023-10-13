package edu.ncsu.csc.itrust2.forms;

import edu.ncsu.csc.itrust2.models.ICDCode;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Intermediate form for adding or editing ICDCodes. Used to create and serialize ICDCodes.
 *
 * @author Thomas
 * @author Kai Presler-Marshall
 */
@Data
@NoArgsConstructor
public class ICDCodeForm {

    /** The code of the Diagnosis */
    private String code;

    /** The description of the diagnosis */
    private String description;

    private Long id;

    /**
     * Construct a form off an existing ICDCode object
     *
     * @param code The object to fill this form with
     */
    public ICDCodeForm(@NotNull final ICDCode code) {
        setCode(code.getCode());
        setDescription(code.getDescription());
        setId(code.getId());
    }
}
