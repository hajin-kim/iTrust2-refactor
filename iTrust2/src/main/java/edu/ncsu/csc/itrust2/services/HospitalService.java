package edu.ncsu.csc.itrust2.services;

import edu.ncsu.csc.itrust2.models.Hospital;
import edu.ncsu.csc.itrust2.repositories.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class HospitalService extends Service {

    private final HospitalRepository repository;

    @Override
    protected JpaRepository getRepository () {
        return repository;
    }

    public Hospital findByName ( final String name ) {
        return repository.findByName( name );
    }

}
