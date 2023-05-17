package day12_webTables_exelOtomasyon;

import org.apache.poi.ss.usermodel.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class C03_ReadExcel {

    @Test
    public void test01() throws IOException {

        /*
        Excel icin daha once inceledigimiz Web Table yapisina benzer bir yapi vardir.
        Excel’de bir hucredeki bilgiye ulasmak icin :
        dosya/sayfa/satir/sutun sirasiyla ilerlemeliyiz
        Excel ile ilgili otomasyonda web table’da oldugu gibi sutun yapisi yoktur,
        ihtiyac duyarsak kodla sutunu elde edebiliriz ancak bir dataya ulasmak
        icin sutun kullanamayiz

       Workbook       excel dosyamiz
       Sheet          Her açık excel sekmesi (Sheet1, etc)
       Row(satir)     Java, yalnızca içeride veri varsa satırları sayar. Default olarak, Java
                      perspektifinden satır sayısı 0'dır
       Cells (hucre)  Java her satıra bakar ve yalnızca hücrede veri varsa hücre sayısını sayar.
         */

        //öncelikle mvnrepository.com'dan apache poi ve poi-ooxml sdosyaları pom.xml'e yüklenir

        // bilgisayarimizda olan bir dosyaya erisebilmek icin FileInputStream Class'indan obje olusturalim
        String dosyaYolu = System.getProperty("user.dir")+ "/src/test/java/day12_webTables_excelOtomasyon/ulkeler.xlsx";

        FileInputStream fis = new FileInputStream(dosyaYolu);

        // projemize ekledigimiz POI kutuphanelerini kullanarak
        // class'imizin icinde istenen excel'in bir kopyasini olusturalim

        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheet("Sayfa1");
        Row row = sheet.getRow(1); // index 0'dan basliyor - sayfa1 den 1. satır a gider
        Cell cell = row.getCell(3);// index 0'dan basliyor - satırdan data'ya gider

        System.out.println(cell);//cell bilgisini yazdırabilsek de bir string değil, cell bilgisinin data türü Cell

        // 5.index'deki satirin, 1.index'indeki datanin "Luanda" oldugunu test edin
       //tekrar workbook ve sheet oluşturmamıza gerek yok

        row= sheet.getRow(5);
        cell= row.getCell(1);

        String expectedData= "Luanda";

        // cell objesinin data turu Cell
        // Java'da farkli data turlerindeki variable'lari equals ile KULLANAMAYIZ
        Assert.assertEquals(expectedData,cell.toString());//cell'in data türü Cell olduğu için toString() methodu ile
                                                         //stringe çeviririz

        // 12.satir,2.sutundaki bilginin "Baku" oldugunu test edin

        String actualData= sheet.getRow(11).getCell(1).toString();//sayfa, satır ve sütunu aynı satırda da
        expectedData = "Baku";                                        //oluşturabiliriz

        Assert.assertEquals(expectedData,actualData);



    }
}
