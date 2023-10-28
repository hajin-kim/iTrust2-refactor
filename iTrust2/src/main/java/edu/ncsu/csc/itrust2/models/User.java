package edu.ncsu.csc.itrust2.models;

import edu.ncsu.csc.itrust2.forms.UserForm;
import edu.ncsu.csc.itrust2.models.enums.Role;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Basic class for a User in the system. This User class is a shared type that is used for all users
 * of the iTrust2 system and handles basic functionality such as authenticating the user in the
 * system. For specific rolls, an additional record, such as a Patient or Personnel record, is
 * created and references the User object for that user. This allows the iTrust2 system to keep only
 * what information is needed for a particular type of user.
 *
 * <p>Note use of JsonIgnoreProperties to make sure that even the (hashed) password isn't sent over
 * the API when the Java objects are serialised to JSON.
 *
 * @author Kai Presler-Marshall
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@JsonIgnoreProperties(value = {"password"})
public class User extends DomainObject {

    /**
     * All-argument constructor for User
     *
     * @param username Username
     * @param password The _already encoded_ password of the user.
     * @param role Role of the user
     * @param enabled 1 if the user is enabled 0 if not
     */
    protected User(
            final String username, final String password, final Role role, final Integer enabled) {
        setUsername(username);
        setPassword(password);
        addRole(role);
        setEnabled(enabled);
    }

    /**
     * Create a new user based off of the UserForm
     *
     * @param form the filled-in user form with user information
     */
    protected User(final UserForm form) {
        setUsername(form.getUsername());
        if (!form.getPassword().equals(form.getPassword2())) {
            throw new IllegalArgumentException("Passwords do not match!");
        }
        final PasswordEncoder pe = new BCryptPasswordEncoder();
        setPassword(pe.encode(form.getPassword()));
        setEnabled(null != form.getEnabled() ? 1 : 0);
        setRoles(form.getRoles().stream().map(Role::valueOf).collect(Collectors.toSet()));
    }

    /** The username of the user */
    @Setter // TODO: remove
    @Id
    @Column(length = 20)
    @Length(max = 20) private String username;

    /** The password of the user */
    @Setter private String password;

    /** Whether or not the user is enabled */
    @Setter
    @Min(0)
    @Max(1)
    private Integer enabled;

    /** The role of the user */
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    /**
     * Set the roles of this user. Throws an exception if the Set of roles provided contains either
     * a Patient or Admin role, and that role is not the only one present
     *
     * @param roles the roles to set this user to
     */
    public void setRoles(final Set<Role> roles) {
        /* Patient & admin can't have any other roles */
        if ((roles.contains(Role.ROLE_PATIENT) || roles.contains(Role.ROLE_ADMIN))
                && 1 != roles.size()) {
            throw new IllegalArgumentException(
                    "Tried to create a Patient or Admin user with a secondary role.  Patient &"
                            + " admin can only have a single role!");
        }

        this.roles = roles;
    }

    /**
     * Adds a new Role to this user. Throws an exception when trying to add roles to a
     * patient/admin, or add patient/admin roles to anyone with any existing role(s).
     *
     * @param role the Role to add
     */
    public void addRole(final Role role) {
        if (null == this.roles) {
            this.roles = new HashSet<>();
        }
        if (role.equals(Role.ROLE_ADMIN) || role.equals(Role.ROLE_PATIENT)) {
            throw new IllegalArgumentException("Admin and Patient roles cannot be added");
        }
        if (this.roles.contains(Role.ROLE_ADMIN) || this.roles.contains(Role.ROLE_PATIENT)) {
            throw new IllegalArgumentException(
                    "Admins and Patients cannot have additional roles added");
        }
        this.roles.add(role);
    }

    /**
     * Get the hashCode of this user
     *
     * @return the hashCode of this user
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((roles == null) ? 0 : roles.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    /**
     * Whether or not this user is equal to the passed User
     *
     * @param obj the user to compate this user to
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (enabled == null) {
            if (other.enabled != null) {
                return false;
            }
        } else if (!enabled.equals(other.enabled)) {
            return false;
        }
        if (password == null) {
            if (other.password != null) {
                return false;
            }
        } else if (!password.equals(other.password)) {
            return false;
        }
        if (roles != other.roles) {
            return false;
        }
        if (username == null) {
            return other.username == null;
        } else return username.equals(other.username);
    }

    /**
     * Get the id of this user (aka, the username)
     *
     * @return The Username
     */
    @Override
    public String getId() {
        return getUsername();
    }

    /**
     * Checks if a user is a Doctor.
     *
     * @return true if the user has the `ROLE_HCP` role
     */
    public boolean isDoctor() {
        return roles.contains(Role.ROLE_HCP);
    }
}
