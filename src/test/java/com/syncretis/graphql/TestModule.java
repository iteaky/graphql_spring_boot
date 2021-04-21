package com.syncretis.graphql;

import com.syncretis.graphql.dto.CityTestDTO;
import com.syncretis.graphql.dto.MallTestDTO;
import com.syncretis.graphql.dto.StreetTestDTO;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class TestModule {

    public static void compareCitiesLists(List<CityTestDTO> actual, List<CityTestDTO> expected) {
        actual.sort(Comparator.comparingLong(CityTestDTO::getId));
        expected.sort(Comparator.comparingLong(CityTestDTO::getId));

        Map<Long, List<CityTestDTO>> cityIdto2CitiesMap = Stream.of(actual, expected)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(CityTestDTO::getId));


        assertThat(expected)
                .usingElementComparatorIgnoringFields("streets")
                .isEqualTo(actual);

        cityIdto2CitiesMap.keySet().
                forEach(cityId -> {
                    List<CityTestDTO> actualToExpected = cityIdto2CitiesMap.get(cityId);
                    List<StreetTestDTO> streets0 = actualToExpected.get(0).getStreets();
                    List<StreetTestDTO> streets1 = actualToExpected.get(1).getStreets();
                    compareStreetsLists(streets0, streets1);
                });
    }

    public static void compareStreetsLists(List<StreetTestDTO> streets0, List<StreetTestDTO> streets1) {
        streets0.sort(Comparator.comparingLong(StreetTestDTO::getId));
        streets1.sort(Comparator.comparingLong(StreetTestDTO::getId));

        assertThat(streets0)
                .usingElementComparatorIgnoringFields("malls")
                .isEqualTo(streets1);

        Map<Long, List<StreetTestDTO>> collect = Stream.of(streets0, streets1).flatMap(Collection::stream)
                .collect(Collectors.groupingBy(StreetTestDTO::getId));
        collect.keySet().
                forEach(streetId -> {
                    List<StreetTestDTO> actualToExpectedStreet = collect.get(streetId);
                    List<MallTestDTO> malls0 = actualToExpectedStreet.get(0).getMalls();
                    List<MallTestDTO> malls1 = actualToExpectedStreet.get(1).getMalls();
                    malls0.sort(Comparator.comparingLong(MallTestDTO::getId));
                    malls1.sort(Comparator.comparingLong(MallTestDTO::getId));
                    assertThat(malls0).isEqualTo(malls1);
                });
    }

    public static List<CityTestDTO> getRealData() {

        List<MallTestDTO> street1 = Arrays.asList(
                new MallTestDTO(4L, "Lenta", 33),
                new MallTestDTO(7L, "KFC", 30),
                new MallTestDTO(3L, "Shinomontaj", 27),
                new MallTestDTO(5L, "Kia", 25));

        List<MallTestDTO> street2 = Arrays.asList(
                new MallTestDTO(2L, "Mashtab", 13),
                new MallTestDTO(6L, "Pulkovo 3", 15),
                new MallTestDTO(1L, "Toyota", 17)
        );

        List<StreetTestDTO> city1 = Arrays.asList(
                new StreetTestDTO(1, "Sheremetevskaya", "Moskovsky", "s5yx73", street2),
                new StreetTestDTO(4, "Nevsky", "Centr", "s5yx73"),
                new StreetTestDTO(6, "Tolmachevskaya", "Moskovsky", "s5yx73"),
                new StreetTestDTO(7, "Pulkovskoe", "Moskovsky", "s5yx73", street1),
                new StreetTestDTO(10, "Vosstaniya", "Centr", "s5yx73"),
                new StreetTestDTO(11, "Mayakovskaya", "Centr", "s5yx73"));
        List<StreetTestDTO> city2 = Arrays.asList(
                new StreetTestDTO(3, "Frunze", "Sovetsky", "s5yx73"),
                new StreetTestDTO(12, "Sibirskaya", "Sovetsky", "s5yx73"),
                new StreetTestDTO(8, "Altayskaya", "Sovetsky", "s5yx73"));
        List<StreetTestDTO> city3 = Arrays.asList(
                new StreetTestDTO(2, "Viktora Gugo", "Leningradsky", "s5yx73"),
                new StreetTestDTO(5, "A. Ahmatovoy", "Leningradsky", "s5yx73"),
                new StreetTestDTO(9, "Admirala Makarova", "Leningradsky", "s5yx73"));


        return Arrays.asList(
                new CityTestDTO(1, "Saint-Petersburg", "Russia", city1),
                new CityTestDTO(2, "Tomsk", "Russia", city2),
                new CityTestDTO(3, "Kalliningrad ", "Russia", city3));
    }
}
