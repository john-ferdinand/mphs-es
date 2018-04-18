
package controller.subjectcategory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import view.subjectcategory.DialogSubjectCategoryCrud;

/**
 *
 * @author Jordan
 */
public class DisplaySubjectCategoryCrudDialog implements ActionListener{

    private final JTable jtblSubjectCategoryMasterList;
    
    public DisplaySubjectCategoryCrudDialog(JTable jtblSubjectCategoryMasterList) {
        this.jtblSubjectCategoryMasterList = jtblSubjectCategoryMasterList;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = getAction(e);
        String dialogTitle = getDialogTitle(e);
        if(action.equalsIgnoreCase("create")){
            displayDialog(dialogTitle, action);
        }else if(action.equalsIgnoreCase("edit") || action.equalsIgnoreCase("view")){
            validateSelection(dialogTitle, action);
        }
    }
    
    private String getDialogTitle(ActionEvent e) {
        String dialogTitle = e.getActionCommand().equalsIgnoreCase("create") ? "Create Subject Category"
                : e.getActionCommand().equalsIgnoreCase("edit") ? "Edit Subject Category"
                : e.getActionCommand().equalsIgnoreCase("view") ? "Subject Category Info(View)"
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

    private boolean hasSubjectCategorySelected() {
        boolean hasSubjectSelected = false;
        if (jtblSubjectCategoryMasterList.getSelectedRow() > -1) {
            hasSubjectSelected = true;
        }
        return hasSubjectSelected;
    }

    private void validateSelection(String dialogTitle, String action) {
        if (hasSubjectCategorySelected()) {
            displayDialog(dialogTitle, action);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a subject category from the list.");
        }
    }

    private void displayDialog(String dialogTitle, String action) {
        if (action.equals("edit") || action.equals("view")) {
            if (hasSubjectCategorySelected()) {
                int subjectCategoryIdColIdx = 0;
                int rowSelected = jtblSubjectCategoryMasterList.getSelectedRow();
                int subjectCategoryId = Integer.parseInt(jtblSubjectCategoryMasterList.getValueAt(rowSelected, subjectCategoryIdColIdx).toString());
                DialogSubjectCategoryCrud dialogSubjectCategoryCrud = new DialogSubjectCategoryCrud(null, true, action, subjectCategoryId);
                dialogSubjectCategoryCrud.setTitle(dialogTitle);
                dialogSubjectCategoryCrud.setLocationRelativeTo(null);
                dialogSubjectCategoryCrud.setVisible(true);
            }
        } else {
            DialogSubjectCategoryCrud dialogSubjectCategoryCrud = new DialogSubjectCategoryCrud(null, true, action);
            dialogSubjectCategoryCrud.setTitle(dialogTitle);
            dialogSubjectCategoryCrud.setLocationRelativeTo(null);
            dialogSubjectCategoryCrud.setVisible(true);
        }
    }
    
}
