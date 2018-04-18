
package controller.subject;

import component_model_loader.SubjectJCompModelLoader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jordan
 */
public class SearchSubjectByWildCard implements ActionListener{
    
    private JTable jtblSubjectMasterList;
    private JTextField jtfSearchBox;
    
    private SubjectJCompModelLoader subjectJCompModelLoader;
    
    public SearchSubjectByWildCard(JTable jtblSubjectMasterList,JTextField jtfSearchBox){
        subjectJCompModelLoader = new SubjectJCompModelLoader();
        this.jtblSubjectMasterList = jtblSubjectMasterList;
        this.jtfSearchBox = jtfSearchBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        loadDataToTable();
    }
    
    private void loadDataToTable(){
        if(jtfSearchBox.getText().isEmpty()){
            DefaultTableModel tableModel = subjectJCompModelLoader.getAllSubjectsInfo(jtblSubjectMasterList);
            jtblSubjectMasterList.setModel(tableModel);
        }else{
            DefaultTableModel tableModel = subjectJCompModelLoader.getSubjectBySearchKeyword(jtblSubjectMasterList, jtfSearchBox);
            jtblSubjectMasterList.setModel(tableModel);
        }
        
    }
}
