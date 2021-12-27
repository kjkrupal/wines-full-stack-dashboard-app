package com.kubeio.search.subscribers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WineSubscriber {

    @RabbitListener(queues = "${create.queue.name}")
    public void onWineCreateEvent(Object object) {
        log.info("Received wine create event. Object is {}", object.toString());
    }

    @RabbitListener(queues = "${update.queue.name}")
    public void onWineUpdateEvent(Object object) {
        log.info("Received wine create event. Object is {}", object.toString());
    }

    @RabbitListener(queues = "${delete.queue.name}")
    public void onWineDeleteEvent(Object object) {
        log.info("Received wine create event. Object is {}", object.toString());
    }
}
