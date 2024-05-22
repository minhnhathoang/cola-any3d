package org.nhathm.websocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.*;

@Component
@Log4j2
@RequiredArgsConstructor
public class WebSocketService {

    private final SimpMessagingTemplate template;

    public void notifyToUser(String userId, Object message) {
        log.info("Notify to user: {}", userId);
        template.convertAndSendToUser(userId, "/queue/notify", message);
    }

    @SendToUser("/queue/greetings")
    public String reply(@Payload String message) {
        return "Hello " + message;
    }

    @Scheduled(fixedRate = 1000)
    @SendTo("/topic/time")
    public void broadcastNews() {
        template.convertAndSendToUser("username1", "/queue/notify", "any3d - 1");
    }

    @SendToUser("/")
    public void send() {
        template.convertAndSend("", "any3d1");
        template.convertAndSend("/", "any3d1");
        template.convertAndSend("/websocket", "any3d1");
        System.out.println("Broadcasting message sw: " + "any3d1");
    }

    @MessageMapping("app")
    @SendTo("/topic/greetings")
    public String greeting(String message) {
        template.convertAndSend("", "any3d1");
        template.convertAndSend("/", "any3d1");
        template.convertAndSend("/websocket", "any3d1");
        System.out.println("Received message: " + message);
        return "Hello, " + message + "!";
    }
}
