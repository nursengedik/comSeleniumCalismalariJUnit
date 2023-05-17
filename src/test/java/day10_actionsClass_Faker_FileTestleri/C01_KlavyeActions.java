package day10_actionsClass_Faker_FileTestleri;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.TestBase;

public class C01_KlavyeActions extends TestBase {

    /*
      Keyboard Base Actions
      Action Class’indaki hazir method’lar ile klavyedeki tuslar kontrol edilebilir.
      Klavyede cok fazla tus vardir ama tum tuslar 3 temel islev ile kontrol edilebilir.

     1 ) sendKeys ( ): Öğeye bir dizi anahtar gönderir (bir işlem yapacaksak kullanılır.
                       Shift ile S ye basma gibi-büyük yazar)
     2 ) keyDown ( ): Klavyede tuşa basma işlemi gerçekleştirir
     3 ) keyUp ( ) : Klavyede tuşu serbest bırakma işlemi gerçekleştirir
   */

    //1 tane testimiz olduğu için extends'i TestBase'e yaparız

    @Test
    public void test01() throws InterruptedException {
        //2- https://www.amazon.com sayfasina gidelim
        driver.get("https://www.amazon.com");

        //3- Arama kutusuna actions method’larini kullanarak Samsung A71 yazdirin
        //   ve Enter’a basarak arama yaptirin
        Actions actions = new Actions(driver);//Actions objesi oluşturulur ve driver'ımız gönderilir
        //kullanacağımız arama kutusu locate edilir
        WebElement aramaKutusu= driver.findElement(By.id("twotabsearchtextbox"));

        //actions methodlarıyla yazımızı yazdırıp Enter'e basacağız
        actions.click(aramaKutusu)//önce arama kutusuna click yaparız
                .keyDown(Keys.SHIFT)//shift'e basarız
                .sendKeys("s")//shift'e basılı iken s'ye basarız ve büyük S yazar
                .keyUp(Keys.SHIFT)//sonra elimizi shift'ten çekeriz
                .sendKeys("amsung ")//büyük A'ya kadar kalan yazıyı göndeririz
                .keyDown(Keys.SHIFT)//tekrar shift'te basar
                .sendKeys("a")//büyük A yazılır
                .keyUp(Keys.SHIFT)//elimizi shift'ten çekeriz
                .sendKeys("71")//A'dan sonra kalan yazıyı yazdırırız
                .sendKeys(Keys.ENTER)//Enter'e basılır
                .perform();//çalışması için perform

        Thread.sleep(5000);


        //4- aramanin gerceklestigini test edin
        WebElement sonucYaziElementi = driver.findElement(By.xpath("//h1[@class='a-size-base s-desktop-toolbar a-text-normal']"));

        String expectedIcerik = "Samsung A71";
        String actualSonucYazisi= sonucYaziElementi.getText();

        Assert.assertTrue(actualSonucYazisi.contains(expectedIcerik));

    }
}
