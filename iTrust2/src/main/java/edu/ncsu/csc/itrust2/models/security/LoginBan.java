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

import com.google.gson.annotations.JsonAdapter;
import lombok.Getter;
import lombok.Setter;

/**
 * Contains info about a LoginBan from the system. A ban does not expire, and can only be removed by
 * an admin (Not Implemented). A ban can be for either a User or an IP.
 *
 * @author Thomas
 * @author Kai Presler-Marshall
 */
@Getter
@Entity
public class LoginBan extends DomainObject {
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
