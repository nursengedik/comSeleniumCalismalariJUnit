package day10_actionsClass_Faker_FileTestleri;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.TestBase;

public class C07_FileUploadTesti extends TestBase {

    //Yüklenen dosya testi

    @Test
    public void test01() throws InterruptedException {

        /*
            driver ile gittigimiz bir websayfasinda
            bilgisayarimizdaki bir dosyayi upload (yüklemek) etmek istersek

            bilgisayarimizdaki klasorlere erismemiz gerekir
            Selenium webdriver bilgisayardaki dosyalara erisemez

            bunun yerine 3 adimla su islemleri yapariz
            1- choose file  (dosya seç) butonunu locate edelim
            2- bilgisayarimizdan yuklemek istedigimiz dosyanin dosya yolunu olusturalim
               (Mumkunse dinamik dosya yolu olsun)
            3- chooseButonu.sendkeys(dosyaYolu) ile dosya yolunu sisteme tanitalim
         */

        //https://the-internet.herokuapp.com/upload adresine gidelim
        driver.get("https://the-internet.herokuapp.com/upload");

        // chooseFile butonunu kullanarak
        // projemizdeki deneme.txt dosyasini secelim.

        //görevi yapabilmemiz için öncelikle yukarıdaki 3 adımı yapmalıyız
        //1-locate etmek
        WebElement chooseFileButonu = driver.findElement(By.xpath("//input[@id='file-upload']"));

        //2-bilgisayarimizdan yuklemek istedigimiz dosyanin dosya yolunu olusturalim
        String herkesteFarkliOlan = System.getProperty("user.dir");
        String herkesteAyniOlan = "/src/test/java/day10_actionsClass_Faker_FileTestleri/deneme.txt";
        String dosyaYolu= herkesteFarkliOlan+herkesteAyniOlan;

        //3- chooseButonu.sendkeys(dosyaYolu) ile dosya yolunu sisteme tanitalim
        chooseFileButonu.sendKeys(dosyaYolu);

        //Upload butonuna basalim.
        driver.findElement(By.id("file-submit")).click();

        //“File Uploaded!” textinin goruntulendigini test edelim.
        WebElement fileUplodedElementi = driver.findElement(By.tagName("h3"));

        Assert.assertTrue(fileUplodedElementi.isDisplayed());

        Thread.sleep(10000);

    }
}
