package edu.ncsu.csc.itrust2.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class to represent a Diagnosis made by an HCP as part of an Office Visit
 *
 * @author Thomas
 * @author Kai Presler-Marshall
 */
@NoArgsConstructor
@Getter
@Entity
@Table(name = "diagnosis")
public class Diagnosis extends DomainObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NotNull @ManyToOne
    @JoinColumn(name = "visit_id", nullable = false)
    @JsonBackReference
    private OfficeVisit visit;

    @Setter private String note;

    @Setter
    @NotNull @ManyToOne
    @JoinColumn(name = "code_id")
    private ICDCode code;

    @Override
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the Diagnosis
     *
     * @param id The new ID of the Diagnosis. For Hibernate.
     */
    public void setId(final Long id) {
        this.id = id;
    }
}
