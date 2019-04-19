import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

class PricePackage {

    String getPackagePrice(WebDriver driver){
        return driver.findElement(By.xpath("//h3[contains(text(),'Общая цена:')]"))
                .getText()
                .replace("Общая цена: ", "")
                .replace(" \u20BD","");
    }
}
