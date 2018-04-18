
package controller.enrollment;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import view.section.Dialog_SectionAssignment;

/**
 *
 * @author Jordan
 */
public class DialogSectionAssignment_CapacityDenominatorDocumentPropertyListener implements DocumentListener{

    private final Dialog_SectionAssignment view;

    public DialogSectionAssignment_CapacityDenominatorDocumentPropertyListener(Dialog_SectionAssignment view) {
        this.view = view;
    }
    
    
    @Override
    public void insertUpdate(DocumentEvent e) {
        changeAutoAssignButtonText();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        changeAutoAssignButtonText();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        changeAutoAssignButtonText();
    }
    
    private void changeAutoAssignButtonText() {
        String sectionCapacity = view.getJtfCapacityDenominator().getText().trim();
        String text = "";
        if (sectionCapacity.isEmpty()) {
            text = "Auto Assign";
        } else {
            text = "Auto Assign First " + sectionCapacity;
        }
        view.getJbtnAutoAssign().setText(text);
    }
    
}
