
package controller.discount;

import daoimpl.DiscountDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.discount.Discount;
import view.discount.Dialog_DiscountCRUD;
import view.discount.Panel_Discount;

/**
 *
 * @author Jordan
 */
public class Controller_CreateSave_Discount_JButton implements ActionListener{
    
    private Panel_Discount panelDiscount;
    private Dialog_DiscountCRUD dialogDiscountCrud;
    private final DiscountDaoImpl discountDaoImpl;
    private int discountId;

    public Controller_CreateSave_Discount_JButton(Panel_Discount panelDiscount, Dialog_DiscountCRUD dialogDiscountCrud) {
        this.dialogDiscountCrud = dialogDiscountCrud;
        this.discountDaoImpl = new DiscountDaoImpl();
        this.panelDiscount = panelDiscount;
    }
    
    public Controller_CreateSave_Discount_JButton(Panel_Discount panelDiscount, Dialog_DiscountCRUD dialogDiscountCrud, int discountId) {
        this.dialogDiscountCrud = dialogDiscountCrud;
        this.discountDaoImpl = new DiscountDaoImpl();
        this.panelDiscount = panelDiscount;
        this.discountId = discountId;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equalsIgnoreCase("create")){
            int choice = JOptionPane.showConfirmDialog(null, "Create Discount? ","Create Confirmation",JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                if (create()) {
                    JOptionPane.showMessageDialog(null, "Successfully created discount.");
                    panelDiscount.loadAllDiscounts();
                    dialogDiscountCrud.dispose();
                }
            }
        }else if(e.getActionCommand().equalsIgnoreCase("save")){
            int choice = JOptionPane.showConfirmDialog(null, "Update Discount? ","Update Confirmation",JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                if (update(discountId)) {
                    JOptionPane.showMessageDialog(null, "Successfully updated discount.");
                    panelDiscount.loadAllDiscounts();
                    dialogDiscountCrud.dispose();
                }
            }
        }
    }
    
    private boolean create(){
        Discount discount = new Discount();
        discount.setCreatedBy(dialogDiscountCrud.getUser());
        discount.setDiscountName(dialogDiscountCrud.getJtfDiscountName().getText().trim());
        discount.setPercent(Integer.parseInt(dialogDiscountCrud.getJsprPercentage().getValue().toString().trim()));
        discount.setProvision(dialogDiscountCrud.getJtaDiscountProvision().getText().trim());
        discount.setDescription(dialogDiscountCrud.getJtaDiscountDescription().getText().trim());
        return discountDaoImpl.create(discount);
    }
    
    private boolean update(int discountId){
        Discount discount = new Discount();
        discount.setDiscountID(discountId);
        discount.setCreatedBy(dialogDiscountCrud.getUser());
        discount.setDiscountName(dialogDiscountCrud.getJtfDiscountName().getText().trim());
        discount.setPercent(Integer.parseInt(dialogDiscountCrud.getJsprPercentage().getValue().toString().trim()));
        discount.setProvision(dialogDiscountCrud.getJtaDiscountProvision().getText().trim());
        discount.setDescription(dialogDiscountCrud.getJtaDiscountDescription().getText().trim());
        discount.setIsActive(dialogDiscountCrud.getJcmbStatus().getSelectedItem().toString().trim().equalsIgnoreCase("Active")? true : false);
        return discountDaoImpl.update(discount);
    }
    
}
