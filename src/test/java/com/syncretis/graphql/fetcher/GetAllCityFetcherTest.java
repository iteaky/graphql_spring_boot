package com.syncretis.graphql.fetcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.syncretis.graphql.GraphqlApplication;
import com.syncretis.graphql.TestModule;
import com.syncretis.graphql.config.AuthTestConfig;
import com.syncretis.graphql.dto.CityTestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = GraphqlApplication.class)
@ContextConfiguration(classes = {AuthTestConfig.class})
class GetAllCityFetcherTest {

    @Autowired
    private GraphQLTestTemplate template;

    @Test
    void get() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        GraphQLResponse graphQLResponse = template.postForResource("all_cities.graphql");

        List<CityTestDTO> actual = (List<CityTestDTO>) graphQLResponse.get("$['data']['getAllCities']", List.class).stream()
                .map(it -> objectMapper.convertValue(it, CityTestDTO.class))
                .collect(Collectors.toList());

        List<CityTestDTO> expected = TestModule.getRealData();
        TestModule.compareCitiesLists(actual, expected);


    }



}
