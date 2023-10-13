package edu.ncsu.csc.itrust2.forms;

import edu.ncsu.csc.itrust2.models.Prescription;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * A form for REST API communication. Contains fields for constructing
 * Prescription objects.
 *
 * @author Connor
 */
@Setter
@Getter
@NoArgsConstructor
public class PrescriptionForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long              id;
    private String            drug;
    private int               dosage;
    private String            startDate;
    private String            endDate;
    private int               renewals;
    private String            patient;

    /**
     * Constructs a new form with information from the given prescription.
     *
     * @param prescription
     *            the prescription object
     */
    public PrescriptionForm ( final Prescription prescription ) {
        setId( prescription.getId() );
        setDrug( prescription.getDrug().getCode() );
        setDosage( prescription.getDosage() );
        setStartDate( prescription.getStartDate().toString() );
        setEndDate( prescription.getEndDate().toString() );
        setRenewals( prescription.getRenewals() );
        setPatient( prescription.getPatient().getUsername() );
    }

}
