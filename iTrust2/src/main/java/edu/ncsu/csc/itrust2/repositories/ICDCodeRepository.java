package edu.ncsu.csc.itrust2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ncsu.csc.itrust2.models.ICDCode;

public interface ICDCodeRepository extends JpaRepository<ICDCode, Long> {

    ICDCode findByCode(String code);

}
