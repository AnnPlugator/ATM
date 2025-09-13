package api;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;

public class RequestTest {

    private final Request request = new Request();

    @Test
    @DisplayName("GET запрос по поиску банкомата в городе Санкт-Петербург")
    public void getRequestCity1() {
        Response response = request.sendingRequest("Санкт-Петербург");

        assertThat(response.statusCode())
                .as("Статус кода не 200")
                .isEqualTo(200);

        assertThat(response.jsonPath().getList("suggests"))
                .as("Массив 'suggests' пустой")
                .isNotEmpty();
    }

    @Test
    @DisplayName("GET запрос по поиску банкомата в городе Москва")
    public void getRequestCity2() {
        Response response = request.sendingRequest("Москва");

        assertThat(response.statusCode())
                .as("Статус кода не 200")
                .isEqualTo(200);

        assertThat(response.jsonPath().getList("suggests"))
                .as("Массив 'suggests' пустой")
                .isNotEmpty();
    }

    @Test
    @DisplayName("GET запрос по поиску банкомата в городе Казань")
    public void getRequestCity3() {
        Response response = request.sendingRequest("Казань");

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getList("suggests")).isNotEmpty();
    }

    @Test
    @DisplayName("GET запрос по поиску банкомата в городе Омск")
    public void getRequestCity4() {
        Response response = request.sendingRequest("Омск");

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getList("suggests")).isNotEmpty();
    }

    @Test
    @DisplayName("GET запрос по поиску банкомата в городе Екатеринбург")
    public void getRequestCity5() {
        Response response = request.sendingRequest("Екатеринбург");

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getList("suggests")).isNotEmpty();
    }

    @Test
    @DisplayName("Негативный GET запрос по поиску банкомата без указания города")
    public void getRequestCity6() {
        Response response = request.sendingRequest("");

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getList("suggests")).isNotEmpty(); // ← если так и должно быть
    }
}






