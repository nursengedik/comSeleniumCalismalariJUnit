package day09_switchingWindow_actionsClass;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WindowType;
import utilities.TestBase;

public class C01_NewWindow extends TestBase {

    /*
        Eger driver.switchTo().newWindow() kullanilirsa
        driver otomatik olarak acilan yeni sayfaya gecer

        eger gorevimizde yeni window'a gittik'ten sonra
        window'lar arasi gecis islemi varsa
        uzerinde calistigimiz window'larin
        windowHandleDegerlerini alip kaydetmekte fayda var

     */

    @Test
    public void test01() throws InterruptedException {
        // gerekli ayarlamalari yapip
        // amazon anasayfaya gidin
        driver.get("https://www.amazon.com");
        String ilkSayfaWHD= driver.getWindowHandle();
        //amazondan wisequatır window'una gittikten sonra tekrar önceki window'a geçiş yaparken
        // driver.switchTo().window(ilkSayfaWHD); window method'u bizden WindowHandle değerini isteyeceği için
        //window'da iken yani window'u kapatmadan kaydederiz, kapatıp açınca WHD değişir

        // title'in Amazon kelimesi icerdigini test edin
        String expectedIcerik = "Amazon";
        String actualTitle = driver.getTitle();
        Assert.assertTrue(actualTitle.contains(expectedIcerik));
        // yeni bir tab acarak wisequarter anasayfaya gidin
        //yeni sayfa açılacağı için önce geçiş yapmalıyız

        driver.switchTo().newWindow(WindowType.TAB);//yeni window içerisinde yeni tab'a geçiş
        Thread.sleep(1000);
        driver.get("https://www.wisequarter.com");
        String ikinciSayfaWHD= driver.getWindowHandle();
        // url'in wisequarter icerdigini test edin
        expectedIcerik = "wisequarter";
        String actualUrl= driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedIcerik));

        // tekrar amazon'un acik oldugu window'a gecin
        // ve url'in amazon icerdigini test edin

        //bu test'te yeni tab açılarak farklı sayfaya gidildiği için geçiş işlemi driver.switchTo().window()
        //methodu ile yapılır, navigate() methodu ise aynı (yeni tab açılmadan) sayfada window'lar arasında geçiş
        // yapılırken kullanılır

        driver.switchTo().window(ilkSayfaWHD);
        // hangi window'a gideceksek daha önce kaydettiğimiz WHD'den o window'un değeri girilir
        expectedIcerik = "amazon";
        actualUrl= driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedIcerik));
        Thread.sleep(3000);

    }


}
