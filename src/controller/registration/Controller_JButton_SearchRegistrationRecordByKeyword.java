package controller.registration;

import component_model_loader.RegistrationJCompModelLoader;
import daoimpl.SchoolYearDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.schoolyear.SchoolYear;

/**
 *
 * @author Jordan
 */
public class Controller_JButton_SearchRegistrationRecordByKeyword implements ActionListener{
    
    private final RegistrationJCompModelLoader registrationJCompModelLoader;
    private final SchoolYearDaoImpl schoolYearDaoImpl;
    
    private final JTextField jtfSearchRegistered;
    private final JTable jtblRegisteredMasterList;

    public Controller_JButton_SearchRegistrationRecordByKeyword(JTextField jtfSearchRegistered, JTable jtblRegisteredMasterList) {
        this.jtfSearchRegistered = jtfSearchRegistered;
        this.jtblRegisteredMasterList = jtblRegisteredMasterList;
        registrationJCompModelLoader = new RegistrationJCompModelLoader();
        schoolYearDaoImpl = new SchoolYearDaoImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        displayRecord();
    }
    
    private void displayRecord(){
        DefaultTableModel tableModel = new DefaultTableModel();
        JTextField textField = jtfSearchRegistered;
        JTable table = jtblRegisteredMasterList;
        SchoolYear currentSchoolYear = schoolYearDaoImpl.getCurrentSchoolYear();
        tableModel = registrationJCompModelLoader.getAllRegisteredApplicantsByKeyword(textField, table, currentSchoolYear.getYearFrom());
        jtblRegisteredMasterList.setModel(tableModel);
    }
    
}
