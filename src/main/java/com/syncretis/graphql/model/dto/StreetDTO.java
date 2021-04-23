package com.syncretis.graphql.model.dto;

import com.syncretis.graphql.model.entity.Street;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StreetDTO {

    private long id;
    private long cityId;
    private String name;
    private String area;
    private String owner;
    private List<Long> mallIds;

    public static StreetDTO fromEntity(Street street) {
        return StreetDTO.builder()
                .id(street.getId())
                .cityId(street.getCityId())
                .name(street.getName())
                .area(street.getArea())
                .owner(street.getCreatedBy())
                .mallIds(street.getMalls())
                .build();
    }
}
