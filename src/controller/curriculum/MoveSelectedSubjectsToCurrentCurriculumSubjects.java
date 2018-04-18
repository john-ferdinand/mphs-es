
package controller.curriculum;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import utility.jtable.JTableUtil;

/**
 *
 * @author Jordan
 */
public class MoveSelectedSubjectsToCurrentCurriculumSubjects implements ActionListener{

    private JTable jtblSubjectList;
    private JTable jtblCurrentSubjects;
    
    public MoveSelectedSubjectsToCurrentCurriculumSubjects(JTable jtblSubjectList,JTable jtblCurrentSubjects) {
        this.jtblSubjectList = jtblSubjectList;
        this.jtblCurrentSubjects = jtblCurrentSubjects;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        moveSubjects();
    }

    private void moveSubjects(){
        if (!jtblSubjectList.getSelectionModel().isSelectionEmpty()) {
            int[] selectedRows = jtblSubjectList.getSelectedRows();
            JTableUtil.moveRowData(jtblSubjectList, jtblCurrentSubjects);
        }else{
            JOptionPane.showMessageDialog(null,"Please select subject(s) from Subject List");
        }
    }
    
    
}
