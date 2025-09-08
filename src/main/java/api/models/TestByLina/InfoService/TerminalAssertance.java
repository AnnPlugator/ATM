package api.models.InfoService;

import io.restassured.path.json.JsonPath;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import java.util.List;

public class TerminalAssertance {

    public static void verifyPaginationResponse(JsonPath response, int expectedPageNumber, int expectedPageSize) {
        MatcherAssert.assertThat(response.getInt("count"), Matchers.notNullValue());
        MatcherAssert.assertThat(response.getInt("pageNumber"), Matchers.equalTo(expectedPageNumber));
        MatcherAssert.assertThat(response.getInt("pageSize"), Matchers.equalTo(expectedPageSize));
        MatcherAssert.assertThat(response.getList("atm"), Matchers.hasSize(Matchers.lessThanOrEqualTo(expectedPageSize)));
    }

    public static void verifyTerminalStructure(TerminalsData terminal) {
        MatcherAssert.assertThat(terminal.getName(), Matchers.not(Matchers.emptyOrNullString()));
        MatcherAssert.assertThat(terminal.getStreet(), Matchers.not(Matchers.emptyOrNullString()));
        MatcherAssert.assertThat(terminal.getHouse(), Matchers.not(Matchers.emptyOrNullString()));
        MatcherAssert.assertThat(terminal.getAtmLatitude(), Matchers.notNullValue());
        MatcherAssert.assertThat(terminal.getAtmLongitude(), Matchers.notNullValue());
    }

    public static void verifyResponseWithCityParam(JsonPath response) {
        MatcherAssert.assertThat(response.getInt("count"), Matchers.notNullValue());
        MatcherAssert.assertThat(response.getList("atm"), Matchers.hasSize(Matchers.greaterThan(0)));

    }

    public static void verifyTerminalsStructureList(List<TerminalsData> terminals) {
        terminals.forEach(terminal -> {
            MatcherAssert.assertThat(terminal.getName(), Matchers.not(Matchers.emptyOrNullString()));
            MatcherAssert.assertThat(terminal.getStreet(), Matchers.not(Matchers.emptyOrNullString()));
            MatcherAssert.assertThat(terminal.getHouse(), Matchers.not(Matchers.emptyOrNullString()));
            MatcherAssert.assertThat(terminal.getAtmLatitude(), Matchers.notNullValue());
            MatcherAssert.assertThat(terminal.getAtmLongitude(), Matchers.notNullValue());
        });
    }
}

