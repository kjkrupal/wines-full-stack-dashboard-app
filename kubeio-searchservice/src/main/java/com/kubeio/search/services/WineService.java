package com.kubeio.search.services;

import com.kubeio.search.dto.WineDTO;
import com.kubeio.search.models.Wine;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class WineService {

    private static final String WINE_INDEX = "wines";

    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    public WineService(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public void createOrUpdateWineIndex(WineDTO wineDTO) {
        try {
            Wine wine = Wine.getWineFromDTO(wineDTO);
            IndexQuery indexQuery = new IndexQueryBuilder()
                    .withId(wine.getUuid())
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

    public void deleteWineIndex(WineDTO wineDTO) {
        try {
            log.info("Deleting index");
            Wine wine = Wine.getWineFromDTO(wineDTO);
            IndexQuery indexQuery = new IndexQueryBuilder()
                    .withId(wine.getUuid())
                    .build();
            String id = elasticsearchOperations.delete(indexQuery,IndexCoordinates.of(WINE_INDEX));
            log.info("Deleted wine index {}", id);
        } catch (Exception e) {
            log.error("Couldn't delete an index.", e);
        }
    }

    public List<Wine> findWinesForQuery(String query) {

        log.info("Searching for query {}", query);

        QueryBuilder queryBuilder = QueryBuilders
                .multiMatchQuery(query)
                .fuzziness(Fuzziness.AUTO);

        Query searchQuery = new NativeSearchQueryBuilder()
                .withFilter(queryBuilder)
                .build();

        SearchHits<Wine> wineHits = elasticsearchOperations
                .search(searchQuery, Wine.class, IndexCoordinates.of(WINE_INDEX));

        List<Wine> wines = wineHits.stream().map(wineHit -> wineHit.getContent()).collect(Collectors.toList());

        log.info("Found {} wine hits for query {}", wines.size(), query);

        return wines;
    }
}
