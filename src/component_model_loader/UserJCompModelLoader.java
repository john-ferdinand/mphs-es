/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package component_model_loader;

import daoimpl.UserDaoImpl;
import java.util.List;
import javax.swing.JTable;
import javax.swing.plaf.TableUI;
import javax.swing.table.DefaultTableModel;
import model.user.User;
import utility.component.TableUtility;

/**
 *
 * @author John Ferdinand Antonio
 */
public class UserJCompModelLoader {

    UserDaoImpl udi = new UserDaoImpl();

    public DefaultTableModel getAllUsers(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        List<User> list = udi.getAll();
        for (User u : list) {
            model.addRow(new Object[]{u.getUserId(), u.getUsername(), u.getRole().getRoleName(),
                u.getLastName() + ", " + u.getFirstName() + " " + u.getMiddleName()
            });
            TableUtility.setTableColumnWidth(table);
        }
        
        return model;
    }
}
