package day09_switchingWindow_actionsClass;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.TestBase;

import java.util.Set;

public class C03_ActionsClass extends TestBase {


    /*
       Actions Class ne işimize yarar?
       Maus ve klavye'nin bütün işlevlerini driver'ın yapabilmesini sağlıyor

       Mouse Base Actions
        -doubleClick ( ): WebElement’e çift tıklama yapar
        -clickAndHold ( ) : WebElement uzerinde click yapilip olarak bizden komut bekler.
        -dragAndDrop ( ): WebElement’i bir noktadan diğerine sürükler ve bırakır
        -moveToElement ( ): Mouse’u istedigimiz WebElement’in uzerinde tutar
        -contextClick ( ): Mouse ile istedigimiz WebElement’e sag tiklama yapar.

        Actions class oluşturmak için 3 adım :
        1.Adım: Actions class’ta bir object oluşturulur.
        Actions actions=new Actions(driver); driver parametre olarak kullanılır
        actions objesi driver'ı kullanarak çalışsın demektir

        2. Adım: Üzerinde çalışmak istediğiniz WebElement locate edilir.
        WebElement accountListElementi=driver.findElment();

        3.Adim : Ardından bu webelement üzerinde action gerçekleştirilir.
          Örneğin Mouse’u istenen webelement’in uzerine goturmek icin
          actions.moveToElement(accountListElementi).perform();
          objemizi ve elementimizi kullanarak işlemimizi yapmak

        NOT 1 : Action Class’ini her kullanmak istedigimizde yeniden obje olusturmamiz gerekmez.
               action objesi kullanilarak baslayan her komut, calismak icin perform( ) bekler.
       NOT 2 : action objesi’ni bir kere olusturunca, istediginiz kadar action. ile baslayan
              komut yazar ve calismasi icin sonuna perform( ) yazariz.
     */

    @Test
    public void test01() throws InterruptedException {

        //2- https://the-internet.herokuapp.com/context_menu sitesine gidin
        driver.get("https://the-internet.herokuapp.com/context_menu");

        //3- Cizili alan uzerinde sag click yapin
        //çizili alanı locate etmeliyiz
        WebElement ciziliAlan = driver.findElement(By.id("hot-spot"));

        //sağ click için 3 adım
        //locate yaptık
        Actions actions = new Actions(driver);
        actions.contextClick(ciziliAlan).perform();

        //4- Alert’te cikan yazinin “You selected a context menu” oldugunu test edin.

        String expectedAlertYazisi = "You selected a context menu";
        String actualAlertYazisi = driver.switchTo().alert().getText();
        //driver.switchTo().alert() ile alert'e geçiş yapıp getText ile de üzerindeki yazıyı alırız
        Assert.assertEquals(expectedAlertYazisi,actualAlertYazisi);

        //5- Tamam diyerek alert’i kapatalim
        //bir önceki görevde alert'e geçsek de driver'ımız alert'te kalmaz bu görev için tekrar alert'e geçiş
        // yapıp kapatırız
        driver.switchTo().alert().accept();

        //6- Elemental Selenium linkine tiklayalim
        //  linki tikladigimizda yeni window (kontrolsüz) acildigindan
        //  tiklamadan once ilk window'un WHD almamiz gerekir
        String ilkWindowWHD = driver.getWindowHandle();
        driver.findElement(By.linkText("Elemental Selenium")).click();

        // ikinci sayfa biz newWindow() demeden acildigindan
        // ikinciWindow'un WHD'ini Java kullanarak bulmaliyiz
        String ikinciWindowWHD = "";
        Set<String> windowHDegerleriSeti = driver.getWindowHandles(); // icinde 2 tane WHD var
        // ilkWindowWHD'ne esit olmayani ikinciWindowWHD olarak atayalim

        for (String eachWHD : windowHDegerleriSeti
        ) {

            if (!eachWHD.equals(ilkWindowWHD)){//bize gelen WHD ilkWindowWHD'ne eşit değilse
                ikinciWindowWHD=eachWHD;      //ikinciWindowWHD'ne ata
            }
        }
        driver.switchTo().window(ikinciWindowWHD);//2. sayfaya geçme

        //7- Acilan sayfada h1 taginda “Elemental Selenium” yazdigini test edelim

        String expectedYazi="Elemental Selenium";
        WebElement yaziElementi = driver.findElement(By.tagName("h1"));
        String actualYazi= yaziElementi.getText();//actual'ı webelement'ten alacağız

        Assert.assertEquals(expectedYazi,actualYazi);
        Thread.sleep(5000);
    }
}
