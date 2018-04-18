package controller.curriculum;

import daoimpl.CurriculumDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.curriculum.Curriculum;
import model.subject.Subject;

/**
 *
 * @author Jordan
 */
public class EditCurriculum implements ActionListener {

    private int curriculumIdOfSelected;
    private final JTextField jtfCurriculumName;
    private final JTextArea jtaCurriculumDescription;
    private final JComboBox jcmbStatus;
    private final JTable jtblCurrentSubjects;

    private CurriculumDaoImpl curriculumDaoImpl;
    private SchoolYearDaoImpl schoolYearDaoImpl;

    public EditCurriculum(
            int curriculumIdOfSelected,
            JTextField jtfCurriculumName, JTextArea jtaCurriculumDescription,
            JComboBox jcmbStatus, JTable jtblCurrentSubjects) {
        this.curriculumIdOfSelected = curriculumIdOfSelected;
        this.jtfCurriculumName = jtfCurriculumName;
        this.jtaCurriculumDescription = jtaCurriculumDescription;
        this.jcmbStatus = jcmbStatus;
        this.jtblCurrentSubjects = jtblCurrentSubjects;
        
        curriculumDaoImpl = new CurriculumDaoImpl();
        schoolYearDaoImpl = new SchoolYearDaoImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int choice = JOptionPane.showConfirmDialog(null, "Update Curriculum?", "Update Confirmation", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            if (update()) {
                JOptionPane.showMessageDialog(null, "Successfully updated Curriculum.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update Curriculum.");
            }
        }
    }

    private boolean update() {
        boolean isUpdated = false;
        Curriculum curriculum = new Curriculum();
        curriculum.setCurriculumId(curriculumIdOfSelected);
        curriculum.setTitle(jtfCurriculumName.getText().trim());
        curriculum.setDescription(jtaCurriculumDescription.getText().trim());
        curriculum.setIsActive(jcmbStatus.getSelectedItem().toString().equals("Yes") ? true : false);
        curriculum.setSchoolYearId(schoolYearDaoImpl.getCurrentSchoolYearId());
        curriculum.setSubjects(getCurriculumSubjects());
        
        isUpdated = curriculumDaoImpl.updateCurriculum(curriculum);
        return isUpdated;
    }

    private List<Subject> getCurriculumSubjects() {
        List<Subject> curriculumSubjects = new ArrayList<>();
        for (int i = 0; i < jtblCurrentSubjects.getRowCount(); i++) {
            Subject s = new Subject();
            s.setSubjectId(Integer.parseInt(jtblCurrentSubjects.getValueAt(i, 0).toString().trim()));
            s.setSubjectMinutes(Integer.parseInt(jtblCurrentSubjects.getValueAt(i, 4).toString().trim()));
            curriculumSubjects.add(s);
        }
        return curriculumSubjects;
    }

}
