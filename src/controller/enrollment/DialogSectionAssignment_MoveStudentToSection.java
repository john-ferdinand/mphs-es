
package controller.enrollment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import utility.jtable.JTableUtil;
import view.section.Dialog_SectionAssignment;

/**
 *
 * @author Jordan
 */
public class DialogSectionAssignment_MoveStudentToSection implements ActionListener{

    private final Dialog_SectionAssignment view;

    public DialogSectionAssignment_MoveStudentToSection(Dialog_SectionAssignment view) {
        this.view = view;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JTable jtblEnrolledStudents = view.getJtblEnrolledStudents();
        JTable jtblSectionStudents = view.getJtblSectionStudents();
        if(!exceedsCapacity()){
            JTableUtil.moveRowData(jtblEnrolledStudents, jtblSectionStudents);
        }else{
            JOptionPane.showMessageDialog(null,"Total of selected students exceeds section capacity. Please try agian.");
        }
        
    }
    
    private boolean exceedsCapacity(){
        boolean exceeds = true;
        int countOfStudentsToMove = view.getJtblEnrolledStudents().getSelectedRows().length;
        int countOfStudentsInSection = view.getJtblSectionStudents().getRowCount();
        int sectionCapacity = Integer.parseInt(view.getJtfCapacityDenominator().getText().trim());
        if( (countOfStudentsToMove + countOfStudentsInSection) <= sectionCapacity){
            exceeds = false;
        }
        return exceeds;
    }
    
    
}
