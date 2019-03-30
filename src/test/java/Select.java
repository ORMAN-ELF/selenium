import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Реализовано в соответствие с дз, пока нигде не используется
public class Select {

    void selectFromList(WebDriver driver, String nameElement, String nameField, Integer numberRecord){
        //  выбор значения из списка
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@" + nameElement + "='" + nameField + "']/option[" + numberRecord + "]")));
        driver.findElement(By.xpath("//select[@" + nameElement + "='" + nameField + "']/option[" + numberRecord + "]")).click();
    }

    String getValueList(WebDriver driver, String nameElement, String nameField){
        //получения текущего значения списка
        return driver.findElement(By.xpath("//select[@" + nameElement + "='" + nameField + "']/following::div[1]/div/div")).getText();
    }
}
