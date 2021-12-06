package com.kubeio.analysis.kubeioanalysis.repository;

import com.kubeio.analysis.kubeioanalysis.models.Wine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WineRepository extends JpaRepository<Wine, Long> {
}
