package api.models.InfoService.Utilite;

import api.models.InfoService.TerminalSearchRequest;
import io.restassured.path.json.JsonPath;
import api.models.InfoService.TerminalsData;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

public final class TerminalTestsUtilite {

    private TerminalTestsUtilite(){
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
    public static void verifyPagination(JsonPath response, int expectedPageNumber, int expectedPageSize) {
        assertThat(response.getInt("count"), greaterThanOrEqualTo(0));
        assertThat(response.getInt("pageNumber"), equalTo(expectedPageNumber));
        assertThat(response.getInt("pageSize"), equalTo(expectedPageSize));
    }

    public static void verifySorting(List<TerminalsData> terminals, String sortField) {
        if (terminals.size() > 1) {
            List<String> actualValues = terminals.stream()
                    .map(t -> getFieldValue(t, sortField))
                    .collect(Collectors.toList());

            List<String> sortedValues = new ArrayList<>(actualValues);
            sortedValues.sort(String.CASE_INSENSITIVE_ORDER);

            assertThat("ATM should be sorted by " + sortField, actualValues, equalTo(sortedValues));
        }
    }

    private static String getFieldValue(TerminalsData terminal, String field) {
        switch (field) {
            case "name": return terminal.getName();
            default: throw new IllegalArgumentException("Unknown sort field: " + field);
        }
    }

    public static Response executeTerminalSearchWithFullResponse(TerminalSearchRequest request, String endpoint) {
        return given()
                .queryParam("city", request.getCity())
                .queryParam("cashWithdraw", request.isCashWithdraw())
                .queryParam("cashDeposit", request.isCashDeposit())
                .queryParam("acceptPayments", request.isAcceptPayments())
                .queryParam("nfc", request.isNfc())
                .queryParam("is24Hour", request.isIs24Hour())
                .queryParam("searchQuery", request.getSearchQuery())
                .queryParam("pageNumber", request.getPageNumber())
                .queryParam("pageSize", request.getPageSize())
                .queryParam("sortBy", request.getSortBy())
                .when()
                .get(endpoint);
    }

}
