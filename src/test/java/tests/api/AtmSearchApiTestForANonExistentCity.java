package tests.api;

import api.models.AtmResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AtmSearchApiTestForANonExistentCity {
    @DisplayName("Несуществующий город")
    @Test
    public void testAtmSearchWithInvalidCity() {
        Response response = RestAssured
                .given()
                .baseUri("http://172.17.1.70:32005")
                .basePath("/info/api/v1/atm/search")
                .queryParam("city", "Готэм")
                .queryParam("searchQuery", "")
                .when()
                .get()
                .then()
                .statusCode(200) // или 204, если API так устроено
                .extract()
                .response();

        AtmResponse atmResponse = response.as(AtmResponse.class);
        assertThat("Банкоматы не должны быть найдены", atmResponse.getCount(), equalTo(0));
        assertThat("Список банкоматов должен быть пустым", atmResponse.getAtm(), empty());
    }
}
