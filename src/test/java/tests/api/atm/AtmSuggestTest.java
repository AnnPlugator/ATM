package tests.api.atm;

import api.helpers.AtmApiHelper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;
import java.util.Map;

public class AtmSuggestTest {
    @Test
    @DisplayName("Фильтр по nfc = true")
    void testSuggestWithNfcFilter() {
        Map<String, Object> params = AtmApiHelper.buildSuggestParams(
                "Москва", "Воз",
                null, null, null, null,
                true, null);

        RestAssured
                .given()
                .baseUri("http://172.17.1.70:32005")
                .basePath("/info/api/v1/atm/suggest")
                .queryParams(params)
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200)
                .body("suggests", not(empty()));
    }
    @Test
    @DisplayName("Позитивный сценарий — возвращаются подсказки")
    void testAtmSuggestWithValidCityAndQuery() {
        Map<String, Object> params = AtmApiHelper.buildSuggestParams(
                "Москва", "Вер", null, null, null, null,
                null, null);
        RestAssured
                .given()
                .baseUri("http://172.17.1.70:32005")
                .basePath("/info/api/v1/atm/suggest")
                .queryParams(params)
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200)
                .body("suggests", not(empty()));

    }
}

