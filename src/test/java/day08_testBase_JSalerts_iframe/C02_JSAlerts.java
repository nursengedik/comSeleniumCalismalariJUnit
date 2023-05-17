package day08_testBase_JSalerts_iframe;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.TestBaseBeforeClass;

public class C02_JSAlerts extends TestBaseBeforeClass {

    /*
    Alert (Alört) Nedir?
      Alert kullanıcıya bir tür bilgi vermek veya belirli bir işlemi gerçekleştirme izni istemek
      için ekran bildirimi görüntüleyen küçük bir mesaj kutusudur. Uyarı amacıyla da kullanılabilir.
      1- HTML Alerts
         Bir alert ciktiginda sag click ile inspect yapabiliyorsak html alert’dir ve extra bir isleme gerek yoktur.
      2- Js Alerts
         Js alerts inspect yapilamaz, ekstra isleme ihtiyac vardir
     */

    /*
        Otomasyon yaparken iki tur alert (alört) ile karsilasabiliriz
        1- HTML alerts :
           HTML kodlari ile olusturulmus alert'lerdir
           HTML ile olusturuldugu icin inspect (incele) edilebilir,
           locate edilebilir ve driver ile kullanilabilir

        2- JavaScript Alerts :
           Inspect yapilamaz, locate edilemez
           Testlerimiz sirasinda JS alert ile karsilasirsak
           driver.switchTo().alert() method'larini kullanarak
           alert'e gecis yapabilir ve alert uzerinde
           OK, Cancel, Alert yazisini alma getText()
           ve alert'e yazi yollama sendKeys()
           method'larini kullanabiliriz.
           switchTo()=geçiş yapmak,normal sayfamız açıkken başka bir yere geçmek

           JavaScript Alerts'de button inspect edilebiliyor ama button'a bastığımızda
           gelen OK, Cancel bilgi kutucuğunu inspect edemiyoruz
     */


      /*
       1.accept() : Alert üzerindeki OK butonuna basmak için kullanılır.
                   driver.switchTo().alert().accept();
      2. Dismiss() : Alert üzerindeki OK butonuna basmak için kullanılır.
                      driver.switchTo().alert().dismiss();
      3.getText() : Alert üzerindeki yaziyi döndürür.
                    driver.switchTo().alert().getText();
      4.sendKeys(“istenen yazi” ) : Alert üzerindeki text kutusuna istenen metni yazdırır.
                    driver.switchTo().alert().sendKeys();
   */

   /*
     switchTo() : geçmek, geçiş yapmak
     Normal sayfamız açıkken başka bir yere geçmek için switchTo() methodu kullanılır
     Nelere geçiş yapabiliriz?
     switchTo().alert()
     switchTo().frame()
     switchTo().newWindow()
   */

    /*
            3 test method'u olusturup asagidaki gorevi tamamlayin
        1. Test
            -  https://the-internet.herokuapp.com/javascript_alerts adresine gidin
            - 1.alert'e tiklayin
            -  Alert'deki yazinin "I am a JS Alert" oldugunu test edin
            -  OK tusuna basip alert'i kapatin
        2.Test
            - https://the-internet.herokuapp.com/javascript_alerts adresine gidin
            - 2.alert'e tiklayalim
            - Cancel'a basip, cikan sonuc yazisinin "You clicked: Cancel" oldugunu test edin
        3.Test
            - https://the-internet.herokuapp.com/javascript_alerts adresine gidin
            - 3.alert'e tiklayalim
            - Cikan prompt ekranina "Abdullah" yazdiralim
            - OK tusuna basarak alert'i kapatalim
            - Cikan sonuc yazisinin Abdullah icerdigini test edelim
     */

    //öncelikle sistem ayarlamalarını yazmamak için @Before @After mi veya @BeforeClass @AfterClass mı karar
    //vermemiz gerekir ve TestBase'de bu methodları oluşturduğumuz için extends ile TestBase' i parent ediniriz

    @Test
    public void test01() throws InterruptedException {
        // 1. Test
        //-  https://the-internet.herokuapp.com/javascript_alerts adresine gidin
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        //- 1.alert'e tiklayin
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[text()='Click for JS Alert']")).click();

        //-  Alert'deki yazinin "I am a JS Alert" oldugunu test edin
        Thread.sleep(3000);
        String expectedAlertYazisi = "I am a JS Alert";
        //alert üzerindeki yazıyı alabilmek için önce alert'e gidilir sonra yazı alınır (JavaScript Alerts)
        String actualAlertYazisi = driver.switchTo().alert().getText();
        //actual'ı driver sayesinde alırız, alert'e locator ederek gidemiyoruz, driver.switchTo().alert() ile gideriz
        //yazısını alabilmek için de getText() kullanılır
        Assert.assertEquals(expectedAlertYazisi,actualAlertYazisi);

        //-  OK tusuna basip alert'i kapatin
        //her bir görevde alert'e tekrar gidilir
        //önce driver.switchTo().alert() ile alert'e gidip, .accept() ile de kapatırız
        driver.switchTo().alert().accept();
    }

    @Test
    public void test02() throws InterruptedException {
        //2.Test
        //- https://the-internet.herokuapp.com/javascript_alerts adresine gidin
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        //aynı sayfa olsa da test methodunun bağımsız çalışabilmesi için tekrar sayfaya gitmeliyiz

        //- 2.alert'e tiklayalim
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[text()='Click for JS Confirm']")).click();

        //- Cancel'a basip, cikan sonuc yazisinin "You clicked: Cancel" oldugunu test edin
        Thread.sleep(3000);
        driver.switchTo().alert().dismiss();//alert'e gidip Cancel'a basma
        Thread.sleep(3000);
        String expectedSonucYazisi= "You clicked: Cancel";
        //Cancel'a bastığımızda çıkan yazı alert üzerinde olmadığı için locator edilir (HTML alerts)
        String actualSonucYazisi=driver.findElement(By.xpath("//*[@id='result']")).getText();
        Assert.assertEquals(expectedSonucYazisi,actualSonucYazisi);

    }

    @Test
    public void test03() throws InterruptedException {

        //  3.Test
        //- https://the-internet.herokuapp.com/javascript_alerts adresine gidin
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        Thread.sleep(3000);

        //- 3.alert'e tiklayalim
        driver.findElement(By.xpath("//*[text()='Click for JS Prompt']")).click();
        Thread.sleep(3000);

        //- Cikan prompt ekranina "Abdullah" yazdiralim (alert üzerinde yazacağımız için önce alert'e gidilir)
        driver.switchTo().alert().sendKeys("Abdullah");
        Thread.sleep(3000);

        //- OK tusuna basarak alert'i kapatalim (OK tusu alert üzerinde)
        driver.switchTo().alert().accept();

        //- Cikan sonuc yazisinin Abdullah icerdigini test edelim
        // çıkan yazı alert üzerinde değil html'de, locate edilir
        Thread.sleep(3000);

        String expectedIcerik= "Abdullah";
        String actualSonucYazisi=driver.findElement(By.xpath("//*[@id='result']")).getText();

        Assert.assertTrue(actualSonucYazisi.contains(expectedIcerik));
    }


}
