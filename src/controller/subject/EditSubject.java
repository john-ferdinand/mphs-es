package controller.subject;

import daoimpl.GradeLevelDaoImpl;
import daoimpl.SubjectDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.gradelevel.GradeLevel;
import model.subject.Subject;
import view.fees.JdlgFeeCrud;

/**
 *
 * @author Jordan
 */
public class EditSubject implements ActionListener {

    private final int subjectIdOfSelected;
    
    private final JTextField jtfSubjectCode;
    private final JTextField jtfSubjectName;
    private final JComboBox jcmbStatus;
    private final JComboBox jcmbGradeLevel;
    private final JTextArea jtaSubjectDescription;
    private final JDialog jdlgSubjectCrud;
    
    private SubjectDaoImpl subjectDaoImpl;
    private GradeLevelDaoImpl gradeLevelDaoImpl;

    public EditSubject(int subjectIdOfSelected, JTextField jtfSubjectCode, JTextField jtfSubjectName,
            JComboBox jcmbStatus, JComboBox jcmbGradeLevel,
            JTextArea jtaSubjectDescription, JDialog jdlgSubjectCrud) {

        this.subjectIdOfSelected = subjectIdOfSelected;
        
        this.jtfSubjectCode = jtfSubjectCode;
        this.jtfSubjectName = jtfSubjectName;
        this.jcmbGradeLevel = jcmbGradeLevel;
        this.jcmbStatus = jcmbStatus;
        this.jtaSubjectDescription = jtaSubjectDescription;
        this.jdlgSubjectCrud = jdlgSubjectCrud;

        subjectDaoImpl = new SubjectDaoImpl();
        gradeLevelDaoImpl = new GradeLevelDaoImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int choice = JOptionPane.showConfirmDialog(null, "Update Subject?", "Update", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            if (updateSubject()) {
                JOptionPane.showMessageDialog(null, "Successfully updated subject!");
                jdlgSubjectCrud.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update subject.");
            }
        }
    }

    private boolean updateSubject() {
        boolean isUpdated = false;
        Subject subject = new Subject();
        subject.setSubjectId(subjectIdOfSelected);
        subject.setSubjectCode(jtfSubjectCode.getText());
        subject.setSubjectTitle(jtfSubjectName.getText());
        subject.setSubjectDescription(jtaSubjectDescription.getText());
        subject.setIsActive(jcmbStatus.getSelectedItem().equals("Yes"));
        int level = Integer.parseInt(jcmbGradeLevel.getSelectedItem().toString());
        GradeLevel gradeLevel = new GradeLevel();
        gradeLevel.setLevelNo(level);
        gradeLevel.setGradeLevelID(gradeLevelDaoImpl.getId(level));
        
        subject.setGradeLevel(gradeLevel);
        
        isUpdated = subjectDaoImpl.editSubject(subject);
        
        return isUpdated;
    }
}
