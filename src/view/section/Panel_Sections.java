package view.section;

import component_model_loader.GradeLevelJCompModelLoader;
import component_model_loader.SectionJCompModelLoader;
import component_renderers.Renderer_GradeLevel_JComboBox;
import component_renderers.Renderer_Master_GradeLevel_JTableCell;
import controller.global.Controller_JTextField_ClearDefaultSearchText;
import controller.section.DisplaySectionCrudDialog;
import controller.section.DisplaySectionsByGradeLevelStateChange;
import controller.section.DisplaySectionsByWildCardOnKeyPress;
import controller.section.DisplaySectionsByWildCardOnSearch;
import javax.swing.JTable;
import model.schoolyear.SchoolYear;
import model.user.User;
import renderer.section.Renderer_Section_MasterList_JTable;
import utility.initializer.Initializer;
import utility.jtable.JTableUtil;

public class Panel_Sections extends javax.swing.JPanel implements Initializer{

    private SectionJCompModelLoader sectionJCompModelLoader;
    private GradeLevelJCompModelLoader gradeLevelJCompModelLoader;
    
    private Renderer_GradeLevel_JComboBox gradeLevelJComboBoxRenderer;
    
    private final SchoolYear currentSchoolYear;
    private final User user;
    
    public Panel_Sections(SchoolYear currentSchoolYear, User user) {
        initComponents();
        
        this.currentSchoolYear = currentSchoolYear;
        this.user = user;
        
        initJCompModelLoaders();
        initRenderers();
        initModels();
        initViewComponents();
        initControllers();
        initDaoImpl();
    }

    @Override
    public void initRenderers() {
        JTableUtil.applyCustomHeaderRenderer(jtblSectionMasterList);
        jtblSectionMasterList.setDefaultRenderer(Object.class, new Renderer_Section_MasterList_JTable(4, 2, 7));
        gradeLevelJComboBoxRenderer = new Renderer_GradeLevel_JComboBox();
    }
    
    @Override
    public void initGridBagConstraints() {
    }

    @Override
    public void initJCompModelLoaders() {
        sectionJCompModelLoader = new SectionJCompModelLoader();
        gradeLevelJCompModelLoader = new GradeLevelJCompModelLoader();
    }

    @Override
    public void initModels() {
    }

    @Override
    public void initViewComponents() {
        jcmbFilterByGradeLevel.setModel(gradeLevelJCompModelLoader.getAllGradeLevels());
        jcmbFilterByGradeLevel.setRenderer(gradeLevelJComboBoxRenderer);
        loadSectionMasterList();
        JTableUtil.resizeColumnWidthsOf(jtblSectionMasterList);
    }
    
    public void loadSectionMasterList(){
        jtblSectionMasterList.setModel(sectionJCompModelLoader.getAllSections(jtblSectionMasterList));
    }

    @Override
    public void initControllers() {
        tf_searchbox.addMouseListener(new Controller_JTextField_ClearDefaultSearchText());
        tf_searchbox.addKeyListener(new DisplaySectionsByWildCardOnKeyPress(tf_searchbox, jtblSectionMasterList));
        btn_Search.addActionListener(new DisplaySectionsByWildCardOnSearch(tf_searchbox, jtblSectionMasterList));
        jcmbFilterByGradeLevel.addItemListener(new DisplaySectionsByGradeLevelStateChange(jcmbFilterByGradeLevel, jtblSectionMasterList));
        btn_createnew.addActionListener(new DisplaySectionCrudDialog(this,currentSchoolYear));
        btn_Edit.addActionListener(new DisplaySectionCrudDialog(this,currentSchoolYear));
        btn_View.addActionListener(new DisplaySectionCrudDialog(this,currentSchoolYear));
    }

    @Override
    public void initDaoImpl() {
    }

    public JTable getJtblSectionMasterList() {
        return jtblSectionMasterList;
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel_toppanel = new javax.swing.JPanel();
        panel_control = new javax.swing.JPanel();
        btn_createnew = new javax.swing.JButton();
        btn_Edit = new javax.swing.JButton();
        btn_View = new javax.swing.JButton();
        btn_Print = new javax.swing.JButton();
        tf_searchbox = new javax.swing.JTextField();
        btn_Search = new javax.swing.JButton();
        lbl_show = new javax.swing.JLabel();
        jcmbFilterByGradeLevel = new javax.swing.JComboBox<>();
        panel_masterrecord = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblSectionMasterList = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(1200, 600));
        setPreferredSize(new java.awt.Dimension(1200, 600));
        setLayout(new java.awt.GridBagLayout());

        panel_toppanel.setMinimumSize(new java.awt.Dimension(1200, 600));
        panel_toppanel.setPreferredSize(new java.awt.Dimension(1200, 600));
        panel_toppanel.setLayout(new java.awt.GridBagLayout());

        panel_control.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel_control.setMinimumSize(new java.awt.Dimension(1200, 40));
        panel_control.setPreferredSize(new java.awt.Dimension(1200, 40));
        panel_control.setLayout(new java.awt.GridBagLayout());

        btn_createnew.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_createnew.setText("Create New Section");
        btn_createnew.setActionCommand("create");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_control.add(btn_createnew, gridBagConstraints);

        btn_Edit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_Edit.setText("Edit");
        btn_Edit.setActionCommand("edit");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_control.add(btn_Edit, gridBagConstraints);

        btn_View.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_View.setText("View");
        btn_View.setActionCommand("view");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_control.add(btn_View, gridBagConstraints);

        btn_Print.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_Print.setText("Print");
        btn_Print.setActionCommand("print");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_control.add(btn_Print, gridBagConstraints);

        tf_searchbox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_searchbox.setText("Search here");
        tf_searchbox.setMinimumSize(new java.awt.Dimension(150, 25));
        tf_searchbox.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_control.add(tf_searchbox, gridBagConstraints);

        btn_Search.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_Search.setText("Search");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_control.add(btn_Search, gridBagConstraints);

        lbl_show.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_show.setText("Show Grade Level :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_control.add(lbl_show, gridBagConstraints);

        jcmbFilterByGradeLevel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panel_control.add(jcmbFilterByGradeLevel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toppanel.add(panel_control, gridBagConstraints);

        panel_masterrecord.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sections Master List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_masterrecord.setMinimumSize(new java.awt.Dimension(1200, 555));
        panel_masterrecord.setPreferredSize(new java.awt.Dimension(1200, 555));
        panel_masterrecord.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane1.setMinimumSize(new java.awt.Dimension(1185, 530));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1185, 530));

        jtblSectionMasterList.setAutoCreateRowSorter(true);
        jtblSectionMasterList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtblSectionMasterList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Section Id", "Section Name", "Grade Level", "Adviser", "Session", "School Year", "Status", "Section Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblSectionMasterList.setMinimumSize(new java.awt.Dimension(1185, 10000));
        jtblSectionMasterList.setPreferredSize(new java.awt.Dimension(1185, 10000));
        jtblSectionMasterList.setRowHeight(20);
        jtblSectionMasterList.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtblSectionMasterList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
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
    private javax.swing.JButton btn_Edit;
    private javax.swing.JButton btn_Print;
    private javax.swing.JButton btn_Search;
    private javax.swing.JButton btn_View;
    private javax.swing.JButton btn_createnew;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcmbFilterByGradeLevel;
    private javax.swing.JTable jtblSectionMasterList;
    private javax.swing.JLabel lbl_show;
    private javax.swing.JPanel panel_control;
    private javax.swing.JPanel panel_masterrecord;
    private javax.swing.JPanel panel_toppanel;
    private javax.swing.JTextField tf_searchbox;
    // End of variables declaration//GEN-END:variables
}
