package edu.ncsu.csc.itrust2.repositories;

import edu.ncsu.csc.itrust2.models.Email;
import edu.ncsu.csc.itrust2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Long> {

    List<Email> findByReceiver(@NotNull User receiver);

}
