
package controller.curriculum;

import component_model_loader.CurriculumJCompModelLoader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jordan
 */
public class SearchCurriculumByWildCard implements ActionListener{
    private JTextField jtfSearchBox;
    private JTable jtblCurriculumMasterRecord;
    
    private CurriculumJCompModelLoader curriculumJCompModelLoader;
    
    public SearchCurriculumByWildCard(JTextField jtfSearchBox, JTable jtblCurriculumMasterRecord){
        this.jtfSearchBox = jtfSearchBox;
        this.jtblCurriculumMasterRecord = jtblCurriculumMasterRecord;
        curriculumJCompModelLoader = new CurriculumJCompModelLoader();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        loadRecord();
    }

    private void loadRecord(){
        DefaultTableModel tableModel = curriculumJCompModelLoader.getCurriculumByWildCard(jtfSearchBox, jtblCurriculumMasterRecord);
        jtblCurriculumMasterRecord.setModel(tableModel);
    }
}
