package kz.sabit.rabbitmq_mongo_server.controllers;

import kz.sabit.rabbitmq_mongo_server.models.Event;
import kz.sabit.rabbitmq_mongo_server.repository.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sabit Murzaliev on 12.09.2021 11:11
 */

@RestController
@RequestMapping("/events")
public class EventController {

    EventRepository repository;

    public EventController(EventRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAll() {
        List<Event> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
