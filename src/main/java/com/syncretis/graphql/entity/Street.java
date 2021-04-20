package com.syncretis.graphql.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Street {

    @Id
    @Column(name = "street_id")
    private long id;

    @Column(name = "city_id")
    private long cityId;

    private String name;

    private String area;

    @Column(name = "created_by")
    private String createdBy;

}
