package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateClientTest extends BaseApiTest {

    @Test
    public void createClientShouldReturn201() {
        String randomPassport = UUID.randomUUID().toString().substring(0, 10);

        String requestBody = "{\n" +
                "  \"firstName\": \"Анна\",\n" +
                "  \"lastName\": \"Тестова\",\n" +
                "  \"passportNumber\": \"" + randomPassport + "\",\n" +
                "  \"email\": \"anna" + randomPassport + "@test.com\",\n" +
                "  \"phoneNumber\": \"79001234567\",\n" +
                "  \"snils\": \"" + randomPassport + "\"\n" +
                "}";

        Response response = given()
                .header("Authorization", "Bearer your_token_here") // замени на рабочий токен
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/v1/clients")
                .then()
                .extract()
                .response();

        assertEquals(201, response.statusCode(), "Клиент не был создан");
    }
}
