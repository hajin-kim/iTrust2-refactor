package edu.ncsu.csc.itrust2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ncsu.csc.itrust2.models.OfficeVisit;
import edu.ncsu.csc.itrust2.models.User;

public interface OfficeVisitRepository extends JpaRepository<OfficeVisit, Long> {

    public List<OfficeVisit> findByHcp ( User hcp );

    public List<OfficeVisit> findByPatient ( User patient );

    public List<OfficeVisit> findByHcpAndPatient ( User hcp, User patient );

}
