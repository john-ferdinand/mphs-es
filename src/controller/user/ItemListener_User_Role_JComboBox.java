
package controller.user;

import component_model_loader.FacultyJCompModelLoader;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultComboBoxModel;
import model.role.Role;
import view.user.Dialog_User_Crud;

/**
 *
 * @author Jordan
 */
public class ItemListener_User_Role_JComboBox implements ItemListener{

    private final Dialog_User_Crud view;
    private FacultyJCompModelLoader facultyJCompModelLoader;

    public ItemListener_User_Role_JComboBox(Dialog_User_Crud view) {
        this.view = view;
        this.facultyJCompModelLoader = new FacultyJCompModelLoader();
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED){
            Role role = (Role) view.getJcmbRole().getSelectedItem();
            if(role.getRoleName().equalsIgnoreCase("Faculty")){
                view.getJcmbFaculty().setModel(facultyJCompModelLoader.getAllFacultyByStatus(true));
                view.getJtfLastName().setEnabled(false);
                view.getJtfFirstName().setEnabled(false);
                view.getJtfMiddleName().setEnabled(false);
            }else{
                view.getJcmbFaculty().setModel(new DefaultComboBoxModel<>());
                view.getJtfLastName().setEnabled(true);
                view.getJtfFirstName().setEnabled(true);
                view.getJtfMiddleName().setEnabled(true);
            }
        }
    }
    
    
}
