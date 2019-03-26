import org.openqa.selenium.By;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class Test extends BaseRunner {

    /*@org.junit.Test
    public void testEmptyFields(){
        System.out.println("Тест №1");
        driver.get(baseUrl);

        driver.findElement(By.xpath("//*[contains(@class, 'fio-field')]")).click();
        driver.findElement(By.xpath("//*[contains(@class, 'row_tel')]")).click();
        driver.findElement(By.xpath("//div[@class='ui-form__wrapper']")).click();

        assertEquals("Укажите ваше ФИО",
                driver.findElement(By.xpath("//*[contains(@class, 'fio-field')]//*[contains(@class, 'error-message')]"))
                        .getText());
        assertEquals("Необходимо указать номер телефона",
                driver.findElement(By.xpath("//*[contains(@class, 'row_tel')]//*[contains(@class, 'error-message')]"))
                        .getText());
    }

    @org.junit.Test
    public void testIncorrectData() {
        System.out.println("Тест №2");
        driver.get(baseUrl);

        driver.findElement(By.cssSelector("[name='fio']")).click();
        driver.findElement(By.cssSelector("[name='fio']")).sendKeys("ке");
        driver.findElement(By.cssSelector(".ui-form__wrapper")).click();
        assertEquals("Недостаточно информации. Введите фамилию, имя и отчество через пробел (Например: Иванов Иван Алексеевич)",
                driver.findElement(By.cssSelector(".ui-form__row_dropdownFIO div[class='ui-form-field-error-message ui-form-field-error-message_ui-form']")).getText());
        
        driver.findElement(By.cssSelector("[name='fio']")).click();
        driver.findElement(By.cssSelector("[name='fio']")).clear();
        driver.findElement(By.cssSelector("[name='fio']")).sendKeys("Наталья# Тес Тес");
        driver.findElement(By.cssSelector(".ui-form__wrapper")).click();
        assertEquals("Допустимо использовать только буквы русского алфавита и дефис",
                driver.findElement(By.cssSelector(".ui-form__row_dropdownFIO div[class='ui-form-field-error-message " +
                        "ui-form-field-error-message_ui-form']")).getText());

        driver.findElement(By.cssSelector("[name='fio']")).click();
        driver.findElement(By.cssSelector("[name='fio']")).sendKeys("ке@$%^ @12. Создать maven-строку запуска с возможностью явно указать используемый браузер (Пример строк есть в учебном проекте README.md)");
        driver.findElement(By.cssSelector(".ui-form__wrapper")).click();
        assertEquals("Максимальное количество символов – 133",
                driver.findElement(By.cssSelector(".ui-form__row_dropdownFIO div[class='ui-form-field-error-message " +
                        "ui-form-field-error-message_ui-form']")).getText());

        driver.findElement(By.cssSelector("[name='phone_mobile']")).click();
        driver.findElement(By.cssSelector("[name='phone_mobile']")).sendKeys("+7(789)");
        driver.findElement(By.cssSelector(".ui-form__wrapper")).click();
        assertEquals("Номер телефона должен состоять из 10 цифр, начиная с кода оператора",
                driver.findElement(By.cssSelector(".ui-form__row_tel div[class='ui-form-field-error-message " +
                        "ui-form-field-error-message_ui-form']")).getText());

        driver.findElement(By.cssSelector("[name='phone_mobile']")).click();
        driver.findElement(By.cssSelector("[name='phone_mobile']")).clear();
        driver.findElement(By.cssSelector("[name='phone_mobile']")).sendKeys("+7(789) 686-87-68");
        driver.findElement(By.cssSelector(".ui-form__wrapper")).click();
        assertEquals("Код оператора должен начинаться с цифры 9",
                driver.findElement(By.cssSelector(".ui-form__row_tel div[class='ui-form-field-error-message " +
                        "ui-form-field-error-message_ui-form']")).getText());

        driver.findElement(By.cssSelector("[name='email']")).click();
        driver.findElement(By.cssSelector("[name='email']")).sendKeys("45");
        driver.findElement(By.cssSelector(".ui-form__wrapper")).click();
        assertEquals("Введите корректный адрес эл. почты",
                driver.findElement(By.cssSelector(".ui-form__row_dropdownSuggest div[class='ui-form-field-error-message " +
                        "ui-form-field-error-message_ui-form']:last-child")).getText());

        driver.findElement(By.cssSelector("[name='email']")).click();
        driver.findElement(By.cssSelector("[name='email']")).clear();
        driver.findElement(By.cssSelector("[name='email']")).sendKeys("chuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu@bk.ru");
        driver.findElement(By.cssSelector(".ui-form__wrapper")).click();
        assertEquals("Максимальное количество символов – 50",
                driver.findElement(By.cssSelector(".ui-form__row_dropdownSuggest div[class='ui-form-field-error-message " +
                        "ui-form-field-error-message_ui-form']:last-child")).getText());
    }*/

    @org.junit.Test
    public void testGoogle() throws InterruptedException {
        System.out.println("Тест №3");
        driver.get("https://www.google.ru/");

        driver.findElement(By.xpath("//input[@title='Поиск']")).sendKeys("мобайл тинькофф");
        driver.findElement(By.xpath("//div[@class='sbl1']//span[contains(text(),'мобайл тинькофф')]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Тарифы Тинькофф Мобайла'])[1]/following::cite[1]")).click();
        Thread.sleep(7000);
        ArrayList<String> tabs2 = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        String actualTitle = driver.getTitle();
        String expectedTitle = "Тарифы Тинькофф Мобайла";
        assertEquals(expectedTitle, actualTitle);

        driver.switchTo().window(tabs2.get(0));
        driver.close();
        driver.switchTo().window(tabs2.get(1));
        Thread.sleep(1000);
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://www.tinkoff.ru/mobile-operator/tariffs/" );
    }

    @org.junit.Test
    public void testRegion() throws InterruptedException {
        System.out.println("Тест №4");
        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");
        driver.findElement(By.xpath("//div[@class='DOqnW']")).click();
        driver.findElement(By.xpath("//div[contains(text(),'Москва и Московская обл.')]")).click();
        Thread.sleep(4000);
        assertEquals("Москва и Московская область", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Точки продаж'])[1]/following::div[13]")).getText());
        driver.navigate().refresh();
        Thread.sleep(2000);
        assertEquals("Москва и Московская область", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Точки продаж'])[1]/following::div[13]")).getText());

    }
}
