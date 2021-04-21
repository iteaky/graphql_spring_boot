package com.syncretis.graphql.dto;

import com.syncretis.graphql.entity.City;
import com.syncretis.graphql.entity.Mall;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MallDTO {
    private Long id;
    private String name;
    private Integer building;

    public static MallDTO fromEntity(Mall mall) {
        return MallDTO.builder()
                .id(mall.getMallId())
                .name(mall.getName())
                .building(mall.getBuilding())
                .build();
    }
}
