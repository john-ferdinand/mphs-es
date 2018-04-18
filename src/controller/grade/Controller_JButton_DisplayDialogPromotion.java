/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.grade;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.schoolyear.SchoolYear;
import model.user.User;
import view.promotion.Dialog_Promotion;

/**
 *
 * @author Jordan
 */
public class Controller_JButton_DisplayDialogPromotion implements ActionListener{

    private final User user;
    private final SchoolYear currentSchoolYear;

    public Controller_JButton_DisplayDialogPromotion(User user, SchoolYear currentSchoolYear) {
        this.user = user;
        this.currentSchoolYear = currentSchoolYear;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Dialog_Promotion dialog = new Dialog_Promotion(null, true,user,currentSchoolYear);
        dialog.setPreferredSize(new Dimension(800,650));
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    
    
    
}
