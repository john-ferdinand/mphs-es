/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admintools;

import daoimpl.AdminToolsDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author John Ferdinand Antonio
 */
public class DeactivateAllStudents implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        int choice = JOptionPane.showConfirmDialog
            (null, "Deactivate all students?", "Deactivate confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(choice == JOptionPane.YES_OPTION){
            AdminToolsDaoImpl atdi = new AdminToolsDaoImpl();
            boolean isSuccessful = atdi.deactivateAllStudents();
            if(isSuccessful){
                JOptionPane.showMessageDialog(null,"Successfully deactivated students.");
            }
        }
    }
    
}
