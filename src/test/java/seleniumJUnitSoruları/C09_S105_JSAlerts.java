package seleniumJUnitSoruları;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.TestBaseBeforeClass;

public class C09_S105_JSAlerts extends TestBaseBeforeClass {

   /*
       1.accept() : Alert üzerindeki OK butonuna basmak (kapatmak) için kullanılır.
                   driver.switchTo().alert().accept();
      2. Dismiss() : Alert üzerindeki OK butonuna basmak için kullanılır.
                      driver.switchTo().alert().dismiss();
      3.getText() : Alert üzerindeki yaziyi döndürür.
                    driver.switchTo().alert().getText();
      4.sendKeys(“istenen yazi” ) : Alert üzerindeki text kutusuna istenen metni yazdırır.
                    driver.switchTo().alert().sendKeys();
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

       //  https://the-internet.herokuapp.com/javascript_alerts adresine gidin
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

       // 1.alert'e tiklayin
        //alert'in gelmesi için tıklanan buton HTML alert olduğu için locator edilir
      //sadece tıklayacağımız için locate edilir ve tıklanır
        //Thread.sleep(3000);
        driver.findElement(By.xpath("//*[text()='Click for JS Alert']")).click();

       // -  Alert'deki yazinin "I am a JS Alert" oldugunu test edin
        //Thread.sleep(3000);
        String expectedAlertYazisi="I am a JS Alert";
        //alert'e locator ederek gidemiyoruz (JavaScript Alerts)
        //alert üzerindeki yazıyı alabilmemiz için önce alert'e gidilir driver.switchTo().alert() methodu ile
        //sonra yazısı alınır driver.switchTo().alert().getText();
        String actualAlertYazisi=driver.switchTo().alert().getText();
        Assert.assertEquals(expectedAlertYazisi,actualAlertYazisi);


        // -  OK tusuna basip alert'i kapatin
        //her bir görevde alert'e tekrar gidilir
        //önce driver.switchTo().alert() ile alert'e gidip, .accept() ile de kapatırız
        driver.switchTo().alert().accept();

    }

    @Test
    public void test02(){

         // - https://the-internet.herokuapp.com/javascript_alerts adresine gidin
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        // - 2.alert'e tiklayalim
        driver.findElement(By.xpath("//*[text()='Click for JS Confirm']")).click();

        //- Cancel'a basip, cikan sonuc yazisinin "You clicked: Cancel" oldugunu test edin
       //önce alert'e gidip sonra cancel'e basılır
        //driver.switchTo().alert() methodu ile alert'e gidilir
        driver.switchTo().alert().dismiss();//ile cancel e basılır

        String expectedAlertYazisi="You clicked: Cancel";
        //Cancel'a bastıktan sonra çıkan yazı alert üzerinde olmadığı için locate edilir (HTML alert)
        String actualAlertYazisi=driver.findElement(By.xpath("//p[@id='result']")).getText();
        Assert.assertEquals(expectedAlertYazisi,actualAlertYazisi);


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



































