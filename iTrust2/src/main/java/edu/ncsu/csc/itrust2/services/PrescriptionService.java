package edu.ncsu.csc.itrust2.services;

import edu.ncsu.csc.itrust2.forms.PrescriptionForm;
import edu.ncsu.csc.itrust2.models.Prescription;
import edu.ncsu.csc.itrust2.models.User;
import edu.ncsu.csc.itrust2.repositories.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class PrescriptionService extends Service {

    private final PrescriptionRepository repository;

    private final DrugService            drugService;

    private final UserService            userService;

    @Override
    protected JpaRepository getRepository () {
        return repository;
    }

    public Prescription build ( final PrescriptionForm form ) {
        final Prescription pr = new Prescription();

        pr.setDrug( drugService.findByCode( form.getDrug() ) );
        pr.setDosage( form.getDosage() );
        pr.setRenewals( form.getRenewals() );
        pr.setPatient( userService.findByName( form.getPatient() ) );

        if ( form.getId() != null ) {
            pr.setId( form.getId() );
        }

        pr.setStartDate( LocalDate.parse( form.getStartDate() ) );
        pr.setEndDate( LocalDate.parse( form.getEndDate() ) );

        return pr;
    }

    public List<Prescription> findByPatient ( final User patient ) {
        return repository.findByPatient( patient );
    }

}
