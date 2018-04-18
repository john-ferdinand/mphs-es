
package controller.discount;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.discount.Dialog_DiscountCRUD;
import view.discount.Panel_Discount;

/**
 *
 * @author Jordan
 */
public class Controller_Display_DiscountCRUD_Dialog implements ActionListener{

    private Panel_Discount view;

    public Controller_Display_DiscountCRUD_Dialog(Panel_Discount view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.getJbtnCreateDiscount() || e.getSource() == view.getJbtnEditDiscount()){
            Dialog_DiscountCRUD dialog = new Dialog_DiscountCRUD(null, true, e.getActionCommand(),view, view.getCurrentSchoolYear(), view.getUser());
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        }
    }
    
}
