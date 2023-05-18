package day13_ExcelOtomasyonu_Screenshot;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import utilities.TestBase;

import java.io.File;
import java.io.IOException;

public class C03_TumSayfaScreenshot extends TestBase {

    @Test
    public void test01() throws IOException {


        // amazon anasayfaya gidelim
        driver.get("https://www.amazon.com");

        // Nutella aratalim
        WebElement aramaKutusu= driver.findElement(By.id("twotabsearchtextbox"));
        aramaKutusu.sendKeys("Nutella" + Keys.ENTER);

        //Sonuçların Nutella içerdiğini test edin
        WebElement sonucElementi=driver.findElement(By.xpath("(//div[@class='sg-col-inner'])[1]"));
        String actualSonucYazisi=sonucElementi.getText();
        String expectedIcerik="Nutella";
        Assert.assertTrue(actualSonucYazisi.contains(expectedIcerik));

        //rapora eklenmek üzere, tüm sayfanın ekran görüntüsünü alalım

        TakesScreenshot tss= (TakesScreenshot) driver;//driver'ı cast ederiz

        File tumSayfaResim=new File("target/ekranResimleri/tumSayfaResim.png");
        //target altında ekran resimleri klasörü oluştursun ve adı tumsayfaresim olsun

        //tss objesini kullanarak geçici bir dosyaya resmi çekmek
        File geciciResim=tss.getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(geciciResim,tumSayfaResim);//geçici resmi tumSayfaResim'e kaydederiz

    }
}
