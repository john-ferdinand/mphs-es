package component_model_loader;

import daoimpl.CredentialDaoImpl;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.credential.Credential;

public class CredentialJCompModelLoader {
   
    private final CredentialDaoImpl credentialDaoImpl;
    
    public CredentialJCompModelLoader(){
        credentialDaoImpl = new CredentialDaoImpl();
    }
    
    public DefaultTableModel getAllCredentialsFor(JTable table){
        DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
        tableModel.setRowCount(0);
        List<Credential> credentialList = credentialDaoImpl.getAllCredentials();
        for(Credential c : credentialList){
            Object[] rowData ={c,c.getIsActive()==true?"Active":"Inactive",c.getDateAdded()};
            tableModel.addRow(rowData);
        }
        return tableModel;
    }
}
