
package controller.enrollment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import utility.jtable.JTableUtil;
import view.section.Dialog_SectionAssignment;

/**
 *
 * @author Jordan
 */
public class DialogSectionAssignment_RemoveStudentFromSection implements ActionListener{

    private final Dialog_SectionAssignment view;

    public DialogSectionAssignment_RemoveStudentFromSection(Dialog_SectionAssignment view) {
        this.view = view;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JTable tableFrom = view.getJtblSectionStudents();
        JTable tableTo = view.getJtblEnrolledStudents();
        JTableUtil.moveRowData(tableFrom, tableTo);
    }
}
