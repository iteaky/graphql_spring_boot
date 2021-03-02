package com.syncretis.graphql.dto;

import com.syncretis.graphql.entity.Street;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StreetDTO {

    private long id;
    private long cityId;
    private String name;
    private String area;

    public static StreetDTO fromEntity(Street street) {
        return StreetDTO.builder()
                .id(street.getId())
                .cityId(street.getCityId())
                .name(street.getName())
                .area(street.getArea())
                .build();
    }
}
