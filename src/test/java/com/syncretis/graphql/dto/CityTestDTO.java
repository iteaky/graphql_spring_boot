package com.syncretis.graphql.dto;

import lombok.*;

import java.util.List;
import java.util.Objects;

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
