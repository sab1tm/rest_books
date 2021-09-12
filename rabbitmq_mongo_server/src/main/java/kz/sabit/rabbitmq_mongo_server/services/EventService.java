package kz.sabit.rabbitmq_mongo_server.services;

import kz.sabit.rabbitmq_mongo_server.models.Event;
import kz.sabit.rabbitmq_mongo_server.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Sabit Murzaliev on 12.09.2021 11:15
 */

@Service
public class EventService {

    EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public Optional<Event> save (Event newEvent) {
        try {
            Event result = repository.save(newEvent);
            return Optional.of(result);
        }
        catch (Exception e) {
            return Optional.empty();
        }
    }
}
