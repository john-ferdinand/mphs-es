
package renderer.credential;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import model.credential.Credential;

/**
 *
 * @author Jordan
 */
public class Renderer_Credential_CredentialMasterList_JTable extends DefaultTableCellRenderer{

    private final int credentialObjectIndex;
    private final int credentialStatusIndex;
    
    public Renderer_Credential_CredentialMasterList_JTable(int credentialObjectIndex,int credentialStatusIndex){
        this.credentialObjectIndex = credentialObjectIndex;
        this.credentialStatusIndex = credentialStatusIndex;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
        if(column == credentialObjectIndex){
            if(value instanceof Credential){
                Credential c = (Credential)value;
                ((JLabel)cellComponent).setText(c.getCredentialName());
            }
        }
        return cellComponent;
    }
    
}
