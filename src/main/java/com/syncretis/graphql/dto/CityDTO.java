package com.syncretis.graphql.dto;

import com.syncretis.graphql.entity.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityDTO {
    private long id;
    private String name;
    private String country;
    private List<Long> streetsIds;

    public static CityDTO fromEntity(City city) {
        return CityDTO.builder()
                .id(city.getCityId())
                .name(city.getName())
                .country(city.getCountry())
                .streetsIds(city.getStreets())
                .build();
    }
}
