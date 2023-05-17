package day11_seleniumWaits_webTables;

import com.google.gson.annotations.Until;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class C01_SeleniumWaits {

    /*
      1 ) Java tabanli wait
          Thread.sleep : Dinamik değil, nettir

     2 ) Selenium tabanli wait’ler
         Dinamiktir, zaman odaklı değil görev odaklıdır

         Implicitly Wait: Sayfadaki tüm öğeler için global bir zaman aşımıdır(timeout).
                          Spesifik bir göreve tanımlı değil, genel bekleme süresidir.
        Explicitly Wait: Çoğunlukla belirli öğeler için belirli bir koşul(expected condition) için
                         kullanılır.
                         Spesifik tek bir görev için tanımlanır
     */

    /*
        Otomasyon yaparken
        sayfanin yuklenmesi ve webelementlerin bulunmasi icin gerekli sureyi
        implicitly wait ile ayarlariz

        Implicitly wait sayfanin yuklenmesi ve
        her bir webelementin bulunmasi icin tanimlanan
        max. bekleme suresidir.
        her islem icin sifirdan baslar ve max sure doluncaya kadar
        gorevi tamamlamaya calisir
        max sure icerisinde gorev tamamlanamazsa
        exception verip calismayi durdurur

        Testlerin buyuk cogunlugunda
        implicitly wait suresi
        senkronizasyonu saglamak icin yeterli olur
        ANCAK
        ozel bir gorev icin implicitly wait'de tanimlanan
        max. bekleme suresinden daha fazla beklememiz gerekirse
        O GOREVE OZGU, TEK SEFERLIK bir wait olusturabiliriz

     */

    //2. Iki tane metod olusturun : implicitWait() , explicitWait()
    //
    //	 Iki metod icin de asagidaki adimlari test edin.
    //3. https://the-internet.herokuapp.com/dynamic_controls adresine gidin.
    //4. Remove butonuna basin.
    //5. “It’s gone!” mesajinin goruntulendigini dogrulayin.
    //6. Add buttonuna basin
    //7. It’s back mesajinin gorundugunu test edin

    @Test
    public void test01(){

        // implicitly wait'in rolunu gorebilmek icin
        // baslangic ayarlarini bu test method'unda yapalim

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        //3. https://the-internet.herokuapp.com/dynamic_controls adresine gidin.
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        //4. Remove butonuna basin.
        driver.findElement(By.xpath("//button[text()='Remove']")).click();

        //5. “It’s gone!” mesajinin goruntulendigini dogrulayin.
        WebElement itsGoneElementi= driver.findElement(By.xpath("//p[text()=\"It's gone!\"]"));

        Assert.assertTrue(itsGoneElementi.isDisplayed());

        //6. Add buttonuna basin
        driver.findElement(By.xpath("//*[text()=\"Add\"]")).click();

        //7. It’s back mesajinin gorundugunu test edin

        WebElement itsBackElementi= driver.findElement(By.xpath("//p[@id='message']"));
        Assert.assertTrue(itsBackElementi.isDisplayed());
        driver.close();
    }

    @Test
    public void explicitlyWaitTesti(){//Method isimlerini test isimlerinde tek başına kullandığımızda
                                     //hata verir, bu nedenle ismin yanına test veya testi ibaresini kullanalım

        //3. https://the-internet.herokuapp.com/dynamic_controls adresine gidin.
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        //4. Remove butonuna basin.
        //Remove butonuna basmak için beklemeye gerek yok
        driver.findElement(By.xpath("//button[text()='Remove']")).click();

        //5. “It’s gone!” mesajinin goruntulendigini dogrulayin.
        /*
            Ekstra bekleme tanımlıyorsak
            Burada testi manuel olarak yapip
            bir karar vermemiz gerekiyor
            1- test etmek istedigimiz webelement zaten gorunuyorsa
                webelement'i locate yapip sonra ozel beklemeyi tanimlarim

            2- test etmek istedigimiz elementin ulasilabilir olmasi icin
               beklemek gerekiyorsa
               locatar'i verip, "bu locator ile bir element locate edene kadar
               exception firlatma bekle" diyebiliriz (hem görünür olmasını isterim
               hem de beklerim)
         */
        //5. görev için 2. durum geçerli, bekleme işlemi ile locate işlemini birlikte yapacağız (birleştireceğiz)
        //çünkü remove butonuna basınca it is gone mesajının açılmasını bekliyor, driver bu bekleme süresinde
        //göremediği elementi locate edemez, neyi beklediğimizi bilmeden beklememiz gerekiyor

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));//Wait objesi, driver'ımı beklet, 40 sn
        WebElement itsGoneElementi =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()=\"It's gone!\"]")));
        //xpath'i bu olan element locate edilip görünür oluncaya kadar (until) bekle (wait)
        // its gone elementini locate ettik
        Assert.assertTrue(itsGoneElementi.isDisplayed());
        //implicitlyWait 0 olmasına rağmen bekliyor

        //6. Add buttonuna basin
        //bekleme yapmamıza gerek yok
        driver.findElement(By.xpath("//*[text()=\"Add\"]")).click();

        //7. It’s back mesajinin gorundugunu test edin
        //It's back mesajının göründüğünü beklemem lazım,
        //önce manuel olarak yapmalıyız, locate edemiyoruz beklememiz lazım (2. durum)
        //Wait objesi daha önce oluşturulduğu için tekrar oluşturmamıza gerek yok
        WebElement itsBackElementi=
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='message']")));

        driver.close();
    }
}
