package com.kubeio.wines.publishers;

import com.kubeio.wines.configurations.RabbitMQConfiguration;
import com.kubeio.wines.dto.WineEvent;
import com.kubeio.wines.dto.WineSearchEventDTO;
import com.kubeio.wines.models.Wine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WinePublisher {

    private final RabbitTemplate rabbitTemplate;

    public WinePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public String getRoutingKeyFromEvent(WineEvent wineEvent) {
        switch (wineEvent) {
            case CREATE: return RabbitMQConfiguration.CREATE_QUEUE_ROUTING_KEY;
            case UPDATE: return RabbitMQConfiguration.UPDATE_QUEUE_ROUTING_KEY;
            case DELETE: return RabbitMQConfiguration.DELETE_QUEUE_ROUTING_KEY;
            default: return null;
        }
    }

    public void publishWineEvent(final Wine wine, WineEvent wineEvent) {
        String routingKey = getRoutingKeyFromEvent(wineEvent);
        if (routingKey == null) {
            log.info("Routing key not found for event {}", wineEvent.toString().toLowerCase());
            return;
        }
        log.info("Sending wine {} event", wineEvent.toString().toLowerCase());
        rabbitTemplate.convertAndSend(
                RabbitMQConfiguration.EXCHANGE,
                routingKey,
                convertToWineSearchEventDTO(wine));
        log.info("Sent wine {} event", wineEvent.toString().toLowerCase());
    }

    private WineSearchEventDTO convertToWineSearchEventDTO(Wine wine) {
        return new WineSearchEventDTO(
                wine.getUuid(),
                wine.getCountry(),
                wine.getDescription(),
                wine.getVariety(),
                wine.getWinery()
        );
    }
}
