import org.openqa.selenium.By;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class Test extends BaseRunner {

    private Button button = new Button();
    private TextInput textInput = new TextInput();

    @org.junit.Test
    public void testEmptyFields() throws InterruptedException {
        System.out.println("Тест №1");
        driver.get(baseUrl);

        button.clickButton(driver,"//*[contains(@class, 'fio-field')]");
        button.clickButton(driver,"//*[contains(@class, 'row_tel')]");
        button.clickButton(driver,"//div[@class='ui-form__wrapper']");
        Thread.sleep(1000);
        textInput.getErrorField(driver,"Укажите ваше ФИО", "fio-field");
        textInput.getErrorField(driver,"Необходимо указать номер телефона", "row_tel");
    }

    @org.junit.Test
    public void testIncorrectData() {
        System.out.println("Тест №2");
        driver.get(baseUrl);

        driver.findElement(By.cssSelector("[name='fio']")).click();
        driver.findElement(By.cssSelector("[name='fio']")).sendKeys("ке");
        driver.findElement(By.cssSelector(".ui-form__wrapper")).click();
        assertEquals("Недостаточно информации. Введите фамилию, имя и отчество через пробел (Например: Иванов Иван Алексеевич)",
                driver.findElement(By.cssSelector(".ui-form__row_dropdownFIO div[class='ui-form-field-error-message ui-form-field-error-message_ui-form']"))
                        .getText());
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
        textInput.fillingTheField(driver,"title", "Поиск", "мобайл тинькофф");
        button.clickButton(driver,"//div[@class='sbl1']//span[contains(text(),'мобайл тинькофф')]");
        button.clickButton(driver,"(.//*[normalize-space(text()) and normalize-space(.)='Тарифы Тинькофф Мобайла'])[1]/following::cite[1]");
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
        assertEquals(URL, baseUrl );
    }

    @org.junit.Test
    public void testRegion() throws InterruptedException {
        System.out.println("Тест №4");
        driver.get(baseUrl);
        Thread.sleep(1000);
        if((driver.findElement(By.xpath("//span[text() ='Да']")) != null)){
            button.clickButton(driver,"//span[text() ='Да']");
        }
        button.clickButton(driver,"//div[@class='MvnoRegionConfirmation__wrapper_1Jmmm MvnoRegionConfirmation__wrapperSelected_uxhv3']");
        button.clickButton(driver,"//div[contains(text(),'Москва и Московская обл.')]");
        Thread.sleep(2000);

        assertEquals("Москва и Московская область", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Точки продаж'])[1]/following::div[13]")).getText());
        driver.navigate().refresh();
        Thread.sleep(2000);

        assertEquals("Москва и Московская область", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Точки продаж'])[1]/following::div[13]")).getText());
        String priceMoscow = getPackagePrice();
        button.clickButton(driver,"//span[contains(text(),'Интернет')]");
        button.clickButton(driver,"//span[contains(text(),'Безлимитный интернет')]");
        button.clickButton(driver,"//span[contains(text(),'Звонки')]");
        button.clickButton(driver,"//span[contains(text(),'Безлимитные минуты')]");
        button.clickButton(driver,"//label[contains(text(),'Режим модема')]");
        button.clickButton(driver,"//label[contains(text(),'Безлимитные СМС')]");

        String priceFullPackageMoscow = getPackagePrice();
        button.clickButton(driver,"//div[@class='MvnoRegionConfirmation__wrapper_1Jmmm MvnoRegionConfirmation__wrapperSelected_uxhv3']");
        button.clickButton(driver,"//div[contains(text(),'Краснодарский кр.')]");
        Thread.sleep(1000);

        String priceKrasnodar = getPackagePrice();
        button.clickButton(driver,"//span[contains(text(),'Интернет')]");
        button.clickButton(driver,"//span[contains(text(),'Безлимитный интернет')]");
        Thread.sleep(1000);
        button.clickButton(driver,"//span[contains(text(),'Звонки')]");
        Thread.sleep(1000);
        button.clickButton(driver,"//span[contains(text(),'Безлимитные минуты')]");
        button.clickButton(driver,"//label[contains(text(),'Режим модема')]");
        button.clickButton(driver,"//label[contains(text(),'Безлимитные СМС')]");

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
        driver.get(baseUrl);

        if((driver.findElement(By.xpath("//span[text() ='Да']")) != null)){
            button.clickButton(driver,"//span[text() ='Да']");
        }
        button.clickButton(driver,"//div[@class='MvnoRegionConfirmation__title_DOqnW']");
        button.clickButton(driver,"//div[contains(text(),'Москва и Московская обл.')]");
        Thread.sleep(1000);

        button.clickButton(driver,"//span[contains(text(),'Интернет')]");
        Thread.sleep(1000);

        button.clickButton(driver,"//span[contains(text(),'0 ГБ')]");
        Thread.sleep(1000);

        button.clickButton(driver,"//span[contains(text(),'Звонки')]");
        Thread.sleep(1000);

        button.clickButton(driver,"//span[text() = '0 минут']");
        Thread.sleep(1000);

        button.clickButton(driver,"//label[contains(text(),'Мессенджеры')]");
        button.clickButton(driver,"//label[contains(text(),'Социальные сети')]");
        String priceZero = getPackagePrice();

        button.clickButton(driver,"//*[contains(@class, 'fio-field')]");
        textInput.fillingTheField(driver,"name","fio", "Тест Тест Тест");
        button.clickButton(driver,"//*[contains(@class, 'row_tel')]");
        textInput.fillingTheField(driver,"name","phone_mobile", "9896868768");

        Thread.sleep(1000);
        if(Integer.parseInt(priceZero) == 0){
            button.clickButton(driver,"//div[@class='LoaderRound__container_no-background_GvpfD LoaderRound__container_coverParent_2-_fi']");
        } else {
            System.out.println("Цена пакета не равна нулю");
        }
        Thread.sleep(1000);

        button.clickButton(driver,"//span[contains(text(),'Заказать доставку')]");
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
}
