package day07_JUnit_dropdown;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class C04_DropdownMenu {

     //Dropdown özel bir web elementtir ve Selenium onun için özel Select class'ı oluşturmuştur
    //Dropdown menü, açılan menüler içerisinde özel olandır, her gördüğümüz açılır menü dropdown değildir
    //Bir açılır menünün dropdown olduğunu söyleyebilmemiz için;
    //1- <select tag'ıyla oluşturulmuş olması lazım
    //2-option (opşınların) yani seçildiğinde açılabilecek değerlerin </option> opşın taglarıyla oluşturulması lazım

     /*
       Dropdown'u locate etmek yeterli değildir, Dropdown'daki opsiyonlardan birini seçmek için 3 işlem yapılır
       1- Dropdown elementi locate edilir,
       2- locate ettiğimiz dropdown menüsünü parametre olarak kullanarak Select class'ından bir obje oluşturulur
       3- Select class'ında oluşturduğumuz obje ile sayfadaki menülerden seçim yapabiliriz
     */

    //● https://www.amazon.com/ adresine gidin.
    //- Test 1
    //	Arama kutusunun yanindaki kategori menusundeki kategori sayisinin 45 oldugunu test edin
    //-Test 2
    //	1. Kategori menusunden Books secenegini  secin
    //	2. Arama kutusuna Java yazin ve aratin
    //	3. Bulunan sonuc sayisini yazdirin
    //	4. Sonucun Java kelimesini icerdigini test edin

    static WebDriver driver;

    @BeforeClass  //bir kere sayfaya gidip tüm testleri yapmak için kullanılır
    public static void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://www.amazon.com/");
    }

    @AfterClass
    public static void teardown(){
        driver.close();
    }

    @Test
    public void test01(){
        //	Arama kutusunun yanindaki kategori menusundeki kategori sayisinin 45 oldugunu test edin

        // Element dropdown menu oldugu icin Select class'indan olusturacagimiz
        // obje ile ilgili method'lari kullanmaliyiz

        //öncelikle 3 adım yapılır
        //1. adım, dropdown menüsü locate (lokeyt) edilir
        WebElement dropdownMenuElementi= driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));

        //2. adım Select class ından hazır methodlar kullanmalıyız, hazır methodlar kullanabilmemiz için
        //Select class ında obje oluşturmalıyız, parametresi de web elementim olmalı, yani bu class a dropdwn nımızı
        //göndermeliyiz
        Select select = new Select(dropdownMenuElementi);

        //3. adım Select class ında oluşturduğumuz obje ile menüden seçim yapma
        int expectedOptionSayisi=45;
        int actualOptionSayisi= select.getOptions().size();
        //getOptions() opşınları liste şeklinde getirir, .size() dediğimizde sayısını getirir

        Assert.assertEquals(expectedOptionSayisi,actualOptionSayisi);//45 olduğu denildiği için assertEquals kullanılır
    }

    @Test
    public void test02(){
        WebElement dropdownMenuElementi= driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
        Select select = new Select(dropdownMenuElementi);

        //	1. Kategori menusunden Books secenegini  secin
        select.selectByVisibleText("Books");//görünür ismini ver

        //	2. Arama kutusuna Java yazin ve aratin
        //arama kutusu locate edilir
        WebElement aramaKutusu= driver.findElement(By.id("twotabsearchtextbox"));
        //arama kutusuna Java yazdırılır
        aramaKutusu.sendKeys("Java" + Keys.ENTER);

        //	3. Bulunan sonuc sayisini yazdirin
        WebElement sonucSayiElementi= driver.findElement(By.xpath("(//div[@class='sg-col-inner'])[1]"));
        System.out.println(sonucSayiElementi.getText());

        //	4. Sonucun Java kelimesini icerdigini test edin

        String expectedIcerik="Java";
        String actualSonucYazisi= sonucSayiElementi.getText();
        Assert.assertTrue(actualSonucYazisi.contains(expectedIcerik));
    }

}
