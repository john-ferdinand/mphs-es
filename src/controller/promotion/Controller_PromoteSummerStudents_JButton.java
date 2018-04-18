/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.promotion;

import daoimpl.PromotionDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import daoimpl.StudentDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.gradelevel.GradeLevel;
import model.promotionInfo.Promotion;
import model.student.Student;
import view.enrollment.Panel_Enrollment;

/**
 *
 * @author Jordan
 */
public class Controller_PromoteSummerStudents_JButton implements ActionListener{
    
    private final PromotionDaoImpl promotionDaoImpl;
    private final SchoolYearDaoImpl schoolYearDaoImpl;
    private final StudentDaoImpl studentDaoImpl;
    private final Panel_Enrollment view;
    
    public Controller_PromoteSummerStudents_JButton(Panel_Enrollment view){
        this.promotionDaoImpl = new PromotionDaoImpl();
        this.schoolYearDaoImpl = new SchoolYearDaoImpl();
        this.studentDaoImpl = new StudentDaoImpl();
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int choice = JOptionPane.showConfirmDialog(null, "Promote summer students", "Promotion confirmation", JOptionPane.YES_NO_OPTION);
        if(choice == JOptionPane.YES_OPTION){
            boolean isSuccessful = promotionDaoImpl.promoteSummerStudents(getStudentsToPromote(), view.getUser());
            if(isSuccessful){
                JOptionPane.showMessageDialog(null,"Successfully promoted (enrolled) students");
            }else{
                JOptionPane.showMessageDialog(null,"Encountered problems processing promotion");
            }
        }
    }
    
    private List<Student> getStudentsToPromote() {
        List<Student> studentsToPromote = new ArrayList<>();
        JTable t = view.getJtblSummerStudents();
            for (int row = 0; row < t.getRowCount(); row++) {
                Student student = studentDaoImpl.getStudentByStudentNo(Integer.parseInt(t.getValueAt(row, 1).toString().trim()));
                GradeLevel gradeLevelFrom = new GradeLevel();
                gradeLevelFrom.setLevelNo(Integer.parseInt(t.getValueAt(row, 3).toString().trim()));
                GradeLevel gradeLevelTo = new GradeLevel();
                gradeLevelTo.setLevelNo(gradeLevelFrom.getLevelNo() + 1);
                String isEnrolled = t.getValueAt(row, 6).toString().trim();
                String isAlreadyPromoted = t.getValueAt(row, 8).toString().trim();
                if (isEnrolled.equalsIgnoreCase("Yes") && isAlreadyPromoted.equalsIgnoreCase("No")) {
                    Promotion promotion = new Promotion();
                    promotion.setStudent(student);
                    promotion.setGradeLevelFrom(gradeLevelFrom);
                    promotion.setGradeLevelTo(gradeLevelTo);
                    promotion.setSchoolYearPromoted(schoolYearDaoImpl.getCurrentSchoolYear());
                    student.setPromotion(promotion);
                    studentsToPromote.add(student);
                }
            }
        return studentsToPromote;
    }
}
