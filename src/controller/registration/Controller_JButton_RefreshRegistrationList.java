package controller.registration;

import component_model_loader.RegistrationJCompModelLoader;
import daoimpl.SchoolYearDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jordan
 */
public class Controller_JButton_RefreshRegistrationList implements ActionListener{

    private JTable jtblRegistrationMasterList;
    private RegistrationJCompModelLoader registrationJCompModelLoader;
    
    
    public Controller_JButton_RefreshRegistrationList(JTable jtblRegistrationMasterList) {
        this.jtblRegistrationMasterList = jtblRegistrationMasterList;
        registrationJCompModelLoader = new RegistrationJCompModelLoader();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        refresh();
    }
    
    private void refresh(){
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel = registrationJCompModelLoader.getAllRegisteredApplicants(SchoolYearDaoImpl.getCurrentSchoolYearFrom(), jtblRegistrationMasterList);
        jtblRegistrationMasterList.setModel(tableModel);
    }
    
}
