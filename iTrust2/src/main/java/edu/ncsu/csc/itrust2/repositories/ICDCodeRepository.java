package edu.ncsu.csc.itrust2.repositories;

import edu.ncsu.csc.itrust2.models.ICDCode;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICDCodeRepository extends JpaRepository<ICDCode, Long> {

    ICDCode findByCode(String code);
}
