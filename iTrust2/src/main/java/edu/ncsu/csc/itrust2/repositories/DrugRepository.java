package edu.ncsu.csc.itrust2.repositories;

import edu.ncsu.csc.itrust2.models.Drug;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugRepository extends JpaRepository<Drug, Long> {

    boolean existsByCode(String code);

    Drug findByCode(String code);
}
