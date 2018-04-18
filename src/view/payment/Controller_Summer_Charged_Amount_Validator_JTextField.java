
package view.payment;

import java.awt.Color;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Jordan
 */
public class Controller_Summer_Charged_Amount_Validator_JTextField implements DocumentListener {

    private final Dialog_AddSummerFee dialog;

    public Controller_Summer_Charged_Amount_Validator_JTextField(Dialog_AddSummerFee dialog) {
        this.dialog = dialog;
    }
    
    @Override
    public void insertUpdate(DocumentEvent e) {
        validate();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        validate();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        validate();
    }
    
    private void validate(){
        if(!dialog.getJtfAmountCharged().getText().trim().isEmpty()){
            if(chargedIsValid()){
                dialog.getJtfAmountCharged().setBackground(Color.GREEN);
            }else{
                dialog.getJtfAmountCharged().setBackground(Color.RED);
            }
        }else{
            dialog.getJtfAmountCharged().setBackground(Color.GREEN);
        }
    }
    
    private boolean chargedIsValid() {
        boolean isValid = false;
        try {
            if (!dialog.getJtfAmountCharged().getText().isEmpty()) {
                String charged = dialog.getJtfAmountCharged().getText();
                boolean isValidMoney = charged.matches("^[0-9.]+$");
                if (isValidMoney) {
                    boolean equalToTotal = (Double.parseDouble(charged) == Double.parseDouble(dialog.getJtfTotal().getText()));
                    if (equalToTotal) {
                        isValid = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValid;
    }
}
