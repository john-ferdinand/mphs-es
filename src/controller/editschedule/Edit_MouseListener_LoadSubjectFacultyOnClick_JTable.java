
package controller.editschedule;

import celleditor.editschedule.EditScheduleFacultyCellEditor;
import component_editor.ScheduleFacultyCellEditor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import view.schedule.Dialog_CreateSchedule;
import view.schedule.Dialog_EditSchedule;

/**
 *
 * @author Jordan
 */
public class Edit_MouseListener_LoadSubjectFacultyOnClick_JTable implements MouseListener{

    private final Dialog_EditSchedule editScheduleDialog;

    public Edit_MouseListener_LoadSubjectFacultyOnClick_JTable(Dialog_EditSchedule editScheduleDialog) {
        this.editScheduleDialog = editScheduleDialog;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof JTable) {
            JTable table = (JTable) e.getSource();
            TableColumn facultyColumn =  table.getColumnModel().getColumn(4);
            facultyColumn.setCellEditor(new EditScheduleFacultyCellEditor(table,editScheduleDialog));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    
    
}
