
package view.credential;

import component_model_loader.CredentialJCompModelLoader;
import component_model_loader.GradeLevelJCompModelLoader;
import component_renderers.Renderer_GradeLevel_JComboBox;
import controller.credential.Controller_Credential_Display_CredentialCrud_Dialog;
import controller.credential.Controller_Credential_MouseListener_CredentialMasterList_JTable;
import controller.global.Controller_JTextField_ClearDefaultSearchText;
import daoimpl.GradeLevelDaoImpl;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextField;
import model.schoolyear.SchoolYear;
import model.user.User;
import renderer.credential.Renderer_Credential_CredentialMasterList_JTable;
import utility.initializer.Initializer;
import utility.jtable.JTableUtil;

public class Panel_Credential extends javax.swing.JPanel implements Initializer{

    private final SchoolYear currentSchoolYear;
    private final User user;
    
    private GradeLevelJCompModelLoader gradeLevelJCompModelLoader;
    private CredentialJCompModelLoader credentialJCompModelLoader;
    
    public Panel_Credential(SchoolYear currentSchoolYear, User user) {
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
        gradeLevelJCompModelLoader = new GradeLevelJCompModelLoader();
        credentialJCompModelLoader = new CredentialJCompModelLoader();
    }

    @Override
    public void initRenderers() {
        jcmbFilterByGradeLevel.setRenderer(new Renderer_GradeLevel_JComboBox());
        jtblCredentialMasterList.setDefaultRenderer(Object.class, new Renderer_Credential_CredentialMasterList_JTable(0, 1));
    }

    @Override
    public void initModels() {
    }

    @Override
    public void initViewComponents() {
        JTableUtil.applyCustomHeaderRenderer(jtblCredentialMasterList);
        jcmbFilterByGradeLevel.setModel(gradeLevelJCompModelLoader.getAllActiveGradeLevel());
        jtblCredentialMasterList.setModel(credentialJCompModelLoader.getAllCredentialsFor(jtblCredentialMasterList));
    }

    @Override
    public void initControllers() {
        jtblCredentialMasterList.addMouseListener(new Controller_Credential_MouseListener_CredentialMasterList_JTable(this));
        jtblCredentialMasterList.addKeyListener(new Controller_Credential_MouseListener_CredentialMasterList_JTable(this));
        jtfSearchBox.addMouseListener(new Controller_JTextField_ClearDefaultSearchText());
        jbtnCreateCredential.addActionListener(new Controller_Credential_Display_CredentialCrud_Dialog(currentSchoolYear, user));
    }

    @Override
    public void initDaoImpl() {
    }

    public JButton getJbtnCreateCredential() {
        return jbtnCreateCredential;
    }

    public JButton getJbtnEdit() {
        return jbtnEdit;
    }

    public JButton getJbtnSearch() {
        return jbtnSearch;
    }

    public JComboBox<String> getJcmbFilterByGradeLevel() {
        return jcmbFilterByGradeLevel;
    }

    public JLabel getJlblFilterByGradeLevel() {
        return jlblFilterByGradeLevel;
    }

    public JList<String> getJlstGradeLevelAssignment() {
        return jlstGradeLevelAssignment;
    }

    public JTable getJtblCredentialMasterList() {
        return jtblCredentialMasterList;
    }

    public JTextField getJtfSearchBox() {
        return jtfSearchBox;
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jbtnCreateCredential = new javax.swing.JButton();
        jbtnEdit = new javax.swing.JButton();
        jtfSearchBox = new javax.swing.JTextField();
        jbtnSearch = new javax.swing.JButton();
        jlblFilterByGradeLevel = new javax.swing.JLabel();
        jcmbFilterByGradeLevel = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblCredentialMasterList = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jlstGradeLevelAssignment = new javax.swing.JList<>();

        setLayout(new java.awt.GridBagLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jbtnCreateCredential.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnCreateCredential.setText("Create Credential");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel2.add(jbtnCreateCredential, gridBagConstraints);

        jbtnEdit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnEdit.setText("Edit");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel2.add(jbtnEdit, gridBagConstraints);

        jtfSearchBox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtfSearchBox.setText("Search here");
        jtfSearchBox.setMinimumSize(new java.awt.Dimension(150, 25));
        jtfSearchBox.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel2.add(jtfSearchBox, gridBagConstraints);

        jbtnSearch.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnSearch.setText("Search");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel2.add(jbtnSearch, gridBagConstraints);

        jlblFilterByGradeLevel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblFilterByGradeLevel.setText("Grade Level");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel2.add(jlblFilterByGradeLevel, gridBagConstraints);

        jcmbFilterByGradeLevel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcmbFilterByGradeLevel.setMinimumSize(new java.awt.Dimension(100, 25));
        jcmbFilterByGradeLevel.setPreferredSize(new java.awt.Dimension(100, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel2.add(jcmbFilterByGradeLevel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        add(jPanel2, gridBagConstraints);

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jtblCredentialMasterList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtblCredentialMasterList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Credential Name", "Status", "Date Created"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblCredentialMasterList.setRowHeight(30);
        jtblCredentialMasterList.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtblCredentialMasterList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel5.add(jScrollPane1, gridBagConstraints);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Required For", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jScrollPane2.setMinimumSize(new java.awt.Dimension(80, 41));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(80, 168));

        jlstGradeLevelAssignment.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlstGradeLevelAssignment.setModel(new DefaultListModel());
        jScrollPane2.setViewportView(jlstGradeLevelAssignment);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel5.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        add(jPanel5, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtnCreateCredential;
    private javax.swing.JButton jbtnEdit;
    private javax.swing.JButton jbtnSearch;
    private javax.swing.JComboBox<String> jcmbFilterByGradeLevel;
    private javax.swing.JLabel jlblFilterByGradeLevel;
    private javax.swing.JList<String> jlstGradeLevelAssignment;
    private javax.swing.JTable jtblCredentialMasterList;
    private javax.swing.JTextField jtfSearchBox;
    // End of variables declaration//GEN-END:variables
}
