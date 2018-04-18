
package controller.section;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.schoolyear.SchoolYear;
import view.section.DialogSectionCrud;
import view.section.Panel_Sections;

/**
 *
 * @author Jordan
 */
public class DisplaySectionCrudDialog implements ActionListener{

    private final Panel_Sections panelSections;
    private final SchoolYear currentSchoolYear;
    
    public DisplaySectionCrudDialog(Panel_Sections panelSections, SchoolYear currentSchoolYear){
        this.panelSections = panelSections;
        this.currentSchoolYear = currentSchoolYear;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String dialogTitle = getDialogTitle(e);
        String action = getAction(e);
        
        if (action.equalsIgnoreCase("create")) {
            displayDialog(dialogTitle, action);
        } else if (action.equalsIgnoreCase("edit") || action.equalsIgnoreCase("view")) {
            validateSelection(dialogTitle, action);
        }
    }
    
    private String getDialogTitle(ActionEvent e) {
        String dialogTitle = e.getActionCommand().equalsIgnoreCase("create") ? "Create New Section"
                : e.getActionCommand().equalsIgnoreCase("edit") ? "Edit Section"
                : e.getActionCommand().equalsIgnoreCase("view") ? "Section Info(View)"
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

    private boolean hasSectionSelected() {
        boolean hasSectionSelected = false;
        if (panelSections.getJtblSectionMasterList().getSelectedRow() > -1) {
            hasSectionSelected = true;
        }
        return hasSectionSelected;
    }

    private void validateSelection(String dialogTitle, String action) {
        if (hasSectionSelected()) {
            displayDialog(dialogTitle, action);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a section from the list.");
        }
    }

    private void displayDialog(String dialogTitle, String action) {
        if (action.equals("edit") || action.equals("view")) {
            if (hasSectionSelected()) {
                int sectionIdColIdx = 0;
                int rowSelected = panelSections.getJtblSectionMasterList().getSelectedRow();
                int sectionId = Integer.parseInt(panelSections.getJtblSectionMasterList().getValueAt(rowSelected, sectionIdColIdx).toString());
                DialogSectionCrud dialogSectionCrud = new DialogSectionCrud(null, true,panelSections, action, sectionId, currentSchoolYear);
                dialogSectionCrud.setTitle(dialogTitle);
                dialogSectionCrud.setLocationRelativeTo(null);
                dialogSectionCrud.setVisible(true);
            }
        } else {
            DialogSectionCrud dialogSectionCrud = new DialogSectionCrud(null, false, panelSections,action,currentSchoolYear);
            dialogSectionCrud.setTitle(dialogTitle);
            dialogSectionCrud.setLocationRelativeTo(null);
            dialogSectionCrud.setVisible(true);
        }
    }
    
}
