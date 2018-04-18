package view.subjectcategory;

import component_model_loader.SubjectCategoryJCompModelLoader;
import controller.global.Controller_JTextField_ClearDefaultSearchText;
import controller.subjectcategory.DisplaySubjectCategoryAssignedSubjectsOnKeyPress;
import controller.subjectcategory.DisplaySubjectCategoryAssignedSubjectsOnMouseClick;
import controller.subjectcategory.DisplaySubjectCategoryCrudDialog;
import controller.subjectcategory.SearchSubjectCategoryByWildCard;
import javax.swing.table.DefaultTableModel;
import utility.initializer.Initializer;

public class Panel_SubjectCategory extends javax.swing.JPanel implements Initializer{


    private SubjectCategoryJCompModelLoader subjectCategoryJCompModelLoader;
    
    public Panel_SubjectCategory() {
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
    }
    
    @Override
    public void initGridBagConstraints() {

    }

    @Override
    public void initJCompModelLoaders() {
        subjectCategoryJCompModelLoader = new SubjectCategoryJCompModelLoader();
    }

    @Override
    public void initModels() {

    }

    @Override
    public void initViewComponents() {
        DefaultTableModel tableModel = subjectCategoryJCompModelLoader.getAllSubjectCategoryInfo(jtblSubjectCategoryMasterList);
        jtblSubjectCategoryMasterList.setModel(tableModel);
    }

    @Override
    public void initControllers() {
        jtfSearch.addMouseListener(new Controller_JTextField_ClearDefaultSearchText());
        jbtnSearch.addActionListener(new SearchSubjectCategoryByWildCard(jtfSearch, jtblSubjectCategoryMasterList));
        jbtnCreate.addActionListener(new DisplaySubjectCategoryCrudDialog(jtblSubjectCategoryMasterList));
        jbtnEdit.addActionListener(new DisplaySubjectCategoryCrudDialog(jtblSubjectCategoryMasterList));
        jbtnView.addActionListener(new DisplaySubjectCategoryCrudDialog(jtblSubjectCategoryMasterList));
        jtblSubjectCategoryMasterList.addMouseListener(new DisplaySubjectCategoryAssignedSubjectsOnMouseClick(jtblSubjectCategoryMasterList,jtblAssignedSubjects));
        jtblSubjectCategoryMasterList.addKeyListener(new DisplaySubjectCategoryAssignedSubjectsOnKeyPress(jtblSubjectCategoryMasterList, jtblAssignedSubjects));
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
        jbtnCreate = new javax.swing.JButton();
        jbtnEdit = new javax.swing.JButton();
        jbtnView = new javax.swing.JButton();
        jbtnPrint = new javax.swing.JButton();
        jtfSearch = new javax.swing.JTextField();
        jbtnSearch = new javax.swing.JButton();
        lbl_show = new javax.swing.JLabel();
        combo_filter = new javax.swing.JComboBox<>();
        panel_masterrecord = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblSubjectCategoryMasterList = new javax.swing.JTable();
        panel_particularsubs = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblAssignedSubjects = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1200, 600));
        setLayout(new java.awt.GridBagLayout());

        panel_toppanel.setMinimumSize(new java.awt.Dimension(1200, 600));
        panel_toppanel.setPreferredSize(new java.awt.Dimension(1200, 600));
        panel_toppanel.setLayout(new java.awt.GridBagLayout());

        panel_control.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel_control.setMinimumSize(new java.awt.Dimension(1200, 40));
        panel_control.setPreferredSize(new java.awt.Dimension(1200, 40));
        panel_control.setLayout(new java.awt.GridBagLayout());

        jbtnCreate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnCreate.setText("Create New Subject Category");
        jbtnCreate.setActionCommand("create");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        panel_control.add(jbtnCreate, gridBagConstraints);

        jbtnEdit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnEdit.setText("Edit");
        jbtnEdit.setActionCommand("edit");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        panel_control.add(jbtnEdit, gridBagConstraints);

        jbtnView.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnView.setText("View");
        jbtnView.setActionCommand("view");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        panel_control.add(jbtnView, gridBagConstraints);

        jbtnPrint.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnPrint.setText("Print");
        jbtnPrint.setActionCommand("print");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        panel_control.add(jbtnPrint, gridBagConstraints);

        jtfSearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtfSearch.setText("Search here");
        jtfSearch.setMinimumSize(new java.awt.Dimension(150, 25));
        jtfSearch.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 350, 0, 0);
        panel_control.add(jtfSearch, gridBagConstraints);

        jbtnSearch.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnSearch.setText("Search");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        panel_control.add(jbtnSearch, gridBagConstraints);

        lbl_show.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_show.setText("Search by:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        panel_control.add(lbl_show, gridBagConstraints);

        combo_filter.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        combo_filter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        combo_filter.setMinimumSize(new java.awt.Dimension(100, 25));
        combo_filter.setPreferredSize(new java.awt.Dimension(100, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        panel_control.add(combo_filter, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toppanel.add(panel_control, gridBagConstraints);

        panel_masterrecord.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Subject Categories", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_masterrecord.setMinimumSize(new java.awt.Dimension(1200, 270));
        panel_masterrecord.setPreferredSize(new java.awt.Dimension(1200, 270));
        panel_masterrecord.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane1.setMinimumSize(new java.awt.Dimension(1185, 245));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1185, 245));

        jtblSubjectCategoryMasterList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtblSubjectCategoryMasterList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Subject Category", "Description", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblSubjectCategoryMasterList.setMinimumSize(new java.awt.Dimension(1185, 10000));
        jtblSubjectCategoryMasterList.setPreferredSize(new java.awt.Dimension(1185, 10000));
        jtblSubjectCategoryMasterList.setRowHeight(20);
        jtblSubjectCategoryMasterList.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtblSubjectCategoryMasterList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_masterrecord.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toppanel.add(panel_masterrecord, gridBagConstraints);

        panel_particularsubs.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Subjects Under", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_particularsubs.setMinimumSize(new java.awt.Dimension(1200, 270));
        panel_particularsubs.setPreferredSize(new java.awt.Dimension(1200, 270));
        panel_particularsubs.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane2.setMinimumSize(new java.awt.Dimension(1185, 245));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(1185, 245));

        jtblAssignedSubjects.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtblAssignedSubjects.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Subject Name", "Subject Code", "Description", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblAssignedSubjects.setMinimumSize(new java.awt.Dimension(1185, 10000));
        jtblAssignedSubjects.setPreferredSize(new java.awt.Dimension(1185, 10000));
        jtblAssignedSubjects.setRowHeight(20);
        jtblAssignedSubjects.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jtblAssignedSubjects);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_particularsubs.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toppanel.add(panel_particularsubs, gridBagConstraints);

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
    private javax.swing.JComboBox<String> combo_filter;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtnCreate;
    private javax.swing.JButton jbtnEdit;
    private javax.swing.JButton jbtnPrint;
    private javax.swing.JButton jbtnSearch;
    private javax.swing.JButton jbtnView;
    private javax.swing.JTable jtblAssignedSubjects;
    private javax.swing.JTable jtblSubjectCategoryMasterList;
    private javax.swing.JTextField jtfSearch;
    private javax.swing.JLabel lbl_show;
    private javax.swing.JPanel panel_control;
    private javax.swing.JPanel panel_masterrecord;
    private javax.swing.JPanel panel_particularsubs;
    private javax.swing.JPanel panel_toppanel;
    // End of variables declaration//GEN-END:variables
}
