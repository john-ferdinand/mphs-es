/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.payment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Jordan
 */
public class Controller_DisplayAssessed_Dialog implements ActionListener{

    private final Panel_Payment view;

    public Controller_DisplayAssessed_Dialog(Panel_Payment view) {
        this.view = view;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Dialog_Assessed dialog = new Dialog_Assessed(null, true, view);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    
}
