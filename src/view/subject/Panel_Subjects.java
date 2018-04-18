package view.subject;

import component_model_loader.GradeLevelJCompModelLoader;
import component_model_loader.SubjectJCompModelLoader;
import component_renderers.Renderer_GradeLevel_JComboBox;
import component_renderers.Renderer_Master_GradeLevel_JTableCell;
import controller.global.Controller_JTextField_ClearDefaultSearchText;
import controller.subject.DisplaySubjectCrudDialog;
import controller.subject.SearchSubjectByWildCard;
import utility.initializer.Initializer;


public class Panel_Subjects extends javax.swing.JPanel implements Initializer{

    private Renderer_GradeLevel_JComboBox gradeLevelJComboBoxRenderer;
    private GradeLevelJCompModelLoader gradeLevelJCompModelLoader;
    private SubjectJCompModelLoader subjectJCompModelLoader;
    
    public Panel_Subjects() {
        initComponents();
        
        initJCompModelLoaders();
        initRenderers();
        initModels();
        initViewComponents();
        initControllers();
        initDaoImpl();
    }

    @Override
    public void initRenderers() {
        jtblSubjectMasterList.setDefaultRenderer(Object.class, new Renderer_Master_GradeLevel_JTableCell(5));
        gradeLevelJComboBoxRenderer = new Renderer_GradeLevel_JComboBox();
    }
    
    @Override
    public void initGridBagConstraints() {
    }

    @Override
    public void initJCompModelLoaders() {
        gradeLevelJCompModelLoader = new GradeLevelJCompModelLoader();
        subjectJCompModelLoader = new SubjectJCompModelLoader();
    }

    @Override
    public void initModels() {
    }

    @Override
    public void initViewComponents() {
        jcmbGradeLevel.setModel(gradeLevelJCompModelLoader.getAllGradeLevels());
        jcmbGradeLevel.setRenderer(gradeLevelJComboBoxRenderer);
        jtblSubjectMasterList.setModel(subjectJCompModelLoader.getAllSubjectsInfo(jtblSubjectMasterList));
    }

    @Override
    public void initControllers() {
        jtfSearchBox.addMouseListener(new Controller_JTextField_ClearDefaultSearchText());
        jbtnSearch.addActionListener(new SearchSubjectByWildCard(jtblSubjectMasterList, jtfSearchBox));
        jbtnCreateSubject.addActionListener(new DisplaySubjectCrudDialog(jtblSubjectMasterList, jbtnCreateSubject.getActionCommand()));
        jbtnEditSubject.addActionListener(new DisplaySubjectCrudDialog(jtblSubjectMasterList, jbtnEditSubject.getActionCommand()));
        jbtnViewSubject.addActionListener(new DisplaySubjectCrudDialog(jtblSubjectMasterList, jbtnViewSubject.getActionCommand()));
    }

    @Override
    public void initDaoImpl() {
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel_toppanel = new javax.swing.JPanel();
        panel_control = new javax.swing.JPanel();
        jbtnCreateSubject = new javax.swing.JButton();
        jbtnEditSubject = new javax.swing.JButton();
        jbtnViewSubject = new javax.swing.JButton();
        jbtnPrint = new javax.swing.JButton();
        jtfSearchBox = new javax.swing.JTextField();
        jbtnSearch = new javax.swing.JButton();
        lbl_show = new javax.swing.JLabel();
        jcmbGradeLevel = new javax.swing.JComboBox<>();
        jbtnAssignWeightedScores = new javax.swing.JButton();
        panel_masterrecord = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblSubjectMasterList = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(1200, 600));
        setLayout(new java.awt.GridBagLayout());

        panel_toppanel.setMinimumSize(new java.awt.Dimension(1200, 600));
        panel_toppanel.setPreferredSize(new java.awt.Dimension(1200, 600));
        panel_toppanel.setLayout(new java.awt.GridBagLayout());

        panel_control.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel_control.setMinimumSize(new java.awt.Dimension(1200, 40));
        panel_control.setPreferredSize(new java.awt.Dimension(1200, 40));
        panel_control.setLayout(new java.awt.GridBagLayout());

        jbtnCreateSubject.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnCreateSubject.setText("Create New Subject");
        jbtnCreateSubject.setActionCommand("create");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        panel_control.add(jbtnCreateSubject, gridBagConstraints);

        jbtnEditSubject.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnEditSubject.setText("Edit");
        jbtnEditSubject.setActionCommand("edit");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        panel_control.add(jbtnEditSubject, gridBagConstraints);

        jbtnViewSubject.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnViewSubject.setText("View");
        jbtnViewSubject.setActionCommand("view");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        panel_control.add(jbtnViewSubject, gridBagConstraints);

        jbtnPrint.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnPrint.setText("Print");
        jbtnPrint.setActionCommand("print");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        panel_control.add(jbtnPrint, gridBagConstraints);

        jtfSearchBox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtfSearchBox.setText("Search here");
        jtfSearchBox.setMinimumSize(new java.awt.Dimension(150, 25));
        jtfSearchBox.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 140, 0, 0);
        panel_control.add(jtfSearchBox, gridBagConstraints);

        jbtnSearch.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnSearch.setText("Search");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_control.add(jbtnSearch, gridBagConstraints);

        lbl_show.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_show.setText("Show Grade Level :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        panel_control.add(lbl_show, gridBagConstraints);

        jcmbGradeLevel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcmbGradeLevel.setMinimumSize(new java.awt.Dimension(100, 25));
        jcmbGradeLevel.setPreferredSize(new java.awt.Dimension(100, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        panel_control.add(jcmbGradeLevel, gridBagConstraints);

        jbtnAssignWeightedScores.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnAssignWeightedScores.setText("Assign Weighted Scores (WS)");
        jbtnAssignWeightedScores.setActionCommand("assignws");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        panel_control.add(jbtnAssignWeightedScores, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toppanel.add(panel_control, gridBagConstraints);

        panel_masterrecord.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Subjects Master List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_masterrecord.setMinimumSize(new java.awt.Dimension(1200, 555));
        panel_masterrecord.setPreferredSize(new java.awt.Dimension(1200, 555));
        panel_masterrecord.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane1.setMinimumSize(new java.awt.Dimension(1185, 530));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1185, 530));

        jtblSubjectMasterList.setAutoCreateRowSorter(true);
        jtblSubjectMasterList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtblSubjectMasterList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Subject Name", "Subject Code", "Description", "Status", "Grade Level", "WW (WS)", "PT (WS)", "QA (WS)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblSubjectMasterList.setMinimumSize(new java.awt.Dimension(1185, 10000));
        jtblSubjectMasterList.setPreferredSize(new java.awt.Dimension(1185, 10000));
        jtblSubjectMasterList.setRowHeight(20);
        jtblSubjectMasterList.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtblSubjectMasterList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        panel_masterrecord.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toppanel.add(panel_masterrecord, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        add(panel_toppanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnAssignWeightedScores;
    private javax.swing.JButton jbtnCreateSubject;
    private javax.swing.JButton jbtnEditSubject;
    private javax.swing.JButton jbtnPrint;
    private javax.swing.JButton jbtnSearch;
    private javax.swing.JButton jbtnViewSubject;
    private javax.swing.JComboBox<String> jcmbGradeLevel;
    private javax.swing.JTable jtblSubjectMasterList;
    private javax.swing.JTextField jtfSearchBox;
    private javax.swing.JLabel lbl_show;
    private javax.swing.JPanel panel_control;
    private javax.swing.JPanel panel_masterrecord;
    private javax.swing.JPanel panel_toppanel;
    // End of variables declaration//GEN-END:variables
}
