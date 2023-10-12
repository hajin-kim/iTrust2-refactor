package edu.ncsu.csc.itrust2.config;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import edu.ncsu.csc.itrust2.models.enums.TransactionType;
import edu.ncsu.csc.itrust2.services.UserService;
import edu.ncsu.csc.itrust2.services.security.LoginAttemptService;
import edu.ncsu.csc.itrust2.services.security.LoginBanService;
import edu.ncsu.csc.itrust2.services.security.LoginLockoutService;
import edu.ncsu.csc.itrust2.utils.LoggerUtil;

/**
 * Listens for AuthenticationEvents to Log them and to clear FaieldAttempts on
 * successful authentication.
 *
 * @author Kai Presler-Marshall
 *
 */
@Component
@RequiredArgsConstructor
public class LoginAuditingListener implements ApplicationListener<ApplicationEvent> {

    private final LoggerUtil          util;

    private final LoginAttemptService loginAttemptService;

    private final LoginBanService     loginBanService;

    private final UserService         userService;

    private final LoginLockoutService loginLockoutService;

    @Override
    public void onApplicationEvent (@NotNull final ApplicationEvent event ) {
        if ( event instanceof InteractiveAuthenticationSuccessEvent ) {
            final InteractiveAuthenticationSuccessEvent authEvent = (InteractiveAuthenticationSuccessEvent) event;
            final Authentication authentication = authEvent.getAuthentication();
            final UserDetails details = (UserDetails) authentication.getPrincipal();
            final UsernamePasswordAuthenticationToken source = (UsernamePasswordAuthenticationToken) authEvent
                    .getSource();
            final WebAuthenticationDetails det = (WebAuthenticationDetails) source.getDetails();

            // Clear login attempts for this User and this IP. if not IP banned.
            // If IP lockout or banned, this is still called, but the redirect
            // invalidates the credentials if they happen to be correct (and
            // bypassed the lockout page via a direct API call).
            final String addr = det.getRemoteAddress();
            if ( !loginLockoutService.isIPLocked( addr ) && !loginBanService.isIPBanned( addr ) ) {
                loginAttemptService.clearIP( addr );
                loginAttemptService.clearUser( userService.findByName( details.getUsername() ) );
                util.log( TransactionType.LOGIN_SUCCESS, details.getUsername() );
            }

        }

        if ( event instanceof AbstractAuthenticationFailureEvent ) {
            final AbstractAuthenticationFailureEvent authEvent = (AbstractAuthenticationFailureEvent) event;
            final Authentication authentication = authEvent.getAuthentication();
            util.log( TransactionType.LOGIN_FAILURE, authentication.getPrincipal().toString() );
        }
    }
}
