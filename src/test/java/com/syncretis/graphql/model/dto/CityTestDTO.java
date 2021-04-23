package com.syncretis.graphql.model.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "streets")
public class CityTestDTO {
    private long id;
    private String name;
    private String country;
    private List<StreetTestDTO> streets;
}
