package component_model_loader;

import daoimpl.RegistrationDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.registration.Registration;
import model.schoolyear.SchoolYear;

/**
 *
 * @author Jordan
 */
public class RegistrationJCompModelLoader {
    private final RegistrationDaoImpl registrationDaoImpl;
    private final SchoolYearDaoImpl schoolYearDaoImpl;
    
    public RegistrationJCompModelLoader(){
        registrationDaoImpl = new RegistrationDaoImpl();
        schoolYearDaoImpl = new SchoolYearDaoImpl();
    }
    
    public DefaultTableModel getAllRegistrationInfoByAdmissionStatus(JComboBox jcmbAdmissionStatus, JTable jTable) {
        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
        tableModel.setRowCount(0);
        int admissionStatus = jcmbAdmissionStatus.getSelectedItem().toString().equalsIgnoreCase("Complete") ? 1 : 0;
        SchoolYear currentSy = schoolYearDaoImpl.getCurrentSchoolYear();
        List<Registration> registrationList = registrationDaoImpl.getAllRegistrationInfoByAdmissionStatus(admissionStatus,currentSy.getYearFrom());
        for (Registration r : registrationList) {
            Object[] rowData = {
                r.getRegistrationId(),
                r.getLastName(),
                r.getFirstName(),
                r.getMiddleName(),
                r.getGradeLevelNo() == 0 ? "Kindergarten" : r.getGradeLevelNo(),
                r.getRegistrationDate(),
                r.getIsAdmissionComplete() == true ? "Completed" : "Pending"
            };
            tableModel.addRow(rowData);
        }
        return tableModel;
    }
    
    public DefaultTableModel getAllRegisteredApplicants(int yearFrom, JTable jTable){
        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
        tableModel.setRowCount(0);
        Object[] registrationList = registrationDaoImpl.getAllRegistrationInfoBySyYearFrom(yearFrom).toArray();
        for (Object o : registrationList) {
            Registration r = (Registration) o;
            Object[] rowData = {
                r.getRegistrationId(),
                r.getLastName(),
                r.getFirstName(),
                r.getMiddleName(),
                r.getGradeLevelNo() == 0 ? "Kindergarten" : r.getGradeLevelNo(),
                r.getRegistrationDate(),
                r.getIsAdmissionComplete() == true? "Completed" : "Pending",
                r.getStudentNo() == 0? "" : r.getStudentNo()
            };
            tableModel.addRow(rowData);
        }
        return tableModel;
    }
    
    public DefaultTableModel getAllRegisteredApplicants(int yearFrom, int gradeLevelNo, JTable jTable){
        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
        tableModel.setRowCount(0);
        Object[] registrationList = registrationDaoImpl.getAllRegistrationInfoBy(yearFrom,gradeLevelNo).toArray();
        for (Object o : registrationList) {
            Registration r = (Registration) o;
            Object[] rowData = {
                r.getRegistrationId(),
                r.getLastName(),
                r.getFirstName(),
                r.getMiddleName(),
                r.getGradeLevelNo() == 0 ? "Kindergarten" : r.getGradeLevelNo(),
                r.getRegistrationDate(),
                r.getIsAdmissionComplete() == true? "Completed" : "Pending",
                r.getStudentNo() == 0? "" : r.getStudentNo()
            };
            tableModel.addRow(rowData);
        }
        return tableModel;
    }
    
    
    public DefaultTableModel getAllRegisteredApplicantsByKeyword(JTextField jtfSearchBox,JTable jTable, int schoolYearFrom){
        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
        tableModel.setRowCount(0);
        List<Registration> registrationList = registrationDaoImpl.getAllRegistrationInfoByWildCard(jtfSearchBox.getText().trim(),schoolYearFrom);
        for (Registration r : registrationList) {
            Object[] rowData = {
                r.getRegistrationId(),
                r.getLastName(),
                r.getFirstName(),
                r.getMiddleName(),
                r.getGradeLevelNo() == 0 ? "Kindergarten" : r.getGradeLevelNo(),
                r.getRegistrationDate(),
                r.getIsAdmissionComplete() == true? "Completed" : "Pending"
            };
            tableModel.addRow(rowData);
        }
        return tableModel;
    }
}
