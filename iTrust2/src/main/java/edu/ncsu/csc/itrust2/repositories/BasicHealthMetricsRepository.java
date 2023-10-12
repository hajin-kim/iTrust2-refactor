package edu.ncsu.csc.itrust2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ncsu.csc.itrust2.models.BasicHealthMetrics;

public interface BasicHealthMetricsRepository extends JpaRepository<BasicHealthMetrics, Long> {

}
