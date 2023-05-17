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

public class C02_S91_Assertions {

   // 1)  YoutubeAssertions
   // 2) https://www.youtube.com adresine gidin
   // 3) Aşağıdaki adları kullanarak 4 test metodu oluşturun ve gerekli testleri yapin
   //    ○ titleTest => Sayfa başlığının “YouTube” oldugunu test edin
   //    ○ imageTest => YouTube resminin görüntülendiğini (isDisplayed()) test edin
   //    ○ Search Box 'in erisilebilir oldugunu test edin (isEnabled())
   //    ○ wrongTitleTest => Sayfa basliginin “youtube” olmadigini dogrulayin
    //birden fazla test yapılacağı için driver'ın açma kapama işlemini @BeforeClass ile yaparız

    static WebDriver driver;

    @BeforeClass
    public static void setup(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        // 2) https://www.youtube.com adresine gidin
        driver.get("https://www.youtube.com");
    }

    @AfterClass
    public static void teardown(){
        driver.close();
    }

    @Test
    public void titleTest(){

        //○ titleTest => Sayfa başlığının “YouTube” (eşit) oldugunu  test edin (olumlu cümle-true)
        String expectedIcerik="YouTube";
        String actualTitle=driver.getTitle();
        Assert.assertEquals(expectedIcerik,actualTitle);
    }

    @Test
    public void imageTest(){

        //○ imageTest => YouTube resminin görüntülendiğini (isDisplayed()) test edin (olumlu-true)
        WebElement youTubeElementi=driver.findElement(By.xpath("(//yt-icon[@id='logo-icon'])[1]"));
        Assert.assertTrue(youTubeElementi.isDisplayed());
    }

    @Test
    public void searchBoxTest(){

        //○ Search Box 'in erisilebilir oldugunu test edin (isEnabled()) (olumlu cümle-true)
        WebElement searchBoxElementi=driver.findElement(By.xpath("//input[@id='search']"));
        Assert.assertTrue(searchBoxElementi.isEnabled());
}
    @Test
    public void wrongTitleTest(){

    //○ wrongTitleTest => Sayfa basliginin “youtube” (eşit) olmadigini dogrulayin (olumsuz cümle)
    String expectedTitle="youtube";
    String actualTitle=driver.getTitle();
    Assert.assertNotEquals(expectedTitle,actualTitle);
}


}
