package org.nhathm.config;

import com.alibaba.cola.exception.SysException;
import com.alibaba.fastjson.JSON;
import domain.security.common.AccessToken;
import domain.security.common.JwtConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.nhathm.domain.auth.domainservice.JwtProperties;
import org.nhathm.domain.auth.domainservice.JwtTokenProvider;
import org.nhathm.domain.auth.domainservice.UnauthorizedException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

/**
 * @author nhathm
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final JwtProperties jwtProperties;

    private final JwtTokenProvider jwtTokenProvider;

    private final AdviceWebsocketHandshakeHandler adviceWebsocketHandshakeHandler;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setHandshakeHandler(adviceWebsocketHandshakeHandler)
                .setAllowedOrigins("*");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("", "/app");
        registry.enableSimpleBroker("/topic", "/queue");
        registry.setUserDestinationPrefix("/user");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    String authorizationHeader = accessor.getFirstNativeHeader(jwtProperties.getHeader());
                    if (StringUtils.isNotBlank(authorizationHeader)
                            && authorizationHeader.startsWith(JwtConstants.BEARER_PREFIX)) {
                        AccessToken accessToken = AccessToken.builder().value(authorizationHeader.substring(JwtConstants.BEARER_PREFIX.length())).build();
                        try {
                            jwtTokenProvider.validateToken(accessToken);
                        } catch (Exception e) {
                            throw new UnauthorizedException("Unauthorized. Invalid token");
                        }
                        try {
                            Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
                            SecurityContextHolder.getContext().setAuthentication(authentication);

                            //
                            accessor.setUser(authentication);
                        } catch (ParseException e) {
                            throw new SysException("Failed to parse token", e);
                        }
                    } else {
                        throw new UnauthorizedException("Unauthorized");
                    }
                }
                // TODO:
                log.info("preSend: {}", JSON.toJSONString(message));
                return message;
            }
        });
    }

    private AccessToken resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(jwtTokenProvider.getJwtProperties().getHeader());
        if (StringUtils.isNotBlank(bearerToken) && bearerToken.startsWith(JwtConstants.BEARER_PREFIX)) {
            return AccessToken.builder().value(bearerToken.substring(JwtConstants.BEARER_PREFIX.length())).build();
        }
        return null;
    }
}
