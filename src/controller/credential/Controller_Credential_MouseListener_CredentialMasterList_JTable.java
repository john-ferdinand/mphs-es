package controller.credential;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import model.credential.Credential;
import model.gradelevel.GradeLevel;
import view.credential.Panel_Credential;

/**
 *
 * @author Jordan
 */
public class Controller_Credential_MouseListener_CredentialMasterList_JTable implements MouseListener, KeyListener{
    
    private final Panel_Credential view;

    public Controller_Credential_MouseListener_CredentialMasterList_JTable(Panel_Credential view) {
        this.view = view;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == view.getJtblCredentialMasterList()){
            if(view.getJtblCredentialMasterList().getRowCount() > 0){
                int rowSelected = view.getJtblCredentialMasterList().getSelectedRow();
                Credential credential = (Credential) view.getJtblCredentialMasterList().getValueAt(rowSelected,0);
                DefaultListModel listModel = (DefaultListModel) view.getJlstGradeLevelAssignment().getModel();
                listModel.removeAllElements();
                for(GradeLevel g : credential.getGradeLevelsAssigned()){
                    listModel.addElement(g.getLevelNo() == 0? "Kindergarten" : g.getLevelNo());
                }
                view.getJlstGradeLevelAssignment().setModel(listModel);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (e.getSource() == view.getJtblCredentialMasterList()) {
                if (view.getJtblCredentialMasterList().getRowCount() > 0) {
                    int rowSelected = view.getJtblCredentialMasterList().getSelectedRow();
                    Credential credential = (Credential) view.getJtblCredentialMasterList().getValueAt(rowSelected, 0);
                    DefaultListModel listModel = (DefaultListModel) view.getJlstGradeLevelAssignment().getModel();
                    listModel.removeAllElements();
                    for (GradeLevel g : credential.getGradeLevelsAssigned()) {
                        listModel.addElement(g.getLevelNo() == 0 ? "Kindergarten" : g.getLevelNo());
                    }
                    view.getJlstGradeLevelAssignment().setModel(listModel);
                }
            }
        }
    }
    
    
    
}
