package view.admintools;


import component_model_loader.GradeLevelJCompModelLoader;
import component_model_loader.PaymentTermJCompModelLoader;
import component_model_loader.SchoolYearJCompModelLoader;
import component_renderers.Renderer_GradeLevel_JComboBox;
import controller.admintools.DeactivateAllStudents;
import controller.admintools.DeleteAlPaymentAndTransactionRecord;
import controller.admintools.DeleteAllStudentRecord;
import controller.admintools.GenerateSubjects;
import controller.admintools.RecordGeneratorController;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author John Ferdinand Antonio
 */
public class AdminTools extends javax.swing.JFrame {

    public AdminTools() {
        initComponents();
        UIManager.put("ComboBox.disabledBackground", new Color(212, 212, 210));
        UIManager.put("ComboBox.disabledForeground", Color.BLACK);
        UIManager.put("TextField.disabledBackground", new Color(255, 255, 255));
        UIManager.put("TextField.inactiveForeground", Color.BLACK);
        initializeModels();
        initializeRenderers();
        initializeControllers();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jcmbGradeLevel = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jcmbSchoolYear = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jcmbPaymentTerm = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jcmbGradeLevelForDob = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jcmbRecordCount = new javax.swing.JComboBox<>();
        jbtnGenerateRegistration = new javax.swing.JButton();
        jbtnDeactivateStudents = new javax.swing.JButton();
        jbtnDeleteAllStudentRecord = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jbtnDeletePaymentAndTuitionRecord = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jbtnGenerateSubjects = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Grade Level");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jLabel1, gridBagConstraints);

        jcmbGradeLevel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbGradeLevelItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jcmbGradeLevel, gridBagConstraints);

        jLabel2.setText("School Year");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jLabel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jcmbSchoolYear, gridBagConstraints);

        jLabel3.setText("Payment Term");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jLabel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jcmbPaymentTerm, gridBagConstraints);

        jLabel4.setText("GradeLevel for DOB ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jLabel4, gridBagConstraints);

        jcmbGradeLevelForDob.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        jcmbGradeLevelForDob.setSelectedIndex(-1);
        jcmbGradeLevelForDob.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jcmbGradeLevelForDob, gridBagConstraints);

        jLabel16.setText("Record Count ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jLabel16, gridBagConstraints);

        jcmbRecordCount.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcmbRecordCount.setSelectedIndex(-1);
        jcmbRecordCount.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcmbRecordCountItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jcmbRecordCount, gridBagConstraints);

        jbtnGenerateRegistration.setText("Generate");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel1.add(jbtnGenerateRegistration, gridBagConstraints);

        jbtnDeactivateStudents.setText("Deactivate Students");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 9;
        jPanel1.add(jbtnDeactivateStudents, gridBagConstraints);

        jbtnDeleteAllStudentRecord.setText("Delete All Students");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jbtnDeleteAllStudentRecord, gridBagConstraints);

        jTabbedPane1.addTab("Student", jPanel1);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jbtnDeletePaymentAndTuitionRecord.setText("Delete Payment & Tuition Record");
        jPanel2.add(jbtnDeletePaymentAndTuitionRecord, new java.awt.GridBagConstraints());

        jTabbedPane1.addTab("Payment & Fees", jPanel2);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jbtnGenerateSubjects.setText("Generate K12 Subjects");
        jPanel3.add(jbtnGenerateSubjects, new java.awt.GridBagConstraints());

        jTabbedPane1.addTab("Subject", jPanel3);

        getContentPane().add(jTabbedPane1, new java.awt.GridBagConstraints());

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcmbGradeLevelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbGradeLevelItemStateChanged
            int selected = jcmbGradeLevel.getSelectedIndex();
            jcmbGradeLevelForDob.setSelectedIndex(selected);
    }//GEN-LAST:event_jcmbGradeLevelItemStateChanged

    private void jcmbRecordCountItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcmbRecordCountItemStateChanged
        String recordCount = jcmbRecordCount.getSelectedItem().toString().trim();
        jbtnGenerateRegistration.setText("Generate "+recordCount+ " Student record.");
    }//GEN-LAST:event_jcmbRecordCountItemStateChanged

    private DefaultComboBoxModel getRecordCountModel(){
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for(int x = 1; x <= 500; x++){
            model.addElement(x);
        }
        model.setSelectedItem(null);
        return model;
    }
    
    private void initializeControllers() {
        jbtnGenerateRegistration.addActionListener(
                new RecordGeneratorController
                    (this,jcmbGradeLevelForDob, jcmbGradeLevel, 
                            jcmbSchoolYear, jcmbPaymentTerm, jcmbRecordCount)
        );
        jbtnDeletePaymentAndTuitionRecord.addActionListener(new DeleteAlPaymentAndTransactionRecord());
        jbtnDeleteAllStudentRecord.addActionListener(new DeleteAllStudentRecord());
        jbtnDeactivateStudents.addActionListener(new DeactivateAllStudents());
        jbtnGenerateSubjects.addActionListener(new GenerateSubjects());
    }
    
    private void initializeModels(){
        jcmbRecordCount.setModel(getRecordCountModel());
        jcmbGradeLevel.setModel(new GradeLevelJCompModelLoader().getAllGradeLevels());
        jcmbGradeLevelForDob.setModel(new GradeLevelJCompModelLoader().getAllGradeLevels());
        jcmbSchoolYear.setModel(new SchoolYearJCompModelLoader().getAllSchoolYearStart());
        jcmbSchoolYear.setSelectedIndex(-1);
//        jcmbPaymentTerm.setModel(new PaymentTermJCompModelLoader().getNames());
    }
    
    private void initializeRenderers(){
        jcmbGradeLevel.setRenderer(new Renderer_GradeLevel_JComboBox());
        jcmbGradeLevelForDob.setRenderer(new Renderer_GradeLevel_JComboBox());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton jbtnDeactivateStudents;
    private javax.swing.JButton jbtnDeleteAllStudentRecord;
    private javax.swing.JButton jbtnDeletePaymentAndTuitionRecord;
    private javax.swing.JButton jbtnGenerateRegistration;
    private javax.swing.JButton jbtnGenerateSubjects;
    private javax.swing.JComboBox<String> jcmbGradeLevel;
    private javax.swing.JComboBox<String> jcmbGradeLevelForDob;
    private javax.swing.JComboBox<String> jcmbPaymentTerm;
    private javax.swing.JComboBox<String> jcmbRecordCount;
    private javax.swing.JComboBox<String> jcmbSchoolYear;
    // End of variables declaration//GEN-END:variables
}
