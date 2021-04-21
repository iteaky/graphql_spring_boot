package com.syncretis.graphql.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class MallTestDTO {
    private Long id;
    private String name;
    private Integer building;
}
