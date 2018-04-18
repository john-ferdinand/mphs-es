package view.schedule;

import component_model_loader.FacultyJCompModelLoader;
import component_model_loader.GradeLevelJCompModelLoader;
import component_model_loader.SchoolYearJCompModelLoader;
import component_model_loader.SectionJCompModelLoader;
import component_renderers.Renderer_Faculty_JComboBox;
import component_renderers.Renderer_GradeLevel_JComboBox;
import component_renderers.Renderer_SchoolYear_JComboBox;
import component_renderers.Renderer_Section_JComboBox;
import controller.global.Controller_JTextField_ClearDefaultSearchText;
import controller.schedule.ActionListener_Schedule_Display_Create_Dialog_JButton;
import controller.schedule.ActionListener_Schedule_Display_Edit_Dialog_JButton;
import controller.schedule.ItemListener_ScheduleMasterList_Day_JComboBox;
import controller.schedule.ItemListener_ScheduleMasterRecord_Faculty_JComboBox;
import controller.schedule.ItemListener_ScheduleMasterRecord_GradeLevel_JComboBox;
import controller.schedule.ItemListener_ScheduleMasterRecord_Section_JComboBox;
import controller.schedule.KeyListener_ScheduleMasterList_SearchBox_JTextField;
import daoimpl.SchoolYearDaoImpl;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import utility.initializer.Initializer;


public class Panel_ClassSchedules extends javax.swing.JPanel implements Initializer{

    private SchoolYearJCompModelLoader schoolYearJCompModelLoader;
    private FacultyJCompModelLoader facultyJCompModelLoader;
    private SchoolYearDaoImpl schoolYearDaoImpl;
    private GradeLevelJCompModelLoader gradeLevelJCompModelLoader;
    private SectionJCompModelLoader sectionJCompModelLoader;
    
    public Panel_ClassSchedules() {
        initComponents();
        
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
        schoolYearJCompModelLoader = new SchoolYearJCompModelLoader();
        facultyJCompModelLoader = new FacultyJCompModelLoader();
        gradeLevelJCompModelLoader = new GradeLevelJCompModelLoader();
        sectionJCompModelLoader = new SectionJCompModelLoader();
    }

    @Override
    public void initRenderers() {
        jcmbSchoolYear.setRenderer(new Renderer_SchoolYear_JComboBox());
        jcmbFaculty.setRenderer(new Renderer_Faculty_JComboBox());
        jcmbGradeLevelFilter.setRenderer(new Renderer_GradeLevel_JComboBox());
        jcmbSection.setRenderer(new Renderer_Section_JComboBox());
    }

    @Override
    public void initModels() {
    }

    @Override
    public void initViewComponents() {
        jcmbSchoolYear.setModel(schoolYearJCompModelLoader.getCurrentSchoolYear());
        jcmbFaculty.setModel(facultyJCompModelLoader.getAllFacultyByStatus(true));
        jcmbGradeLevelFilter.setModel(gradeLevelJCompModelLoader.getAllGradeLevelsAsModel());
        jtfSearch.addKeyListener(new KeyListener_ScheduleMasterList_SearchBox_JTextField(this));
        jcmbSection.setModel(sectionJCompModelLoader.getAllSections());
    }

    @Override
    public void initControllers() {
        jtfSearch.addMouseListener(new Controller_JTextField_ClearDefaultSearchText());
        jbtnCreate.addActionListener(new ActionListener_Schedule_Display_Create_Dialog_JButton());
        jbtnEdit.addActionListener(new ActionListener_Schedule_Display_Edit_Dialog_JButton(this));
        jcmbFaculty.addItemListener(new ItemListener_ScheduleMasterRecord_Faculty_JComboBox(this));
        jcmbGradeLevelFilter.addItemListener(new ItemListener_ScheduleMasterRecord_GradeLevel_JComboBox(this));
        jcmbDay.addItemListener(new ItemListener_ScheduleMasterList_Day_JComboBox(this));
        jcmbSection.addItemListener(new ItemListener_ScheduleMasterRecord_Section_JComboBox(this));
    }

    @Override
    public void initDaoImpl() {
        schoolYearDaoImpl = new SchoolYearDaoImpl();
    }

    public JComboBox<String> getJcmbSection() {
        return jcmbSection;
    }
    
    public JComboBox<String> getJcmbGradeLevelFilter() {
        return jcmbGradeLevelFilter;
    }

    
    public JComboBox<String> getJcmbDay() {
        return jcmbDay;
    }
    
    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public JButton getJbtnCreate() {
        return jbtnCreate;
    }

    public JButton getJbtnEdit() {
        return jbtnEdit;
    }

    public JButton getJbtnPrint() {
        return jbtnPrint;
    }

    public JTable getJtblScheduleMasterList() {
        return jtblScheduleMasterList;
    }

    public JButton getJbtnSearch() {
        return jbtnSearch;
    }

    public JComboBox<String> getJcmbFaculty() {
        return jcmbFaculty;
    }

    public JComboBox<String> getJcmbSchoolYear() {
        return jcmbSchoolYear;
    }

    public JPanel getJpnlControls() {
        return jpnlControls;
    }

    public JTextField getJtfSearch() {
        return jtfSearch;
    }

    public JLabel getLbl_sy() {
        return lbl_sy;
    }

    public JLabel getLbl_sy1() {
        return lbl_sy1;
    }

    public JPanel getPanel_masterrecord() {
        return panel_masterrecord;
    }

    public JPanel getPanel_toppanel() {
        return panel_toppanel;
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel_toppanel = new javax.swing.JPanel();
        jpnlControls = new javax.swing.JPanel();
        jbtnCreate = new javax.swing.JButton();
        jbtnEdit = new javax.swing.JButton();
        jbtnPrint = new javax.swing.JButton();
        jtfSearch = new javax.swing.JTextField();
        jbtnSearch = new javax.swing.JButton();
        lbl_sy = new javax.swing.JLabel();
        jcmbSchoolYear = new javax.swing.JComboBox<>();
        lbl_sy1 = new javax.swing.JLabel();
        jcmbFaculty = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jcmbDay = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jcmbGradeLevelFilter = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jcmbSection = new javax.swing.JComboBox<>();
        panel_masterrecord = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblScheduleMasterList = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(1200, 600));
        setLayout(new java.awt.GridBagLayout());

        panel_toppanel.setMinimumSize(new java.awt.Dimension(1200, 600));
        panel_toppanel.setPreferredSize(new java.awt.Dimension(1200, 600));
        panel_toppanel.setLayout(new java.awt.GridBagLayout());

        jpnlControls.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jpnlControls.setMinimumSize(new java.awt.Dimension(1200, 40));
        jpnlControls.setPreferredSize(new java.awt.Dimension(1200, 40));
        jpnlControls.setLayout(new java.awt.GridBagLayout());

        jbtnCreate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnCreate.setText("Create");
        jbtnCreate.setActionCommand("create");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControls.add(jbtnCreate, gridBagConstraints);

        jbtnEdit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnEdit.setText("Edit");
        jbtnEdit.setActionCommand("edit");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControls.add(jbtnEdit, gridBagConstraints);

        jbtnPrint.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnPrint.setText("Print");
        jbtnPrint.setActionCommand("print");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControls.add(jbtnPrint, gridBagConstraints);

        jtfSearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtfSearch.setText("Search here");
        jtfSearch.setMinimumSize(new java.awt.Dimension(80, 25));
        jtfSearch.setPreferredSize(new java.awt.Dimension(80, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControls.add(jtfSearch, gridBagConstraints);

        jbtnSearch.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnSearch.setText("Search");
        jbtnSearch.setActionCommand("search");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControls.add(jbtnSearch, gridBagConstraints);

        lbl_sy.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_sy.setText("School Year :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControls.add(lbl_sy, gridBagConstraints);

        jcmbSchoolYear.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcmbSchoolYear.setEnabled(false);
        jcmbSchoolYear.setMinimumSize(new java.awt.Dimension(100, 25));
        jcmbSchoolYear.setPreferredSize(new java.awt.Dimension(100, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControls.add(jcmbSchoolYear, gridBagConstraints);

        lbl_sy1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_sy1.setText("Faculty :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControls.add(lbl_sy1, gridBagConstraints);

        jcmbFaculty.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcmbFaculty.setMinimumSize(new java.awt.Dimension(150, 25));
        jcmbFaculty.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControls.add(jcmbFaculty, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Day :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 15;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControls.add(jLabel1, gridBagConstraints);

        jcmbDay.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcmbDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "M", "T", "W", "TH", "F" }));
        jcmbDay.setSelectedIndex(-1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControls.add(jcmbDay, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("GradeLevel :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControls.add(jLabel2, gridBagConstraints);

        jcmbGradeLevelFilter.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcmbGradeLevelFilter.setMinimumSize(new java.awt.Dimension(90, 25));
        jcmbGradeLevelFilter.setPreferredSize(new java.awt.Dimension(90, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControls.add(jcmbGradeLevelFilter, gridBagConstraints);

        jLabel3.setText("Section :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 17;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControls.add(jLabel3, gridBagConstraints);

        jcmbSection.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControls.add(jcmbSection, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toppanel.add(jpnlControls, gridBagConstraints);

        panel_masterrecord.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Schedule Master List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_masterrecord.setMinimumSize(new java.awt.Dimension(1200, 555));
        panel_masterrecord.setPreferredSize(new java.awt.Dimension(1200, 555));
        panel_masterrecord.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane1.setMinimumSize(new java.awt.Dimension(1185, 530));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1185, 530));

        jtblScheduleMasterList.setAutoCreateRowSorter(true);
        jtblScheduleMasterList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtblScheduleMasterList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Day", "Start Time", "End Time", "Section", "Subject", "Room", "Faculty", "Session"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblScheduleMasterList.setMinimumSize(new java.awt.Dimension(1185, 8000));
        jtblScheduleMasterList.setPreferredSize(new java.awt.Dimension(1185, 8000));
        jtblScheduleMasterList.setRowHeight(30);
        jtblScheduleMasterList.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtblScheduleMasterList);

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnCreate;
    private javax.swing.JButton jbtnEdit;
    private javax.swing.JButton jbtnPrint;
    private javax.swing.JButton jbtnSearch;
    private javax.swing.JComboBox<String> jcmbDay;
    private javax.swing.JComboBox<String> jcmbFaculty;
    private javax.swing.JComboBox<String> jcmbGradeLevelFilter;
    private javax.swing.JComboBox<String> jcmbSchoolYear;
    private javax.swing.JComboBox<String> jcmbSection;
    private javax.swing.JPanel jpnlControls;
    private javax.swing.JTable jtblScheduleMasterList;
    private javax.swing.JTextField jtfSearch;
    private javax.swing.JLabel lbl_sy;
    private javax.swing.JLabel lbl_sy1;
    private javax.swing.JPanel panel_masterrecord;
    private javax.swing.JPanel panel_toppanel;
    // End of variables declaration//GEN-END:variables
}
