package component_model_loader;

import daoimpl.GradeLevelDaoImpl;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import model.gradelevel.GradeLevel;
import model.schoolyear.SchoolYear;

public class GradeLevelJCompModelLoader {

    private final GradeLevelDaoImpl gradeLevelDaoImpl; 
    
    public GradeLevelJCompModelLoader(){
        gradeLevelDaoImpl = new GradeLevelDaoImpl();
    }

    /**
     * Gets all gradelevels both ACTIVE and INACTIVE 
     * @return 
     * DefaultComboBoxModel - containing gradelevel no only. As in gradLevel.getLevelNo() 
     *
     */
    public DefaultComboBoxModel getAllGradeLevels() {
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        List<GradeLevel> gradeLevelList = gradeLevelDaoImpl.getAllGradeLevelsInfo();
        for (GradeLevel g : gradeLevelList) {
            comboModel.addElement(g.getLevelNo());
        }
        comboModel.setSelectedItem(null);
        return comboModel;
    }
    
    public DefaultComboBoxModel getAllGradeLevelsAsModel() {
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        List<GradeLevel> gradeLevelList = gradeLevelDaoImpl.getAllGradeLevelsInfo();
        for (GradeLevel gradeLevel : gradeLevelList) {
            comboModel.addElement(gradeLevel);
        }
        comboModel.setSelectedItem(null);
        return comboModel;
    }
    
    public DefaultComboBoxModel getAllActiveGradeLevelNo(){
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        List<GradeLevel> gradeLevelList = gradeLevelDaoImpl.getAllActiveGradeLevels();
        for (GradeLevel g : gradeLevelList) {
            comboModel.addElement(g.getLevelNo());
        }
        return comboModel;
    }
    
    public DefaultComboBoxModel getAllActiveGradeLevelId(){
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        List<GradeLevel> gradeLevelList = gradeLevelDaoImpl.getAllActiveGradeLevels();
        for (GradeLevel g : gradeLevelList) {
            comboModel.addElement(g.getGradeLevelId());
        }
        comboModel.setSelectedItem(null);
        return comboModel;
    }
    
    public DefaultComboBoxModel getAllActiveGradeLevel(){
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        List<GradeLevel> gradeLevelList = gradeLevelDaoImpl.getAllActiveGradeLevels();
        for (GradeLevel gradeLevel : gradeLevelList) {
            comboModel.addElement(gradeLevel);
        }
        comboModel.setSelectedItem(null);
        return comboModel;
    }

    public DefaultComboBoxModel getSummerGradeLevelsOf(SchoolYear schoolYear){
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        List<GradeLevel> summerGradeLevels = gradeLevelDaoImpl.getSummerGradeLevelsOf(schoolYear);
        for(GradeLevel summerGradeLevel : summerGradeLevels){
            comboModel.addElement(summerGradeLevel);
        }
        comboModel.setSelectedItem(null);
        return comboModel;
    }
}
