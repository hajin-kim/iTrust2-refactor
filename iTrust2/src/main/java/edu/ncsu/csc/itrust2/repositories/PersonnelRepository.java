package edu.ncsu.csc.itrust2.repositories;

import edu.ncsu.csc.itrust2.models.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonnelRepository extends JpaRepository<Personnel, Long> {

}
