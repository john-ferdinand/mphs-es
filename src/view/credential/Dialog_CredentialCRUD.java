
package view.credential;

import controller.credential.Controller_CreateCredential_JButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.schoolyear.SchoolYear;
import model.user.User;
import utility.initializer.Initializer;

/**
 *
 * @author Jordan
 */
public class Dialog_CredentialCRUD extends javax.swing.JDialog implements Initializer{

    private final SchoolYear currentSchoolYear;
    private final User user;
    
    public Dialog_CredentialCRUD(java.awt.Frame parent, boolean modal, String actionCommand, SchoolYear currentSchoolYear, User user) {
        super(parent, modal);
        initComponents();
        this.currentSchoolYear = currentSchoolYear;
        this.user = user;
        
        initDaoImpl();
        initJCompModelLoaders();
        initRenderers();
        initViewComponents();
        initControllers();
    }

    @Override
    public void initGridBagConstraints() {
    }

    @Override
    public void initJCompModelLoaders() {
    }

    @Override
    public void initRenderers() {
    }

    @Override
    public void initModels() {
    }

    @Override
    public void initViewComponents() {
    }

    @Override
    public void initControllers() {
        jbtnSave.addActionListener(new Controller_CreateCredential_JButton(this, currentSchoolYear, user));
    }

    @Override
    public void initDaoImpl() {
    }

    public JButton getJbtnCancel() {
        return jbtnCancel;
    }

    public JButton getJbtnSave() {
        return jbtnSave;
    }

    public JCheckBox getJcbGrade1() {
        return jcbGrade1;
    }

    public JCheckBox getJcbGrade10() {
        return jcbGrade10;
    }

    public JCheckBox getJcbGrade2() {
        return jcbGrade2;
    }

    public JCheckBox getJcbGrade3() {
        return jcbGrade3;
    }

    public JCheckBox getJcbGrade4() {
        return jcbGrade4;
    }

    public JCheckBox getJcbGrade5() {
        return jcbGrade5;
    }

    public JCheckBox getJcbGrade6() {
        return jcbGrade6;
    }

    public JCheckBox getJcbGrade7() {
        return jcbGrade7;
    }

    public JCheckBox getJcbGrade8() {
        return jcbGrade8;
    }

    public JCheckBox getJcbGrade9() {
        return jcbGrade9;
    }

    public JCheckBox getJcbKindergarten() {
        return jcbKindergarten;
    }

    public JComboBox<String> getJcmbStatus() {
        return jcmbStatus;
    }

    public JLabel getJlblCredentialName() {
        return jlblCredentialName;
    }

    public JLabel getJlblStatus() {
        return jlblStatus;
    }

    public JPanel getJpnlControls() {
        return jpnlControls;
    }

    public JPanel getJpnlGradeLevelAssignment() {
        return jpnlGradeLevelAssignment;
    }

    public JTextArea getJtaDescription() {
        return jtaDescription;
    }

    public JTextField getJtfCredentialName() {
        return jtfCredentialName;
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jlblCredentialName = new javax.swing.JLabel();
        jtfCredentialName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaDescription = new javax.swing.JTextArea();
        jlblStatus = new javax.swing.JLabel();
        jcmbStatus = new javax.swing.JComboBox<>();
        jpnlGradeLevelAssignment = new javax.swing.JPanel();
        jcbKindergarten = new javax.swing.JCheckBox();
        jcbGrade1 = new javax.swing.JCheckBox();
        jcbGrade2 = new javax.swing.JCheckBox();
        jcbGrade3 = new javax.swing.JCheckBox();
        jcbGrade4 = new javax.swing.JCheckBox();
        jcbGrade5 = new javax.swing.JCheckBox();
        jcbGrade6 = new javax.swing.JCheckBox();
        jcbGrade7 = new javax.swing.JCheckBox();
        jcbGrade8 = new javax.swing.JCheckBox();
        jcbGrade9 = new javax.swing.JCheckBox();
        jcbGrade10 = new javax.swing.JCheckBox();
        jpnlControls = new javax.swing.JPanel();
        jbtnCancel = new javax.swing.JButton();
        jbtnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(560, 345));
        setPreferredSize(new java.awt.Dimension(560, 345));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jlblCredentialName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblCredentialName.setText("Credential Name :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jlblCredentialName, gridBagConstraints);

        jtfCredentialName.setColumns(10);
        jtfCredentialName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jtfCredentialName, gridBagConstraints);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Description", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jScrollPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane1.setMinimumSize(new java.awt.Dimension(150, 39));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(150, 102));

        jtaDescription.setColumns(20);
        jtaDescription.setRows(5);
        jtaDescription.setPreferredSize(new java.awt.Dimension(150, 80));
        jScrollPane1.setViewportView(jtaDescription);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        jlblStatus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblStatus.setText("Status :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jlblStatus, gridBagConstraints);

        jcmbStatus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jcmbStatus, gridBagConstraints);

        jpnlGradeLevelAssignment.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Grade Level Assignment", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jpnlGradeLevelAssignment.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jpnlGradeLevelAssignment.setMinimumSize(new java.awt.Dimension(150, 0));
        jpnlGradeLevelAssignment.setPreferredSize(new java.awt.Dimension(150, 48));
        jpnlGradeLevelAssignment.setLayout(new java.awt.GridBagLayout());

        jcbKindergarten.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcbKindergarten.setText("Kindergarten");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlGradeLevelAssignment.add(jcbKindergarten, gridBagConstraints);

        jcbGrade1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcbGrade1.setText("Grade 1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlGradeLevelAssignment.add(jcbGrade1, gridBagConstraints);

        jcbGrade2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcbGrade2.setText("Grade 2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlGradeLevelAssignment.add(jcbGrade2, gridBagConstraints);

        jcbGrade3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcbGrade3.setText("Grade 3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlGradeLevelAssignment.add(jcbGrade3, gridBagConstraints);

        jcbGrade4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcbGrade4.setText("Grade 4");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlGradeLevelAssignment.add(jcbGrade4, gridBagConstraints);

        jcbGrade5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcbGrade5.setText("Grade 5");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlGradeLevelAssignment.add(jcbGrade5, gridBagConstraints);

        jcbGrade6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcbGrade6.setText("Grade 6");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlGradeLevelAssignment.add(jcbGrade6, gridBagConstraints);

        jcbGrade7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcbGrade7.setText("Grade 7");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlGradeLevelAssignment.add(jcbGrade7, gridBagConstraints);

        jcbGrade8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcbGrade8.setText("Grade 8");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlGradeLevelAssignment.add(jcbGrade8, gridBagConstraints);

        jcbGrade9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcbGrade9.setText("Grade 9");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlGradeLevelAssignment.add(jcbGrade9, gridBagConstraints);

        jcbGrade10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcbGrade10.setText("Grade 10");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlGradeLevelAssignment.add(jcbGrade10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jpnlGradeLevelAssignment, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jPanel1, gridBagConstraints);

        jpnlControls.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jpnlControls.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jpnlControls.setLayout(new java.awt.GridBagLayout());

        jbtnCancel.setText("Cancel");
        jbtnCancel.setActionCommand("cancel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControls.add(jbtnCancel, gridBagConstraints);

        jbtnSave.setText("Save");
        jbtnSave.setActionCommand("save");
        jpnlControls.add(jbtnSave, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jpnlControls, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnCancel;
    private javax.swing.JButton jbtnSave;
    private javax.swing.JCheckBox jcbGrade1;
    private javax.swing.JCheckBox jcbGrade10;
    private javax.swing.JCheckBox jcbGrade2;
    private javax.swing.JCheckBox jcbGrade3;
    private javax.swing.JCheckBox jcbGrade4;
    private javax.swing.JCheckBox jcbGrade5;
    private javax.swing.JCheckBox jcbGrade6;
    private javax.swing.JCheckBox jcbGrade7;
    private javax.swing.JCheckBox jcbGrade8;
    private javax.swing.JCheckBox jcbGrade9;
    private javax.swing.JCheckBox jcbKindergarten;
    private javax.swing.JComboBox<String> jcmbStatus;
    private javax.swing.JLabel jlblCredentialName;
    private javax.swing.JLabel jlblStatus;
    private javax.swing.JPanel jpnlControls;
    private javax.swing.JPanel jpnlGradeLevelAssignment;
    private javax.swing.JTextArea jtaDescription;
    private javax.swing.JTextField jtfCredentialName;
    // End of variables declaration//GEN-END:variables
}
