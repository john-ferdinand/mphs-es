package controller.enrollment;

import component_model_loader.EnrollmentJCompModelLoader;
import daoimpl.EnrollmentDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import view.enrollment.Panel_Enrollment;

/**
 *
 * @author Jordan
 */
public class RefreshEnrolledRecord implements ActionListener {

    private Panel_Enrollment view;
    private SchoolYearDaoImpl schoolYearDaoImpl;
    private EnrollmentDaoImpl enrollmentDaoImpl;
    private EnrollmentJCompModelLoader enrollmentJCompModelLoader;

    public RefreshEnrolledRecord(Panel_Enrollment view) {
        this.view = view;
        schoolYearDaoImpl = new SchoolYearDaoImpl();
        enrollmentDaoImpl = new EnrollmentDaoImpl();
        enrollmentJCompModelLoader = new EnrollmentJCompModelLoader(schoolYearDaoImpl.getCurrentSchoolYear());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.getJcmbEnrolledShowFilter().setSelectedIndex(-1);
        view.getJcmbEnrolledFilterGradeLevel().setSelectedIndex(-1);
        JTable table = view.getJtblEnrolledMasterList();
        DefaultTableModel tableModel = enrollmentJCompModelLoader.getAllEnrolledOfCurrentSchoolYear(table);
        table.setModel(tableModel);
    }

}
