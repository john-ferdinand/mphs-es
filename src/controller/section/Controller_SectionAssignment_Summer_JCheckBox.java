
package controller.section;

import component_model_loader.GradeLevelJCompModelLoader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import model.schoolyear.SchoolYear;
import view.section.Dialog_SectionAssignment;

/**
 *
 * @author Jordan
 */
public class Controller_SectionAssignment_Summer_JCheckBox implements ActionListener{

    private final Dialog_SectionAssignment view;
    private final SchoolYear currentSchoolYear;
    private final GradeLevelJCompModelLoader gradeLevelJCompModelLoader;

    public Controller_SectionAssignment_Summer_JCheckBox(Dialog_SectionAssignment view,SchoolYear currentSchoolYear) {
        this.view = view;
        this.currentSchoolYear = currentSchoolYear;
        this.gradeLevelJCompModelLoader = new GradeLevelJCompModelLoader();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.getJcbSummer()){
            if(view.getJcbSummer().isSelected()){
                DefaultComboBoxModel comboModel = gradeLevelJCompModelLoader.getSummerGradeLevelsOf(currentSchoolYear);
                view.getJcmbGradeLevel().setModel(comboModel);
            }else{
                DefaultComboBoxModel comboModel = gradeLevelJCompModelLoader.getAllActiveGradeLevel();
                view.getJcmbGradeLevel().setModel(comboModel);
                view.getJcmbSection().setSelectedIndex(-1);
            }
        }
    }
    
}
