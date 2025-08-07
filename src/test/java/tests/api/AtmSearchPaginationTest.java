package tests.api;

import api.models.Atm;
import api.models.AtmResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AtmSearchPaginationTest {

    @DisplayName("Проверка пагинации")
    @Test
    public void testAtmPaginationPage1() {
        int pageSize = 5;
        int pageNumber = 1;

        Response response = RestAssured
                .given()
                .baseUri("http://172.17.1.70:32005")
                .basePath("/info/api/v1/atm/search")
                .queryParam("city", "Москва")
                .queryParam("pageNumber", pageNumber)
                .queryParam("pageSize", pageSize)
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .response();

        AtmResponse atmResponse = response.as(AtmResponse.class);

        // Проверка параметров пагинации
        assertThat(atmResponse.getPageNumber(), equalTo(pageNumber));
        assertThat(atmResponse.getPageSize(), equalTo(pageSize));

        // Проверка, что количество банкоматов не превышает pageSize
        List<Atm> atms = atmResponse.getAtm();
        assertThat(atms.size(), lessThanOrEqualTo(pageSize));

        // Проверка, что count >= фактическому количеству atm на странице
        assertThat(atmResponse.getCount(), greaterThanOrEqualTo(atms.size()));

        // Дополнительно: проверим, что в каждом банкомате указано метро
        atms.forEach(atm -> {
            assertThat("Поле metroStation не должно быть пустым",
                    atm.getMetroStation(), not(isEmptyOrNullString()));
        });
    }
}