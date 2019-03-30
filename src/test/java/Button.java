import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

class Button{

    void clickButton(WebDriver driver, String path){
        driver.findElement(By.xpath(path)).click();
    }
}
