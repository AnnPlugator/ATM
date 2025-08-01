package tests.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AtmSearchApiTest {

    @Test
    public void testAtmSearchByCityAndQuery() {
        Response response = RestAssured
                .given()
                .baseUri("http://172.17.1.70:32005")
                .basePath("/info/api/v1/atm/search")
                .queryParam("searchQuery", "Арбатская")
                .queryParam("city", "Москва")
                .when()
                .get()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .response();

        // Проверка поля count
        int count = response.jsonPath().getInt("count");
        assertThat("Ожидается 4 банкомата", count, equalTo(4));

        // Проверка размера списка atm
        List<Map<String, Object>> atms = response.jsonPath().getList("atm");
        assertThat("Список банкоматов должен содержать 4 элемента", atms.size(), equalTo(4));

        // Проверка, что у всех банкоматов метро "Арбатская"
        atms.forEach(atm -> {
            String metro = (String) atm.get("metroStation");
            assertThat("Ожидается метро Арбатская", metro, equalTo("Арбатская"));
        });
    }
}
