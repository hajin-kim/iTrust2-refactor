package edu.ncsu.csc.itrust2.repositories;

import edu.ncsu.csc.itrust2.models.Email;
import edu.ncsu.csc.itrust2.models.User;

import java.util.List;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {

    List<Email> findByReceiver(@NotNull User receiver);
}
