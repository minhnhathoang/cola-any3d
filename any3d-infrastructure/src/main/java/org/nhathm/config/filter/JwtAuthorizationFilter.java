package org.nhathm.config.filter;

import domain.security.common.AccessToken;
import domain.security.common.JwtConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.nhathm.domain.auth.domainservice.JwtTokenProvider;
import org.nhathm.domain.auth.domainservice.UnauthorizedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Slf4j
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private static final String TOKEN_IS_REQUIRED = "Token is required";

    private final JwtTokenProvider jwtTokenProvider;

    private final PathMatcher pathMatcher;


    public JwtAuthorizationFilter(
            JwtTokenProvider jwtTokenProvider,
            PathMatcher pathMatcher
    ) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.pathMatcher = pathMatcher;
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
                                    @NotNull FilterChain chain) throws IOException, ServletException {
        log.info("doFilterInternal");
        try {
            if (!isAnonymousUrls(request)) {
                AccessToken accessToken = resolveToken(request);
                if (accessToken == null) {
                    throw new UnauthorizedException(TOKEN_IS_REQUIRED);
                }
                try {
                    this.jwtTokenProvider.validateToken(accessToken);
                } catch (Exception e) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write(e.getMessage());
                    return;
                }
                Authentication authentication = this.jwtTokenProvider.getAuthentication(accessToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            chain.doFilter(request, response);
        } catch (UnauthorizedException e) {
            log.error("SC_UNAUTHORIZED" + e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        }
    }

    private AccessToken resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(jwtTokenProvider.getJwtProperties().getHeader());
        if (StringUtils.isNotBlank(bearerToken) && bearerToken.startsWith(JwtConstants.BEARER_PREFIX)) {
            return AccessToken.builder().value(bearerToken.substring(JwtConstants.BEARER_PREFIX.length())).build();
        }
        return null;
    }

    private boolean isAnonymousUrls(HttpServletRequest request) {
        List<String> anonymousUrls = jwtTokenProvider.getJwtProperties().getAnonymousUrls();
        if (CollectionUtils.isEmpty(anonymousUrls)) {
            return false;
        }
        String requestURI = request.getRequestURI();
        return anonymousUrls.stream().anyMatch(url -> pathMatcher.match(url, requestURI));
    }
}
