package edu.ncsu.csc.itrust2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ncsu.csc.itrust2.models.Diagnosis;
import edu.ncsu.csc.itrust2.models.OfficeVisit;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {

    public List<Diagnosis> findByVisit ( OfficeVisit visit );

}
