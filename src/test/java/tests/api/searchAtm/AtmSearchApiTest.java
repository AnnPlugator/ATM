package tests.api.searchAtm;

import api.helpers.AtmApiHelper;
import api.models.AtmResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AtmSearchApiTest {

    @BeforeAll
    public static void setup() {
        baseURI = "http://172.17.1.70:32005";
        basePath = "/info/api/v1/atm/search";
    }

    @DisplayName("Получение банкоматов по городу и поисковому запросу")
    @Test
    public void testSearchAtmsByCityAndQuery() {
        AtmResponse response = AtmApiHelper.searchAtms("Москва", "Арбатская");

        assertThat(response.getCount(), equalTo(4));
        assertThat(response.getAtm().size(), equalTo(4));
        response.getAtm().forEach(atm ->
                assertThat(atm.getMetroStation(), equalTo("Арбатская"))
        );
    }
}