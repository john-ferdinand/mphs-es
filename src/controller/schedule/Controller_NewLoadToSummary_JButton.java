/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import utility.form.FormValidator;
import view.schedule.Dialog_CreateSchedule;

/**
 *
 * @author Jordan
 */
public class Controller_NewLoadToSummary_JButton implements ActionListener, FormValidator{

    private Dialog_CreateSchedule view;

    public Controller_NewLoadToSummary_JButton(Dialog_CreateSchedule view) {
        this.view = view;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(formIsValid()){
            load();
        }
    }
    
    private void load(){
        
    }

    @Override
    public boolean formIsValid() {
        return true;
    }
    
}
