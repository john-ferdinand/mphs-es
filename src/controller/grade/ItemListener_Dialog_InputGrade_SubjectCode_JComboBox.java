/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.grade;

import daoimpl.GradeDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JComboBox;
import model.grade.Grade;
import model.schoolyear.SchoolYear;
import model.student.Student;
import model.subject.Subject;
import model.user.User;
import view.grades.View_Dialog_InputGrade;

/**
 *
 * @author Jordan
 */
public class ItemListener_Dialog_InputGrade_SubjectCode_JComboBox implements ItemListener{
    
    private final View_Dialog_InputGrade view;
    private final GradeDaoImpl gradeDaoImpl;
    private final SchoolYearDaoImpl schoolYearDaoImpl;
    private final User user;
    private final SchoolYear currentSchoolYear;
    
    public ItemListener_Dialog_InputGrade_SubjectCode_JComboBox(View_Dialog_InputGrade view, User user, SchoolYear currentSchoolYear) {
        this.view = view;
        this.user = user;
        this.currentSchoolYear = currentSchoolYear;
        gradeDaoImpl = new GradeDaoImpl();
        schoolYearDaoImpl = new SchoolYearDaoImpl();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            clearQuartersColumnValues();
            JComboBox jcmbSubject = (JComboBox) e.getSource();
            Subject subject = (Subject) jcmbSubject.getSelectedItem();
            SchoolYear schoolYear = schoolYearDaoImpl.getCurrentSchoolYear();
            List<Grade> gradeList = new ArrayList<>();
            if (view.getJtblGradingSheet().getRowCount() > 0) {
                for (int row = 0; row < view.getJtblGradingSheet().getRowCount(); row++) {
                    Object studentId = view.getJtblGradingSheet().getValueAt(row, 0);
                    Student student = new Student();
                    student.setStudentId(Integer.parseInt(studentId.toString().trim()));
                    gradeList = gradeDaoImpl.getGradesOf(student, subject, schoolYear);
                    setGradesToColumns(gradeList, row);
                }
               List<Integer>quarterColumns = Arrays.asList(3,4,5,6);
//               view.getJtblGradingSheet().setModel(new MyInputGradeTableModel(view.getJtblGradingSheet(),quarterColumns, user,currentSchoolYear));
            }
        }
    }
    
    private void setGradesToColumns(List<Grade> gradeList, int row) {
        for (Grade g : gradeList) {
            for (int r = 0; r < view.getJtblGradingSheet().getRowCount(); r++) {
                if (g.getGradingPeriod() == 1) {
                    view.getJtblGradingSheet().setValueAt(g.getValue(), row, 3);
                }else if(g.getGradingPeriod() == 2){
                    view.getJtblGradingSheet().setValueAt(g.getValue(), row, 4);
                }else if(g.getGradingPeriod() == 3){
                    view.getJtblGradingSheet().setValueAt(g.getValue(), row, 5);
                }else if(g.getGradingPeriod() == 4){
                    view.getJtblGradingSheet().setValueAt(g.getValue(), row, 6);
                }
            }
        }
    }
    
    private void clearQuartersColumnValues(){
        for(int row = 0; row < view.getJtblGradingSheet().getRowCount(); row++){
            for(int col = 0; col < view.getJtblGradingSheet().getColumnCount(); col++){
                if(col == 3 || col == 4 || col == 5 || col == 6){
                    view.getJtblGradingSheet().setValueAt("", row, col);
                }
            }
        }
    }
    
}
