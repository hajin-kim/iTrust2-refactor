package edu.ncsu.csc.itrust2.services;

import javax.transaction.Transactional;

import edu.ncsu.csc.itrust2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import edu.ncsu.csc.itrust2.repositories.PersonnelRepository;

@Component
@Transactional
public class PersonnelService extends UserService {

    private final PersonnelRepository repository;

    public PersonnelService(UserRepository userRepository, PersonnelRepository repository) {
        super(userRepository);
        this.repository = repository;
    }

    @Override
    protected JpaRepository getRepository () {
        return repository;
    }

}
