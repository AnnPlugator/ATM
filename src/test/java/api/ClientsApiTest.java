package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ClientsApiTest {

    private final String BASE_URL = "http://172.17.1.70:32015";
    private final String TOKEN = "Bearer вставь_сюда_твой_токен";

    @Test
    public void shouldCreateClientSuccessfully() {
        String body = """
                    {
                      "firstName": "Анна",
                      "lastName": "Тестова",
                      "email": "anna.test.%s@mail.ru",
                      "phoneNumber": "+79001112233",
                      "passportNumber": "1234567890",
                      "snils": "123-456-789 00"
                    }
                """.formatted(System.currentTimeMillis()); // Уникальный email на каждый запуск

        Response response = given()
                .baseUri(BASE_URL)
                .header("Authorization", TOKEN)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/api/v1/clients");

        response.then().statusCode(201);

        System.out.println("Client created: " + response.getBody().asString());
    }
}
