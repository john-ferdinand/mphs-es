package controller.user;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.user.Dialog_User_Crud;

/**
 *
 * @author John Ferdinand Antonio
 */
public class DisplayEditUser implements ActionListener {


    @Override
    public void actionPerformed(ActionEvent e) {
        Dialog_User_Crud userInfo = new Dialog_User_Crud(null, true);
        userInfo.setTitle("Edit User");
        userInfo.setPreferredSize(new Dimension(500, 500));
        userInfo.pack();
        userInfo.setLocationRelativeTo(null);
        userInfo.setVisible(true);
    }

}
