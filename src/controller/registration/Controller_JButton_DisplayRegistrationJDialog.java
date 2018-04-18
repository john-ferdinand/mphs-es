
package controller.registration;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import view.enrollment.Panel_Enrollment;
import view.registration.View_Panel_Registration;

/**
 *
 * @author Jordan
 */
public class Controller_JButton_DisplayRegistrationJDialog implements ActionListener{

    private final Panel_Enrollment panelEnrollment;
    private JDialog dialog;
    private JTable jtblRegistrationMasterList;
    private final String actionCommand;

    public Controller_JButton_DisplayRegistrationJDialog(Panel_Enrollment panelEnrollment,JTable jtblRegistrationMasterList, String actionCommand) {
        this.panelEnrollment = panelEnrollment;
        this.jtblRegistrationMasterList = jtblRegistrationMasterList;
        this.actionCommand = actionCommand;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(hasSelectedRow()){
            displayDialog();
        }else{
            JOptionPane.showMessageDialog(null,"Please select a registration record from the list.");
        }
    }
    
    private GridBagConstraints setPosition(int gridX, int gridY, double weightX, double weightY, int fillType){
        Insets insets = new Insets(3, 3, 3, 3);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridX;
        gbc.gridy = gridY;
        gbc.weightx = weightX;
        gbc.weighty = weightY;
        gbc.fill = fillType;
        gbc.insets = insets;
        return gbc;
    }
    
    private void displayDialog() {
        dialog = new JDialog();
        dialog.setLayout(new GridBagLayout());
        dialog.setModal(true);
        View_Panel_Registration panel_Registration = new View_Panel_Registration(panelEnrollment,actionCommand, getRegistrationId());
        dialog.add(panel_Registration, setPosition(0, 0, 0.5, 0.5, GridBagConstraints.BOTH));
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    
    private boolean hasSelectedRow(){
        boolean hasSelected = false;
        if (jtblRegistrationMasterList.getSelectedRow() > -1) {
            hasSelected = true;
        }
        return hasSelected;
    }
    
    private int getRegistrationId(){
        int row = jtblRegistrationMasterList.getSelectedRow();
        int registrationId = Integer.parseInt(jtblRegistrationMasterList.getValueAt(row, 0).toString());
        return registrationId;
    }
}
