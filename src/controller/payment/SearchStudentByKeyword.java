
package controller.payment;

import daoimpl.StudentDaoImpl;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JOptionPane;
import model.schoolyear.SchoolYear;
import model.student.Student;
import model.user.User;
import view.payment.Dialog_SearchStudentByKeyword;
import view.payment.Panel_Payment;

/**
 *
 * @author Jordan
 */
public class SearchStudentByKeyword implements KeyListener{

    private final Panel_Payment view;
    private final SchoolYear currentSchoolYear;
    private final StudentDaoImpl studentDaoImpl;
    private final User user;

    public SearchStudentByKeyword(Panel_Payment view) {
        this.view = view;
        this.currentSchoolYear = view.getCurrentSchoolYear();
        this.user = view.getUser();
        studentDaoImpl = new StudentDaoImpl();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getSource() == view.getJtfSearchBoxMakePayment()){
            String keyword = view.getJtfSearchBoxMakePayment().getText().trim();
            if (e.getKeyCode() == KeyEvent.VK_ENTER && !keyword.isEmpty()) {
                List<Student> students = studentDaoImpl.getStudentsByKeyword(keyword);
                if(students.size() != 0){
                    Dialog_SearchStudentByKeyword dialog = new Dialog_SearchStudentByKeyword(null, true, view, students, currentSchoolYear,user);
                    dialog.pack();
                    dialog.setLocationRelativeTo(null);
                    dialog.setPreferredSize(new Dimension(800, 450));
                    dialog.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null,"No record found.\nPlease try to input Registration Id or Student No or Lastname");
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
