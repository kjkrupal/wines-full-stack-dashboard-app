package com.kubeio.search.services;

import com.kubeio.search.dto.WineDTO;
import com.kubeio.search.models.Wine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WineService {

    private static final String WINE_INDEX = "wines";

    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    public WineService(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public void createWineIndex(WineDTO wineDTO) {

        try {
            Wine wine = Wine.getWineFromDTO(wineDTO);

            IndexQuery indexQuery = new IndexQueryBuilder()
                    .withId(wine.getUuid().toString())
                    .withObject(wine)
                    .build();

            String documentId = elasticsearchOperations
                    .index(indexQuery, IndexCoordinates.of(WINE_INDEX));

            log.info("Create wine index with documentId {}", documentId);
        }
        catch (Exception e) {
            log.error("Couldn't create an index.", e);
        }

    }

    public void updateWineIndex(WineDTO wine) {

    }

    public void deleteWineIndex(WineDTO wine) {

    }
}
