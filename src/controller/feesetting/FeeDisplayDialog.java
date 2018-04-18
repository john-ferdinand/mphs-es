package controller.feesetting;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import view.fees.JdlgFeeCrud;
import static view.fees.Panel_FeeRecord.jtblFeeRecord;

/**
 *
 * @author Jordan
 */
public class FeeDisplayDialog implements ActionListener {

    private JTable jtblFeeRecord;
    
    public FeeDisplayDialog(JTable jtblFeeRecord) {
        this.jtblFeeRecord = jtblFeeRecord;
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
        String dialogTitle = e.getActionCommand().equalsIgnoreCase("Create New Fee") ? "Create Fee"
                : e.getActionCommand().equalsIgnoreCase("edit") ? "Edit Fee"
                : e.getActionCommand().equalsIgnoreCase("view") ? "Fee Details"
                : e.getActionCommand().equalsIgnoreCase("print") ? "Print Fee Details" : "";
        return dialogTitle;
    }

    private String getAction(ActionEvent e) {
        String action = e.getActionCommand().equalsIgnoreCase("Create New Fee") ? "create"
                : e.getActionCommand().equalsIgnoreCase("Edit") ? "edit"
                : e.getActionCommand().equalsIgnoreCase("View") ? "view"
                : e.getActionCommand().equalsIgnoreCase("Print") ? "print" : "";
        return action;
    }

    private void validateSelection(String dialogTitle, String action) {
        if (hasFeeSelected()) {
            displayDialog(dialogTitle, action);
        } else {
            JOptionPane.showMessageDialog(null, "Please select an item from the list.");
        }
    }

    private boolean hasFeeSelected() {
        boolean hasFeeSelected = false;
        if (jtblFeeRecord.getSelectedRow() > -1) {
            hasFeeSelected = true;
        }
        return hasFeeSelected;
    }

    private void displayDialog(String dialogTitle, String action) {
        if (action.equals("edit") || action.equals("view")) {
            if (hasFeeSelected()) {
                int feeIdColIdx = 0;
                int rowSelected = jtblFeeRecord.getSelectedRow();
                int feeId = Integer.parseInt(jtblFeeRecord.getValueAt(rowSelected, feeIdColIdx).toString());
                JdlgFeeCrud jdlgFeeCrud = new JdlgFeeCrud(null, true, action, feeId);
                jdlgFeeCrud.setTitle(dialogTitle);
                jdlgFeeCrud.setLocationRelativeTo(null);
                jdlgFeeCrud.setVisible(true);
            }
        } else {
            JdlgFeeCrud jdlgFeeCrud = new JdlgFeeCrud(null, true, action);
            jdlgFeeCrud.setTitle(dialogTitle);
            jdlgFeeCrud.setLocationRelativeTo(null);
            jdlgFeeCrud.setVisible(true);
        }
    }
}
