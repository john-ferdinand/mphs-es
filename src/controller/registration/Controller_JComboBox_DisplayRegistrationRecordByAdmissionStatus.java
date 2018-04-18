package controller.registration;

import component_model_loader.RegistrationJCompModelLoader;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jordan
 */
public class Controller_JComboBox_DisplayRegistrationRecordByAdmissionStatus implements ItemListener{
    
    private RegistrationJCompModelLoader registrationJCompModelLoader;
    private JTable jtblRegistrationRecord;
    private JComboBox jcmbAdmissionStatus;

    public Controller_JComboBox_DisplayRegistrationRecordByAdmissionStatus(JTable jtblRegistrationRecord, JComboBox jcmbAdmissionStatus) {
        registrationJCompModelLoader = new RegistrationJCompModelLoader();
        this.jtblRegistrationRecord = jtblRegistrationRecord;
        this.jcmbAdmissionStatus = jcmbAdmissionStatus;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (jcmbAdmissionStatus.getSelectedIndex() > -1) {
            loadDataToTable();
        }
    }
    
    private void loadDataToTable(){
        registrationJCompModelLoader.getAllRegistrationInfoByAdmissionStatus(jcmbAdmissionStatus, jtblRegistrationRecord);
    }
}
