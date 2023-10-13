package edu.ncsu.csc.itrust2.forms;

import edu.ncsu.csc.itrust2.models.Drug;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A form for REST API communication. Contains fields for constructing Drug objects.
 *
 * @author Connor
 */
@Getter
@Setter
@NoArgsConstructor
public class DrugForm {

    private Long id;
    private String name;
    private String code;
    private String description;

    /**
     * Constructs a new form with information from the given drug.
     *
     * @param drug the drug object
     */
    public DrugForm(@NotNull final Drug drug) {
        setId(drug.getId());
        setName(drug.getName());
        setCode(drug.getCode());
        setDescription(drug.getDescription());
    }
}
