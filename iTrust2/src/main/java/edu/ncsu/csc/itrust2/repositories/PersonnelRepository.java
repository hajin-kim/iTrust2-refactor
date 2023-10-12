package edu.ncsu.csc.itrust2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ncsu.csc.itrust2.models.Personnel;

public interface PersonnelRepository extends JpaRepository<Personnel, Long> {

}
