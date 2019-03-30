import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Test extends BaseRunner {

    @org.junit.Test
    public void testEmptyFields(){
        System.out.println("Тест №1");
        driver.get(baseUrl);

        clickButton("//*[contains(@class, 'fio-field')]");
        clickButton("//*[contains(@class, 'row_tel')]");
        clickButton("//div[@class='ui-form__wrapper']");

        getErrorField("Укажите ваше ФИО", "fio-field");
        getErrorField("Необходимо указать номер телефона", "row_tel");
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
    }

    @org.junit.Test
    public void testGoogle() throws InterruptedException {
        System.out.println("Тест №3");
        driver.get("https://www.google.ru/");
        Thread.sleep(3000);
        fillingTheField("title", "Поиск", "мобайл тинькофф");
        clickButton("//div[@class='sbl1']//span[contains(text(),'мобайл тинькофф')]");
        clickButton("(.//*[normalize-space(text()) and normalize-space(.)='Тарифы Тинькофф Мобайла'])[1]/following::cite[1]");
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
        Thread.sleep(1000);
        clickButton("//div[@class='MvnoRegionConfirmation__wrapper_1Jmmm MvnoRegionConfirmation__wrapperSelected_uxhv3']");
        clickButton("//div[contains(text(),'Москва и Московская обл.')]");
        Thread.sleep(2000);

        assertEquals("Москва и Московская область", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Точки продаж'])[1]/following::div[13]")).getText());
        driver.navigate().refresh();
        Thread.sleep(2000);

        assertEquals("Москва и Московская область", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Точки продаж'])[1]/following::div[13]")).getText());
        String priceMoscow = getPackagePrice();
        clickButton("//span[contains(text(),'Интернет')]");
        clickButton("//span[contains(text(),'Безлимитный интернет')]");
        clickButton("//span[contains(text(),'Звонки')]");
        clickButton("//span[contains(text(),'Безлимитные минуты')]");
        clickButton("//label[contains(text(),'Режим модема')]");
        clickButton("//label[contains(text(),'Безлимитные СМС')]");

        String priceFullPackageMoscow = getPackagePrice();
        clickButton("//div[@class='MvnoRegionConfirmation__wrapper_1Jmmm MvnoRegionConfirmation__wrapperSelected_uxhv3']");
        clickButton("//div[contains(text(),'Краснодарский кр.')]");
        Thread.sleep(1000);

        String priceKrasnodar = getPackagePrice();
        clickButton("//span[contains(text(),'Интернет')]");
        clickButton("//span[contains(text(),'Безлимитный интернет')]");
        Thread.sleep(1000);
        clickButton("//span[contains(text(),'Звонки')]");
        Thread.sleep(1000);
        clickButton("//span[contains(text(),'Безлимитные минуты')]");
        clickButton("//label[contains(text(),'Режим модема')]");
        clickButton("//label[contains(text(),'Безлимитные СМС')]");

        String priceFullPackageKrasnodar = getPackagePrice();
        if(priceMoscow.equals(priceKrasnodar)){
            System.out.println("Суммы общей цены тарифа с выбранными пакетами и сервисами по дефолту для регионов Москва и Краснодар одинаковые");
        } else {
            System.out.println("Суммы общей цены тарифа с выбранными пакетами и сервисами по дефолту для регионов Москва и Краснодар разные");
        }
        if(priceFullPackageMoscow.equals(priceFullPackageKrasnodar)){
            System.out.println("Суммы общей цены тарифа с максимальными выбранными пакетами и сервисами для регионов Москва и Краснодар одинаковые");
        } else {
            System.out.println("суммы общей цены тарифа с максимальными выбранными пакетами и сервисами для регионов Москва и Краснодар одинаковые");
        }
    }

    @org.junit.Test
    public void notActiveButton() throws InterruptedException {

        System.out.println("Тест №5");
        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");

        clickButton("//span[text() ='Да']");
        clickButton("//div[@class='MvnoRegionConfirmation__title_DOqnW']");
        clickButton("//div[contains(text(),'Москва и Московская обл.')]");
        Thread.sleep(1000);

        clickButton("//span[contains(text(),'Интернет')]");
        Thread.sleep(1000);

        clickButton("//span[contains(text(),'0 ГБ')]");
        Thread.sleep(1000);

        clickButton("//span[contains(text(),'Звонки')]");
        Thread.sleep(1000);

        clickButton("//span[text() = '0 минут']");
        Thread.sleep(1000);

        clickButton("//label[contains(text(),'Мессенджеры')]");
        clickButton("//label[contains(text(),'Социальные сети')]");
        String priceZero = getPackagePrice();

        clickButton("//*[contains(@class, 'fio-field')]");
        fillingTheField("name","fio", "Тест Тест Тест");
        clickButton("//*[contains(@class, 'row_tel')]");
        fillingTheField("name","phone_mobile", "9896868768");

        Thread.sleep(1000);
        if(Integer.parseInt(priceZero) == 0){
            clickButton("//div[@class='LoaderRound__container_no-background_GvpfD LoaderRound__container_coverParent_2-_fi']");
        } else {
            System.out.println("Цена пакета не равна нулю");
        }
        Thread.sleep(1000);

        clickButton("//span[contains(text(),'Заказать доставку')]");
        Thread.sleep(1000);

        if(driver.findElement(By.xpath("//div[contains(text(),'Укажите район или город')]")) != null){
            System.out.println("Кнопка активна");
        }else {
            System.out.println("Кнопка нективна");
        }
    }

    private String getPackagePrice(){
        return driver.findElement(By.xpath("//h3[contains(text(),'Общая цена:')]"))
                .getText()
                .replace("Общая цена: ", "")
                .replace(" \u20BD","");
    }


        // Для класса TextInput
        private void fillingTheField(String nameElement, String nameField, String setValue){
            driver.findElement(By.xpath("//input[@" + nameElement + "='" + nameField + "']")).sendKeys(setValue);
        }

        String getValue(String nameElement, String nameField){
            // Реализовано из-за пункта в дз. В тестах метод пока не применяется
            return driver.findElement(By.xpath("//[" + nameElement + "='" + nameField + "']")).getAttribute("value");
        }

        private void getErrorField(String actualResult, String nameField){
            assertEquals( actualResult,
                    driver.findElement(By.xpath("//*[contains(@class, '" + nameField + "')]//*[contains(@class, 'error-message')]"))
                            .getText());
        }


        // Для класса Button
        private void clickButton(String path){
            driver.findElement(By.xpath(path)).click();
        }


        //Для класса Select
        void selectFromList(String nameElement, String nameField, Integer numberRecord){
            //  выбор значения из списка
            WebDriverWait wait = new WebDriverWait(driver,30);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@" + nameElement + "='" + nameField + "']/option[" + numberRecord + "]")));
            driver.findElement(By.xpath("//select[@" + nameElement + "='" + nameField + "']/option[" + numberRecord + "]")).click();
        }

        String getValueList(String nameElement, String nameField){
            //получения текущего значения списка
            return driver.findElement(By.xpath("//select[@" + nameElement + "='" + nameField + "']/following::div[1]/div/div")).getText();
        }


        //для класса checkBox
        private String getTextCheckBox(Integer numberCheckBox){
            //получение текста чек-бокса по его номеру на странице
            List<WebElement> list = driver.findElements(By.xpath("//label[@class='CheckboxWithDescription__checkboxTitle_1a3dy']"));
            return list.get(numberCheckBox).getText();
        }

        private String stateCheckBox(String nameCheckBox){
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
