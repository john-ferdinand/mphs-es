package controller.subjectcategory;

import component_model_loader.SubjectCategoryJCompModelLoader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jordan
 */
public class SearchSubjectCategoryByWildCard implements ActionListener{
    
    private final JTextField jtfSearchBox;
    private final JTable jtblSubjectCategoryMasterList;
    private SubjectCategoryJCompModelLoader subjectCategoryJCompModelLoader;
    
    public SearchSubjectCategoryByWildCard(JTextField jtfSearchBox, JTable jtblSubjectCategoryMasterList){
        this.jtfSearchBox = jtfSearchBox;
        this.jtblSubjectCategoryMasterList = jtblSubjectCategoryMasterList;
        subjectCategoryJCompModelLoader = new SubjectCategoryJCompModelLoader();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (jtfSearchBox.getText().trim().isEmpty()) {
            DefaultTableModel tableModel = subjectCategoryJCompModelLoader.getAllSubjectCategoryInfo(jtblSubjectCategoryMasterList);
            jtblSubjectCategoryMasterList.setModel(tableModel);
        } else {
            DefaultTableModel tableModel = subjectCategoryJCompModelLoader.getAllSubjectCategoryInfoByWildCard(jtfSearchBox, jtblSubjectCategoryMasterList);
            jtblSubjectCategoryMasterList.setModel(tableModel);
        }
    }
    
    
}
