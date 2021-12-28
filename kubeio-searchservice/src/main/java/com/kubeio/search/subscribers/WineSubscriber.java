package com.kubeio.search.subscribers;

import com.kubeio.search.dto.WineDTO;
import com.kubeio.search.services.WineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WineSubscriber {

    private final WineService wineService;

    public WineSubscriber(WineService wineService) {
        this.wineService = wineService;
    }

    @RabbitListener(queues = "${create.queue.name}")
    public void onWineCreateEvent(WineDTO wine) {
        log.info("Received wine create event. {}", wine);
        wineService.createOrUpdateWineIndex(wine);
    }

    @RabbitListener(queues = "${update.queue.name}")
    public void onWineUpdateEvent(WineDTO wine) {
        log.info("Received wine update event. {}", wine);
        wineService.createOrUpdateWineIndex(wine);
    }

    @RabbitListener(queues = "${delete.queue.name}")
    public void onWineDeleteEvent(WineDTO wine) {
        log.info("Received wine delete event. {}", wine);
        wineService.deleteWineIndex(wine);
    }
}
