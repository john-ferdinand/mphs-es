/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.paymentsetting;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import view.paymentsetting.DialogPaymentScheduleCrud;
import view.paymentsetting.PaymentScheduleSettings;

/**
 *
 * @author John Ferdinand Antonio
 */
public class DisplayNewPaymentScheduleController {

    private final PaymentScheduleSettings panelPaymentScheduleSettings;
    private final JButton jbtnCreateNew;
    private final JButton jbtnEdit;
    private ActionListener displayCreate;
    private ActionListener displayEdit;

    public DisplayNewPaymentScheduleController(PaymentScheduleSettings panelPaymentScheduleSettings, JButton jbtnCreateNew, JButton jbtnEdit) {
        this.panelPaymentScheduleSettings = panelPaymentScheduleSettings;
        this.jbtnCreateNew = jbtnCreateNew;
        this.jbtnEdit = jbtnEdit;
    }

    public void displayForCreate() {
        displayCreate = (ActionEvent e) -> {
            String actionCommand = jbtnCreateNew.getActionCommand(); 
            DialogPaymentScheduleCrud dialogPaymentSchedule = new DialogPaymentScheduleCrud(panelPaymentScheduleSettings,actionCommand);
            dialogPaymentSchedule.setTitle("Create Payment Schedule");
            dialogPaymentSchedule.pack();
            dialogPaymentSchedule.setLocationRelativeTo(null);
            dialogPaymentSchedule.setVisible(true);
        };
        jbtnCreateNew.addActionListener(displayCreate);
    }

    public void displayForEdit() {
        displayEdit = (ActionEvent e) -> {
            String actionCommand = jbtnEdit.getActionCommand();
            DialogPaymentScheduleCrud dialogPaymentSchedule = new DialogPaymentScheduleCrud(panelPaymentScheduleSettings,actionCommand);
            dialogPaymentSchedule.setTitle("Edit Payment Schedule");
            dialogPaymentSchedule.pack();
            dialogPaymentSchedule.setLocationRelativeTo(null);
            dialogPaymentSchedule.setVisible(true);
        };
        jbtnEdit.addActionListener(displayEdit);
    }
}
