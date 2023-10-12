package edu.ncsu.csc.itrust2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ncsu.csc.itrust2.models.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, String> {

    public Hospital findByName ( String name );

}
