import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;


public class Test extends BaseRunner {

    @org.junit.Test
    public void testEmptyFields(){
        System.out.println("Тест №1");
        driver.get(baseUrl);

        driver.findElement(By.name("fio")).click();
        driver.findElement(By.name("phone_mobile")).click();

        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)=" +
                "'Укажите контактный телефон'])[1]/following::div[2]")).click();

        assertEquals("Укажите ваше ФИО", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)=" +
                "'Фамилия, имя и отчество'])[1]/following::div[3]")).getText());
        assertEquals("Необходимо указать номер телефона", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)=" +
                "'Контактный телефон'])[1]/following::div[2]")).getText());
    }

    @org.junit.Test
    public void testIncorrectData() {
        System.out.println("Тест №2");
        driver.get(baseUrl);

        driver.findElement(By.name("fio")).click();
        driver.findElement(By.name("fio")).sendKeys("ке");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Укажите контактный телефон'])[1]/following::div[1]")).click();
        assertEquals("Недостаточно информации. Введите фамилию, имя и отчество через пробел (Например: Иванов Иван Алексеевич)", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='римов'])[1]/following::div[1]")).getText());

        driver.findElement(By.name("fio")).click();
        driver.findElement(By.name("fio")).clear();
        driver.findElement(By.name("fio")).sendKeys("Наталья# Тес Тес");
        driver.findElement(By.name("phone_mobile")).click();
        assertEquals("Допустимо использовать только буквы русского алфавита и дефис", driver.findElement(By.xpath("//*[@id=\"form-application\"]/div[2]/div/div/form/div[1]/div/div[2]")).getText());

        driver.findElement(By.name("fio")).click();
        driver.findElement(By.name("fio")).sendKeys("ке@$%^ @12. Создать maven-строку запуска с возможностью явно указать используемый браузер (Пример строк есть в учебном проекте README.md)");
        driver.findElement(By.name("phone_mobile")).click();
        assertEquals("Максимальное количество символов – 133", driver.findElement(By.xpath("//*[@id=\"form-application\"]/div[2]/div/div/form/div[1]/div/div[2]")).getText());

        driver.findElement(By.name("phone_mobile")).click();
        driver.findElement(By.name("phone_mobile")).sendKeys("+7(789)");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Укажите контактный телефон'])[1]/following::div[1]")).click();
        assertEquals("Номер телефона должен состоять из 10 цифр, начиная с кода оператора", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Контактный телефон'])[1]/following::div[2]")).getText());

        driver.findElement(By.name("phone_mobile")).click();
        driver.findElement(By.name("phone_mobile")).clear();
        driver.findElement(By.name("phone_mobile")).sendKeys("+7(789) 686-87-68");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Укажите контактный телефон'])[1]/following::div[1]")).click();
        assertEquals("Код оператора должен начинаться с цифры 9", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Контактный телефон'])[1]/following::div[2]")).getText());

        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).sendKeys("45");
        driver.findElement(By.name("phone_mobile")).click();
        assertEquals("Введите корректный адрес эл. почты", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Электронная почта'])[1]/following::div[3]")).getText());

        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("chuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu@bk.ru");
        driver.findElement(By.name("phone_mobile")).click();
        assertEquals("Максимальное количество символов – 50", driver.findElement(By.xpath("//*[@id=\"form-application\"]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]")).getText());
    }
}