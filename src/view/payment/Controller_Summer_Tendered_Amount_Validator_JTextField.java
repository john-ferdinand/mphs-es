
package view.payment;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Jordan
 */
public class Controller_Summer_Tendered_Amount_Validator_JTextField implements DocumentListener{

    private final Dialog_AddSummerFee dialog;

    public Controller_Summer_Tendered_Amount_Validator_JTextField(Dialog_AddSummerFee dialog) {
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

    private void validate() {
        Color tenderedColor = (tenderedIsValid() == true ? Color.GREEN : Color.RED);
        dialog.getJtfTendered().setBackground(tenderedColor);
        Color chargedColor = (chargedIsValid() == true ? Color.GREEN : Color.RED);
        dialog.getJtfAmountCharged().setBackground(chargedColor);
        if(tenderedIsValid() && chargedIsValid()){
            dialog.getJtfTendered().setBackground(Color.GREEN);
            dialog.getJtfAmountCharged().setBackground(Color.GREEN);
            dialog.getJbtnPaySummer().setEnabled(true);
        }else{
            dialog.getJbtnPaySummer().setEnabled(false);
        }
    }
    
    private boolean tenderedIsValid() {
        boolean isValid = false;
        try {
            if (!dialog.getJtfTendered().getText().isEmpty()) {
                String tendered = dialog.getJtfTendered().getText();
                boolean isValidMoney = tendered.matches("^[0-9.]+$");
                if (isValidMoney) {
                    boolean gtEqTotal = Double.parseDouble(tendered) >= Double.parseDouble(dialog.getJtfTotal().getText());
                    if (gtEqTotal) {
                        isValid = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValid;
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
