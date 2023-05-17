package day08_testBase_JSalerts_iframe;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.TestBase;

public class C03_BasicAuthentication extends TestBase {

    //@Before method mu @BeforeClass mı? Bizden kaç test methodu isteniyorsa ona göre karar veririz (1 test)
    //1 test methodu istendiği için ikisi de kullanılabilir farketmez ama bir den fazla methodlarda @BeforeClass
    //tercih edilir, çünkü @Before notasyonu her methodda sayfayı açıp kapatacaktır
    //@Before notasyonu seçildiği için TestBase extends ile parent edinilir
    //@BeforeClass tercih edilmiş olsaydı TestBaseBeforeClass extends ile parent edinilirdi


   /*
     Authentication Nedir?
     Kısaca, herhangi bir internet kullanıcısının, uygulamanın
     ya da programın, söz konusu sisteme dahil olup
     olamayacağını belirleyen formu ifade eder
   */


    //1- BasicAuthentication
    //2- https://the-internet.herokuapp.com/basic_auth sayfasina gidin
    //3- asagidaki yontem ve test datalarini kullanarak authentication’i yapin
    //
    //Html komutu : https://username:password@URL
    //	Username     : admin
    // 	password      : admin
    //
    //4- Basarili sekilde sayfaya girildigini dogrulayin

    @Test
    public void test01() throws InterruptedException {

        //2- https://the-internet.herokuapp.com/basic_auth sayfasina gidin
        //3- asagidaki yontem ve test datalarini kullanarak authentication’i yapin
        //
        //Html komutu : https://username:password@URL
        //	Username     : admin
        // 	password      : admin
        //

        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");

        //4- Basarili sekilde sayfaya girildigini dogrulayin
        //sayfaya başarıyla girildi yazısının görünürlüğü test edilecek
        //congrat=tebrikler demek
        WebElement congratyaziElementi = driver.findElement(By.tagName("p"));
        Assert.assertTrue(congratyaziElementi.isDisplayed());

        Thread.sleep(5000);

    }
}
