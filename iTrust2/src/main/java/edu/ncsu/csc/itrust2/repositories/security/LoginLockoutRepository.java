package edu.ncsu.csc.itrust2.repositories.security;

import edu.ncsu.csc.itrust2.models.User;
import edu.ncsu.csc.itrust2.models.security.LoginLockout;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginLockoutRepository extends JpaRepository<LoginLockout, Long> {

    /**
     * Retrieves a list of LoginLockout records for the given IP address.
     *
     * @param ipAddress The IP address to search on.
     * @return The list of matching LoginLockouts.
     */
    List<LoginLockout> findByIp(String ipAddress);

    /**
     * Deletes all saved LoginLockouts for the given IP address.
     *
     * @param ipAddress The IP address to delete by.
     * @return The number of records deleted.
     */
    long deleteByIp(String ipAddress);

    /**
     * Retrieves a list of LoginLockout records for the given user.
     *
     * @param user The User to search on.
     * @return The list of matching LoginLockouts.
     */
    List<LoginLockout> findByUser(User user);

    /**
     * Deletes all saved LoginLockouts for the given User.
     *
     * @param user The user to delete by.
     * @return The number of records deleted.
     */
    long deleteByUser(User user);
}
