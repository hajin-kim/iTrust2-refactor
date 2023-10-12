package edu.ncsu.csc.itrust2.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import edu.ncsu.csc.itrust2.repositories.PersonnelRepository;

@Component
@Transactional
public class PersonnelService extends UserService {

    @Autowired
    private PersonnelRepository repository;

    @Override
    protected JpaRepository getRepository () {
        return repository;
    }

}
