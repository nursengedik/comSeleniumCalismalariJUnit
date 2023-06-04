package day07_JUnit_dropdown;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C01_BeforeAfterClass {

      /*
        Genellikle @Before notasyonu kullanan method icin setup,
        @After notasyonu kullanan method icin teardown isimleri kullanilir.
     */


     //2) https://www.youtube.com adresine gidin
    // 3) Aşağıdaki adları kullanarak 4 test metodu oluşturun ve gerekli testleri yapin
    //    ○ titleTest => Sayfa başlığının “YouTube” oldugunu test edin
    //    ○ imageTest => YouTube resminin görüntülendiğini (isDisplayed()) test edin
    //    ○ Search Box 'in erisilebilir oldugunu test edin (isEnabled())
    //    ○ wrongTitleTest => Sayfa basliginin “youtube” olmadigini dogrulayin


    static WebDriver driver;
    @BeforeClass
    public static void setup(){//setup ismini vermemizin nedeni gerekli ayarlamaları yaptığımız için
        //öncelikle testi okuyup açma ve kapamayı bir defa class ın başında ve sonunda mı yoksa
        //methodundan önce veya sonra mı yapmalıyım, 1 kere,BeforeClass bir kere classın başında çalışıp
        //sonunda da bitirsin, yani her test methodu için ortak olan 4 adım her testte yazılmayacak
        //BeforeClass her test methodunun başında çalışıp sayfayı açacak ve AfterClass ile sayfa kapanacak
        //day06-C05 te olduğu gibi

        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        //2) https://www.youtube.com adresine gidin
        driver.get("https://www.youtube.com");
    }

    @AfterClass
    public void teardown(){

        driver.close();
    }

    @Test
    public void titleTest(){
        //	○ titleTest 	=> Sayfa başlığının “YouTube” oldugunu test edin
        String expectedTitle="YouTube";
        String actualTitle= driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);
    }
    @Test
    public void imageTest(){

        //	○ imageTest 	=> YouTube resminin görüntülendiğini (isDisplayed()) test edin
        WebElement logoElementi= driver.findElement(By.xpath("(//yt-icon[@id='logo-icon'])[1]"));
        Assert.assertTrue(logoElementi.isDisplayed());
    }

    @Test
    public void searchBoxTest(){
        //	○ Search Box 'in erisilebilir oldugunu test edin (isEnabled())
        WebElement searchBoxElementi= driver.findElement(By.xpath("//input[@id='search']"));
        Assert.assertTrue(searchBoxElementi.isEnabled());

    }

    @Test
    public void wrongTitleTest(){
        //	○ wrongTitleTest => Sayfa basliginin “youtube” olmadigini dogrulayin
        String expectedTitle="youtube";
        String actualTitle= driver.getTitle();
        Assert.assertNotEquals(expectedTitle,actualTitle);
    }



}
