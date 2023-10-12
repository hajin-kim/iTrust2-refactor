package edu.ncsu.csc.itrust2.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import edu.ncsu.csc.itrust2.repositories.OfficeVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import edu.ncsu.csc.itrust2.forms.DiagnosisForm;
import edu.ncsu.csc.itrust2.models.Diagnosis;
import edu.ncsu.csc.itrust2.models.OfficeVisit;
import edu.ncsu.csc.itrust2.models.User;
import edu.ncsu.csc.itrust2.repositories.DiagnosisRepository;

@Component
@Transactional
public class DiagnosisService extends Service {

    private final DiagnosisRepository repository;

    private final ICDCodeService      icdCodeService;

    private final OfficeVisitRepository  officeVisitRepository;

    public DiagnosisService(DiagnosisRepository repository,
                            OfficeVisitRepository officeVisitRepository,
                            ICDCodeService icdCodeService) {
        this.repository = repository;
        this.officeVisitRepository = officeVisitRepository;
        this.icdCodeService = icdCodeService;
    }

    @Override
    protected JpaRepository getRepository () {
        return repository;
    }

    public Diagnosis build ( final DiagnosisForm form ) {
        var id = form.getId();
        if ( null == id ) {
            return null;
        }
        final Diagnosis diag = new Diagnosis();
        diag.setVisit(officeVisitRepository.findById( id ).orElse(null));
        diag.setNote( form.getNote() );
        diag.setCode( icdCodeService.findByCode( form.getCode() ) );
        diag.setId( form.getId() );

        return diag;
    }

    public List<Diagnosis> findByPatient ( final User patient ) {
        return officeVisitRepository.findByPatient( patient ).stream().map( e -> findByVisit( e ) ).flatMap( e -> e.stream() )
                .collect( Collectors.toList() );

    }

    public List<Diagnosis> findByVisit ( final OfficeVisit visit ) {
        return repository.findByVisit( visit );
    }

}
