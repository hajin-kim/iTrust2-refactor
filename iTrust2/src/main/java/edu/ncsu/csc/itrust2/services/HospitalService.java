package edu.ncsu.csc.itrust2.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import edu.ncsu.csc.itrust2.models.Hospital;
import edu.ncsu.csc.itrust2.repositories.HospitalRepository;

@Component
@Transactional
public class HospitalService extends Service {

    private final HospitalRepository repository;

    public HospitalService(HospitalRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository getRepository () {
        return repository;
    }

    public Hospital findByName ( final String name ) {
        return repository.findByName( name );
    }

}
