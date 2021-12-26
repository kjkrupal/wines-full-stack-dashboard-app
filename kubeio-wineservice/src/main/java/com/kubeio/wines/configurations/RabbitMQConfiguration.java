package com.kubeio.wines.configurations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class RabbitMQConfiguration {

    public static String EXCHANGE;
    public static String CREATE_QUEUE_ROUTING_KEY;
    public static String UPDATE_QUEUE_ROUTING_KEY;
    public static String DELETE_QUEUE_ROUTING_KEY;

    @Value("${create.queue.name}")
    private String createQueueName;

    @Value("${update.queue.name}")
    private String updateQueueName;

    @Value("${delete.queue.name}")
    private String deleteQueueName;

    @Value("${wine.exchange.name}")
    public void setExchangeName(String exchangeName) {
        this.EXCHANGE = exchangeName;
    }

    @Value("${create.queue.routing.key}")
    public void setCreateQueueRoutingKey(String createQueueRoutingKey) {
        this.CREATE_QUEUE_ROUTING_KEY = createQueueRoutingKey;
    }

    @Value("${update.queue.routing.key}")
    public void setUpdateQueueRoutingKey(String updateQueueRoutingKey) {
        this.UPDATE_QUEUE_ROUTING_KEY = updateQueueRoutingKey;
    }

    @Value("${delete.queue.routing.key}")
    public void setDeleteQueueRoutingKey(String deleteQueueRoutingKey) {
        this.DELETE_QUEUE_ROUTING_KEY = deleteQueueRoutingKey;
    }

    @Bean(name="createQueue")
    public Queue createQueue() {
        return new Queue(createQueueName);
    }

    @Bean(name="updateQueue")
    public Queue updateQueue() {
        return new Queue(updateQueueName);
    }

    @Bean(name="deleteQueue")
    public Queue deleteQueue() {
        return new Queue(deleteQueueName);
    }

    @Bean(name="exchange")
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding createQueueBinding(@Qualifier("createQueue") Queue queue, @Qualifier("exchange") TopicExchange topicExchange) {
        return BindingBuilder
                .bind(queue)
                .to(topicExchange)
                .with(CREATE_QUEUE_ROUTING_KEY);
    }

    @Bean
    public Binding updateQueueBinding(@Qualifier("updateQueue") Queue queue, @Qualifier("exchange") TopicExchange topicExchange) {
        return BindingBuilder
                .bind(queue)
                .to(topicExchange)
                .with(UPDATE_QUEUE_ROUTING_KEY);
    }

    @Bean
    public Binding deleteQueueBinding(@Qualifier("deleteQueue") Queue queue, @Qualifier("exchange") TopicExchange topicExchange) {
        return BindingBuilder
                .bind(queue)
                .to(topicExchange)
                .with(DELETE_QUEUE_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
