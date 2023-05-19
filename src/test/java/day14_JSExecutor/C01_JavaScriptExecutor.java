package day14_JSExecutor;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import utilities.TestBase;

public class C01_JavaScriptExecutor extends TestBase {

    @Test
    public void test01() throws InterruptedException {

        // wisequarter anasayfaya gidin
        driver.get("https://www.wisequarter.com");

        // asagiya inerek Software Development Engineer in Test - SDET
        // kursunda Explore the Course butonuna basin

        //normalde wisequarter sayfasına gidip pgDown tuşu ile sayfanın aşağısına inmeliyiz
        //bir sayfaya gittiğimizde driver sayfanın alt kısmında element olsa bile elementi görmediği için test
        //failed der, bazı web sayfaları driver'ın elementi göremese de işlem yapmasına müsade eder işlemi
        //sonlandırmaz, driver elementi bulamadığı durumlarda Java Scrip Executor'u kullanırız

        WebElement exploreButonu= driver.findElement(By.xpath("(//a[@class='elementskit-btn whitespace--normal'])[2]"));

        JavascriptExecutor jse = (JavascriptExecutor) driver;

        // jse ile butona kadar scroll (taslak) yapalim
        //scroll'lar birer kod parçacığıdır
        jse.executeScript("arguments[0].scrollIntoView();",exploreButonu);
        //bu obje (exploreButonu) görünene kadar scroll yap
        //"arguments[0].scrollIntoView();" scroll'u ile aşağıya kadar iner

        // jse ile click yapalim
        jse.executeScript("arguments[0].click();",exploreButonu);

        // jse ile alert olusturma
        jse.executeScript("alert('Bu is BU KADAR');");
        //testi yaparken sayfada görünmesini istediğimiz bir uyarı alerti de oluşturabiliriz

        Thread.sleep(5000);

        //
    }
}
