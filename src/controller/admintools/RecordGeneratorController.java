/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admintools;


import daoimpl.GradeLevelDaoImpl;
import daoimpl.PaymentTermDaoImpl;
import daoimpl.AdminToolsDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.testdata.StudentTestDataModel;
import model.registration.Registration;


/**
 *
 * @author John Ferdinand Antonio
 */
public class RecordGeneratorController implements ActionListener{
    GradeLevelDaoImpl gradeLevelDaoImpl;
    PaymentTermDaoImpl paymentTermDaoImpl;
    SchoolYearDaoImpl schoolYearDaoImpl;
    StudentTestDataModel recordModel;
    private final JFrame frame;
    private final JComboBox jcmbGradeLevelForDob;
    private final JComboBox jcmbGradeLevel;
    private final JComboBox jcmbSchoolYear;
    private final JComboBox jcmbPaymentTerm;
    private final JComboBox jcmbRecordCount;
    
    public RecordGeneratorController(JFrame frame, JComboBox jcmbGradeLevelForDob, JComboBox jcmbGradeLevel,
            JComboBox jcmbSchoolYear, JComboBox jcmbPaymentTerm, JComboBox jcmbRecordCount) {
        this.frame = frame;
        this.jcmbGradeLevelForDob = jcmbGradeLevelForDob;
        this.jcmbGradeLevel = jcmbGradeLevel;
        this.jcmbSchoolYear = jcmbSchoolYear;
        this.jcmbPaymentTerm = jcmbPaymentTerm;
        this.jcmbRecordCount = jcmbRecordCount;
        gradeLevelDaoImpl = new GradeLevelDaoImpl();
        paymentTermDaoImpl = new PaymentTermDaoImpl();
        schoolYearDaoImpl = new SchoolYearDaoImpl();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int choice = JOptionPane.showConfirmDialog(null, "Generate record?");
        if(choice == JOptionPane.YES_OPTION){
            generate();
        }
        
    }
 
    private void generate(){
        AdminToolsDaoImpl rdi = new AdminToolsDaoImpl();
        int levelForDOB = Integer.parseInt(jcmbGradeLevelForDob.getSelectedItem().toString().trim());
        int level = Integer.parseInt(jcmbGradeLevel.getSelectedItem().toString().trim());
        int schoolYear = Integer.parseInt(jcmbSchoolYear.getSelectedItem().toString().trim());
        String paymentTerm = jcmbPaymentTerm.getSelectedItem().toString().trim();
        int recordCount = Integer.parseInt(jcmbRecordCount.getSelectedItem().toString().trim());
        recordModel = new StudentTestDataModel();
        
        int gradeLevelId = gradeLevelDaoImpl.getId(level);
        int schoolYearId = schoolYearDaoImpl.getId(schoolYear);
        int paymentTermId = paymentTermDaoImpl.getPaymentTermIDByName(paymentTerm);
        
        int countOfFailed = 0;
        int countOfAdded = 0;
        for (int i = 1; i <= recordCount; i++) {
            Registration registration
                    = recordModel.getRegistrationRecord(levelForDOB, gradeLevelId, schoolYearId, paymentTermId);
            boolean isAdded = rdi.generateStudent(registration);
            if (isAdded) {
                countOfAdded++;
                System.out.println("Added: " + i);
            } else {
                countOfFailed++;
                System.out.println("Failed to add: " + i);
            }
        }
        if(countOfFailed == 0){
            JOptionPane.showMessageDialog(null,"Sucecssfully generated "+ countOfAdded+" records.");
            frame.dispose();
        }else{
            JOptionPane.showMessageDialog(null,countOfFailed + " failed to be generated.");
        }
    }
}
