
package controller.editschedule;

import component_model_loader.SectionJCompModelLoader;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.gradelevel.GradeLevel;
import model.schoolyear.SchoolYear;
import model.section.Section;
import view.schedule.Dialog_CreateSchedule;
import view.schedule.Dialog_EditSchedule;
import static view.schedule.Dialog_EditSchedule.jcmbSection;

/**
 *
 * @author Jordan
 */
public class Edit_ItemListener_SectionType_JComboBox implements ItemListener{
    
    private final Dialog_EditSchedule view;
    private final SectionJCompModelLoader sectionJCompModelLoader;

    public Edit_ItemListener_SectionType_JComboBox(Dialog_EditSchedule view) {
        this.view = view;
        this.sectionJCompModelLoader = new SectionJCompModelLoader();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(view.getJcmbSectionType().getSelectedIndex() > -1){
            resetForm();
            loadSectionsByGradeLevel();
//            enableSectionCombo();
        }
    }
    
    private void enableSectionCombo(){
        view.getJcmbSection().setEnabled(true);
    }
    
    private void loadSectionsByGradeLevel() {
        GradeLevel gradeLevel = (GradeLevel) view.getJcmbGradeLevel().getSelectedItem();
        int schoolYearId = Integer.parseInt(view.getJcmbSchoolYearFrom().getSelectedItem().toString().trim());
        SchoolYear schoolYear = new SchoolYear();
        schoolYear.setSchoolYearId(schoolYearId);
        String sectionType = view.getJcmbSectionType().getSelectedItem().toString().trim();
        view.getJcmbSection().setModel(
                sectionJCompModelLoader.getSectionsByGradeLevelNo(view.getJcmbGradeLevel())
        );
    }
    
    private void resetForm(){
        
    }
    
}
