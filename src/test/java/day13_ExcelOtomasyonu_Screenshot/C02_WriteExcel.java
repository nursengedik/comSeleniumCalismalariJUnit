package day13_ExcelOtomasyonu_Screenshot;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class C02_WriteExcel {

    //sonradan excel'e data eklemek

    @Test
    public void test01() throws IOException {
        //yeni bir cell oluşturmak için excel'in kopyasını almalıyız
        String dosyaYolu=System.getProperty("user.dir") + "/src/test/java/day12_webTables_exelOtomasyon/ulkeler.xlsx";
        FileInputStream fis=new FileInputStream(dosyaYolu);
        Workbook workbook= WorkbookFactory.create(fis);
        Sheet sheet=workbook.getSheet("Sayfa1");//sayfa1'e gitme

        //excel'de sınırsız satır olabilir ama bizim class'ımızda oluşturduğumuz workbook'da sadece data olan
        //satırları görebiliyoruz

        //4) 4.hucreye yeni bir cell olusturalim
        //5) Olusturdugumuz hucreye “Nufus” yazdiralim
        sheet.getRow(0).createCell(4).setCellValue("Nufus");

        //6) 2.satir nufus kolonuna 1500000 yazdiralim
        sheet.getRow(1).createCell(4).setCellValue("1500000");
        // 7) 10.satir nufus kolonuna 250000 yazdiralim
        sheet.getRow(9).createCell(4).setCellValue(250000);
        // 8) 15.satir nufus kolonuna 54000 yazdiralim
        sheet.getRow(14).createCell(4).setCellValue(54000);
        // 9) Dosyayi kaydedelim
       //nüfus sütunu oluşturma ve değerlerinin girilmesi class'ta yapıldı excel tablosunda değil
        //excel açıkken dosyaya birşey yazmamalıyız, bozulur, excel'i kapatmalıyız

        FileOutputStream fos= new FileOutputStream(dosyaYolu);//class'taki dataları excel'e aktarır
        workbook.write(fos);//workbook'daki değişiklikleri
        //excel'in kapalı olduğundan emin olduktan sonra çalıştırırız

        // 10)Dosyayi kapatalim
        fis.close();
        fos.close();
        workbook.close();


    }
}
