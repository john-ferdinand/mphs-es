/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.user;

import component_model_loader.RolesJCompModelLoader;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import view.user.Dialog_User_Crud;

/**
 *
 * @author John Ferdinand Antonio
 */
public class ActionListener_Display_Dialog_Crud implements ActionListener{

    public ActionListener_Display_Dialog_Crud(){
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Dialog_User_Crud userInfo = new Dialog_User_Crud(null,true);
        userInfo.setTitle("Create User");
        userInfo.setPreferredSize(new Dimension(450,450));
        userInfo.pack();
        userInfo.setLocationRelativeTo(null);
        userInfo.setVisible(true);
    }
    
}
