package day10_actionsClass_Faker_FileTestleri;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.TestBase;

public class C02_KlavyeActions extends TestBase {//1 tane test methodu var

    @Test
    public void test01() throws InterruptedException {
        //1- https://www.facebook.com adresine gidelim ve cookies'i kabul edelim
        driver.get("https://www.facebook.com");
        //cookies'i kabul etmek
        //driver.findElement(By.xpath("//button[@*='_42ft _4jy0 _al65 _4jy3 _4jy1 selected _51sy']")).click();
        //cookies ben de çıkmadığı için satırı yoruma aldım, hata veriyor

        //2- Yeni hesap olustur butonuna basalim
        //butona basacağımız için locator'i kaydetmemize gerek yok
        driver.findElement(By.xpath("//a[@* = '_42ft _4jy0 _6lti _4jy6 _4jy2 selected _51sy']")).click();

        //3- Ad, soyad, mail ve sifre kutularina deger yazalim ve kaydol tusuna basalim
        //birden fazla kutuya değer yazılacağı için her birini locator etmemek için actions objesi oluşturarak
        //driver'a klavye nin işlevlerini yapabilmesini sağlarız (3 adım)
        Actions actions = new Actions(driver);
        WebElement isimKutusu = driver.findElement(By.xpath("//*[@name='firstname']"));

        actions.click(isimKutusu)//değerleri gireceğimiz sayfayı takip ederek adım adım değerler girilir
                .sendKeys("Ali")
                .sendKeys(Keys.TAB)//tab'a basıp
                .sendKeys("Karahan")//Karahan yazdıracağız
                .sendKeys(Keys.TAB)
                .sendKeys("a1234321bc@gmail.com")
                .sendKeys(Keys.TAB)
                .sendKeys("a1234321bc@gmail.com")
                .sendKeys(Keys.TAB)
                .sendKeys("Malatya44")
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys("15")
                .sendKeys(Keys.TAB)
                .sendKeys("Mar")
                .sendKeys(Keys.TAB)
                .sendKeys("1990")
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.ARROW_RIGHT)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.ENTER).perform();

        //4- Kaydol tusuna basalim
        //sendKeys ile Enter'e bastık
        Thread.sleep(5000);
    }
    //facebook hesabı oluşturduk ve değerleri kendimiz yazarak oluşturduk,
    //rastgele değerler üreterek de yapabiliriz, Faker kütüphanesi
}
