package view.container;

import daoimpl.UserDaoImpl;
import javax.swing.JOptionPane;
import model.user.User;
import utility.password.PasswordUtil;
import utility.string.StringUtil;

/**
 *
 * @author John Ferdinand Antonio
 */
public class EditAccount extends javax.swing.JDialog {
    private int userId;
    
    public EditAccount(java.awt.Frame parent, boolean modal, User user) {
        super(parent, modal);
        initComponents();
        this.userId = user.getUserId();
        jtfUsername.setText(user.getUsername());
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jtfUsername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jbtnSave = new javax.swing.JButton();
        jpfNewPassword = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jpfNewPasswordReEnter = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Username :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jLabel1, gridBagConstraints);

        jtfUsername.setColumns(8);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jtfUsername, gridBagConstraints);

        jLabel2.setText("Enter new password :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jLabel2, gridBagConstraints);

        jbtnSave.setText("Save");
        jbtnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jbtnSave, gridBagConstraints);

        jpfNewPassword.setColumns(8);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jpfNewPassword, gridBagConstraints);

        jLabel3.setText("Re-enter new password :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jLabel3, gridBagConstraints);

        jpfNewPasswordReEnter.setColumns(8);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jpfNewPasswordReEnter, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private boolean passwordLengthIsValid(){
        boolean valid = true;
        int minimumLength = 8;
        String newPassword = PasswordUtil.toString(jpfNewPassword.getPassword());
        char[] parts = newPassword.toCharArray();
        if(parts.length < minimumLength){
            valid = false;
        }
        return valid;
    }
    
    private boolean updateUser(){
        boolean isUpdated = false;
        if (passwordHasSpecialChars()) {
            JOptionPane.showMessageDialog(null, "Special characters are not allowed.");
        } else if (!passwordMatched()) {
            JOptionPane.showMessageDialog(null, "passwords didn't match");
        } else if (jtfUsername.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a username");
        } else if(!passwordLengthIsValid()){
            JOptionPane.showMessageDialog(null,"Password must have at least 8 charaters");
        } 
        else {
            String newPasswordReEnter = PasswordUtil.toString(jpfNewPasswordReEnter.getPassword());
            UserDaoImpl udi = new UserDaoImpl();
            User user = new User();
            user.setUserID(userId);
            user.setUsername(jtfUsername.getText().trim());
            user.setPassword(newPasswordReEnter);
            isUpdated = udi.update(user);
            if (isUpdated) {
                JOptionPane.showMessageDialog(null, "Successfully updated login information.");
                this.dispose();
            }
        }
        
        return isUpdated;
    }
    
    private boolean passwordHasSpecialChars(){
        boolean hasSpecialChar = true;
        String newPassword = PasswordUtil.toString(jpfNewPassword.getPassword());
        String newPasswordReEnter = PasswordUtil.toString(jpfNewPasswordReEnter.getPassword());
        if(!StringUtil.hasSpecialCharaters(newPassword) && !StringUtil.hasSpecialCharaters(newPasswordReEnter)){
            hasSpecialChar = false;
        }
        return hasSpecialChar;
    }
    
    private boolean passwordMatched(){
        boolean matched;
        String newPassword = PasswordUtil.toString(jpfNewPassword.getPassword());
        String newPasswordReEnter = PasswordUtil.toString(jpfNewPasswordReEnter.getPassword());
        matched = newPassword.equals(newPasswordReEnter);
        return matched;
    }
    
    private void jbtnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSaveActionPerformed
        updateUser();
    }//GEN-LAST:event_jbtnSaveActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton jbtnSave;
    private javax.swing.JPasswordField jpfNewPassword;
    private javax.swing.JPasswordField jpfNewPasswordReEnter;
    private javax.swing.JTextField jtfUsername;
    // End of variables declaration//GEN-END:variables
}
