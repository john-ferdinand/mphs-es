
package controller.registration;

import controller.global.Controller_JTextField_StringOnly_Validator;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import view.registration.View_Panel_Registration;

/**
 *
 * @author Jordan
 */
public class Controller_JTextField_Registration_StringOnly_Validator extends Controller_JTextField_StringOnly_Validator{
    
    private final View_Panel_Registration view;
    
    public Controller_JTextField_Registration_StringOnly_Validator(JTextField textField,View_Panel_Registration view) {
        super(textField);
        this.view = view;
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        super.changedUpdate(e); //To change body of generated methods, choose Tools | Templates.
        if(hasNumber()){
            view.getJtaWarnings().setText("Number value is not allowed.");
        }else{
//             view.getJtaWarnings().setText("");
        }
        
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        super.removeUpdate(e); //To change body of generated methods, choose Tools | Templates.
        if(hasNumber()){
            view.getJtaWarnings().setText("Number value is not allowed.");
        }else{
             view.getJtaWarnings().setText("");
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        super.insertUpdate(e); //To change body of generated methods, choose Tools | Templates.
        if(hasNumber()){
            view.getJtaWarnings().setText("Number value is not allowed.");
        }else{
//             view.getJtaWarnings().setText("");
        }
    }
    
    
    
}
