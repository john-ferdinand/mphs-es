
package controller.subjectcategory;

import component_model_loader.SubjectCategoryJCompModelLoader;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jordan
 */
public class DisplaySubjectCategoryAssignedSubjectsOnKeyPress implements KeyListener{
    
    private JTable jtblSubjectCategoryMasterList;
    private JTable jtblAssignedSubjects;
    private SubjectCategoryJCompModelLoader subjectCategoryJCompModelLoader;
    
    public DisplaySubjectCategoryAssignedSubjectsOnKeyPress(JTable jtblSubjectCategoryMasterList, JTable jtblAssignedSubjects){
        this.jtblSubjectCategoryMasterList = jtblSubjectCategoryMasterList;
        this.jtblAssignedSubjects = jtblAssignedSubjects;
        subjectCategoryJCompModelLoader = new SubjectCategoryJCompModelLoader();
    }

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
         
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP) {
            if (jtblSubjectCategoryMasterList.getSelectedRow() > -1) {
                DefaultTableModel tableModel = 
                        subjectCategoryJCompModelLoader.getSubjectCategoryAssignedSubjectsById(jtblSubjectCategoryMasterList,jtblAssignedSubjects);
                jtblAssignedSubjects.setModel(tableModel);
            }
        }
    }
    
    
    
}
