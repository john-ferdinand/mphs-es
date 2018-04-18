package view.section;

import component_model_loader.FacultyJCompModelLoader;
import component_model_loader.GradeLevelJCompModelLoader;
import component_model_loader.SectionJCompModelLoader;
import component_renderers.Renderer_Faculty_JComboBox;
import component_renderers.Renderer_GradeLevel_JComboBox;
import component_renderers.Renderer_Master_GradeLevel_JTableCell;
import component_renderers.Renderer_Section_JComboBox;
import controller.enrollment.DialogSectionAssignment_CapacityDenominatorDocumentPropertyListener;
import controller.enrollment.DialogSectionAssignment_Clear;
import controller.enrollment.DialogSectionAssignment_MoveStudentToSection;
import controller.enrollment.DialogSectionAssignment_OnGradeLevelItemStateChange;
import controller.enrollment.DialogSectionAssignment_RemoveStudentFromSection;
import controller.enrollment.DialogSectionAssignment_Save;
import controller.global.Controller_JButton_ExitJDialog;
import controller.section.Controller_SectionAssignment_Summer_JCheckBox;
import controller.section.DialogSectionAssignment_SectionStudentTableModelListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import model.schoolyear.SchoolYear;
import utility.initializer.Initializer;
import utility.jtable.JTableUtil;
import view.enrollment.Panel_Enrollment;

public class Dialog_SectionAssignment extends javax.swing.JDialog implements Initializer {
    
    private final Panel_Enrollment panelEnrollment;
    private GradeLevelJCompModelLoader gradeLevelJCompModelLoader;
    private SectionJCompModelLoader sectionJCompModelLoader;
    private FacultyJCompModelLoader facultyJCompModelLoader;
    private final SchoolYear currentSchoolYear;
    
    public Dialog_SectionAssignment(java.awt.Frame parent, boolean modal,Panel_Enrollment panelEnrollment, SchoolYear currentSchoolYear) {
        super(parent, modal);
        initComponents();
        this.panelEnrollment = panelEnrollment;
        this.currentSchoolYear = currentSchoolYear;
        
        initDaoImpl();
        initJCompModelLoaders();
        initModels();
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
        sectionJCompModelLoader = new SectionJCompModelLoader();
        facultyJCompModelLoader = new FacultyJCompModelLoader();
    }

    @Override
    public void initRenderers() {
        jtblEnrolledStudents.setDefaultRenderer(Object.class, new Renderer_Master_GradeLevel_JTableCell(6));
        jtblSectionStudents.setDefaultRenderer(Object.class, new Renderer_Master_GradeLevel_JTableCell(6));
        jcmbGradeLevel.setRenderer(new Renderer_GradeLevel_JComboBox());
        jcmbSection.setRenderer(new Renderer_Section_JComboBox());
        jcmbAdviser.setRenderer(new Renderer_Faculty_JComboBox());
    }

    @Override
    public void initModels() {
    }

    @Override
    public void initViewComponents() {
        JTableUtil.applyCustomHeaderRenderer(jtblSectionStudents);
        JTableUtil.applyCustomHeaderRenderer(jtblEnrolledStudents);
        jcmbGradeLevel.setModel(gradeLevelJCompModelLoader.getAllGradeLevelsAsModel());
        jcmbSection.setModel(sectionJCompModelLoader.getAllSectionByStatusAndSchoolYearId(true, currentSchoolYear.getSchoolYearId()));
        jcmbAdviser.setModel(facultyJCompModelLoader.getAllFacultyAsId());
        jcmbAdviser.setSelectedIndex(-1);
    }

    @Override
    public void initControllers() {
        jcbSummer.addActionListener(new Controller_SectionAssignment_Summer_JCheckBox(this,currentSchoolYear));
        jcmbGradeLevel.addItemListener(new DialogSectionAssignment_OnGradeLevelItemStateChange(this));
        jbtnMoveStudentToSection.addActionListener(new DialogSectionAssignment_MoveStudentToSection(this));
        jbtnRemoveStudentFromSection.addActionListener(new DialogSectionAssignment_RemoveStudentFromSection(this));
        jtblSectionStudents.getModel().addTableModelListener(new DialogSectionAssignment_SectionStudentTableModelListener(this));
        jtfCapacityDenominator.getDocument().addDocumentListener(new DialogSectionAssignment_CapacityDenominatorDocumentPropertyListener(this));
        jbtnClear.addActionListener(new DialogSectionAssignment_Clear(this));
        jbtnCancel.addActionListener(new Controller_JButton_ExitJDialog(this));
        jbtnSave.addActionListener(new DialogSectionAssignment_Save(panelEnrollment,this));
        
    }

    @Override
    public void initDaoImpl() {
    }

    public JCheckBox getJcbSummer() {
        return jcbSummer;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel_toopanel = new javax.swing.JPanel();
        jpnlSectionDetails = new javax.swing.JPanel();
        lbl_name = new javax.swing.JLabel();
        jcmbGradeLevel = new javax.swing.JComboBox<>();
        lbl_name1 = new javax.swing.JLabel();
        jcmbSection = new javax.swing.JComboBox<>();
        lbl_name3 = new javax.swing.JLabel();
        lbl_name4 = new javax.swing.JLabel();
        jcmbAdviser = new javax.swing.JComboBox<>();
        lbl_name7 = new javax.swing.JLabel();
        jtfCapacityNumerator = new javax.swing.JTextField();
        lbl_name8 = new javax.swing.JLabel();
        jtfCapacityDenominator = new javax.swing.JTextField();
        jcmbSession = new javax.swing.JComboBox<>();
        jbtnAutoAssign = new javax.swing.JButton();
        jcbSummer = new javax.swing.JCheckBox();
        panel_subjectlist = new javax.swing.JPanel();
        panel_subjtable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblEnrolledStudents = new javax.swing.JTable();
        panel_centercontrol = new javax.swing.JPanel();
        jbtnMoveStudentToSection = new javax.swing.JButton();
        jbtnRemoveStudentFromSection = new javax.swing.JButton();
        panel_currentsubjects = new javax.swing.JPanel();
        panel_subjtable1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblSectionStudents = new javax.swing.JTable();
        jpnlFooter = new javax.swing.JPanel();
        jbtnCancel = new javax.swing.JButton();
        jbtnClear = new javax.swing.JButton();
        jbtnSave = new javax.swing.JButton();
        jbtnSaveAndNew = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Section Assignment");
        setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        setMinimumSize(new java.awt.Dimension(1000, 700));
        setModal(true);
        setPreferredSize(new java.awt.Dimension(1200, 600));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        panel_toopanel.setMinimumSize(new java.awt.Dimension(1000, 700));
        panel_toopanel.setPreferredSize(new java.awt.Dimension(1000, 700));
        panel_toopanel.setLayout(new java.awt.GridBagLayout());

        jpnlSectionDetails.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Control", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jpnlSectionDetails.setMinimumSize(new java.awt.Dimension(995, 100));
        jpnlSectionDetails.setPreferredSize(new java.awt.Dimension(995, 100));
        jpnlSectionDetails.setLayout(new java.awt.GridBagLayout());

        lbl_name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_name.setText("Select Grade Level :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlSectionDetails.add(lbl_name, gridBagConstraints);

        jcmbGradeLevel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcmbGradeLevel.setActionCommand("gradelevel");
        jcmbGradeLevel.setMinimumSize(new java.awt.Dimension(110, 25));
        jcmbGradeLevel.setPreferredSize(new java.awt.Dimension(110, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlSectionDetails.add(jcmbGradeLevel, gridBagConstraints);

        lbl_name1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_name1.setText("Select Section :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlSectionDetails.add(lbl_name1, gridBagConstraints);

        jcmbSection.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcmbSection.setActionCommand("section");
        jcmbSection.setEnabled(false);
        jcmbSection.setMinimumSize(new java.awt.Dimension(150, 25));
        jcmbSection.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlSectionDetails.add(jcmbSection, gridBagConstraints);

        lbl_name3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_name3.setText("Session :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlSectionDetails.add(lbl_name3, gridBagConstraints);

        lbl_name4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_name4.setText("Adviser  :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlSectionDetails.add(lbl_name4, gridBagConstraints);

        jcmbAdviser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcmbAdviser.setActionCommand("adviser");
        jcmbAdviser.setEnabled(false);
        jcmbAdviser.setMinimumSize(new java.awt.Dimension(180, 25));
        jcmbAdviser.setPreferredSize(new java.awt.Dimension(180, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlSectionDetails.add(jcmbAdviser, gridBagConstraints);

        lbl_name7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_name7.setText("Total Capacity :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlSectionDetails.add(lbl_name7, gridBagConstraints);

        jtfCapacityNumerator.setColumns(2);
        jtfCapacityNumerator.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtfCapacityNumerator.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jtfCapacityNumerator.setEnabled(false);
        jtfCapacityNumerator.setMinimumSize(new java.awt.Dimension(30, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlSectionDetails.add(jtfCapacityNumerator, gridBagConstraints);

        lbl_name8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_name8.setText("/");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlSectionDetails.add(lbl_name8, gridBagConstraints);

        jtfCapacityDenominator.setColumns(2);
        jtfCapacityDenominator.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtfCapacityDenominator.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jtfCapacityDenominator.setEnabled(false);
        jtfCapacityDenominator.setMinimumSize(new java.awt.Dimension(30, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlSectionDetails.add(jtfCapacityDenominator, gridBagConstraints);

        jcmbSession.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcmbSession.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AM", "PM", "WD" }));
        jcmbSession.setSelectedIndex(-1);
        jcmbSession.setActionCommand("session");
        jcmbSession.setEnabled(false);
        jcmbSession.setMinimumSize(new java.awt.Dimension(50, 25));
        jcmbSession.setPreferredSize(new java.awt.Dimension(50, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlSectionDetails.add(jcmbSession, gridBagConstraints);

        jbtnAutoAssign.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnAutoAssign.setText("Auto Assign");
        jbtnAutoAssign.setActionCommand("autoassign");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlSectionDetails.add(jbtnAutoAssign, gridBagConstraints);

        jcbSummer.setText("Summer");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlSectionDetails.add(jcbSummer, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toopanel.add(jpnlSectionDetails, gridBagConstraints);

        panel_subjectlist.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Enrolled Students (Active)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_subjectlist.setMinimumSize(new java.awt.Dimension(470, 500));
        panel_subjectlist.setPreferredSize(new java.awt.Dimension(470, 500));
        panel_subjectlist.setLayout(new java.awt.GridBagLayout());

        panel_subjtable.setMinimumSize(new java.awt.Dimension(455, 470));
        panel_subjtable.setPreferredSize(new java.awt.Dimension(455, 470));
        panel_subjtable.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(450, 465));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(450, 465));

        jtblEnrolledStudents.setAutoCreateRowSorter(true);
        jtblEnrolledStudents.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtblEnrolledStudents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Student No", "LN", "FN", "MN", "Type", "Grade Level"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblEnrolledStudents.setRowHeight(20);
        jtblEnrolledStudents.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtblEnrolledStudents);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_subjtable.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_subjectlist.add(panel_subjtable, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toopanel.add(panel_subjectlist, gridBagConstraints);

        panel_centercontrol.setMinimumSize(new java.awt.Dimension(45, 400));
        panel_centercontrol.setPreferredSize(new java.awt.Dimension(45, 400));
        panel_centercontrol.setLayout(new java.awt.GridBagLayout());

        jbtnMoveStudentToSection.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jbtnMoveStudentToSection.setText(">");
        jbtnMoveStudentToSection.setEnabled(false);
        jbtnMoveStudentToSection.setMaximumSize(new java.awt.Dimension(45, 40));
        jbtnMoveStudentToSection.setMinimumSize(new java.awt.Dimension(45, 40));
        jbtnMoveStudentToSection.setPreferredSize(new java.awt.Dimension(45, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_centercontrol.add(jbtnMoveStudentToSection, gridBagConstraints);

        jbtnRemoveStudentFromSection.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jbtnRemoveStudentFromSection.setText("<");
        jbtnRemoveStudentFromSection.setEnabled(false);
        jbtnRemoveStudentFromSection.setMaximumSize(new java.awt.Dimension(45, 40));
        jbtnRemoveStudentFromSection.setMinimumSize(new java.awt.Dimension(45, 40));
        jbtnRemoveStudentFromSection.setPreferredSize(new java.awt.Dimension(45, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_centercontrol.add(jbtnRemoveStudentFromSection, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        panel_toopanel.add(panel_centercontrol, gridBagConstraints);

        panel_currentsubjects.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Assigned Students", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_currentsubjects.setMinimumSize(new java.awt.Dimension(470, 500));
        panel_currentsubjects.setPreferredSize(new java.awt.Dimension(470, 500));
        panel_currentsubjects.setLayout(new java.awt.GridBagLayout());

        panel_subjtable1.setMinimumSize(new java.awt.Dimension(455, 470));
        panel_subjtable1.setPreferredSize(new java.awt.Dimension(455, 470));
        panel_subjtable1.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setMinimumSize(new java.awt.Dimension(450, 465));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(450, 465));

        jtblSectionStudents.setAutoCreateRowSorter(true);
        jtblSectionStudents.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtblSectionStudents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Student No", "Last Name", "First Name", "Middle Name", "Type", "Grade Level"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblSectionStudents.setRowHeight(20);
        jtblSectionStudents.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jtblSectionStudents);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_subjtable1.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_currentsubjects.add(panel_subjtable1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toopanel.add(panel_currentsubjects, gridBagConstraints);

        jpnlFooter.setMinimumSize(new java.awt.Dimension(798, 50));
        jpnlFooter.setPreferredSize(new java.awt.Dimension(798, 50));
        jpnlFooter.setLayout(new java.awt.GridBagLayout());

        jbtnCancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnCancel.setText("Cancel");
        jbtnCancel.setActionCommand("cancel");
        jbtnCancel.setMaximumSize(new java.awt.Dimension(120, 30));
        jbtnCancel.setMinimumSize(new java.awt.Dimension(120, 30));
        jbtnCancel.setPreferredSize(new java.awt.Dimension(120, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlFooter.add(jbtnCancel, gridBagConstraints);

        jbtnClear.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnClear.setText("Clear");
        jbtnClear.setActionCommand("clear");
        jbtnClear.setMaximumSize(new java.awt.Dimension(120, 30));
        jbtnClear.setMinimumSize(new java.awt.Dimension(120, 30));
        jbtnClear.setPreferredSize(new java.awt.Dimension(120, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlFooter.add(jbtnClear, gridBagConstraints);

        jbtnSave.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnSave.setText("Save");
        jbtnSave.setActionCommand("save");
        jbtnSave.setMaximumSize(new java.awt.Dimension(120, 30));
        jbtnSave.setMinimumSize(new java.awt.Dimension(120, 30));
        jbtnSave.setPreferredSize(new java.awt.Dimension(120, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlFooter.add(jbtnSave, gridBagConstraints);

        jbtnSaveAndNew.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnSaveAndNew.setText("Save & New");
        jbtnSaveAndNew.setActionCommand("save_and_new");
        jbtnSaveAndNew.setMaximumSize(new java.awt.Dimension(120, 30));
        jbtnSaveAndNew.setMinimumSize(new java.awt.Dimension(120, 30));
        jbtnSaveAndNew.setPreferredSize(new java.awt.Dimension(120, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlFooter.add(jbtnSaveAndNew, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 30, 0);
        panel_toopanel.add(jpnlFooter, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(panel_toopanel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public JScrollPane getjScrollPane2() {
        return jScrollPane2;
    }

    public JButton getJbtnAutoAssign() {
        return jbtnAutoAssign;
    }

    public JButton getJbtnCancel() {
        return jbtnCancel;
    }

    public JButton getJbtnClear() {
        return jbtnClear;
    }

    public JButton getJbtnMoveStudentToSection() {
        return jbtnMoveStudentToSection;
    }

    public JButton getJbtnRemoveStudentFromSection() {
        return jbtnRemoveStudentFromSection;
    }

    public JButton getJbtnSave() {
        return jbtnSave;
    }

    public JButton getJbtnSaveAndNew() {
        return jbtnSaveAndNew;
    }

    public JComboBox<String> getJcmbAdviser() {
        return jcmbAdviser;
    }

    public JComboBox<String> getJcmbGradeLevel() {
        return jcmbGradeLevel;
    }

    public JComboBox<String> getJcmbSection() {
        return jcmbSection;
    }

    public JComboBox<String> getJcmbSession() {
        return jcmbSession;
    }

    public JPanel getJpnlFooter() {
        return jpnlFooter;
    }

    public JPanel getJpnlSectionDetails() {
        return jpnlSectionDetails;
    }

    public JTable getJtblEnrolledStudents() {
        return jtblEnrolledStudents;
    }

    public JTable getJtblSectionStudents() {
        return jtblSectionStudents;
    }

    public JTextField getJtfCapacityDenominator() {
        return jtfCapacityDenominator;
    }

    public JTextField getJtfCapacityNumerator() {
        return jtfCapacityNumerator;
    }
    
    public JLabel getLbl_name() {
        return lbl_name;
    }

    public JLabel getLbl_name1() {
        return lbl_name1;
    }

    public JLabel getLbl_name3() {
        return lbl_name3;
    }

    public JLabel getLbl_name4() {
        return lbl_name4;
    }

    public JLabel getLbl_name7() {
        return lbl_name7;
    }

    public JLabel getLbl_name8() {
        return lbl_name8;
    }

    public JPanel getPanel_centercontrol() {
        return panel_centercontrol;
    }

    public JPanel getPanel_currentsubjects() {
        return panel_currentsubjects;
    }

    public JPanel getPanel_subjectlist() {
        return panel_subjectlist;
    }

    public JPanel getPanel_subjtable() {
        return panel_subjtable;
    }

    public JPanel getPanel_subjtable1() {
        return panel_subjtable1;
    }

    public JPanel getPanel_toopanel() {
        return panel_toopanel;
    }

    public JTextField getTf_subcode1() {
        return jtfCapacityNumerator;
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtnAutoAssign;
    private javax.swing.JButton jbtnCancel;
    private javax.swing.JButton jbtnClear;
    private javax.swing.JButton jbtnMoveStudentToSection;
    private javax.swing.JButton jbtnRemoveStudentFromSection;
    private javax.swing.JButton jbtnSave;
    private javax.swing.JButton jbtnSaveAndNew;
    private javax.swing.JCheckBox jcbSummer;
    private javax.swing.JComboBox<String> jcmbAdviser;
    private javax.swing.JComboBox<String> jcmbGradeLevel;
    private javax.swing.JComboBox<String> jcmbSection;
    private javax.swing.JComboBox<String> jcmbSession;
    private javax.swing.JPanel jpnlFooter;
    private javax.swing.JPanel jpnlSectionDetails;
    private javax.swing.JTable jtblEnrolledStudents;
    private javax.swing.JTable jtblSectionStudents;
    private javax.swing.JTextField jtfCapacityDenominator;
    private javax.swing.JTextField jtfCapacityNumerator;
    private javax.swing.JLabel lbl_name;
    private javax.swing.JLabel lbl_name1;
    private javax.swing.JLabel lbl_name3;
    private javax.swing.JLabel lbl_name4;
    private javax.swing.JLabel lbl_name7;
    private javax.swing.JLabel lbl_name8;
    private javax.swing.JPanel panel_centercontrol;
    private javax.swing.JPanel panel_currentsubjects;
    private javax.swing.JPanel panel_subjectlist;
    private javax.swing.JPanel panel_subjtable;
    private javax.swing.JPanel panel_subjtable1;
    private javax.swing.JPanel panel_toopanel;
    // End of variables declaration//GEN-END:variables
}
