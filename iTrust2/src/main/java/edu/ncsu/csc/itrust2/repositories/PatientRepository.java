package edu.ncsu.csc.itrust2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ncsu.csc.itrust2.models.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
