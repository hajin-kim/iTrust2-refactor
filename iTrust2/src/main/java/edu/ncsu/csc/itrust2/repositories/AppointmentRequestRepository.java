package edu.ncsu.csc.itrust2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ncsu.csc.itrust2.models.AppointmentRequest;
import edu.ncsu.csc.itrust2.models.User;

import javax.validation.constraints.NotNull;

public interface AppointmentRequestRepository extends JpaRepository<AppointmentRequest, Long> {

    List<AppointmentRequest> findByPatient(@NotNull User patient);

    List<AppointmentRequest> findByHcp(@NotNull User hcp);

    List<AppointmentRequest> findByHcpAndPatient(@NotNull User hcp, @NotNull User patient);

}
