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
public class DeleteAllStudentRecord implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        int choice = JOptionPane.showConfirmDialog
            (null, "Delete all Student Record?", "Delete confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(choice == JOptionPane.YES_OPTION){
            AdminToolsDaoImpl atdi = new AdminToolsDaoImpl();
            boolean isSuccessful = atdi.deleteAllStudentRecord();
            if(isSuccessful){
                JOptionPane.showMessageDialog(null,"Successfully deleted all student record.");
            }
        }
    }
}
