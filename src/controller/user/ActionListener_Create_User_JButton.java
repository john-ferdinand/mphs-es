
package controller.user;

import component_model_loader.UserJCompModelLoader;
import daoimpl.RoleDaoImpl;
import daoimpl.UserDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.faculty.Faculty;
import model.role.Role;
import model.user.User;
import utility.password.PasswordUtil;
import utility.string.StringUtil;
import static view.user.AllUsersRecord.jtblRecord;
import view.user.Dialog_User_Crud;

/**
 *
 * @author Jordan
 */
public class ActionListener_Create_User_JButton implements ActionListener{
    
    private final UserDaoImpl userDaoImpl;
    private final Dialog_User_Crud view;

    public ActionListener_Create_User_JButton(Dialog_User_Crud view) {
        this.userDaoImpl = new UserDaoImpl();
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isFormValid()) {
            User user = getUser();
            if (addUser(user)) {
                JOptionPane.showMessageDialog(null, "Successfully added user");
                jtblRecord.setModel(new UserJCompModelLoader().getAllUsers(jtblRecord));
            } else {
                JOptionPane.showMessageDialog(null, "Error encountered. User not added.");
            }
        }
    }
    
    private User getUser() {
        String username = view.getJtfUserName().getText().trim();
        String password = PasswordUtil.toString(view.getJpfPassword().getPassword());
//        Puzzler puzzler = new Puzzler(password);
//        String enryptedPassword = puzzler.getEncrypted();
        String firstName = view.getJtfFirstName().getText().trim();
        String lastName = view.getJtfLastName().getText().trim();
        String middleName = view.getJtfMiddleName().getText().trim();

        Role role = (Role) view.getJcmbRole().getSelectedItem();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setLastName(lastName);
        user.setMiddleName(middleName);
        user.setFirstName(firstName);
        user.setRole(role);

        return user;
    }
    
    private boolean isFormValid() {
        boolean isValid;
        if (passwordHasSpecialChars()) {
            JOptionPane.showMessageDialog(null, "Special characters are not allowed.");
            isValid = false;
        } else if (!passwordMatched()) {
            JOptionPane.showMessageDialog(null, "passwords didn't match");
            isValid = false;
        } else if (view.getJtfUserName().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a username");
            isValid = false;
        } else if (!passwordLengthIsValid()) {
            JOptionPane.showMessageDialog(null, "Password must have at least 8 charaters");
            isValid = false;
        } else {
            isValid = true;
        }
        return isValid;
    }
    
    private boolean addUser(User user) {
        boolean isAdded;
        if(user.getRole().getRoleName().trim().equalsIgnoreCase("Faculty")){
            Role role = (Role) view.getJcmbRole().getSelectedItem();
            String username = view.getJtfUserName().getText().trim();
            String password = PasswordUtil.toString(view.getJpfPassword().getPassword());
            Faculty faculty = (Faculty) view.getJcmbFaculty().getSelectedItem();
            faculty.setUsername(username);
            faculty.setPassword(password);
            faculty.setRole(role);
            isAdded = userDaoImpl.addFacultyAsUser(faculty);
        }else {
            isAdded = userDaoImpl.add(user);
        }
        return isAdded;
    }
    
    private boolean passwordMatched() {
        boolean matched;
        String newPassword = PasswordUtil.toString(view.getJpfPassword().getPassword());
        String newPasswordReEnter = PasswordUtil.toString(view.getJpfPasswordReEnter().getPassword());
        matched = newPassword.equals(newPasswordReEnter);
        return matched;
    }

    private boolean passwordLengthIsValid() {
        boolean valid = true;
        int minimumLength = 8;
        String newPassword = PasswordUtil.toString(view.getJpfPasswordReEnter().getPassword());
        char[] parts = newPassword.toCharArray();
        if (parts.length < minimumLength) {
            valid = false;
        }
        return valid;
    }
    
    private boolean passwordHasSpecialChars() {
        boolean hasSpecialChar = true;
        String newPassword = PasswordUtil.toString(view.getJpfPassword().getPassword());
        String newPasswordReEnter = PasswordUtil.toString(view.getJpfPasswordReEnter().getPassword());
        if (!StringUtil.hasSpecialCharaters(newPassword) && !StringUtil.hasSpecialCharaters(newPasswordReEnter)) {
            hasSpecialChar = false;
        }
        return hasSpecialChar;
    }
}
