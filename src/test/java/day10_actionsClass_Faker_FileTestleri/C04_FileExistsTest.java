package day10_actionsClass_Faker_FileTestleri;

import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

public class C04_FileExistsTest {

     /*
       Selenium ile bilgisayarımızdaki dosyalar ile ilgili testler yapamayız.
       Selenium WebDriver ile çalışır ve WebDriver ile web sayfasına gider,
       bir web elementi ile ilgili görevleri yapar.Selenium browser'lar ile
       ilgilidir.Ama Selenium'un bilgisayarımızdaki dosyalara erişim yetkisi
       vardır.Selenium'da bizim dosyalarla ne zaman işimiz olur?
       İndirilen  ya da yüklenen bir dosyayı test edeceğimiz zaman

       İndirilen bir dosyaya ulaşmak ya da bir siteye file yüklememiz için
       bilgisayarımızdaki dosyalara ihtiyacımız vardır.

       Javada, bilgisayarımızdaki bir dosya ile ilgili işlem yapmak istiyorsak
       öncelikle o dosyanın dosya yoluna ulaşabilmektir

     */

    @Test
    public void test01(){

        // com.Team113JUnit projesi icerisinde
        // deneme.txt dosyasinin varoldugunu test edin

        String dosyaYolu = "src/test/java/day10_actionsClass_Faker_FileTestleri/deneme.txt";

        //bir dosyanın var olduğunu test etme
        Assert.assertTrue(Files.exists(Paths.get(dosyaYolu)));//Files class'ından
                                                             //exists:var    Paths:yollar

    }
}
