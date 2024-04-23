package org.nhathm.config;


import org.jetbrains.annotations.NotNull;
import org.nhathm.domain.user.entity.User;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

@Component
public class AdviceWebsocketHandshakeHandler extends DefaultHandshakeHandler {

    protected Principal determineUser(@NotNull ServerHttpRequest request, @NotNull WebSocketHandler wsHandler, @NotNull Map<String, Object> attributes) {
        try {
            Principal principal = request.getPrincipal();
            UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
            assert token != null;
            User user = (User) token.getPrincipal();
            return new AdviceWebsocketPrincipal(user.getId());
        } catch (Exception e) {
            return super.determineUser(request, wsHandler, attributes);
        }
    }
}
