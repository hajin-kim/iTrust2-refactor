package edu.ncsu.csc.itrust2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ncsu.csc.itrust2.models.OfficeVisit;
import edu.ncsu.csc.itrust2.models.User;

import javax.validation.constraints.NotNull;

public interface OfficeVisitRepository extends JpaRepository<OfficeVisit, Long> {

    List<OfficeVisit> findByHcp(@NotNull User hcp);

    List<OfficeVisit> findByPatient(@NotNull User patient);

    List<OfficeVisit> findByHcpAndPatient(@NotNull User hcp, @NotNull User patient);

}
