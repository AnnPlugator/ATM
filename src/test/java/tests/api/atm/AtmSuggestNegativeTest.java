package tests.api.atm;

import api.helpers.AtmApiHelper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;
import java.util.Map;

public class AtmSuggestNegativeTest {
    @Test
    @DisplayName("Получение списка подсказок только с searchQuery")
    void testSuggestWithoutCity() {
        Map<String, Object> params = AtmApiHelper.buildSuggestParams(
                null, "Мира", null, null, null, null,
                null, null);
        RestAssured
                .given()
                .baseUri("http://172.17.1.70:32005")
                .basePath("info/api/v1/atm/suggest")
                .params(params)
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(400)
                .body("suggests", not(empty()));

    }
    @Test
    @DisplayName("Проверка на SQL-инъекцию в параметре city")
    void testSuggestWithSqlInjectionInCity() {
        Map<String, Object> params = AtmApiHelper.buildSuggestParams("'; DROP TABLE users; --", "Мира", null, null, null, null,
                null, null);
        RestAssured
                .given()
                .baseUri("http://172.17.1.70:32005")
                .basePath("info/api/v1/atm/suggest")
                .params(params)
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200)
                .body("suggests", (empty()));
}
}
