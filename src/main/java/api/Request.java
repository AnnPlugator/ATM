package api;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class Request {

    public static final String BASE_URL = "http://172.17.1.70:32005";

    public Response sendingRequest(String city) {
        return given()
                .contentType("application/json")
                .when()
                .get(BASE_URL + "/info/api/v1/atm/suggest?city=" + city);
    }
}
