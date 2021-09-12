package kz.sabit.rabbitmq_mongo_server.receiver;

import kz.sabit.rabbitmq_mongo_server.models.Event;
import kz.sabit.rabbitmq_mongo_server.services.EventService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sabit Murzaliev on 12.09.2021 16:18
 */

@Component
public class RabbitMqReceiver implements RabbitListenerConfigurer {

    EventService service;

    public RabbitMqReceiver(EventService service) {
        this.service = service;
    }

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqReceiver.class);

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(Event event) {
        logger.info("Received object: " + event);

        // запись в MongoDB
        service.save(event);
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
    }

}