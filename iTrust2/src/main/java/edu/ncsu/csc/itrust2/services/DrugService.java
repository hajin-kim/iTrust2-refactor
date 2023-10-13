package edu.ncsu.csc.itrust2.services;

import edu.ncsu.csc.itrust2.models.Drug;
import edu.ncsu.csc.itrust2.repositories.DrugRepository;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
@Transactional
@RequiredArgsConstructor
public class DrugService extends Service {

    private final DrugRepository repository;

    @Override
    protected JpaRepository getRepository() {
        return repository;
    }

    public boolean existsByCode(final String code) {
        return repository.existsByCode(code);
    }

    public Drug findByCode(final String code) {
        return repository.findByCode(code);
    }
}
