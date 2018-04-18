
package controller.curriculum;

import daoimpl.CurriculumDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.curriculum.Curriculum;
import model.subject.Subject;

/**
 *
 * @author Jordan
 */
public class CreateCurriculum implements ActionListener{

    private CurriculumDaoImpl curriculumDaoImpl;
    private SchoolYearDaoImpl schoolYearDaoImpl;
    
    private JTextField jtfCurriculumName;
    private JTextArea jtaCurriculumDescription;
    private JTable jtblCurrentSubjects;
    
    public CreateCurriculum(JTextField jtfCurriculumName, JTextArea jtaCurriculumDescription, JTable jtblCurrentSubjects){
        this.jtfCurriculumName = jtfCurriculumName;
        this.jtaCurriculumDescription = jtaCurriculumDescription;
        this.jtblCurrentSubjects = jtblCurrentSubjects;
        
        schoolYearDaoImpl = new SchoolYearDaoImpl();
        curriculumDaoImpl = new CurriculumDaoImpl();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int choice = JOptionPane.showConfirmDialog(null, "Save Curriculum?", "Save Confirmation", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            if (add()) {
                JOptionPane.showMessageDialog(null,"Successfully added Curriculum.");
            }else{
                JOptionPane.showMessageDialog(null,"Failed to add Curriculum.");
            }
        }
    }

    private boolean add() {
        boolean isAdded;
        Curriculum curriculum = new Curriculum();
        curriculum.setTitle(jtfCurriculumName.getText().trim());
        curriculum.setDescription(jtaCurriculumDescription.getText().trim());
        curriculum.setSubjects(getCurriculumSubjects());
        curriculum.setSchoolYearId(schoolYearDaoImpl.getCurrentSchoolYearId());
        isAdded = curriculumDaoImpl.addCurriculum(curriculum);
        return isAdded;
    }
    
    private List<Subject> getCurriculumSubjects() {
        List<Subject> subjectList = new ArrayList<>();
        DefaultTableModel tableModel = (DefaultTableModel) jtblCurrentSubjects.getModel();
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            Subject s = new Subject();
            s.setSubjectId(Integer.parseInt(tableModel.getValueAt(row, 0).toString().trim()));
            s.setSubjectMinutes(Integer.parseInt(tableModel.getValueAt(row, 4).toString()));
            subjectList.add(s);
        }
        return subjectList;
    }
}
