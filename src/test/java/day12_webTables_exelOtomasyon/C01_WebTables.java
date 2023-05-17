package day12_webTables_exelOtomasyon;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.TestBase;

import java.util.List;

public class C01_WebTables extends TestBase {

    @Test
    public void test01() throws InterruptedException {
        //1."https://www.amazon.com" adresine gidin
        driver.get("https://www.amazon.com");

        //2.Sayfanin en altina inin
        //manuel olarak sayfanın altına END tuşu ile ineriz
        //maus ve klavyenin işlevlerini driver'a yaptırabilmek için Actions objesi oluşturulur ve actions objesi ile
        //maus ve klavye methodları kullanılır
        Actions actions= new Actions(driver);
        actions.sendKeys(Keys.END).perform();

        //3.Web table tum body’sini yazdirin
        Thread.sleep(2000);
        WebElement tumBodyElementi= driver.findElement(By.tagName("tbody"));//tüm Body'i tek element olarak locate ettik
        System.out.println(tumBodyElementi.getText());//webelementi direk yazdıramayız, sout ile
        // tum Body'yi tek element olarak locate etmek
        // body icinde gecen bir metnin varligini test etmek gibi gorevler icin kullanilabilir

       //4.Web table’daki satir sayisinin 9 oldugunu test edin
        //satırlar tr'la oluşturulur html kodlarında tbody/tr (tbody altında tr) dediğimizde 9 tane olduğu görünür
        //herbir satırı locate edip bir list'e koyarız
       List<WebElement> satirElementleriList = driver.findElements(By.xpath("//tbody/tr"));
       int expectedSatirSayisi=9;
       int actualSatirsayisi= satirElementleriList.size();
       Assert.assertEquals(expectedSatirSayisi,actualSatirsayisi);

       //5.Tum satirlari yazdirin
       System.out.println("============================");
       //List'i for ile yazdırırız
       for (WebElement eachSatir:satirElementleriList //webelementlerden oluşan herbir each'i
       ) {                                           //satirElementleriList'inden alıp
           System.out.println(eachSatir.getText());//herbir satırı alıp getText() ile üzerindeki yazıları alacağım
           System.out.println("============================");
       }
      //6.Web table’daki sutun sayisinin 13 olduğunu test edin (yazılı sütunlar arasındaki boşluklar da sütun sayılıyor)
        //html kodlarında locator alınırken //tbody/tr/td bize tüm satırlardaki tüm sütunları verir(69)
        //biz sadece 1. satırdaki alarak sütun sayısını bulmuş oluruz
       List<WebElement> birinciSatirDatalari=driver.findElements(By.xpath("//tbody/tr[1]/td"));
       int expectedSutunSayisi=13;
       int actualSutunSayisi=birinciSatirDatalari.size();
       Assert.assertEquals(expectedSutunSayisi,actualSutunSayisi);

      //7. 5.sutunu yazdirin
      System.out.println("-----------------------------------");
      List<WebElement> besinciSutunElementleri = driver.findElements(By.xpath("//tbody/tr/td[5]"));
      //webelement list'ini for ile yazdırırız
      for (WebElement eachData:besinciSutunElementleri
      ) {
           System.out.println(eachData.getText());
       }

      //8.Satir ve sutun sayisini parametre olarak alip,
      //  hucredeki bilgiyi döndüren bir method olusturun

      System.out.println("................................");
      System.out.println(dataGetir(1,9));//data 1,9 daki veriyi getirip yazdır
        //böyle bir methodumuz olmadığı için method oluştururuz
    }



    private String dataGetir(int satirNo, int sutunNo) {

        // //tbody/tr[5]/td[9]  dinamik olması için locator'ı method parametresine göre düzenleriz
        String dinamikXpath = "//tbody/tr["+satirNo+"]/td["+sutunNo+"]";

        WebElement istenenDataElementi= driver.findElement(By.xpath(dinamikXpath));

        return istenenDataElementi.getText();//döndürürüz
    }
}
