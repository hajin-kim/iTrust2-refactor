package edu.ncsu.csc.itrust2.services;

import edu.ncsu.csc.itrust2.forms.OfficeVisitForm;
import edu.ncsu.csc.itrust2.models.BasicHealthMetrics;
import edu.ncsu.csc.itrust2.repositories.BasicHealthMetricsRepository;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
@Transactional
@RequiredArgsConstructor
public class BasicHealthMetricsService extends Service {
    private final BasicHealthMetricsRepository basicHealthMetricsRepository;

    private final UserService userService;

    @Override
    protected JpaRepository getRepository() {
        return basicHealthMetricsRepository;
    }

    public BasicHealthMetrics build(final OfficeVisitForm ovf) {
        final BasicHealthMetrics bhm = new BasicHealthMetrics();
        bhm.setPatient(userService.findByName(ovf.getPatient()));
        bhm.setHcp(userService.findByName(ovf.getHcp()));

        bhm.setDiastolic(ovf.getDiastolic());
        bhm.setHdl(ovf.getHdl());
        bhm.setHeight(ovf.getHeight());
        bhm.setHouseSmokingStatus(ovf.getHouseSmokingStatus());
        bhm.setHeadCircumference(ovf.getHeadCircumference());
        bhm.setLdl(ovf.getLdl());
        bhm.setPatientSmokingStatus(ovf.getPatientSmokingStatus());
        bhm.setSystolic(ovf.getSystolic());
        bhm.setTri(ovf.getTri());
        bhm.setWeight(ovf.getWeight());

        return bhm;
    }
}
