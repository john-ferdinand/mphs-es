
package controller.schedule;

import component_editor.ScheduleFacultyCellEditor;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import view.schedule.Dialog_CreateSchedule;

/**
 *
 * @author Jordan
 */
public class KeyListener_LoadSubjectFacultyOnArrowKeyPressed_JTable implements KeyListener{
    
    private final Dialog_CreateSchedule createScheduleDialog;

    public KeyListener_LoadSubjectFacultyOnArrowKeyPressed_JTable(Dialog_CreateSchedule createScheduleDialog) {
        this.createScheduleDialog = createScheduleDialog;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN
                || e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (e.getSource() instanceof JTable) {
                JTable table = (JTable) e.getSource();
                TableColumn facultyColumn = table.getColumnModel().getColumn(4);
                facultyColumn.setCellEditor(new ScheduleFacultyCellEditor(table, createScheduleDialog));
            }
        }
    }
    
    
}
