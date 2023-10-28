package edu.ncsu.csc.itrust2.models;

import edu.ncsu.csc.itrust2.forms.DrugForm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 * Represents a drug in the NDC format.
 *
 * @author Connor
 * @author Kai Presler-Marshall
 */
@NoArgsConstructor
@Getter
@Entity
@Table(name = "drug")
public class Drug extends DomainObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    @NotNull @Pattern(regexp = "^\\d{4}-\\d{4}-\\d{2}$")
    private String code;

    @Setter
    @NotEmpty
    @Length(max = 64) private String name;

    @Setter
    @NotNull @Length(max = 1024) private String description;

    public Drug(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    /**
     * Constructs a new form from the details in the given form
     *
     * @param form the form to base the new drug on
     */
    public Drug(final DrugForm form) {
        id = form.getId();
        setCode(form.getCode());
        setName(form.getName());
        setDescription(form.getDescription());
    }
}
