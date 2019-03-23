import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BaseRunner {

    WebDriver driver;
    String baseUrl;
    private String browserName = System.getProperty("browser");

    @Before
    public void setUp(){
        driver = getDriver();
        baseUrl = "https://www.tinkoff.ru/mobile-operator/tariffs/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public void tearDown(){
        driver.quit();
    }


    private WebDriver getDriver() {
        try {
            BrowsersFactory.valueOf(System.getProperty("browser"));
        } catch (NullPointerException | IllegalArgumentException e) {
            browserName = randomBrowserPicker();
            System.setProperty("browser", browserName);
        }
        return BrowsersFactory.valueOf(browserName).create();
    }
    private String randomBrowserPicker() {
        System.out.println("\nThe driver is not set. Running a random browser...");
        int pick = new Random().nextInt(BrowsersFactory.values().length);
        return BrowsersFactory.values()[pick].toString();
    }
}
