package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class TestBaseBeforeClass {//parent class

    protected static WebDriver driver;//@BeforeClass ve @AfterClass static olduğu için WebDriver ve methodumuz da
                                     //static olmak zorundadır
    @BeforeClass
    public static void setup(){

        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));


    }

    @AfterClass
    public static void teardown(){
        driver.quit();
    }
}
