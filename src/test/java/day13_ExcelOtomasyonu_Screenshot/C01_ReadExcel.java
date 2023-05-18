package day13_ExcelOtomasyonu_Screenshot;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class C01_ReadExcel {

    //bilgisayardaki dosya ile çalışma yapacağımız için bir webdriver'a ihtiyacımız yok
    //bu nedenle extends yapmamıza gerek yok
    //dosyaya ulaşabilmemiz için önce dosya yolunu oluştururuz


    public void test01() throws IOException {

        String dosyaYolu = System.getProperty("user.dir") + "/src/test/java/day12_webTables_exelOtomasyon/ulkeler.xlsx";
        //tüm dosya yolunu elde etmek için
        //ser.dir bilgisayardan projeye kadar olan kısmı verir
        //daha sonra excel'in yolunu Path From Content Root ile kopyala yapıştır yaparız
        //dosya yolu javanın o dosyayı nerede bulacağı için önemli

        //dosyaya ulaşmak için
        FileInputStream fis = new FileInputStream(dosyaYolu);

        //bilgisayarımızdaki excel dosyasının bir kopyasını projemizde kodlarla oluştururuz
        Workbook workbook = WorkbookFactory.create(fis);//workbook üzerinden sayfaya
        //workbook interface olduğu için contructor'ı olmaz new Workbook diyemeyiz WorkbookFactory'den
        //create (oluştur) deriz

        //ayrı ayrı workbook,sheet, row ve cell objeleri oluşturmak yerine tek satırda oluşturduk
        String actualIsim = workbook
                .getSheet("Sayafa1")
                .getRow(15)
                .getCell(2)
                .toString();//toString() methodu ile de string yaparız

        String expectedIsim = "Bardabos";
        Assert.assertEquals(expectedIsim, actualIsim);

        // Excel'de ingilizce ismi Benin olan bir ulke
        // olup olmadigini test edin

        //Benin diye bir ülkenin olduğunu görebilmek için bütün satırlardaki ülke isimlerine bakmamız lazım

        int sonSatırIndexi = workbook.getSheet("Sayfa1").getLastRowNum();
        System.out.println(sonSatırIndexi);
        boolean beninVarMi = false;//birden fazla satırı kontrol ediyorsak hiçbir satırda Benin olmazsa testimiz
        //failed olacak, bu durumda bir flag (boolean) oluştururlur

        for (int i = 1; i <= sonSatırIndexi; i++) {

            actualIsim = workbook   // actualIsim her satırdaki ingilizce ismi alacak
                    .getSheet("Sayafa1")
                    .getRow(i)
                    .getCell(0)
                    .toString();

            if (actualIsim.equalsIgnoreCase("Benin")) {
                beninVarMi = true;
                break;
            }

        }

        Assert.assertTrue(beninVarMi);


        // Sayfa2'de kullanilan satir sayisinin 7 oldugunu test edin

        int kullanılanSatırSayisi = workbook
                .getSheet("Sayfa2")
                .getPhysicalNumberOfRows();

        int expectedSatirSayisi = 7;
        Assert.assertEquals(expectedSatirSayisi, kullanılanSatırSayisi);


        //Bir excel'de son satır numarasını getLastRowNum() ile,
        //fiziki olarak kullanılan satır numarasına kadar aradaki boşlukları çıktıktan sonraki
        //gerçek satır numarasını görmek istiyorsak getPhysicalNumberOfRows() kullanırız


        // Sayfa1'deki tum ulke isimlerini
        // ve tum bilgileri bir Map olarak kaydedin
        // ingilizce isim key, geriye kalanlar ise birlestirilerek value olsun

        Map<String, String> ulkelerMap = new TreeMap<>();//ülke isimlerine göre sıralayacak
        Sheet sheet=workbook.getSheet("Sayfa1");

        for (int i = 1; i <=sonSatırIndexi ; i++) {//her satırdaki datayı alır,

            String key=sheet.getRow(i).getCell(0).toString();//0. index -   key ve

            String value=sheet.getRow(i).getCell(1).toString()+ ", " +   //value'ye çevirip
                         sheet.getRow(i).getCell(2).toString()+ ", " +
                         sheet.getRow(i).getCell(3).toString();

            ulkelerMap.put(key,value);// map'e atar
        }

        System.out.println(ulkelerMap);//map'i yazdırırız

        // Ismi Netherlands olan ulke var mi test edin

        Assert.assertTrue(ulkelerMap.containsKey("Netherlands"));


    }
}
