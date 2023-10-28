package edu.ncsu.csc.itrust2.forms;

import edu.ncsu.csc.itrust2.models.User;
import edu.ncsu.csc.itrust2.models.enums.Role;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 * Form used for creating a new User. Will be parsed into an actual User object to be saved.
 *
 * @author Kai Presler-Marshall
 */
@Setter
@NoArgsConstructor
@Getter
public class UserForm {

    /** Username of the user */
    @NotEmpty
    @Length(max = 20) private String username;

    /** Password of the user */
    @NotEmpty
    @Length(min = 6, max = 20) private String password;

    /***
     * Confirmation password of the user
     */
    @NotEmpty
    @Length(min = 6, max = 20) private String password2;

    /** Role of the user */
    @NotEmpty private Set<String> roles;

    /** Whether the User is enabled or not */
    private String enabled;

    /**
     * Create a UserForm from all of its fields.
     *
     * @param username Username of the new user.
     * @param password Password of the new user
     * @param role Role of the new User
     * @param enabled Whether the new User is enabled or not
     */
    public UserForm(
            final String username, final String password, final String role, final String enabled) {
        setUsername(username);
        setPassword(password);
        setPassword2(password);
        addRole(role);
        setEnabled(enabled);
    }

    /**
     * Create a new UserForm from all of its fields.
     *
     * @param username Username of the new user
     * @param password Password of the new user
     * @param role Role (Role Enum) of the new user
     * @param enabled Whether the user is enabled; 1 for enabled, 0 for disabled.
     */
    public UserForm(
            final String username, final String password, final Role role, final Integer enabled) {
        this(username, password, role.toString(), enabled != 0 ? "true" : null);
    }

    /**
     * Create a UserForm from the User object provided. This unfortunately cannot fill out the
     * password as the password cannot be un-hashed.
     *
     * @param u User object to convert to a UserForm.
     */
    public UserForm(@NotNull final User u) {
        setUsername(u.getUsername());
        setRoles(u.getRoles().stream().map(Enum::toString).collect(Collectors.toSet()));
        setEnabled(u.getEnabled().toString());
    }

    /**
     * Adds the provided Role to this User
     *
     * @param role The Role to add
     */
    public void addRole(final String role) {
        if (null == this.roles) {
            this.roles = new HashSet<>();
        }
        this.roles.add(role);
    }
}
