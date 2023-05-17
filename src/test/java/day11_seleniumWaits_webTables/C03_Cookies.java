package day11_seleniumWaits_webTables;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import utilities.TestBase;

import java.util.Set;
import java.util.SimpleTimeZone;

public class C03_Cookies extends TestBase {

    @Test
    public void test01(){
        //1- Amazon anasayfaya gidin
        driver.get("https://www.amazon.com");
        //2- tum cookie’leri listeleyin
        Set<Cookie> cookiesSet = driver.manage().getCookies();
        System.out.println(cookiesSet);//Set direk yazdırılabilir ama hangisinin kaçıncı cookies olduğu belli olmaz
        int siraNo=1;

        for (Cookie eachCookie : cookiesSet
        ) {
            System.out.println(siraNo+"------" + eachCookie);
            siraNo++;
        }
        //3- Sayfadaki cookies sayisinin 5’den buyuk oldugunu test edin
        int expectedMinCookieSayisi = 5;
        int actualCookiSayisi = cookiesSet.size();
        Assert.assertTrue(actualCookiSayisi>expectedMinCookieSayisi);

        //4- ismi i18n-prefs olan cookie degerinin USD oldugunu test edin
        String actualCookieValue = driver.manage().getCookieNamed("i18n-prefs").getValue();
        String expectedCookieValue= "USD";
        Assert.assertEquals(expectedCookieValue,actualCookieValue);

        //5- ismi “en sevdigim cookie” ve degeri “cikolatali” olan bir cookie olusturun ve sayfaya ekleyin
        //yeni bir cookies oluşturuyoruz
        Cookie eklenecekCookie= new Cookie("en sevdigim cookie","cikolatali");
        driver.manage().addCookie(eklenecekCookie);//cookies'i ekledik

        //6- eklediginiz cookie’nin sayfaya eklendigini test edin
        siraNo=1;
        cookiesSet = driver.manage().getCookies();//tüm cookis'leri aldık vr for ile yazdırırız
        String enSevdigimCookieValue = "";

        for (Cookie eachCookie : cookiesSet //bütün cookies'leri elden geçiriyorum
        ) {
            System.out.println(siraNo+"------" + eachCookie);//ve yazdırırken
            if (eachCookie.getName().equals("en sevdigim cookie")){//en sevdiğim cookie isimli cookie bulursan
                enSevdigimCookieValue = eachCookie.getValue();//değerini kaydet
            }
            siraNo++;
        }

        expectedCookieValue= "cikolatali";//beklenen
        Assert.assertEquals(expectedCookieValue,enSevdigimCookieValue);//Set'ten alınan ile bekleneni test ettik

        //7- ismi skin olan cookie’yi silin ve silindigini test edin

        driver.manage().deleteCookieNamed("skin");//ismi skin olan cookie'yi sildi
        siraNo=1;
        cookiesSet = driver.manage().getCookies();
        //skin isimli cookie'yenin olmadığını nasıl test ederiz
        String flag = "skin isminde bir cookie yok";//yokluğu test etmenin en iyi yolu

        for (Cookie eachCookie : cookiesSet
        ) {
            System.out.println(siraNo+"------" + eachCookie);//ele aldığımız ve yazdırdığımız her bir cookie'den
            if (eachCookie.getName().equals("skin")){//eğer ismi skin ise
                flag="skin isminde cookie bulundu";//varsa bu satırı yazdırır
            }
            siraNo++;
        }

        Assert.assertTrue(flag.equals("skin isminde bir cookie yok"));//yoksa flag'ın bu olduğunu doğrula

        //8- tum cookie’leri silin ve silindigini test edin
        driver.manage().deleteAllCookies();
        cookiesSet = driver.manage().getCookies();
        //tamamının silindiğini nasıl test edriz
        Assert.assertEquals(cookiesSet.size(), 0);


    }
}
