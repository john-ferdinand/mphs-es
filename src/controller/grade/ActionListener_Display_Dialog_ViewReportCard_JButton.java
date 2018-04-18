
package controller.grade;

import daoimpl.StudentDaoImpl;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.student.Student;
import view.grades.View_Dialog_ViewReportCard;
import view.grades.View_Panel_GradingSystem;

/**
 *
 * @author Jordan
 */
public class ActionListener_Display_Dialog_ViewReportCard_JButton implements ActionListener {

    private final View_Panel_GradingSystem view;
    private final StudentDaoImpl studentDaoImpl;

    public ActionListener_Display_Dialog_ViewReportCard_JButton(View_Panel_GradingSystem view) {
        this.view = view;
        studentDaoImpl = new StudentDaoImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (view.getJtblAdvisoryGradesList().getSelectedRowCount() > 0) {
            displayDialog();
        } else {
            JOptionPane.showMessageDialog(null, "Please select record from the list.");
        }
    }

    private void displayDialog() {
        Student student = getStudent();

        View_Dialog_ViewReportCard dialog = new View_Dialog_ViewReportCard(null, true, student);
        dialog.pack();
        dialog.setTitle("Report Card");
        dialog.setPreferredSize(new Dimension(600, 500));
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private Student getStudent() {
        int rowSelected = view.getJtblAdvisoryGradesList().getSelectedRow();
        int studentId = Integer.parseInt(view.getJtblAdvisoryGradesList().getValueAt(rowSelected, 0).toString());
        Student student = studentDaoImpl.getStudentByStudentId(studentId);
        return student;
    }

}
