
package controller.admintools;

import daoimpl.AdminToolsDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.testdata.SubjectTestDataModel;

/**
 *
 * @author John Ferdinand Antonio
 */
public class GenerateSubjects implements ActionListener{
    
    @Override
    public void actionPerformed(ActionEvent e) {
        AdminToolsDaoImpl atdi = new AdminToolsDaoImpl();
        SubjectTestDataModel s = new SubjectTestDataModel();
        
        int choice = JOptionPane.showConfirmDialog(null, "Generate K12 Subjects? ");
        if (choice == JOptionPane.YES_OPTION) {
            boolean isAdded = atdi.addSubjects(s);
            if (isAdded) {
                JOptionPane.showMessageDialog(null, "Successfully generated subjects.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to generate subjects.");
                System.out.println("Failed to generate Subjects.");
            }
        }
    }
    
}
