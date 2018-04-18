
package controller.section;

import daoimpl.FacultyDaoImpl;
import daoimpl.GradeLevelDaoImpl;
import daoimpl.SectionDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.faculty.Faculty;
import model.gradelevel.GradeLevel;
import model.schoolyear.SchoolYear;
import model.section.Section;
import utility.form.FormValidator;
import utility.string.StringUtil;
import view.section.DialogSectionCrud;
import view.section.Panel_Sections;

/**
 *
 * @author Jordan
 */
public class UpdateSection implements ActionListener, FormValidator{
    
    private final int sectionIdOfSelected;
    
    private final Panel_Sections panelSections;
    private final DialogSectionCrud view;
    
    private final SectionDaoImpl sectionDaoImpl;
    private final GradeLevelDaoImpl gradeLevelDaoImpl;
    private final SchoolYear currentSchoolYear;
    
    public UpdateSection(int sectionIfOfSelected,Panel_Sections panelSections, DialogSectionCrud view, SchoolYear currentSchoolYear){
        this.panelSections = panelSections;
        this.view = view;
        this.sectionIdOfSelected = sectionIfOfSelected;
        this.currentSchoolYear = currentSchoolYear;
        
        sectionDaoImpl = new SectionDaoImpl();
        gradeLevelDaoImpl = new GradeLevelDaoImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int choice = 
                JOptionPane.showConfirmDialog(null, "Update Section?", "Update Confirmation", JOptionPane.YES_NO_OPTION);
        if(choice == JOptionPane.YES_OPTION){
            if (formIsValid()) {
                if (update()) {
                    JOptionPane.showMessageDialog(null, "Successfully updated section!");
                    panelSections.loadSectionMasterList();
                    view.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update section.");
                }
            } 
        }
    }

    @Override
    public boolean formIsValid() {
        boolean isValid = false;
        if(facultyIsAvailable()){
            isValid = true;
        }else{
            JOptionPane.showMessageDialog(null,"Faculty Advisory Class Session conflict.");
        }
        if (sectionNameExists()) {
            isValid = false;
            JOptionPane.showMessageDialog(null, "Section name is already taken.\n Please try a different name.");
        }
        return isValid;
    }
    
    private boolean facultyIsAvailable(){
        FacultyDaoImpl facultyDaoImpl = new FacultyDaoImpl();
        int facultyId = Integer.parseInt(view.getJcmbAdviser().getSelectedItem().toString().trim());
        Faculty faculty = new Faculty();
        faculty.setFacultyID(facultyId);
        Section section = new Section();
        section.setSectionId(sectionIdOfSelected);
        section.setSectionSession(view.getJcmbSession().getSelectedItem().toString().trim());
        section.setSectionType(view.getJcmbSectionType().getSelectedItem().toString().trim());
        return facultyDaoImpl.isFacultyAvailableAdviserFor(section, faculty, currentSchoolYear);
    }
    
    private boolean sectionNameExists(){
        return sectionDaoImpl.sectionExists(view.getJtfSectionName().getText().trim());
    }
    
    private boolean update(){
        boolean isUpdated = false;
        
        GradeLevel gradeLevel = new GradeLevel();
        int gradeLevelNo = Integer.parseInt(view.getJcmbGradeLevel().getSelectedItem().toString().trim());
        gradeLevel.setGradeLevelID(gradeLevelDaoImpl.getId(gradeLevelNo));

        String selectedAdviser = StringUtil.removeAllNonNumeric(view.getJcmbAdviser().getSelectedItem().toString().trim());
        int adviserId = Integer.parseInt(StringUtil.removeWhiteSpaces(selectedAdviser));
        Faculty adviser = new Faculty();
        adviser.setFacultyID(adviserId);
        
        Section section = new Section();
        section.setSectionId(sectionIdOfSelected);
        section.setSectionName(view.getJtfSectionName().getText().trim());
        section.setIsActive(view.getJcmbStatus().getSelectedItem().toString().trim().equalsIgnoreCase("Yes")?true:false);
        section.setSchoolYear(currentSchoolYear);
        section.setGradeLevel(gradeLevel);
        section.setSectionSession(view.getJcmbSession().getSelectedItem().toString().trim());
        section.setAdviser(adviser);
        section.setCapacity(Integer.parseInt(view.getJtfCapacity().getText().trim()));
        section.setSectionType(view.getJcmbSectionType().getSelectedItem().toString().trim());
        isUpdated = sectionDaoImpl.updateSection(section);
        
        return isUpdated;
    }
    
    
}
