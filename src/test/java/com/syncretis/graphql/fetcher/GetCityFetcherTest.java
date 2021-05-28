package com.syncretis.graphql.fetcher;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.syncretis.graphql.GraphqlApplication;
import com.syncretis.graphql.TestModule;
import com.syncretis.graphql.config.AuthTestConfig;
import com.syncretis.graphql.dto.CityTestDTO;
import com.syncretis.graphql.dto.StreetTestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static com.syncretis.graphql.TestModule.getRealData;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = GraphqlApplication.class)
@ContextConfiguration(classes = {AuthTestConfig.class})
class GetCityFetcherTest {

    @Autowired
    private GraphQLTestTemplate template;

    @Test
    void get() throws IOException, URISyntaxException {
        String resourceAsString = this.getResourceAsString("city_payload.txt");

        List<CityTestDTO> expected = getRealData();

        expected
                .forEach(it -> {
                    String format = String.format(resourceAsString, it.getId());
                    GraphQLResponse response = template.post(format);
                    CityTestDTO actual = response.get("$['data']['getCity']", CityTestDTO.class);
                    assertThat(it).isEqualTo(actual);
                    TestModule.compareStreetsLists(it.getStreets(), actual.getStreets());
                });
    }

    protected String getResourceAsString(String name) throws IOException, URISyntaxException {
        return new String(Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource(name).toURI())));
    }
    }
