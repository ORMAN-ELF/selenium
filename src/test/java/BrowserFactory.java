import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;


enum  BrowsersFactory {
    chrome {
        public WebDriver create() {
            updateProperty("chrome");
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            return  new ChromeDriver(options);
        }
    },

    firefox {
        public WebDriver create() {
            updateProperty("firefox");
            System.setProperty("webdriver.gecko.driver", "drivers/geckodriver");
            System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "./src/test/java/log");

            FirefoxOptions options = new FirefoxOptions();
            options.addPreference("dom.webnotifications.enabled", false);
            return new FirefoxDriver(options);
        }
    },

    opera {
        public WebDriver create() {
            updateProperty("opera");
            System.setProperty("webdriver.opera.driver", "drivers/operadriver");
            OperaOptions options = new OperaOptions();
            return new OperaDriver(options);
        }
    };

    public WebDriver create() {
        return null;
    }

    void updateProperty(String browserName) {
        System.out.println(String.format("\nstarting %s-browser......", browserName));
        if (System.getProperty("browser") == null) System.setProperty("browser", browserName);
    }
}