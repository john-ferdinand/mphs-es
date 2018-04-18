
package controller.payment;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTable;
import view.payment.Dialog_SearchStudentByKeyword;

/**
 *
 * @author Jordan
 */
public class Controller_Dialog_SearchStudentByKeyword_JTable implements MouseListener, KeyListener{

    private final Dialog_SearchStudentByKeyword view;

    public Controller_Dialog_SearchStudentByKeyword_JTable(Dialog_SearchStudentByKeyword view) {
        this.view = view;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(view.getJtblStudents().getSelectedRowCount() > 0){
            JTable t = view.getJtblStudents();
            int selectedRow = t.getSelectedRow();
            String admissionStatus = t.getValueAt(selectedRow, 2).toString().trim();
            
            if(admissionStatus.equalsIgnoreCase("Pending") || admissionStatus.contains("Pen")){
                view.getJbtnLoad().setEnabled(false);
            }else if(admissionStatus.equalsIgnoreCase("Complete") || admissionStatus.contains("Complete")){
                view.getJbtnLoad().setEnabled(true);
            }
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

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP){
            if (view.getJtblStudents().getSelectedRowCount() > 0) {
                JTable t = view.getJtblStudents();
                int selectedRow = t.getSelectedRow();
                String admissionStatus = t.getValueAt(selectedRow, 2).toString().trim();

                if (admissionStatus.equalsIgnoreCase("Pending") || admissionStatus.contains("Pen")) {
                    view.getJbtnLoad().setEnabled(false);
                } else if (admissionStatus.equalsIgnoreCase("Complete") || admissionStatus.contains("Complete")) {
                    view.getJbtnLoad().setEnabled(true);
                }
            }
        }
    }
}
