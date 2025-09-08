package api.InfoServiseTests;

import BaseTests.BaseTerminalTest;
import api.models.InfoService.Utilite.TerminalTestsUtilite;
import api.models.InfoService.TerminalAssertance;
import io.qameta.allure.*;
import io.restassured.path.json.JsonPath;
import api.models.InfoService.TerminalSearchRequest;
import api.models.InfoService.TerminalsData;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Epic("API)")
@Feature("Поиск банкоматов, фильтрация")
public class GettingTerminalTests extends BaseTerminalTest {

    @ParameterizedTest(name = "SearchingATMInCity")
    @DisplayName("Поиск терминала по параметру город")
    @ValueSource(strings = {"Москва", "Санкт-Петербург"})
    @Story("A6-2.2")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет корректность поиска банкомата по параметру город")
    public void gettingTerminalByCityParameter(String city) {

        JsonPath response = BaseTerminalTest.searchTerminals(city)
                .then()
                .log().all()
                .extract()
                .jsonPath();

        TerminalAssertance.verifyResponseWithCityParam(response);
        TerminalAssertance.verifyTerminalsStructureList(response.getList("atm", TerminalsData.class));
    }

    @ParameterizedTest
    @DisplayName("Поиск с пагинацией")
    @MethodSource("paginationSizeAndNumber")
    @Story("A6-2.2")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет корректность поиска банкомата c уетом пагинации")
    void testAtmSearchWithPagination(int pageNumber, int pageSize, String city) {

        JsonPath response = BaseTerminalTest.searchTerminalWithPagination(city, pageNumber, pageSize)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .jsonPath();

        TerminalAssertance.verifyPaginationResponse(response, pageNumber, pageSize);

        List<TerminalsData> terminals = response.getList("atm", TerminalsData.class);
        terminals.forEach(TerminalAssertance::verifyTerminalStructure);
    }


    @ParameterizedTest(name = "SearchingAtmWithAllParams: {0}")
    @MethodSource("searchATMAllParamsRequest")
    @DisplayName("Поиск по всем параметрам - различные сценарии")
    @Story("A6-2.2")
    @Severity(SeverityLevel.NORMAL)
    @Description("Тест проверяет поиск банкоматов с использованием всех query-параметров")
    public void fullValidTerminalRequest(String testName, TerminalSearchRequest request) {

        Response apiResponse = TerminalTestsUtilite.executeTerminalSearchWithFullResponse(request,
                ENDPOINT);
        JsonPath response = apiResponse.jsonPath();


        List<TerminalsData> terminals = response.getList("atm", TerminalsData.class);


        assertThat(terminals, not(empty()));
        terminals.forEach(this::verifyTerminalStructure);

        TerminalTestsUtilite.verifyPagination(response, request.getPageNumber(), request.getPageSize());
        TerminalTestsUtilite.verifySorting(terminals, request.getSortBy());
    }
}
