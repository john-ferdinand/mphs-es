
package controller.subjectcategory;

import daoimpl.SubjectCategoryDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.subject.Subject;
import model.subjectcategory.SubjectCategory;

/**
 *
 * @author Jordan
 */
public class CreateSubjectCategory implements ActionListener {

    private JTextField jtfCategoryName;
    private JTextArea jtaDescription;
    private JTable jtblAssignedSubjects;

    private SubjectCategoryDaoImpl subjectCategoryDaoImpl;

    public CreateSubjectCategory(JTextField jtfCategoryName, JTextArea jtaDescription, JTable jtblAssignedSubjects) {
        this.jtfCategoryName = jtfCategoryName;
        this.jtaDescription = jtaDescription;
        this.jtblAssignedSubjects = jtblAssignedSubjects;
        subjectCategoryDaoImpl = new SubjectCategoryDaoImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int choice = JOptionPane.showConfirmDialog(null, "Add Subject Category?", "Add Confirmation", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            if (!exists()) {
                if (add()) {
                    JOptionPane.showMessageDialog(null, "Successfully Added Subject Category.");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add Subject Category.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Subject Category Name already exists.");
            }
        }
    }

    private boolean exists(){
        boolean exists = subjectCategoryDaoImpl.subjectCategoryNameExists(jtfCategoryName.getText().trim());
        return exists;
    }
    
    private boolean add() {
        boolean isAdded = false;
        SubjectCategory sc = new SubjectCategory();
        sc.setDescription(jtaDescription.getText());
        sc.setSubjectCategoryName(jtfCategoryName.getText());
        sc.setSubjectsAssigned(getAssignedSubjects());

        isAdded = subjectCategoryDaoImpl.addSubjectCategory(sc);

        return isAdded;
    }

    private List<Subject> getAssignedSubjects() {
        DefaultTableModel tableModel = (DefaultTableModel) jtblAssignedSubjects.getModel();
        List<Subject> subjectList = new ArrayList<>();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Subject s = new Subject();
            s.setSubjectId(Integer.parseInt(tableModel.getValueAt(i, 0).toString().trim()));
//            s.setSubjectTitle(tableModel.getValueAt(i, 1).toString().trim());
//            s.setSubjectCode(tableModel.getValueAt(i, 2).toString().trim());
            subjectList.add(s);
        }
        return subjectList;
    }
}
