
package controller.enrollment;

import component_model_loader.EnrollmentJCompModelLoader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import model.schoolyear.SchoolYear;
import view.enrollment.Panel_Enrollment;

/**
 *
 * @author Jordan
 */
public class Controller_JButton_SearchEnrolledRecordByKeyword implements ActionListener{

    private final EnrollmentJCompModelLoader enrollmentJCompModelLoader;
    private final SchoolYear schoolYear;
    private final Panel_Enrollment view;

    public Controller_JButton_SearchEnrolledRecordByKeyword(Panel_Enrollment view,SchoolYear schoolYear) {
        this.schoolYear = schoolYear;
        this.view = view;
        this.enrollmentJCompModelLoader = new EnrollmentJCompModelLoader(schoolYear);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        DefaultTableModel enrolledTableModel = enrollmentJCompModelLoader.getAllEnrolledOfCurrentSchoolYearByWildCard(view.getJtblEnrolledMasterList(), view.getJtfEnrolledSearchBox());
        view.getJtblEnrolledMasterList().setModel(enrolledTableModel);
    }
    
}
