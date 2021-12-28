package com.kubeio.search.repositories;

import com.kubeio.search.models.Wine;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WineRepository extends ElasticsearchRepository<Wine, String> { }
