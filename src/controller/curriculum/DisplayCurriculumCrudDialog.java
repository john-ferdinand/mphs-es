
package controller.curriculum;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import view.curriculum.Dialog_CurriculumCrud;
import view.subject.DialogSubjectCrud;

/**
 *
 * @author Jordan
 */
public class DisplayCurriculumCrudDialog implements ActionListener{
    
    private final JTable jtblCurriculumMasterRecord;
    
    public DisplayCurriculumCrudDialog(JTable jtblCurriculumMasterRecord){
        this.jtblCurriculumMasterRecord = jtblCurriculumMasterRecord;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            String action = getAction(e);
            String dialogTitle = getDialogTitle(e);
            
            if (action.equalsIgnoreCase("create")) {
                displayDialog(dialogTitle, action);
            } else if (action.equalsIgnoreCase("edit") || action.equalsIgnoreCase("view")) {
                validateSelection(dialogTitle, action);
            }
    }

    private String getDialogTitle(ActionEvent e) {
        String dialogTitle = e.getActionCommand().equalsIgnoreCase("create") ? "Create New Curriculum"
                : e.getActionCommand().equalsIgnoreCase("edit") ? "Edit Curriculum"
                : e.getActionCommand().equalsIgnoreCase("view") ? "Curriculum Info(View)"
                : e.getActionCommand().equalsIgnoreCase("print") ? "Print" : "";
        return dialogTitle;
    }

    private String getAction(ActionEvent e) {
        String action = e.getActionCommand().equalsIgnoreCase("create") ? "create"
                : e.getActionCommand().equalsIgnoreCase("edit") ? "edit"
                : e.getActionCommand().equalsIgnoreCase("view") ? "view"
                : e.getActionCommand().equalsIgnoreCase("print") ? "print" : "";
        return action;
    }

    private boolean hasCurriculumSelected() {
        boolean hasCurriculumSelected = false;
        if (jtblCurriculumMasterRecord.getSelectedRow() > -1) {
            hasCurriculumSelected = true;
        }
        return hasCurriculumSelected;
    }

    private void validateSelection(String dialogTitle, String action) {
        if (hasCurriculumSelected()) {
            displayDialog(dialogTitle, action);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a curriculum from the list.");
        }
    }

    private void displayDialog(String dialogTitle, String action) {
        if (action.equals("edit") || action.equals("view")) {
            if (hasCurriculumSelected()) {
                int curriculumIdColIdx = 0;
                int rowSelected = jtblCurriculumMasterRecord.getSelectedRow();
                int curriculumId = Integer.parseInt(jtblCurriculumMasterRecord.getValueAt(rowSelected, curriculumIdColIdx).toString());
                Dialog_CurriculumCrud dialogCurriculumCrud = new Dialog_CurriculumCrud(null, true, action, curriculumId);
                dialogCurriculumCrud.setTitle(dialogTitle);
                dialogCurriculumCrud.setLocationRelativeTo(null);
                dialogCurriculumCrud.setVisible(true);
            }
        } else {
            Dialog_CurriculumCrud dialogCurriculumCrud = new Dialog_CurriculumCrud(null, true, action);
            dialogCurriculumCrud.setTitle(dialogTitle);
            dialogCurriculumCrud.setLocationRelativeTo(null);
            dialogCurriculumCrud.setVisible(true);
        }
    }
    
}
