package controller.subject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import view.subject.DialogSubjectCrud;

/**
 *
 * @author Jordan
 */
public class DisplaySubjectCrudDialog implements ActionListener {

    private final JTable jtblSubjectMasterList;
    private final String actionCommand;

    public DisplaySubjectCrudDialog(JTable jtblSubjectMasterList, String actionCommand) {
        this.jtblSubjectMasterList = jtblSubjectMasterList;
        this.actionCommand = actionCommand;
    }

   @Override
    public void actionPerformed(ActionEvent e) {
//        if (hasSubjectSelected()) {
            String action = getAction(e);
            String dialogTitle = getDialogTitle(e);
            
            if (action.equalsIgnoreCase("create")) {
                displayDialog(dialogTitle, action);
            } else if (action.equalsIgnoreCase("edit") || action.equalsIgnoreCase("view")) {
                validateSelection(dialogTitle, action);
            }
//        }
    }

    private String getDialogTitle(ActionEvent e) {
        String dialogTitle = e.getActionCommand().equalsIgnoreCase("create") ? "Create New Subject"
                : e.getActionCommand().equalsIgnoreCase("assignws") ? "Assigned Weighted Score"
                : e.getActionCommand().equalsIgnoreCase("edit") ? "Edit Subject"
                : e.getActionCommand().equalsIgnoreCase("view") ? "Subject Info(View)"
                : e.getActionCommand().equalsIgnoreCase("print") ? "Print" : "";
        return dialogTitle;
    }

    private String getAction(ActionEvent e) {
        String action = e.getActionCommand().equalsIgnoreCase("create") ? "create"
                : e.getActionCommand().equalsIgnoreCase("assignws") ? "assignws"
                : e.getActionCommand().equalsIgnoreCase("edit") ? "edit"
                : e.getActionCommand().equalsIgnoreCase("view") ? "view"
                : e.getActionCommand().equalsIgnoreCase("print") ? "print" : "";
        return action;
    }

    private boolean hasSubjectSelected() {
        boolean hasSubjectSelected = false;
        if (jtblSubjectMasterList.getSelectedRow() > -1) {
            hasSubjectSelected = true;
        }
        return hasSubjectSelected;
    }

    private void validateSelection(String dialogTitle, String action) {
        if (hasSubjectSelected()) {
            displayDialog(dialogTitle, action);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a subject from the list.");
        }
    }

    private void displayDialog(String dialogTitle, String action) {
        if (action.equals("edit") || action.equals("view")) {
            if (hasSubjectSelected()) {
                int subjectIdColIdx = 0;
                int rowSelected = jtblSubjectMasterList.getSelectedRow();
                int subjectId = Integer.parseInt(jtblSubjectMasterList.getValueAt(rowSelected, subjectIdColIdx).toString());
                DialogSubjectCrud jdlgSubjectCrud = new DialogSubjectCrud(null, true, action, subjectId);
                jdlgSubjectCrud.setTitle(dialogTitle);
                jdlgSubjectCrud.setLocationRelativeTo(null);
                jdlgSubjectCrud.setVisible(true);
            }
        } else {
            DialogSubjectCrud jdlgSubjectCrud = new DialogSubjectCrud(null, true, action);
            jdlgSubjectCrud.setTitle(dialogTitle);
            jdlgSubjectCrud.setLocationRelativeTo(null);
            jdlgSubjectCrud.setVisible(true);
        }
    }
}
