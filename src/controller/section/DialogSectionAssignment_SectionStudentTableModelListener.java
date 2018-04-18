
package controller.section;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import view.section.Dialog_SectionAssignment;

/**
 *
 * @author Jordan
 */
public class DialogSectionAssignment_SectionStudentTableModelListener implements TableModelListener{

    private final Dialog_SectionAssignment view;

    public DialogSectionAssignment_SectionStudentTableModelListener(Dialog_SectionAssignment view) {
        this.view = view;
    }
    
    @Override
    public void tableChanged(TableModelEvent e) {
        int rowCount = view.getJtblSectionStudents().getRowCount();
        view.getJtfCapacityNumerator().setText(""+rowCount);
    }
    
    
}
