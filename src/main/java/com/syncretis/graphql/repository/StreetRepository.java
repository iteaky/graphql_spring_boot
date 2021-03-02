package com.syncretis.graphql.repository;

import com.syncretis.graphql.entity.City;
import com.syncretis.graphql.entity.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreetRepository extends JpaRepository<Street, Long> {
}
