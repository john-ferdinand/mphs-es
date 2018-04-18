package controller.subjectcategory;

import daoimpl.SubjectCategoryDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.subject.Subject;
import model.subjectcategory.SubjectCategory;

/**
 *
 * @author Jordan
 */
public class EditSubjectCategory implements ActionListener{
    private int subjectCategoryId;
    
    private final JTextField jtfCategoryName;
    private final JTextArea jtaDescription;
    private final JTable jtblAssignedSubjects;
    private final JComboBox jcmbStatus;
    private final JDialog dialogSubjectCategoryCrud;
    
    private SubjectCategoryDaoImpl subjectCategoryDaoImpl;
    
    public EditSubjectCategory(
            int subjectCategoryId,JTextField jtfCategoryName, 
            JTextArea jtaDescription, JComboBox jcmbStatus, 
            JTable jtblAssignedSubjects, JDialog dialogSubjectCategoryCrud) {
        this.subjectCategoryId = subjectCategoryId;
        this.jtfCategoryName = jtfCategoryName;
        this.jtaDescription = jtaDescription;
        this.jcmbStatus = jcmbStatus;
        this.jtblAssignedSubjects = jtblAssignedSubjects;
        this.dialogSubjectCategoryCrud = dialogSubjectCategoryCrud;
        subjectCategoryDaoImpl = new SubjectCategoryDaoImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int choice = JOptionPane.showConfirmDialog(null, "Update Subject Category?", "Update Confirmation", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            if(update()){
                JOptionPane.showMessageDialog(null,"Successfully updated.");
                dialogSubjectCategoryCrud.dispose();
            }else{
                JOptionPane.showMessageDialog(null,"Failed to update.");
            }
        }
    }

    private boolean update() {
        boolean isUpdated = false;
        SubjectCategory sc = new SubjectCategory();
        sc = getSubjectCategory();
        isUpdated = subjectCategoryDaoImpl.updateSubjectCategory(sc);
        return isUpdated;
    }
    
    private SubjectCategory getSubjectCategory() {
        SubjectCategory subjectCategory = new SubjectCategory();
        subjectCategory.setSubjectCategoryId(subjectCategoryId);
        subjectCategory.setSubjectsAssigned(getAssignedSubjects());
        subjectCategory.setDescription(jtaDescription.getText());
        subjectCategory.setSubjectCategoryName(jtfCategoryName.getText());
        subjectCategory.setIsActive(jcmbStatus.getSelectedItem().equals("Yes") ? true : false);
        return subjectCategory;
    }
    
    private List<Subject> getAssignedSubjects(){
        List<Subject> assignedSubjects = new ArrayList<>();
        for(int i = 0; i<jtblAssignedSubjects.getRowCount(); i++){
            Subject s = new Subject();
            s.setSubjectId(Integer.parseInt(jtblAssignedSubjects.getValueAt(i, 0).toString().trim()));
            assignedSubjects.add(s);
        }
        return assignedSubjects;
    }
}
