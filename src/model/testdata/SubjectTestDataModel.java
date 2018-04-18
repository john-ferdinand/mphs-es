package model.testdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 *
 * @author John Ferdinand Antonio
 */
public class SubjectTestDataModel {

    private ArrayList<String> subject;
    private ArrayList<String> code;
    private ArrayList<String> description;

    public SubjectTestDataModel() {
        populateData();
    }

    public ArrayList<String> getSubject() {
        return subject;
    }

    public ArrayList<String> getCode() {
        return code;
    }

    public ArrayList<String> getDescription() {
        return description;
    }

    private void populateData() {
        try {
            ExcelTestDataLocationModel e = new ExcelTestDataLocationModel();
            File xlsFile = new File(e.getFileLocation());
            Workbook wb = Workbook.getWorkbook(xlsFile);
            Sheet ws = wb.getSheet(0);

            subject = new ArrayList<>();
            code = new ArrayList<>();
            description = new ArrayList<>();
            
            int colSubject = 0;
            int colSubjectCode = 1;
            int colDescription = 2;
            int colGradeLevel = 3;

            for (int rowIndex = 1; rowIndex < ws.getRows(); rowIndex++) {
                String s = ws.getCell(colSubject, rowIndex).getContents();
                String sCode = ws.getCell(colSubjectCode, rowIndex).getContents();
                String sDescription = ws.getCell(colDescription, rowIndex).getContents();

//                System.out.print("Subject :"+s +" ");
//                System.out.print("Code: "+sCode+ " ");
//                System.out.print("Description :"+sDescription+" "+"\n");
                
                
                subject.add(s);
                code.add(sCode);
                description.add(sDescription);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(SubjectTestDataModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BiffException ex) {
            ex.printStackTrace();
            Logger.getLogger(SubjectTestDataModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
