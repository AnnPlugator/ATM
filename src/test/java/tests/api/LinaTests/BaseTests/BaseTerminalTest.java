package BaseTests;

import api.models.InfoService.InfoServiceSpecifications;
import io.restassured.response.Response;
import api.models.InfoService.TerminalsData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BaseTerminalTest {

    protected final static String URL = "http://172.17.1.70:32005/";
    protected final static String ENDPOINT = "info/api/v1/atm/search";

    public static Response searchTerminalWithPagination(String city, int pageNumber, int pageSize) {
        return given()
                .baseUri(URL)
                .queryParam("city", city)
                .queryParam("pageNumber", pageNumber)
                .queryParam("pageSize", pageSize)
                .when()
                .get(ENDPOINT);
    }

    public static Response searchTerminals(String city) {
        return given()
                .baseUri(URL)
                .queryParam("city", city)
                .when()
                .get(ENDPOINT);
    }

    static Stream<Arguments> searchingByCity(){
        return Stream.of(
                Arguments.of("Москва")
        );
    }

    static Stream<Arguments> paginationSizeAndNumber() {
        return Stream.of(
                Arguments.of(1, 5, "а"),
                Arguments.of(2, 5, "а"),
                Arguments.of(3, 2, "а"),
                Arguments.of(4, 1, "а")
        );
    }

    static Stream<Arguments> searchATMAllParamsRequest(){
        return Stream.of(
                Arguments.of("Москва - все сервисы", createMoscowAllServicesRequest()),
                Arguments.of("Москва - только снятие", createMoscowCashWithdrowOnlyRequest()),
                Arguments.of("СПб - круглосуточно", createSpb24hRequest())
        );

    }

    @BeforeEach
    public void setup() {
        InfoServiceSpecifications.usingSpecification(
                InfoServiceSpecifications.requestSpec(URL),
                InfoServiceSpecifications.responseSpecOk200()
        );
    }

    protected void verifyTerminalStructure(TerminalsData terminal) {
        assertThat(terminal.getName(), not(emptyOrNullString()));
        assertThat(terminal.getStreet(), not(emptyOrNullString()));
        assertThat(terminal.getHouse(), not(emptyOrNullString()));
        assertThat(terminal.getAtmLatitude(), notNullValue());
        assertThat(terminal.getAtmLongitude(), notNullValue());
    }
}
