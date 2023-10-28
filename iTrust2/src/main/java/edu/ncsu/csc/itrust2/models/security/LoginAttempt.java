package edu.ncsu.csc.itrust2.models.security;

import edu.ncsu.csc.itrust2.adapters.ZonedDateTimeAdapter;
import edu.ncsu.csc.itrust2.adapters.ZonedDateTimeAttributeConverter;
import edu.ncsu.csc.itrust2.models.DomainObject;
import edu.ncsu.csc.itrust2.models.User;

import java.time.ZonedDateTime;
import javax.persistence.Basic;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.JsonAdapter;
import lombok.Getter;
import lombok.Setter;

/**
 * Class to hold failed login attempts. An entry is either for an IP address or for a User, but not
 * both. This way, IP lockouts and User lockouts are independent, and clearing one will not affect
 * the other. Once the number of Attempts for a user or IP reaches a threshold, all Attempts are
 * removed and a LoginLockout is created. Attempts do not expire, but are cleared on successful
 * authentication. If an attempt is for a known username, two objects are created, one for the IP
 * and one for the user. If the username is unknown, then only one is created for the IP.
 *
 * @author Thomas
 * @author Kai Presler-Marshall
 */
@Getter
@Entity
@Table(name = "login_attempt")
public class LoginAttempt extends DomainObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter private String ip;

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id", columnDefinition = "varchar(100)")
    private User user;

    @Setter
    @Basic
    // Allows the field to show up nicely in the database
    @Convert(converter = ZonedDateTimeAttributeConverter.class)
    @JsonAdapter(ZonedDateTimeAdapter.class)
    private ZonedDateTime time;
}
