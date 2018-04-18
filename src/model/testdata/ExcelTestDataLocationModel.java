package model.testdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.database.DBUtil;

/**
 *
 * @author John Ferdinand Antonio
 */
public class ExcelTestDataLocationModel {

    private String fileLocation;
    private FileInputStream fis;

    public FileInputStream getFis() {
        return fis;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public ExcelTestDataLocationModel() throws FileNotFoundException{
        File f = new File(".\\src\\model\\testdata\\exceltestdata.xls");
        try {
            fileLocation = ".\\src\\model\\testdata\\exceltestdata.xls";
            fis = new FileInputStream(f);
            System.out.println("File Found");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
