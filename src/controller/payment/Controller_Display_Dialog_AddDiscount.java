
package controller.payment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.fee.Fee;
import model.schoolyear.SchoolYear;
import model.student.Student;
import model.user.User;
import view.payment.Dialog_AddDiscount;
import view.payment.Panel_Payment;

/**
 *
 * @author Jordan
 */
public class Controller_Display_Dialog_AddDiscount implements ActionListener{
    
    private final Panel_Payment view;

    public Controller_Display_Dialog_AddDiscount(Panel_Payment view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.getJbtnSelectDiscount()){
            Dialog_AddDiscount dialog = new Dialog_AddDiscount(null, true, view);
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        }
    }
    
}
