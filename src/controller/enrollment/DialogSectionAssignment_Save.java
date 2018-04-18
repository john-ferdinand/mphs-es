package controller.enrollment;

import daoimpl.SectionDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.section.Section;
import model.student.Student;
import view.enrollment.Panel_Enrollment;
import view.section.Dialog_SectionAssignment;

/**
 *
 * @author Jordan
 */
public class DialogSectionAssignment_Save implements ActionListener {

    private final Panel_Enrollment panelEnrollment;
    private final Dialog_SectionAssignment dialog;

    public DialogSectionAssignment_Save(Panel_Enrollment panelEnrollment,Dialog_SectionAssignment dialog) {
        this.panelEnrollment = panelEnrollment;
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SectionDaoImpl sectionDaoImpl = new SectionDaoImpl();
        int sectionId = ((Section)dialog.getJcmbSection().getSelectedItem()).getSectionId();
        JTable table = dialog.getJtblSectionStudents();
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < table.getRowCount(); i++) {
            int studentId = Integer.parseInt(table.getValueAt(i, 0).toString().trim());
            Student student = new Student();
            student.setStudentId(studentId);
            studentList.add(student);
        }

        Section section = new Section();
        section.setSectionId(sectionId);
        section.setStudents(studentList);

        int choice = JOptionPane.showConfirmDialog(null, "Save?", "Save Confirmation", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            boolean isSuccessful = sectionDaoImpl.addStudentsToSection(section);
            if (isSuccessful) {
                JOptionPane.showMessageDialog(null, "Successfully saved changes!");
                panelEnrollment.loadEnrolledRecord();
            } else {
                JOptionPane.showMessageDialog(null, "Encountered problems while assigning students to section.\n Please contact your support.");
            }
        }
    }

}
