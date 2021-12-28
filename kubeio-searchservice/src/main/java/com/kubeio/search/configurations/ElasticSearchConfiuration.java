package com.kubeio.search.configurations;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.kubeio.search.repositories")
public class ElasticSearchConfiuration extends AbstractElasticsearchConfiguration {

    @Value("${search.connection.uri}")
    private String elasticsearchConnectionURI;

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration
                .builder()
                .connectedTo(elasticsearchConnectionURI)
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
