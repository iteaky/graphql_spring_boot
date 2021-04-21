package com.syncretis.graphql.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Mall {
    @Id
    @Column(name = "mall_id")
    private Long mallId;

    private String name;
    private Integer building;

    @Column(name = "street_id")
    private Long streetId;
}
