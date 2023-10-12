package edu.ncsu.csc.itrust2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ncsu.csc.itrust2.models.User;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername ( String username );

}
