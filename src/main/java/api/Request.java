package api;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class Request {

    public static final String BASE_URL = "http://172.17.1.70:32005";
    public static final String BASE_PATH = "/info/api/v1/atm/suggest";

    public Response sendingRequest(String city) {
        return given()
                .contentType("application/json")
                .queryParam("city", city)
                .when()
                .get(BASE_URL + BASE_PATH);
    }
}