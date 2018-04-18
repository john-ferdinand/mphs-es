
package validation;

import daoimpl.CurriculumDaoImpl;
import daoimpl.GradeLevelDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import daoimpl.SubjectDaoImpl;
import java.awt.Color;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;
import model.curriculum.Curriculum;
import model.gradelevel.GradeLevel;
import model.schoolyear.SchoolYear;
import model.subject.Subject;

/**
 *
 * @author francisunoxx
 */
public class Validation extends InputVerifier {

    SchoolYearDaoImpl sydi = new SchoolYearDaoImpl();
    CurriculumDaoImpl cdi = new CurriculumDaoImpl();
    GradeLevelDaoImpl gldi = new GradeLevelDaoImpl();
    SubjectDaoImpl sdi = new SubjectDaoImpl();

    GradeLevel gradeLevel = new GradeLevel();
    Curriculum curriculum = new Curriculum();
    SchoolYear schoolYear = new SchoolYear();
    Subject subject = new Subject();

    @Override
    public boolean verify(JComponent input) {
        String componentName = input.getName();

        if (input instanceof JTextField) {
            String tfCurriculumText = ((JTextField) input).getText();
            if (componentName.equals("tfCurriculumName")) {
                gradeLevel.setGradeLevelID(gradeLevel.getGradeLevelId());
                schoolYear.getSchoolYearId();
//                curriculum.setCurriculumTitle(tfCurriculumText);

//                if (cdi.checkCurriculumExists(curriculum,schoolYear) == false) {
//                    input.setBackground(Color.WHITE);
////                    getCbSchoolYearStart().setBackground(Color.WHITE);
////                    getCbSchoolYearEnd().setBackground(Color.WHITE);
////                    getCbGradeLevel().setBackground(Color.WHITE);
//                } else {
//                    input.setBackground(Color.PINK);
////                    getCbSchoolYearStart().setBackground(Color.PINK);
////                    getCbSchoolYearEnd().setBackground(Color.PINK);
////                    getCbGradeLevel().setBackground(Color.PINK);
//
//                    return false;
//                }
            } else if (componentName.equals("tfSubjectCode")) {
//                subject.setSubjectCode(getTfSubjectCode().getText());

                if (sdi.checkSubjectExists(subject) == false) {
                    input.setBackground(Color.WHITE);
                } else {
                    input.setBackground(Color.PINK);
                    return false;
                }
            }
        }
        return true;
    }

}
