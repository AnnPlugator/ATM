package api.helpers;

import api.models.AtmResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;

public class AtmApiHelper {

    private static final String BASE_URI = "http://172.17.1.70:32005";
    private static final String ATM_SEARCH_PATH = "/info/api/v1/atm/search";
    private static final String ATM_INFO_SUGGEST = "/info/api/v1/atm/suggest";

    public static AtmResponse searchAtms(String city, String searchQuery) {
        return RestAssured
                .given()
                .baseUri(BASE_URI)
                .basePath(ATM_SEARCH_PATH)
                .queryParam("city", city)
                .queryParam("searchQuery", searchQuery)
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .as(AtmResponse.class);
    }
    public static Map<String, Object> buildSuggestParams(String city, String searchQuery,
                                                         Boolean cashWithdraw,
                                                         Boolean cashDeposit,
                                                         Boolean acceptPayments,
                                                         Boolean moneyTransfer,
                                                         Boolean nfc,
                                                         Boolean is24Hour) {
        Map<String, Object> params = new HashMap<>();

        if (city != null) params.put("city", city);
        if (searchQuery != null) params.put("searchQuery", searchQuery);
        if (cashWithdraw != null) params.put("cashWithdraw", cashWithdraw);
        if (cashDeposit != null) params.put("cashDeposit", cashDeposit);
        if (acceptPayments != null) params.put("acceptPayments", acceptPayments);
        if (moneyTransfer != null) params.put("moneyTransfer", moneyTransfer);
        if (nfc != null) params.put("nfc", nfc);
        if (is24Hour != null) params.put("is24Hour", is24Hour);

        return params;
    }
}
