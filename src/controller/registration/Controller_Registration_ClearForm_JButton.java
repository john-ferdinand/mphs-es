/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.registration;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import view.registration.View_Panel_Registration;

/**
 *
 * @author Jordan
 */
public class Controller_Registration_ClearForm_JButton implements ActionListener{

    private final View_Panel_Registration view;

    public Controller_Registration_ClearForm_JButton(View_Panel_Registration view) {
        this.view = view;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to clear the form?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if(choice == JOptionPane.YES_OPTION){
            clearForm();
        }
    }
    
    private void clearForm(){
        view.getJcbTransferee().setEnabled(true);
        view.getJcmbGradeLevel().setSelectedIndex(-1);
        view.getJcbTransferee().setSelected(false);
        view.getJcmbGender().setSelectedIndex(-1);
        view.getDpBirthday().getJFormattedTextField().setText("");
        view.getJlblAge().setText("");
        view.getJtaWarnings().setText("");
        List<Component[]> componentList = new ArrayList<>();
        componentList.add(view.getJpnlStudentInfo().getComponents());
        componentList.add(view.getJpnlHomeAddress().getComponents());
        componentList.add(view.getJpnlParentInfo().getComponents());
        componentList.add(view.getJpnlGuardianInfo().getComponents());
        componentList.add(view.getJpnlCredentials().getComponents());
        for(int i = 0; i < componentList.size(); i++){
            for(Component c : componentList.get(i)){
                if(c instanceof JTextField){
                    ((JTextField)c).setText("");
                }else if(c instanceof JCheckBox){
                    ((JCheckBox)c).setSelected(false);
                }
            }
        }
        
    }
}
