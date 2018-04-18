
package component_model_loader;

import daoimpl.RoleDaoImpl;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import model.role.Role;

/**
 *
 * @author John Ferdinand Antonio
 */
public class RolesJCompModelLoader {

    private final RoleDaoImpl roleDaoImpl;

    public RolesJCompModelLoader(){
        roleDaoImpl = new RoleDaoImpl();
    }
    
    public DefaultComboBoxModel getAllRoleNames() {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        List<Role> roleList = roleDaoImpl.getAll();
        for (Role role : roleList) {
            model.addElement(role.getRoleName());
        }
        return model;
    }
    
    public DefaultComboBoxModel getAllRoles(){
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        List<Role> roleList = roleDaoImpl.getAll();
        for (Role role : roleList) {
            model.addElement(role);
        }
        return model;
    }
}
