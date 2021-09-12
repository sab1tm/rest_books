package kz.sab1tm.rest_books_server.services;

/**
 * Created by Sabit Murzaliev on 11.09.2021 16:28
 */

import kz.sab1tm.rest_books_server.models.rabbitmq.Event;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RabbitMQService {

    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    public RabbitMQService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String username, String method, String endpoint, String params) {
        Event event = new Event();
        event.setActionDt(LocalDateTime.now().toString());
        event.setUsername(username);
        event.setMethod(method);
        event.setEndpoint(endpoint);
        event.setParams(params);

        System.out.println("Sending object: "+ event);
        rabbitTemplate.convertAndSend(exchange,routingkey, event);
    }


}
