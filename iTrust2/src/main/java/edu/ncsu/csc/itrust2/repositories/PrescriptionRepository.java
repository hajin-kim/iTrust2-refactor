package edu.ncsu.csc.itrust2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ncsu.csc.itrust2.models.Prescription;
import edu.ncsu.csc.itrust2.models.User;

import javax.validation.constraints.NotNull;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    List<Prescription> findByPatient(@NotNull User patient);

}
