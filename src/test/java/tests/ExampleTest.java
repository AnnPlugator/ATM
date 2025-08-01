package tests;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.ExamplePage;
import static org.junit.jupiter.api.Assertions.*;

public class ExampleTest extends BaseTest {

    @Test
    public void exampleComHeadingShouldBeCorrect() {
        driver.get("http://172.17.1.70:32000/");
        ExamplePage examplePage = new ExamplePage(driver);
        assertEquals("Example Domain", examplePage.getHeadingText());
    }
}
