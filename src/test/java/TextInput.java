import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

class TextInput {

    void fillingTheField(WebDriver driver, String nameElement, String nameField, String setValue){
        driver.findElement(By.xpath("//input[@" + nameElement + "='" + nameField + "']"))
                .sendKeys(setValue);
    }

    String getValue(WebDriver driver, String nameElement, String nameField){

        // Реализовано из-за пункта в дз. В тестах метод пока не применяется
        return driver.findElement(By.xpath("//[" + nameElement + "='" + nameField + "']"))
                .getAttribute("value");
    }

    void getErrorField(WebDriver driver, String actualResult, String nameField){
        assertEquals( actualResult,
                driver.findElement(By.xpath("//*[contains(@class, '" + nameField +
                        "')]//*[contains(@class, 'error-message')]"))
                        .getText());
    }

}
