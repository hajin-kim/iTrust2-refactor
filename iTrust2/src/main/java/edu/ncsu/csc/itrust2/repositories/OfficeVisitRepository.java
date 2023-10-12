package edu.ncsu.csc.itrust2.repositories;

import edu.ncsu.csc.itrust2.models.OfficeVisit;
import edu.ncsu.csc.itrust2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface OfficeVisitRepository extends JpaRepository<OfficeVisit, Long> {

    List<OfficeVisit> findByHcp(@NotNull User hcp);

    List<OfficeVisit> findByPatient(@NotNull User patient);

    List<OfficeVisit> findByHcpAndPatient(@NotNull User hcp, @NotNull User patient);

}
