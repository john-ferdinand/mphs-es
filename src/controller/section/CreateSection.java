
package controller.section;

import component_model_loader.SectionJCompModelLoader;
import daoimpl.FacultyDaoImpl;
import daoimpl.GradeLevelDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import daoimpl.SectionDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.faculty.Faculty;
import model.gradelevel.GradeLevel;
import model.schoolyear.SchoolYear;
import model.section.Section;
import utility.form.FormValidator;
import view.section.DialogSectionCrud;
import view.section.Panel_Sections;

/**
 *
 * @author Jordan
 */
public class CreateSection implements ActionListener, FormValidator{
    
    private final SchoolYear currentSchoolYear;
    private final Panel_Sections panelSections;
    private final DialogSectionCrud dialog;
    private final FacultyDaoImpl facultyDaoImpl;
    private final SchoolYearDaoImpl schoolYearDaoImpl;
    private final GradeLevelDaoImpl gradeLevelDaoImpl;
    private final SectionDaoImpl sectionDaoImpl;
    private final SectionJCompModelLoader sectionJCompModelLoader;
    
    public CreateSection(Panel_Sections panelSections,DialogSectionCrud dialog,SchoolYear currentSchoolYear){
        this.currentSchoolYear = currentSchoolYear;
        this.panelSections = panelSections;
        this.dialog = dialog;
        schoolYearDaoImpl = new SchoolYearDaoImpl();
        facultyDaoImpl = new FacultyDaoImpl();
        gradeLevelDaoImpl = new GradeLevelDaoImpl();
        sectionDaoImpl = new SectionDaoImpl();
        sectionJCompModelLoader = new SectionJCompModelLoader();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int choice = JOptionPane.showConfirmDialog(null, "Save Section?", "Save Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (choice == JOptionPane.YES_OPTION) {
            if (formIsValid()) {
                if (addSection()) {
                    JOptionPane.showMessageDialog(null, "Successfully added Section!");
                    refreshSectionMasterList();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add Section");
                }
            }
        }
    }
    
    private void refreshSectionMasterList(){
        sectionJCompModelLoader.getAllSections(panelSections.getJtblSectionMasterList());
    }
    
    private boolean addSection() {
        boolean isAdded = false;
        SchoolYear schoolYear = new SchoolYear();
        schoolYear.setSchoolYearId(schoolYearDaoImpl.getCurrentSchoolYearId());

        GradeLevel gradeLevel = new GradeLevel();
        int level = Integer.parseInt(dialog.getJcmbGradeLevel().getSelectedItem().toString().trim());
        gradeLevel.setGradeLevelID(gradeLevelDaoImpl.getId(level));
        
        Section section = new Section();
        section.setSectionName(dialog.getJtfSectionName().getText().trim());
        section.setSchoolYear(schoolYear);
        section.setGradeLevel(gradeLevel);
        section.setAdviser(getFacultyAdviser());
        section.setSectionSession(dialog.getJcmbSession().getSelectedItem().toString().trim());
        section.setCapacity(Integer.parseInt(dialog.getJtfCapacity().getText().trim()));
        section.setSectionType(dialog.getJcmbSectionType().getSelectedItem().toString().equalsIgnoreCase("R")? "R":"S");
        isAdded = sectionDaoImpl.addSection(section);

        return isAdded;
    }
    
    private Faculty getFacultyAdviser(){
        String selectedAdviser = dialog.getJcmbAdviser().getSelectedItem().toString().trim();
        int adviserId = Integer.parseInt(selectedAdviser);
        Faculty f = new Faculty();
        f.setFacultyID(adviserId);
        return f;
    }
    
    @Override
    public boolean formIsValid() {
        boolean isValid = true;
        String sectionSession = dialog.getJcmbSession().getSelectedItem().toString().trim();
        int adviserId = Integer.parseInt(dialog.getJcmbAdviser().getSelectedItem().toString().trim());
        Faculty adviser = new Faculty();
        adviser.setFacultyID(adviserId);
        if (!facultyDaoImpl.isFacultyAvailableAdviserFor(sectionSession, adviser, currentSchoolYear)) {
            isValid = false;
            JOptionPane.showMessageDialog(null, "Faculty already has an advisory class.");
        }
        if (sectionDaoImpl.sectionExists(dialog.getJtfSectionName().getText().trim())) {
            isValid = false;
            JOptionPane.showMessageDialog(null, "Section name is already taken.\n Please try a different name.");
        }
        return isValid;
    }
    
}
