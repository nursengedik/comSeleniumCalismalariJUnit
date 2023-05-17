package day10_actionsClass_Faker_FileTestleri;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.TestBase;

import java.nio.file.Files;
import java.nio.file.Paths;

public class C05_FileDownloadTest extends TestBase {

    //İndirilen dosya testi

    @Test
    public void test01() throws InterruptedException {

        //2. https://the-internet.herokuapp.com/download adresine gidelim.
        driver.get("https://the-internet.herokuapp.com/download");

        //3. foto.png dosyasını indirelim
        //benim sayfada çıkmadığı için yoruma alıp image.png'yi dosyasını indireceğim
        //WebElement fotoElementi= driver.findElement(By.linkText("foto.png"));
        //fotoElementi.click();

        WebElement imageElementi= driver.findElement(By.linkText("image.png"));//locator ile gidilir
        imageElementi.click();//click ile indirilir
        Thread.sleep(3000);//indirme işlemi için biraz süreye ihtitaç olabilir

        //4. Dosyanın başarıyla indirilip indirilmediğini test edelim

         //click' ten sonra dosyayi Downloads (indirilenler) klasorune indirecek
        //Download (indirilenler) klasorunun dosya yolunu nasıl alabiliriz
        //Download klasöründe indirilenlerden image dosyasının üzerine sağ tıklayıp Özellikler seçilir/Konum
        //kopyalanıp ıntellij de  yapıştırılır ve sonuna image.png yazılır
        String dosyaYolu= "C:\\Users\\admin\\Downloads\\image.png";

        Assert.assertTrue(Files.exists(Paths.get(dosyaYolu)));


        // herkesin bilgisayarinda farkli bir dosya hiyerarsisi oldugundan
        // herkesin dosya yolu birbirinden farkli olur
        // Dosya yolunu dinamik hale getirmek icin Java'dan yardim alacagiz
        //C:\Users\emre.cigit\Downloads\foto.png"
        //"C:\Users\Berke\Downloads\foto.png"

        //Java'da dosyalarla ilgili 2 tane genel dosya yolu bilgisi var
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("user.home"));


    }
}
