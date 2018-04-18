
package component_model_loader;

import daoimpl.ClassTypeDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import model.classtype.ClassType;

/**
 *
 * @author Jordan
 */
public class ClassTypeJCompModelLoader {
    
    private ClassTypeDaoImpl classTypeDaoImpl;

    public ClassTypeJCompModelLoader(){
        classTypeDaoImpl = new ClassTypeDaoImpl();
    }
    
    public DefaultComboBoxModel getAllClassTypesByStatus(boolean isActive){
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        List<ClassType> classTypeList = new ArrayList<>();
        classTypeList = classTypeDaoImpl.getAllClassTypesByStatus(isActive);
        for(ClassType ct : classTypeList){
            comboModel.addElement(ct);
        }
        comboModel.setSelectedItem(null);
        return comboModel;
    }
    
    public DefaultComboBoxModel getAllClassTypeIDsByStatus(boolean isActive){
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        List<ClassType> classTypeList = new ArrayList<>();
        classTypeList = classTypeDaoImpl.getAllClassTypesByStatus(isActive);
        for(ClassType ct : classTypeList){
            comboModel.addElement(ct.getClassTypeID());
        }
        comboModel.setSelectedItem(null);
        return comboModel;
    }
    
}
