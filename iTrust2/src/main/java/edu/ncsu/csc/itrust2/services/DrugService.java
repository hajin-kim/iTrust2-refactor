package edu.ncsu.csc.itrust2.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import edu.ncsu.csc.itrust2.models.Drug;
import edu.ncsu.csc.itrust2.repositories.DrugRepository;

@Component
@Transactional
public class DrugService extends Service {

    private final DrugRepository repository;

    public DrugService(DrugRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository getRepository () {
        return repository;
    }

    public boolean existsByCode ( final String code ) {
        return repository.existsByCode( code );
    }

    public Drug findByCode ( final String code ) {
        return repository.findByCode( code );
    }
}
