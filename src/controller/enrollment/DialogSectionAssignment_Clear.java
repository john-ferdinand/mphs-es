
package controller.enrollment;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import view.section.Dialog_SectionAssignment;

/**
 *
 * @author Jordan
 */
public class DialogSectionAssignment_Clear implements ActionListener{
    
    private final Dialog_SectionAssignment view;

    public DialogSectionAssignment_Clear(Dialog_SectionAssignment view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int choice = JOptionPane.showConfirmDialog(null, "This will clear data on your form. Continue?", "Clear Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (choice == JOptionPane.YES_OPTION) {
            clearForm();
        }
    }
    
    private void clearForm(){
        Component[] compArr = view.getJpnlSectionDetails().getComponents();
        for (Component c : compArr) {
            if (c instanceof JComboBox) {
                String actionCommand = ((JComboBox) c).getActionCommand().toString().trim();
                if (actionCommand.equalsIgnoreCase("session") || actionCommand.equalsIgnoreCase("section")
                        || actionCommand.equalsIgnoreCase("adviser") || actionCommand.equalsIgnoreCase("gradelevel")) {
                    ((JComboBox) c).setSelectedIndex(-1);
                }
            }
            else if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            }
        }
        ((DefaultTableModel)view.getJtblEnrolledStudents().getModel()).setRowCount(0);
        ((DefaultTableModel)view.getJtblSectionStudents().getModel()).setRowCount(0);
    }
    
}
