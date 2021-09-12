package kz.sabit.rabbitmq_mongo_server.repository;

import kz.sabit.rabbitmq_mongo_server.models.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Sabit Murzaliev on 12.09.2021 11:09
 */

@Repository
public interface EventRepository extends MongoRepository<Event, String> {
    List<Event> findAll();
}