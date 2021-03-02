package com.syncretis.graphql.entity;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
import org.yaml.snakeyaml.tokens.FlowSequenceEndToken;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class City {
    @Id
    @Column(name = "city_id")
    private long cityId;

    private String name;

    private String country;

    @ElementCollection
    @CollectionTable(name="street", joinColumns=@JoinColumn(name="city_id", referencedColumnName = "city_id"))
    @Column(name = "street_id")
    @Fetch(FetchMode.JOIN)
    private List<Long> streets;
}
