package edu.ncsu.csc.itrust2.repositories;

import edu.ncsu.csc.itrust2.models.Hospital;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, String> {

    Hospital findByName(String name);
}
