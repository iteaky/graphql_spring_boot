package com.syncretis.graphql.model.entity;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

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

    @ElementCollection
    @CollectionTable(name="mall", joinColumns=@JoinColumn(name="street_id", referencedColumnName = "street_id"))
    @Column(name = "mall_id")
    @Fetch(FetchMode.JOIN)
    private List<Long> malls;

}
