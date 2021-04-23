package com.syncretis.graphql.repository;

import com.syncretis.graphql.model.entity.Mall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MallRepository extends JpaRepository<Mall, Long> {
}
