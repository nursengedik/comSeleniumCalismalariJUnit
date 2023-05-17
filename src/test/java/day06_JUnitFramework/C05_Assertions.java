package day06_JUnitFramework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C05_Assertions {

    // 1) Bir class oluşturun: BestBuyAssertions
    // 2) https://www.bestbuy.com/ Adresine gidin farkli test method’lari olusturarak
    //    asagidaki testleri yapin
    //		○ Sayfa URL’inin https://www.bestbuy.com/ ‘a esit oldugunu test edin
    //		○ titleTest => Sayfa başlığının “Rest” içermediğini(contains) test edin
    //		○ logoTest => BestBuy logosunun görüntülendigini test edin
    //		○ FrancaisLinkTest => Fransizca Linkin görüntülendiğini test edin

    WebDriver driver;
    public void mahserin4Atlisi(){

        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        //testi bestbuy sayfasında yapacağımız için önce sayfaya gideriz
        driver.get("https://www.bestbuy.com");

    }
    @Test
    public void test01(){

        //Sayfa URL’inin https://www.bestbuy.com/ ‘a esit oldugunu test edin (olumlu cümle)
        mahserin4Atlisi();
        String expectedUrl="https://www.bestbuy.com/";
        String actualUrl= driver.getCurrentUrl(); // https://www.bestbuy.com/

        Assert.assertEquals(expectedUrl,actualUrl);
        //önce expected sonra actual söylenir,sonucu methodda girdiklerimize göre veriyor
        driver.close();

    }

    @Test
    public void test02(){

    //titleTest => Sayfa başlığının “Rest” içermediğini(contains) test edin (olumsuz cümle)
    mahserin4Atlisi();
    String unExpectedIcerik = "Rest";
    String actualTitle= driver.getTitle();

    Assert.assertFalse(actualTitle.contains(unExpectedIcerik));//içeriyor olmadığını söylememiz gerekir
    driver.close();

}
    @Test
    public void test03(){

        //○ logoTest => BestBuy logosunun görüntülendigini test edin (olumlu cümle-true)
        //logoyu test edebilmemiz için web elementini locator ederek kaydetmemiz gerekir
        mahserin4Atlisi();
        WebElement bestBuyLogoElementi=driver.findElement(By.className("logo"));
        Assert.assertTrue(bestBuyLogoElementi.isDisplayed());
        driver.close();
    }

    @Test
    public void test04(){

        //○ FrancaisLinkTest => Fransizca Linkin görüntülendiğini test edin (olumlu cümle-true)
        //linkin görünürlüğü test edileceği için locator edilir
        mahserin4Atlisi();
        WebElement francaIsLinkElementi=driver.findElement(By.className("is-active"));
        Assert.assertTrue(francaIsLinkElementi.isDisplayed());
        driver.close();
    }

    //her testte driver sayfayı açıp kapatmakta

}
