package day10_actionsClass_Faker_FileTestleri;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C08_Waits {

    /*
      Synchronization (Senkranizasyon) :
      Bir testi çalıştırırken 3 şey çalışır; internet, bilgisayar ve kodlar
      Bütün kodlar doğru olmasına ragmen bazen testlerimiz failed olabilir

      Bir testi yaparken otomasyonla çalıştırmadan önce manuel test yapıp
      yani test adımlarını manuel olarak yapmalıyız ki test bekleme sürelerinin
      bizim implicitlyWait süresini geçmemesi lazım (geçerse kodlar doğru olsa da
      hata verir), yani test süresinin implicitlyWait süresinden az olup
      olmadığını görelim

      Test otomasyonunu yapmadan önce senkranizasyon ihtiyacımız var mı yani
      kodlarımızı bekletmemiz gerekir mi, implicitlyWait yeterli olur mu
      yoksa ekstradan başka tedbirler almam mı lazım diye ilk önce manuel test
      yapmamız gerekir
   */

    @Test
    public void test01(){

        // implicitly wait'in rolunu gorebilmek icin
        // baslangic ayarlarini bu test method'unda yapalim
        //extends etmeden

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        //3. https://the-internet.herokuapp.com/dynamic_controls adresine gidin.
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        //4. Remove butonuna basin.
        //basabilmek için locator etmeliyiz
        driver.findElement(By.xpath("//button[text()='Remove']")).click();

        //5. “It’s gone!” mesajinin goruntulendigini dogrulayin.
        WebElement itsGoneElementi= driver.findElement(By.xpath("//p[text()=\"It's gone!\"]"));
        Assert.assertTrue(itsGoneElementi.isDisplayed());

        //6. Add buttonuna basin

        //7. It’s back mesajinin gorundugunu test edin


    }
}
