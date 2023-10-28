package edu.ncsu.csc.itrust2.models;

import edu.ncsu.csc.itrust2.adapters.LocalDateAdapter;

import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.google.gson.annotations.JsonAdapter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateConverter;

/**
 * Represents a prescription in the system. Each prescription is associated with a patient and a
 * drug.
 *
 * @author Connor
 * @author Kai Presler-Marshall
 */
@NoArgsConstructor
@Getter
@Entity
public class Prescription extends DomainObject {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter @NotNull @ManyToOne private Drug drug;

    @Setter
    @Min(1)
    private int dosage;

    @Setter
    @NotNull @Basic
    // Allows the field to show up nicely in the database
    @Convert(converter = LocalDateConverter.class)
    @JsonAdapter(LocalDateAdapter.class)
    private LocalDate startDate;

    @Setter
    @NotNull @Basic
    // Allows the field to show up nicely in the database
    @Convert(converter = LocalDateConverter.class)
    @JsonAdapter(LocalDateAdapter.class)
    private LocalDate endDate;

    @Setter
    @Min(0)
    private int renewals;

    @Setter
    @NotNull @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", columnDefinition = "varchar(100)")
    private User patient;
}
