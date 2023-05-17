package seleniumJUnitSoruları;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C03_S92_Assertions {

    /*
       1. YanlisEmailTesti
       2. http://automationpractice.com/index.php sayfasina gidelim
       3. Sign in butonuna basalim
       4. Email kutusuna @isareti olmayan bir mail yazip enter’a bastigimizda “Invalid
          email address” uyarisi ciktigini test edelim
     */

    WebDriver driver;
    @Before
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @After
    public void teardown(){
        driver.close();
    }

    @Test
    public void yanlisEmailTesti(){

        //2. http://automationpractice.com/index.php sayfasina gidelim
        driver.get("http://automationpractice.com/index.php");

        //3. Sign in butonuna basalim
        //test etmeyeceğimiz için elementi kaydetmiyoruz
        //driver.findElement();

        //4. Email kutusuna @isareti olmayan bir mail yazip enter’a bastigimizda “Invalid
        //   email address” uyarisi ciktigini test edelim
    }
}
