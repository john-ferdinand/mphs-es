/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.editschedule;

import controller.schedule.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.schedule.Dialog_CreateSchedule;

/**
 *
 * @author John Ferdinand Antonio
 */
public class Edit_ActionListener_Schedule_Display_Edit_Dialog_JButton implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        Dialog_CreateSchedule c = new Dialog_CreateSchedule(null,false);
        c.setPreferredSize(new Dimension(1250,700));
        c.pack();
        c.setLocationRelativeTo(null);
        c.setVisible(true);
    }
    
}
