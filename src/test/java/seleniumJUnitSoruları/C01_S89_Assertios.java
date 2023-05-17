package seleniumJUnitSoruları;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C01_S89_Assertios {

   //1) BestBuyAssertions testi
   //2) https://www.bestbuy.com/ Adresine gidin farkli test method’lari olusturarak
   //   asagidaki testleri yapin
   //      ○ Sayfa URL’inin https://www.bestbuy.com/ ‘a esit oldugunu test edin
   //      ○ titleTest => Sayfa başlığının “Rest” içermediğini(contains) test edin
   //      ○ logoTest => BestBuy logosunun görüntülendigini test edin
   //      ○ FrancaisLinkTest => Fransizca Linkin görüntülendiğini test edin

    static WebDriver driver;
    @BeforeClass
    public static void setup(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        //2- https://www.bestbuy.com/ Adresine gidin
        driver.get("https://www.bestbuy.com/ ");

    }

    @AfterClass
    public static void teardown(){
        driver.close();
    }
    @Test
    public void URLTest(){

        // ○ Sayfa URL’inin https://www.bestbuy.com/ ‘a esit oldugunu test edin (olumlu cümle)

        String expectedUrl="https://www.bestbuy.com/";
        String actualUrl=driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);

    }

    @Test
    public void titleTest(){

        // ○ titleTest => Sayfa başlığının “Rest” içermediğini(contains) test edin
        // (olumsuz cümle) False kullanacağız

        String unexpectedIcerik="Rest";
        String actualTitle=driver.getTitle();
        Assert.assertFalse(actualTitle.contains(unexpectedIcerik));//içeriyor olmadığını söyleriz

    }

    @Test
    public void logoTest(){

        //○ logoTest => BestBuy logosunun görüntülendigini test edin (olumlu cümle-true)
       //logoyu test edebilmemiz için web elementini locate ederek kaydetmemiz gerekir

        WebElement bestBuyLogoElementi=driver.findElement(By.className("logo"));
        Assert.assertTrue(bestBuyLogoElementi.isDisplayed());

    }

    @Test
    public void francaIsLinkTest(){

        //○ FrancaisLinkTest => Fransizca Linkin görüntülendiğini test edin (olumlu cümle-true)
        //linkin görünürlüğü test edileceği için locate edilir
        WebElement francaIsLinkElementi=driver.findElement(By.className("is-active"));
        Assert.assertTrue(francaIsLinkElementi.isDisplayed());

    }



}
