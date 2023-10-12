package edu.ncsu.csc.itrust2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ncsu.csc.itrust2.models.Email;
import edu.ncsu.csc.itrust2.models.User;

import javax.validation.constraints.NotNull;

public interface EmailRepository extends JpaRepository<Email, Long> {

    List<Email> findByReceiver(@NotNull User receiver);

}
