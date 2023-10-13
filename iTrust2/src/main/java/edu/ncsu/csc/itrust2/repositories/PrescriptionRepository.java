package edu.ncsu.csc.itrust2.repositories;

import edu.ncsu.csc.itrust2.models.Prescription;
import edu.ncsu.csc.itrust2.models.User;

import java.util.List;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    List<Prescription> findByPatient(@NotNull User patient);
}
