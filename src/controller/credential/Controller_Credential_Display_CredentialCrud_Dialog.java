
package controller.credential;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.schoolyear.SchoolYear;
import model.user.User;
import view.credential.Dialog_CredentialCRUD;

/**
 *
 * @author Jordan
 */
public class Controller_Credential_Display_CredentialCrud_Dialog implements ActionListener{

    private final SchoolYear currentSchoolYear;
    private final User user;
    
    public Controller_Credential_Display_CredentialCrud_Dialog(SchoolYear currentSchoolYear, User user){
        this.currentSchoolYear = currentSchoolYear;
        this.user = user;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        displayDialog(e.getActionCommand());
    }
    
    private void displayDialog(String actionCommand){
        Dialog_CredentialCRUD dialog = new Dialog_CredentialCRUD(null, true, actionCommand, currentSchoolYear, user);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    
}
