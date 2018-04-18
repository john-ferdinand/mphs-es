package controller.enrollment;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JTable;

/**
 *
 * @author John Ferdinand Antonio
 */
public class Old_ClickPopupMenuItem implements ActionListener {

    private final JMenuItem jmiCopyStudentId;
    private final JMenuItem jmiCopyRegistrationId;
    private final JMenuItem jmiCopyStudentLastName;
    private final JTable jtblStudentsList;
    private final MouseEvent mouseEvent;
    
    public Old_ClickPopupMenuItem(JTable jtblStudentsList,JMenuItem jmiCopyStudentId,JMenuItem jmiCopyRegistrationId,JMenuItem jmiCopyStudentLastName,MouseEvent mouseEvent){
        this.jtblStudentsList = jtblStudentsList;
        this.jmiCopyStudentId = jmiCopyStudentId;
        this.jmiCopyRegistrationId = jmiCopyRegistrationId;
        this.jmiCopyStudentLastName = jmiCopyStudentLastName;
        this.mouseEvent = mouseEvent;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Selected: " + e.getActionCommand());
        JMenuItem item = (JMenuItem) e.getSource();

        Point point = mouseEvent.getPoint();
        int rowSelected = jtblStudentsList.rowAtPoint(point);
        jtblStudentsList.setRowSelectionInterval(rowSelected, rowSelected);
        
        if (item == jmiCopyStudentId) {
            int row = jtblStudentsList.getSelectedRow();
            int colStudentId = 0;

            StringSelection stringSelection = new StringSelection(String.valueOf(jtblStudentsList
                    .getModel().getValueAt(row, colStudentId)));
            Clipboard clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipBoard.setContents(stringSelection, null); //copies the value to clipboard
        } else if (item == jmiCopyRegistrationId) {
            int row = jtblStudentsList.getSelectedRow();
            int colRegistration = 1;

            StringSelection stringSelection = new StringSelection(String.valueOf(jtblStudentsList
                    .getModel().getValueAt(row, colRegistration)));
            Clipboard clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipBoard.setContents(stringSelection, null); //copies the value to clipboard
        } else if (item == jmiCopyStudentLastName) {
            int row = jtblStudentsList.getSelectedRow();
            int colLastName = 3;

            StringSelection stringSelection = new StringSelection(String.valueOf(jtblStudentsList
                    .getModel().getValueAt(row, colLastName)));
            Clipboard clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipBoard.setContents(stringSelection, null); //copies the value to clipboard
        }
    }

}
