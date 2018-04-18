
package controller.reports;

import daoimpl.StudentDaoImpl;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import model.student.Student;
import view.reports.Dialog_ReportCard_SearchStudent;
import view.reports.Panel_Reports;

/**
 *
 * @author Jordan
 */
public class Controller_Reports_ReportCard_Search_JTextField implements KeyListener{
    
    private final Panel_Reports panelReports;
    private final StudentDaoImpl studentDaoImpl;

    public Controller_Reports_ReportCard_Search_JTextField(Panel_Reports panelReports) {
        this.panelReports = panelReports;
        this.studentDaoImpl = new StudentDaoImpl();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!panelReports.getJtfReportCardSearchBox().getText().trim().isEmpty()){
                String searchKeyword = panelReports.getJtfReportCardSearchBox().getText().trim();
                List<Student> students = studentDaoImpl.getStudentsByKeyword(searchKeyword);
                Dialog_ReportCard_SearchStudent dialog = new Dialog_ReportCard_SearchStudent(null, true, panelReports, students);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    
    
    
}
