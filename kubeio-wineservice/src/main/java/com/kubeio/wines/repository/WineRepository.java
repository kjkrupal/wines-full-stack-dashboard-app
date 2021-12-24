package com.kubeio.wines.repository;

import com.kubeio.wines.models.Wine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WineRepository extends JpaRepository<Wine, Long> {
}
