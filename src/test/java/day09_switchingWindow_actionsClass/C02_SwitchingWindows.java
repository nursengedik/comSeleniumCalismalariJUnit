package day09_switchingWindow_actionsClass;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.TestBase;

import java.util.Set;

public class C02_SwitchingWindows extends TestBase {

    /*
        driver.switchTo().newWindow() kullanarak actigimiz
        window'a driver otomatik olarak gecer

        ANCAKKKKK....
        biz newWindow() method'unu kullanmadan
        bir link tikladigimizda yeni window aciliyorsa
        driver eski window'da kalir
        Yeni window'a driver'i gecirebilmek icin
        yeni window'un WindowHandleDegerine ihtiyacimiz vardir.

     */

    @Test
    public void test01() throws InterruptedException {

        //● https://the-internet.herokuapp.com/windows adresine gidin.
        driver.get("https://the-internet.herokuapp.com/windows");

        //● Sayfadaki textin “Opening a new window” olduğunu doğrulayın.
        WebElement openingWindowYaziElementi= driver.findElement(By.tagName("h3"));
        String expectedYazi="Opening a new window";
        String actualYazi= openingWindowYaziElementi.getText();
        Assert.assertEquals(expectedYazi,actualYazi);

        String ilkSayfaWHD= driver.getWindowHandle();//açılan yeni pencere daha olduğu için window'un WHD alınır

        //● Sayfa başlığının(title) “The Internet” olduğunu doğrulayın.
        String expectedTitle = "The Internet";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);

        //● Click Here butonuna basın.
        driver.findElement(By.xpath("//*[text() = 'Click Here']")).click();
        // 46.satir itibariyle yeni window acildi
        // artik 2 window var

        //click yaptığımız için yeni window açıldı
        //yeni sayfaya Click Here linkine tıklayarak geçiş yaptık, driver.switchTo().newWindow() methodu ile yeni
        //sayfaya geçiş yapmadığımız için driver da geçiş yapmamış oluyor ve yeni window'a geçildiğini bilmiyor,
        //driver geçiş yapmadığı için 2. sayfanın WHD'ni alamıyoruz, Set oluşturup for each ile buluyoruz


        Set<String> whDegerleriSet = driver.getWindowHandles();
        //2 window'un WHD'lerini almak için getWindowHandles() methodunu kullanırız, getWindowHandles() methodu bize
        //stringlerden oluşan Set döndüreceği için Set oluşturup kaydederiz

        String ikinciWindowWHD= "";

        for (String eachWhd: whDegerleriSet //1. sayfanın WHD'ni biliyoruz, Set'i elden geçirerek 2. sayfanın WHD'ni
        ) {                                 //buluruz

            if (!eachWhd.equals(ilkSayfaWHD)){//ilkSayfaWHD'ne esit olmayani ikinciWindowWHD olarak atama yaparız
                ikinciWindowWHD = eachWhd;
            }
        }// Artik acilan 2.window'un windowHandleDegerine sahibiz

        //● Acilan yeni pencerenin sayfa başlığının (title) “New Window” oldugunu dogrulayin.
        driver.switchTo().window(ikinciWindowWHD);
        expectedTitle= "New Window";
        actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);

        //● Sayfadaki textin “New Window” olduğunu doğrulayın.
        WebElement newWindowyaziElementi= driver.findElement(By.tagName("h3"));
        expectedYazi = "New Window";
        actualYazi = newWindowyaziElementi.getText();
        Assert.assertEquals(expectedYazi,actualYazi);

        //● Bir önceki pencereye geri döndükten sonra sayfa başlığının “The Internet” olduğunu doğrulayın.
        driver.switchTo().window(ilkSayfaWHD);
        expectedTitle = "The Internet";
        actualTitle = driver.getTitle();

        Assert.assertEquals(expectedTitle,actualTitle);
        Thread.sleep(3000);
    }
}
