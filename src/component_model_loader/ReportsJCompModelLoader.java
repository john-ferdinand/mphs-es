
package component_model_loader;

import daoimpl.ReportsDaoImpl;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.faculty.Faculty;
import model.schoolyear.SchoolYear;
import model.student.Student;

/**
 *
 * @author Jordan
 */
public class ReportsJCompModelLoader {
    
    private final ReportsDaoImpl reportsDaoImpl;

    public ReportsJCompModelLoader() {
        this.reportsDaoImpl = new ReportsDaoImpl();
    }
    
    public DefaultTableModel getClassListFor(JTable table, Faculty faculty, SchoolYear schoolYear) {
        ((DefaultTableModel) table.getModel()).setRowCount(0);
        String[] columns = {"No", "Student No", "Student Name", "Section", "Subject"};
        int no = 1;
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columns);
        List<Student> students = reportsDaoImpl.getClassListOf(faculty, schoolYear);
        
        for (Student student : students) {
            Object[] rowData = {
                no,student.getStudentNo(),
                student.getRegistration().getLastName()+", "+student.getRegistration().getFirstName()+" "+student.getRegistration().getMiddleName(),
                student.getSection().getSectionName(),student.getSubject().getSubjectCode()
            };
            tableModel.addRow(rowData);
            no++;
        }
        
        return tableModel;
    }
    
}
