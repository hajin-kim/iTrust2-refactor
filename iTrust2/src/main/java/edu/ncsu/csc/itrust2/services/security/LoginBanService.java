package edu.ncsu.csc.itrust2.services.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import edu.ncsu.csc.itrust2.models.User;
import edu.ncsu.csc.itrust2.repositories.security.LoginBanRepository;
import edu.ncsu.csc.itrust2.services.Service;

@Component
@Transactional
public class LoginBanService extends Service {

    private final LoginBanRepository repository;

    public LoginBanService(LoginBanRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository getRepository () {
        return repository;
    }

    public boolean isIPBanned ( final String ipAddress ) {
        return repository.existsByIp( ipAddress );
    }

    public boolean isUserBanned ( final User user ) {
        return repository.existsByUser( user );
    }

    public long clearIP ( final String ipAddress ) {
        return repository.deleteByIp( ipAddress );
    }

    public long clearUser ( final User user ) {
        return repository.deleteByUser( user );
    }
}
