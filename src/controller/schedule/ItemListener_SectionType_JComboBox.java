
package controller.schedule;

import component_model_loader.SectionJCompModelLoader;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import model.gradelevel.GradeLevel;
import model.schoolyear.SchoolYear;
import view.schedule.Dialog_CreateSchedule;

/**
 *
 * @author Jordan
 */
public class ItemListener_SectionType_JComboBox implements ItemListener{
    
    private final Dialog_CreateSchedule view;
    private final SectionJCompModelLoader sectionJCompModelLoader;

    public ItemListener_SectionType_JComboBox(Dialog_CreateSchedule view) {
        this.view = view;
        this.sectionJCompModelLoader = new SectionJCompModelLoader();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(view.getJcmbSectionType().getSelectedIndex() > -1){
            resetForm();
            loadSectionsByGradeLevel();
            enableSectionCombo();
        }
    }
    
    private void enableSectionCombo(){
        view.getJcmbSection().setEnabled(true);
    }
    
    private void loadSectionsByGradeLevel() {
        GradeLevel gradeLevel = (GradeLevel) view.getJcmbGradeLevel().getSelectedItem();
        int schoolYearId = Integer.parseInt(view.getJcmbSchoolYear().getSelectedItem().toString().trim());
        SchoolYear schoolYear = new SchoolYear();
        schoolYear.setSchoolYearId(schoolYearId);
        String sectionType = view.getJcmbSectionType().getSelectedItem().toString().trim();
        view.getJcmbSection().setModel(
                sectionJCompModelLoader.getSectionsWithoutScheduleBySectionType(schoolYear, gradeLevel, sectionType)
        );
    }
    
    private void resetForm(){
        
    }
    
}
