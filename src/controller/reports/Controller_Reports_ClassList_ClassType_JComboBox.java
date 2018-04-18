
package controller.reports;

import component_model_loader.SectionJCompModelLoader;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import model.gradelevel.GradeLevel;
import model.schoolyear.SchoolYear;
import view.reports.Panel_Reports;

/**
 *
 * @author Jordan
 */
public class Controller_Reports_ClassList_ClassType_JComboBox implements ItemListener{

    private final SectionJCompModelLoader sectionJCompModelLoader;
    private final Panel_Reports view;

    public Controller_Reports_ClassList_ClassType_JComboBox(Panel_Reports view) {
        this.sectionJCompModelLoader = new SectionJCompModelLoader();
        this.view = view;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == view.getJcmbClassListSectionType()) {
            if (view.getJcmbClassListGradeLevel().getSelectedIndex() > -1 && view.getJcmbClassListSectionType().getSelectedIndex() > -1) {
                SchoolYear schoolYear = (SchoolYear) view.getJcmbClassListSchoolYear().getSelectedItem();
                GradeLevel gradeLevel = (GradeLevel) view.getJcmbClassListGradeLevel().getSelectedItem();
                String sectionType = view.getJcmbClassListSectionType().getSelectedItem().toString().trim();
                view.getJcmbClassListSection().setModel(sectionJCompModelLoader.getSectionsBy(schoolYear, gradeLevel, sectionType));
                view.getJcmbClassListSection().setEnabled(true);
            }
        }
    }
    
}
