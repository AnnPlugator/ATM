package api;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParamTest {
    private final Request request = new Request();

    @ParameterizedTest(name = "Проверка адреса в городе {0}")
    @MethodSource("addressDataProvider")
    @DisplayName("Проверка конкретных адресных полей suggest[0]")
    public void testAddressDetails(String city,
                                   String expectedName,
                                   String expectedStreetType,
                                   String expectedStreet,
                                   String expectedHouse) {

        Response response = request.sendingRequest(city);

        assertEquals(200, response.statusCode(), "Статус кода не 200");
        assertTrue(response.jsonPath().getList("suggests").size() > 0, "Массив 'suggests' пустой");

        assertEquals(expectedName, response.jsonPath().getString("suggests[0].name"), "Имя отличается от ожидаемого");
        assertEquals(expectedStreetType, response.jsonPath().getString("suggests[0].streetType"), "Тип улицы отличается от ожидаемого");
        assertEquals(expectedStreet, response.jsonPath().getString("suggests[0].street"), "Улица отличается от ожидаемой");
        assertEquals(expectedHouse, response.jsonPath().getString("suggests[0].house"), "Дом отличается от ожидаемого");
    }


    private static Stream<Arguments> addressDataProvider() {
        return Stream.of(
                Arguments.of("Санкт-Петербург", "25", "ул.", "2-я Красноармейская", "2/27, лит. А"),
                Arguments.of("Москва", "8", "пр-кт", "Вернадского", "86Б"),
                Arguments.of("Казань", "9", "ул.", "Большая Красная", "29А"),
                Arguments.of("Омск", "11", "пр-кт", "Кирова", "130"),
                Arguments.of("Екатеринбург", "1", "ул.", "Пушкино", "5")
        );

    }
}
