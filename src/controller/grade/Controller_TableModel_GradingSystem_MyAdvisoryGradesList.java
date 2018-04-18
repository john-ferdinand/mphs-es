package controller.grade;

import daoimpl.GradeDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import daoimpl.StudentDaoImpl;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import model.grade.Grade;
import model.schoolyear.SchoolYear;
import model.student.Student;
import view.grades.View_Panel_GradingSystem;

/**
 *
 * @author Jordan
 */
public class Controller_TableModel_GradingSystem_MyAdvisoryGradesList implements TableModelListener {

    private final View_Panel_GradingSystem view;
    private SchoolYearDaoImpl schoolYearDaoImpl;
    private StudentDaoImpl studentDaoImpl;
    private GradeDaoImpl gradeDaoImpl;

    public Controller_TableModel_GradingSystem_MyAdvisoryGradesList(View_Panel_GradingSystem view) {
        this.view = view;
        schoolYearDaoImpl = new SchoolYearDaoImpl();
        studentDaoImpl = new StudentDaoImpl();
        gradeDaoImpl = new GradeDaoImpl();
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        if (e.getColumn() == 0) {
            TableModel tableModel = (TableModel) e.getSource();
            SchoolYear schoolYear = schoolYearDaoImpl.getCurrentSchoolYear();
            if (view.getJtblAdvisoryGradesList().getRowCount() > 0) {
                for (int row = 0; row < view.getJtblAdvisoryGradesList().getRowCount(); row++) {
                    Student student = new Student();
                    student.setStudentId(Integer.parseInt(tableModel.getValueAt(row, 0).toString().trim()));
                    for (int col = 0; col < view.getJtblAdvisoryGradesList().getColumnCount(); col++) {
                        if (col == 5) {
                            int gradingPeriod = 1;
                            Grade grade = gradeDaoImpl.getGradeByStudentGradingPeriodAndSchoolYear(student, gradingPeriod, schoolYear);
                            view.getJtblAdvisoryGradesList().setValueAt(grade.getValue(), row, col);
                        } else if (col == 6) {
                            int gradingPeriod = 2;
                            Grade grade = gradeDaoImpl.getGradeByStudentGradingPeriodAndSchoolYear(student, gradingPeriod, schoolYear);
                            view.getJtblAdvisoryGradesList().setValueAt(grade.getValue(), row, col);
                        } else if (col == 7) {
                            int gradingPeriod = 3;
                            Grade grade = gradeDaoImpl.getGradeByStudentGradingPeriodAndSchoolYear(student, gradingPeriod, schoolYear);
                            view.getJtblAdvisoryGradesList().setValueAt(grade.getValue(), row, col);
                        } else if (col == 8) {
                            int gradingPeriod = 4;
                            Grade grade = gradeDaoImpl.getGradeByStudentGradingPeriodAndSchoolYear(student, gradingPeriod, schoolYear);
                            view.getJtblAdvisoryGradesList().setValueAt(grade.getValue(), row, col);
                        }
                    }
                }
            }
        }

    }

}
