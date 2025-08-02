package api.helpers;

import api.models.AtmResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class AtmApiHelper {

    private static final String BASE_URI = "http://172.17.1.70:32005";
    private static final String ATM_SEARCH_PATH = "/info/api/v1/atm/search";

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
}