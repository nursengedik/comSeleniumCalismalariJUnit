package seleniumJUnitSoruları;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C04_S93_CheckBox {

    /*
       Gerekli yapiyi olusturun ve aşağıdaki görevi tamamlayın.
       a. Verilen web sayfasına gidin.
          https://the-internet.herokuapp.com/checkboxes
       b. Checkbox1 ve checkbox2 elementlerini locate edin.
       c. Checkbox1 seçili değilse onay kutusunu tıklayın
       d. Checkbox2 seçili değilse onay kutusunu tıklayın
       e. Checkbox1ve Checkbox2’nin seçili olduğunu test edin
     */

     //bir tane test olduğu için bir method kullanılır ve @Before kullanılır
     //@Before'u static yapmak zorunda değiliz

     WebDriver driver;
     @Before
     public void setup(){
         WebDriverManager.chromedriver().setup();
         driver=new ChromeDriver();
         driver.manage().window().maximize();
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
     }

     @After
     public void teardown(){
         driver.close();
     }

     @Test
     public void checkBoxTest() throws InterruptedException {

         //a. Verilen web sayfasına gidin. https://the-internet.herokuapp.com/checkboxes
         driver.get("https://the-internet.herokuapp.com/checkboxes");

         //b.Checkbox1 ve checkbox2 elementlerini locate edin.
         WebElement checkBox1=driver.findElement(By.xpath("(//input[@type='checkbox'])[1]"));
         WebElement checkBox2=driver.findElement(By.xpath("(//input[@type='checkbox'])[2]"));
         Thread.sleep(2000);

         //c. Checkbox1 seçili değilse onay kutusunu tıklayın (olumsuz cümle)
         //aynı method içinde olduğumuz için ve checkBox1 locate edildiği için tekrar locate etmiyoruz
         if (!checkBox1.isSelected()){//isSelected()= seçili ise
             checkBox1.click();
         }
         Thread.sleep(2000);

         //d. Checkbox2 seçili değilse onay kutusunu tıklayın
         if (!checkBox2.isSelected()){
             checkBox2.click();
         }
         Thread.sleep(2000);

         //e. Checkbox1ve Checkbox2’nin seçili olduğunu test edin (olumlu cümle-true)
         Assert.assertTrue(checkBox1.isSelected() && checkBox2.isSelected());

     }

}
