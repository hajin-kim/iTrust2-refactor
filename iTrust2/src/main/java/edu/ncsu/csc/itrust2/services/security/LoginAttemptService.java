package edu.ncsu.csc.itrust2.services.security;

import edu.ncsu.csc.itrust2.models.User;
import edu.ncsu.csc.itrust2.repositories.security.LoginAttemptRepository;
import edu.ncsu.csc.itrust2.services.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class LoginAttemptService extends Service {

    private final LoginAttemptRepository repository;

    @Override
    protected JpaRepository getRepository () {
        return repository;
    }

    public long countByIP ( final String ipAddress ) {
        return repository.countByIp( ipAddress );
    }

    public long clearIP ( final String ipAddress ) {
        return repository.deleteByIp( ipAddress );
    }

    public long countByUser ( final User user ) {
        return repository.countByUser( user );
    }

    public long clearUser ( final User user ) {
        return repository.deleteByUser( user );
    }

}
