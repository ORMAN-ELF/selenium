import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BaseRunner {
    //private static ThreadLocal<WebDriver> tl = new ThreadLocal<>();    // специально закомментированный код, см. файл README.md
    WebDriver driver;
    String baseUrl;
    private String browserName = System.getProperty("browser");

    @Before
    public void setUp(){
        driver = getDriver();
        /*if (tl.get() != null) {    // специально закомментированный код, см. файл README.md
            driver = tl.get();
        } else {
            driver = getDriver();
            tl.set(driver);
        }*/
        baseUrl = "https://www.tinkoff.ru/mobile-operator/tariffs/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

       /* Runtime.getRuntime().addShutdownHook(new Thread(() -> {    // специально закомментированный код, см. файл README.md
            driver.quit();
            driver = null;
        }));*/
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    private WebDriver getDriver() {
        try {
            BrowsersFactory.valueOf(System.getProperty("browser"));
        } catch (NullPointerException | IllegalArgumentException e) {
            System.setProperty("browser", browserName);
        }
            return BrowsersFactory.valueOf(browserName).create();
    }
}
