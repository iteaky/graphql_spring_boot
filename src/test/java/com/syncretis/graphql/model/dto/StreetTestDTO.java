package com.syncretis.graphql.model.dto;

import lombok.*;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class StreetTestDTO {

    private long id;
    private String name;
    private String area;
    private String owner;
    private List<MallTestDTO> malls;

    public StreetTestDTO(long id, String name, String area, String owner) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.owner = owner;
        this.malls = Collections.emptyList();
    }
}
