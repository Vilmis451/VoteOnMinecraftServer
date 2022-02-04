import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SeleniumTest {
    String text;
    String username;

    @BeforeMethod
    public void setup() {
        Selenium.setup();
    }

    @AfterMethod
    public void close() {
        Selenium.close();
    }

    //Test just to see if you can vote again
    // Expect to fail, if 24 hours haven't passed after your fist vote
    @Test
    public void voteExpectingToFail() {
        text = "m craft";
        username = "ChicoMono"; //Change username if wanted
        Selenium.searchByKeyword(text);
        Selenium.openLink();
        Selenium.openServer();
        Selenium.vote();
        Selenium.robotTest(username);
        Selenium.ultimateTest();
        Assert.assertEquals(Selenium.finalClick(), false);
    }
}
