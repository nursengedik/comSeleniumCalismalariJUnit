package seleniumJUnitSoruları;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.TestBase;

public class C10_S108_BasicAuthentication extends TestBase {


    //1- BasicAuthentication
    //2- https://the-internet.herokuapp.com/basic_auth sayfasina gidin
    //3- asagidaki yontem ve test datalarini kullanarak authentication’i yapin
    //
    //Html komutu : https://username:password@URL
    //	Username     : admin
    // 	password      : admin
    //
    //4- Basarili sekilde sayfaya girildigini dogrulayin

    //Bir test yapılacağı için @Before notasyonu tercih edilir ve TestBase extends ile parent edinilir


    @Test
    public void test01() throws InterruptedException {

        //2- https://the-internet.herokuapp.com/basic_auth sayfasina gidin
        driver.get("https://the-internet.herokuapp.com/basic_auth");

        //3- asagidaki yontem ve test datalarini kullanarak authentication’i yapin
        //
        //Html komutu : https://username:password@URL
        //	Username     : admin
        // 	password      : admin
        //değerler manuel olarak girilir

        //4- Basarili sekilde sayfaya girildigini dogrulayin
        //sayfaya başarıyla girildi yazısının görünürlüğü test edilecek, yazı locate edilir
        WebElement congratYaziElementi=driver.findElement(By.tagName("p"));
        Assert.assertTrue(congratYaziElementi.isDisplayed());

        Thread.sleep(5000);
    }






}
