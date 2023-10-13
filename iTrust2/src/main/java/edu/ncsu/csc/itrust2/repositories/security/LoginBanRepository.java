package edu.ncsu.csc.itrust2.repositories.security;

import edu.ncsu.csc.itrust2.models.User;
import edu.ncsu.csc.itrust2.models.security.LoginBan;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginBanRepository extends JpaRepository<LoginBan, Long> {

    /**
     * Checks to see if there is a LoginBan for the given IP address.
     *
     * @param ipAddress The IP address to search by.
     * @return True iff a ban was found.
     */
    boolean existsByIp(String ipAddress);

    /**
     * Checks to see if there is a LoginBan for the given user.
     *
     * @param user The User to search by.
     * @return True iff a ban was found.
     */
    boolean existsByUser(User user);

    /**
     * Deletes all saved LoginBans for the given IP address.
     *
     * @param ipAddress The IP address to delete by
     * @return The number of records deleted.
     */
    long deleteByIp(String ipAddress);

    /**
     * Deletes all saved LoginBans for the given user.
     *
     * @param user The User to delete by.
     * @return The number of records deleted.
     */
    long deleteByUser(User user);
}
