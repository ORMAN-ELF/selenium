import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

// Реализовано в соответствие с дз, пока нигде не используется
public class CheckBox {

    private String getTextCheckBox(WebDriver driver, Integer numberCheckBox){
        //получение текста чек-бокса по его номеру на странице
        List<WebElement> list = driver.findElements(By.xpath("//label[@class='CheckboxWithDescription__checkboxTitle_1a3dy']"));
        return list.get(numberCheckBox).getText();
    }

    private String stateCheckBox(WebDriver driver, String nameCheckBox){
        //получение текущего значения чек-бокса по имени
        if (driver.findElements(By.xpath("//div[@class='Checkbox__container_AZX42 Checkbox__container_size_l_3He3r']/" +
                "following::label[contains(text(),'" + nameCheckBox + "')]")) != null){
            return "Галочка не установлена";
        } else if (driver.findElements(By.xpath("//div[@class='Checkbox__container_AZX42 Checkbox__container_checked_3yg5S Checkbox__container_size_l_3He3r']/" +
                "following::label[contains(text(),'" + nameCheckBox + "')]")) != null){
            return "Галочка установлена";
        }else {
            return "Чек-бокс не найден";
        }
    }
}
