package edu.ncsu.csc.itrust2.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import edu.ncsu.csc.itrust2.models.User;
import edu.ncsu.csc.itrust2.repositories.UserRepository;

@Component
@Transactional
@Primary
public class UserService extends Service {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository getRepository () {
        return repository;
    }

    public User findByName ( final String username ) {
        return repository.findByUsername( username );
    }

}
