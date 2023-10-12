package edu.ncsu.csc.itrust2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ncsu.csc.itrust2.models.Email;
import edu.ncsu.csc.itrust2.models.User;

public interface EmailRepository extends JpaRepository<Email, Long> {

    public List<Email> findByReceiver ( User receiver );

}
