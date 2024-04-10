package org.nhathm.app.object.consumer;

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

/**
 * @author nhathm
 */
@Component
@Controller
public class WebsocketController implements WebSocketHandler {

    @Autowired
    private SimpMessagingTemplate template;

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

    @MessageMapping
    @SendTo("")
    public String greeting1(String message) {
        System.out.println("Received message: " + message);
        return "Hello1, " + message + "!";
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("Connected");
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        System.out.println("Received message: " + message.getPayload());
        session.sendMessage(new TextMessage("Hello, " + message.getPayload()));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
