package tests.ui;

import org.junit.jupiter.api.Test;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginWithInvalidCredentials() {
        // Открываем страницу логина
        driver.get("http://172.17.1.70:32000/");

        // Создаём объект страницы логина
        LoginPage loginPage = new LoginPage(driver);

        // Выполняем действия
        loginPage.enterUsername("wrong_user");
        loginPage.enterPassword("wrong_password");
        loginPage.clickLogin();

        // Проверка сообщения об ошибке
        String actualError = loginPage.getErrorMessage();
        assertEquals("Invalid credentials", actualError, "Сообщение об ошибке неверное");
    }
}
