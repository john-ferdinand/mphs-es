/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.user;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import model.faculty.Faculty;
import view.user.Dialog_User_Crud;

/**
 *
 * @author Jordan
 */
public class ItemListener_User_Faculty_JComboBox implements ItemListener{
    
    private final Dialog_User_Crud view;

    public ItemListener_User_Faculty_JComboBox(Dialog_User_Crud view) {
        this.view = view;
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED){
            JComboBox jcmbFaculty = (JComboBox)e.getSource();
            Faculty faculty = (Faculty) jcmbFaculty.getSelectedItem();
            view.getJtfLastName().setText(faculty.getLastName());
            view.getJtfFirstName().setText(faculty.getFirstName());
            view.getJtfMiddleName().setText(faculty.getMiddleName());
        }
    }
}
