package org.nhathm.common;

import org.nhathm.domain.user.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Stream;

@Component
public class SpringSecurityUtils {

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    public static Optional<User> getPrincipal() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(extractPrincipal(securityContext.getAuthentication()));
    }

    public static Optional<String> getUserName() {
        User principal = getPrincipal().orElse(null);
        return Optional.ofNullable(principal).map(User::getUsername);
    }

    public static String getUserId() {
        User principal = getPrincipal().orElse(null);
        return Optional.ofNullable(principal).map(User::getId).orElse(null);
    }

    public static Optional<String> getCredentials() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional
                .ofNullable(securityContext.getAuthentication())
                .filter(authentication -> authentication.getCredentials() instanceof String)
                .map(authentication -> (String) authentication.getCredentials());
    }

    public static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && getAuthorities(authentication).noneMatch(ANONYMOUS::equals);
    }

    public static Authentication getAuthentication() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext.getAuthentication() != null) {
            return securityContext.getAuthentication();
        }
        return null;
    }

    private static User extractPrincipal(Authentication authentication) {
        if (authentication == null) {
            return null;
        }
        if (authentication.getPrincipal() instanceof UserDetails userDetails) {
            return (User) userDetails;
        }
        return null;
    }

    private static Stream<String> getAuthorities(Authentication authentication) {
        return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority);
    }

    public static boolean isMe(String userId) {
        return getPrincipal().map(principal -> principal.getId().equals(userId)).orElse(false);
    }
}
