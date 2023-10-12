package edu.ncsu.csc.itrust2.repositories;

import edu.ncsu.csc.itrust2.models.AppointmentRequest;
import edu.ncsu.csc.itrust2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface AppointmentRequestRepository extends JpaRepository<AppointmentRequest, Long> {

    List<AppointmentRequest> findByPatient(@NotNull User patient);

    List<AppointmentRequest> findByHcp(@NotNull User hcp);

    List<AppointmentRequest> findByHcpAndPatient(@NotNull User hcp, @NotNull User patient);

}
