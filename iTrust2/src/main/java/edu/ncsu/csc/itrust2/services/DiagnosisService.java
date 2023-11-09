package edu.ncsu.csc.itrust2.services;

import edu.ncsu.csc.itrust2.forms.DiagnosisForm;
import edu.ncsu.csc.itrust2.models.Diagnosis;
import edu.ncsu.csc.itrust2.models.OfficeVisit;
import edu.ncsu.csc.itrust2.models.User;
import edu.ncsu.csc.itrust2.repositories.DiagnosisRepository;
import edu.ncsu.csc.itrust2.repositories.OfficeVisitRepository;

import java.util.Collection;
import java.util.List;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
@Transactional
@RequiredArgsConstructor
public class DiagnosisService extends Service {

    private final DiagnosisRepository repository;

    private final ICDCodeService icdCodeService;

    private final OfficeVisitRepository officeVisitRepository;

    @Override
    protected JpaRepository getRepository() {
        return repository;
    }

    public Diagnosis build(final DiagnosisForm form) {
        var id = form.getId();
        if (null == id) {
            return null;
        }
        final Diagnosis diag = new Diagnosis();
        diag.setVisit(officeVisitRepository.findById(id).orElse(null));
        diag.setNote(form.getNote());
        diag.setCode(icdCodeService.findByCode(form.getCode()));
        diag.setId(form.getId());

        return diag;
    }

    public List<Diagnosis> findByPatient(final User patient) {
        return officeVisitRepository.findByPatient(patient).stream()
                .map(this::findByVisit)
                .flatMap(Collection::stream)
                .toList();
    }

    public List<Diagnosis> findByVisit(final OfficeVisit visit) {
        return repository.findByVisit(visit);
    }
}
