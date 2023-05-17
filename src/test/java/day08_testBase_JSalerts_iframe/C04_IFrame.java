package day08_testBase_JSalerts_iframe;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.TestBase;

public class C04_IFrame extends TestBase {//@Before notasyonu seçilir ve TestBase extends ile parent edinilir

    //Bir web sayfasının içerisinde farklı bir web sayfasının gömülmesine imkan tanıyan html kodu IFrame'dir
    //Bir web sayfasının iframe olduğunu
   /*
    HTML kodlarda kullanilan <iframe> tag’i bir HTML sayfasinin icerisine baska bir
    HTML sayfasi gömmek(embed) icin kullanilir.

    <iframe> tag’i icerisinde 1 tane header ve 1 tanebody bulunur. Bir HTML sayfasinda iframe varsa,
    HTML kodlar icerisinde birden fazla header veya body olacaktir.
    Ornek iframe icin : https://html.com/tags/iframe/
   */

    // 1 ) https://the-internet.herokuapp.com/iframe adresine gidin.
    // 2 ) Bir metod olusturun: iframeTest
    //	- “An IFrame containing….” textinin erisilebilir oldugunu test edin
    //    ve  konsolda yazdirin.
    //	- Text Box’a “Merhaba Dunya!” yazin.
    //	- TextBox’in altinda bulunan “Elemental Selenium”
    //    linkini textinin gorunur oldugunu dogrulayin ve  konsolda yazdirin.

    @Test
    public void test01() throws InterruptedException {

        // 1 ) https://the-internet.herokuapp.com/iframe adresine gidin
        driver.get("https://the-internet.herokuapp.com/iframe");

        //	- “An IFrame containing….” textinin erisilebilir oldugunu test edin
        //    ve  konsolda yazdirin.
        WebElement iFrameyaziElementi= driver.findElement(By.tagName("h3"));
        Assert.assertTrue(iFrameyaziElementi.isDisplayed());
        System.out.println(iFrameyaziElementi.getText());

        //	- Text Box’a “Merhaba Dunya!” yazin.
        WebElement frameElementi= driver.findElement(By.id("mce_0_ifr"));
        driver.switchTo().frame(frameElementi);

        WebElement textBoxElementi= driver.findElement(By.xpath("//body[@id='tinymce']"));
        Thread.sleep(1000);
        textBoxElementi.clear();//görünür olduğu yazı öncelikle silinir sonra Merhaba Dünya yazdırılır
        textBoxElementi.sendKeys("Merhaba Dunya!");

        //	- TextBox’in altinda bulunan “Elemental Selenium”
        //    linkini textinin gorunur oldugunu dogrulayin ve  konsolda yazdirin.

        /*
            iframe varsa iframe'e geçilir
            iframe'e geçiş yaptıktan sonra iframe'den çıkış yapmamız gerekir
            (alerts gibi değil, alerts'de sadece geçiş yapıyoruz)

            iframe'e gecis yapilinca
            driver'i oradan cikis yaptirana kadar driver iframe'in icinde kalir

            driver.switchTo().parentFrame() : ic ice birden fazla iframe varsa, bir uste cikar
            driver.switchTo().defaultContent() : direk anasayfaya cikar
         */

        driver.switchTo().defaultContent();
        WebElement elementalSeleniumLinki= driver.findElement(By.linkText("Elemental Selenium"));
        Assert.assertTrue(elementalSeleniumLinki.isDisplayed());//görünür olduğunun testi
        System.out.println(elementalSeleniumLinki.getText());//görünür olduğu yazısını yazdırma
        Thread.sleep(5000);
    }


}

