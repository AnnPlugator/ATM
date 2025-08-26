package api;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
//Zinnur Mubarakshin
public class RequestTest {

    private Request request = new Request();

    @Test
    @DisplayName("Проверка GET запроса по городу Санкт-Петербург")
    public void getRequestCity1() {
        // Отправляем запрос
        Response response = request.sendingRequest("Санкт-Петербург");

        // Проверяем, что статус ответа 200
        assertEquals(200, response.statusCode(), "Статус кода не 200");

        // Проверка, что массив 'suggests' не пустой
        assertTrue(response.jsonPath().getList("suggests").size() > 0, "Массив 'suggests' пустой");

        // Проверка содержимого первого элемента в массиве 'suggests'
        assertEquals("25", response.jsonPath().getString("suggests[0].name"), "Имя отличается от ожидаемого");
        assertEquals("ул.", response.jsonPath().getString("suggests[0].streetType"), "Тип улицы отличается от ожидаемого");
        assertEquals("2-я Красноармейская", response.jsonPath().getString("suggests[0].street"), "Улица отличается от ожидаемой");
        assertEquals("2/27, лит. А", response.jsonPath().getString("suggests[0].house"), "Дом отличается от ожидаемого");
    }

    @Test
    @DisplayName("Проверка GET запроса по городу Москва")
    public void getRequestCity2() {

        Response response = request.sendingRequest("Москва");
        assertEquals(200, response.statusCode(), "Статус кода не 200");
        assertTrue(response.jsonPath().getList("suggests").size() > 0, "Массив 'suggests' пустой");

        assertEquals("25", response.jsonPath().getString("suggests[0].name"), "Имя отличается от ожидаемого");
        assertEquals("ул.", response.jsonPath().getString("suggests[0].streetType"), "Тип улицы отличается от ожидаемого");
        assertEquals("2-я Красноармейская", response.jsonPath().getString("suggests[0].street"), "Улица отличается от ожидаемой");
        assertEquals("2/27, лит. А", response.jsonPath().getString("suggests[0].house"), "Дом отличается от ожидаемого");
    }
}
