
package view.schedule;

import component_model_loader.GradeLevelJCompModelLoader;
import component_model_loader.RoomJCompModelLoader;
import component_model_loader.SchoolYearJCompModelLoader;
import component_renderers.Renderer_Room_JComboBox;
import component_renderers.Renderer_Schedule_Conflict_JTableCell;
import component_renderers.Renderer_Schedule_GradeLevel_JComboBox;
import component_renderers.Renderer_Schedule_Summary_JTable;
import component_renderers.Renderer_SchoolYear_JComboBox;
import component_renderers.Renderer_SectionType_JComboBox;
import component_renderers.Renderer_Section_JComboBox;
import controller.schedule.ActionListener_Create_Schedule_JButton;
import controller.schedule.ActionListener_Day_JCheckBox;
import controller.schedule.ItemListener_GradeLevel_JComboBox;
import controller.schedule.ActionListener_Schedule_LoadToSummary_JButton;
import controller.schedule.Controller_AddRow_JButton;
import controller.schedule.Controller_RemoveRow_JButton;
import controller.schedule.ItemListener_Room_JComboBox;
import controller.schedule.ItemListener_SectionType_JComboBox;
import controller.schedule.MouseListener_LoadSubjectFacultyOnClick_JTable;
import controller.schedule.ItemListener_Section_JComboBox;
import controller.schedule.KeyListener_LoadSubjectFacultyOnArrowKeyPressed_JTable;
import controller.schedule.TableModelListener_ScheduleSheet_JTable;
import daoimpl.SchoolYearDaoImpl;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import utility.initializer.Initializer;

/**
 *
 * @author John Ferdinand Antonio
 */
public class Dialog_CreateSchedule extends javax.swing.JDialog implements Initializer{

    private SchoolYearDaoImpl schoolYearDaoImpl;
    private SchoolYearJCompModelLoader schoolYearJCompModelLoader;
    private GradeLevelJCompModelLoader gradeLevelJCompModelLoader;
    private RoomJCompModelLoader roomJCompModelLoader;

    public Dialog_CreateSchedule(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        initDaoImpl();
        initJCompModelLoaders();
        initRenderers();
        initViewComponents();
        initModels();
        initControllers();
        
//        applyCellEditorListener();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jbtnGrpDays = new javax.swing.ButtonGroup();
        jpnlCreateSchedule = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jlblGradeLevel = new javax.swing.JLabel();
        jcmbGradeLevel = new javax.swing.JComboBox<>();
        jlblSection = new javax.swing.JLabel();
        jcmbSection = new javax.swing.JComboBox<>();
        jcmbSchoolYearFrom = new javax.swing.JComboBox<>();
        jlblSchoolYear = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jcmbRoom = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jcmbSectionType = new javax.swing.JComboBox<>();
        jpnlDaysControl = new javax.swing.JPanel();
        jlblConflictInfo = new javax.swing.JLabel();
        jcbMonday = new javax.swing.JCheckBox();
        jcbTuesday = new javax.swing.JCheckBox();
        jcbWednesday = new javax.swing.JCheckBox();
        jcbThursday = new javax.swing.JCheckBox();
        jcbFriday = new javax.swing.JCheckBox();
        jpnlScheduleTable = new javax.swing.JPanel();
        jpnlScheduleHeader = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jlblsubSection = new javax.swing.JLabel();
        jlblsubAdviser = new javax.swing.JLabel();
        jlblsubSchoolYear = new javax.swing.JLabel();
        jlblsubGradeLevel = new javax.swing.JLabel();
        jtfSectionName = new javax.swing.JTextField();
        jtfAdviserName = new javax.swing.JTextField();
        jtfSchoolYear = new javax.swing.JTextField();
        jtfGradeLevel = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jspSchedule = new javax.swing.JScrollPane();
        jtblSchedule = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtblSubjectHrsSummary = new javax.swing.JTable();
        jpnlSubmitSchedule = new javax.swing.JPanel();
        jbtnRemoveEntry = new javax.swing.JButton();
        jbtnClearSchedule = new javax.swing.JButton();
        jbtnCreate = new javax.swing.JButton();
        jbtnAddRow = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jpnlCreateSchedule.setLayout(new java.awt.GridBagLayout());

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Control"));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jlblGradeLevel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblGradeLevel.setText("Grade Level");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel5.add(jlblGradeLevel, gridBagConstraints);

        jcmbGradeLevel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcmbGradeLevel.setMinimumSize(new java.awt.Dimension(80, 27));
        jcmbGradeLevel.setPreferredSize(new java.awt.Dimension(150, 25));
        jcmbGradeLevel.setRenderer(new component_renderers.Renderer_GradeLevel_JComboBox());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel5.add(jcmbGradeLevel, gridBagConstraints);

        jlblSection.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblSection.setText("Section");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel5.add(jlblSection, gridBagConstraints);

        jcmbSection.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcmbSection.setEnabled(false);
        jcmbSection.setMinimumSize(new java.awt.Dimension(80, 26));
        jcmbSection.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel5.add(jcmbSection, gridBagConstraints);

        jcmbSchoolYearFrom.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcmbSchoolYearFrom.setEnabled(false);
        jcmbSchoolYearFrom.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel5.add(jcmbSchoolYearFrom, gridBagConstraints);

        jlblSchoolYear.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblSchoolYear.setText("School Year");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel5.add(jlblSchoolYear, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Room ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel5.add(jLabel1, gridBagConstraints);

        jcmbRoom.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcmbRoom.setEnabled(false);
        jcmbRoom.setPreferredSize(new java.awt.Dimension(60, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel5.add(jcmbRoom, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Section Type :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel5.add(jLabel2, gridBagConstraints);

        jcmbSectionType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcmbSectionType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "S", "R" }));
        jcmbSectionType.setSelectedIndex(-1);
        jcmbSectionType.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel5.add(jcmbSectionType, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlCreateSchedule.add(jPanel5, gridBagConstraints);

        jpnlDaysControl.setPreferredSize(new java.awt.Dimension(300, 68));
        jpnlDaysControl.setLayout(new java.awt.GridBagLayout());

        jlblConflictInfo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblConflictInfo.setText("Conflict Details");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlDaysControl.add(jlblConflictInfo, gridBagConstraints);

        jbtnGrpDays.add(jcbMonday);
        jcbMonday.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcbMonday.setText("Mon");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlDaysControl.add(jcbMonday, gridBagConstraints);

        jbtnGrpDays.add(jcbTuesday);
        jcbTuesday.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcbTuesday.setText("Tue");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlDaysControl.add(jcbTuesday, gridBagConstraints);

        jbtnGrpDays.add(jcbWednesday);
        jcbWednesday.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcbWednesday.setText("Wed");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlDaysControl.add(jcbWednesday, gridBagConstraints);

        jbtnGrpDays.add(jcbThursday);
        jcbThursday.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcbThursday.setText("Thu");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlDaysControl.add(jcbThursday, gridBagConstraints);

        jbtnGrpDays.add(jcbFriday);
        jcbFriday.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcbFriday.setText("Fri");
        jcbFriday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbFridayActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlDaysControl.add(jcbFriday, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlCreateSchedule.add(jpnlDaysControl, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jpnlCreateSchedule, gridBagConstraints);

        jpnlScheduleTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jpnlScheduleTable.setMinimumSize(new java.awt.Dimension(800, 410));
        jpnlScheduleTable.setPreferredSize(new java.awt.Dimension(800, 410));
        jpnlScheduleTable.setLayout(new java.awt.GridBagLayout());

        jpnlScheduleHeader.setBorder(javax.swing.BorderFactory.createTitledBorder("Schedule Header"));
        jpnlScheduleHeader.setLayout(new java.awt.GridBagLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new java.awt.GridBagLayout());

        jlblsubSection.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblsubSection.setForeground(new java.awt.Color(0, 0, 0));
        jlblsubSection.setText("Section :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel6.add(jlblsubSection, gridBagConstraints);

        jlblsubAdviser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblsubAdviser.setForeground(new java.awt.Color(0, 0, 0));
        jlblsubAdviser.setText("Adviser :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel6.add(jlblsubAdviser, gridBagConstraints);

        jlblsubSchoolYear.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblsubSchoolYear.setForeground(new java.awt.Color(0, 0, 0));
        jlblsubSchoolYear.setText("School Year :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel6.add(jlblsubSchoolYear, gridBagConstraints);

        jlblsubGradeLevel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblsubGradeLevel.setForeground(new java.awt.Color(0, 0, 0));
        jlblsubGradeLevel.setText("Grade Level :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel6.add(jlblsubGradeLevel, gridBagConstraints);

        jtfSectionName.setBackground(new java.awt.Color(255, 255, 255));
        jtfSectionName.setColumns(12);
        jtfSectionName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jtfSectionName.setForeground(new java.awt.Color(0, 0, 0));
        jtfSectionName.setBorder(null);
        jtfSectionName.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jtfSectionName.setEnabled(false);
        jtfSectionName.setMinimumSize(new java.awt.Dimension(110, 30));
        jtfSectionName.setPreferredSize(new java.awt.Dimension(110, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel6.add(jtfSectionName, gridBagConstraints);

        jtfAdviserName.setBackground(new java.awt.Color(255, 255, 255));
        jtfAdviserName.setColumns(12);
        jtfAdviserName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jtfAdviserName.setForeground(new java.awt.Color(0, 0, 0));
        jtfAdviserName.setBorder(null);
        jtfAdviserName.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jtfAdviserName.setEnabled(false);
        jtfAdviserName.setMinimumSize(new java.awt.Dimension(150, 30));
        jtfAdviserName.setPreferredSize(new java.awt.Dimension(150, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel6.add(jtfAdviserName, gridBagConstraints);

        jtfSchoolYear.setBackground(new java.awt.Color(255, 255, 255));
        jtfSchoolYear.setColumns(12);
        jtfSchoolYear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jtfSchoolYear.setForeground(new java.awt.Color(0, 0, 0));
        jtfSchoolYear.setBorder(null);
        jtfSchoolYear.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jtfSchoolYear.setEnabled(false);
        jtfSchoolYear.setMinimumSize(new java.awt.Dimension(150, 30));
        jtfSchoolYear.setPreferredSize(new java.awt.Dimension(150, 30));
        jtfSchoolYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfSchoolYearActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel6.add(jtfSchoolYear, gridBagConstraints);

        jtfGradeLevel.setBackground(new java.awt.Color(255, 255, 255));
        jtfGradeLevel.setColumns(12);
        jtfGradeLevel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jtfGradeLevel.setForeground(new java.awt.Color(0, 0, 0));
        jtfGradeLevel.setBorder(null);
        jtfGradeLevel.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jtfGradeLevel.setEnabled(false);
        jtfGradeLevel.setMinimumSize(new java.awt.Dimension(150, 30));
        jtfGradeLevel.setPreferredSize(new java.awt.Dimension(150, 30));
        jtfGradeLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfGradeLevelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel6.add(jtfGradeLevel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlScheduleHeader.add(jPanel6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlScheduleTable.add(jpnlScheduleHeader, gridBagConstraints);

        jPanel1.setMinimumSize(new java.awt.Dimension(695, 300));
        jPanel1.setPreferredSize(new java.awt.Dimension(1078, 300));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jspSchedule.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Schedule", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jspSchedule.setMinimumSize(new java.awt.Dimension(650, 650));
        jspSchedule.setPreferredSize(new java.awt.Dimension(650, 402));

        jtblSchedule.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtblSchedule.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Day", "Start Time", "End Time", "Subject", "Faculty", "Room", "Session"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblSchedule.setRowHeight(20);
        jtblSchedule.getTableHeader().setReorderingAllowed(false);
        jtblSchedule.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblScheduleMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtblScheduleMousePressed(evt);
            }
        });
        jtblSchedule.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtblScheduleKeyPressed(evt);
            }
        });
        jspSchedule.setViewportView(jtblSchedule);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jspSchedule, gridBagConstraints);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Warnings"));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(20, 120));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(13, 120));

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jScrollPane3.setBackground(new java.awt.Color(0, 0, 0));
        jScrollPane3.setForeground(new java.awt.Color(204, 255, 51));

        jTextArea1.setBackground(new java.awt.Color(0, 0, 0));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(204, 255, 0));
        jTextArea1.setRows(5);
        jTextArea1.setDisabledTextColor(new java.awt.Color(204, 255, 0));
        jTextArea1.setEnabled(false);
        jScrollPane3.setViewportView(jTextArea1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel2.add(jScrollPane3, gridBagConstraints);

        jScrollPane2.setViewportView(jPanel2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jScrollPane2, gridBagConstraints);

        jScrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane4.setMinimumSize(new java.awt.Dimension(350, 19));
        jScrollPane4.setPreferredSize(new java.awt.Dimension(350, 405));

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jScrollPane5.setMinimumSize(new java.awt.Dimension(250, 20));
        jScrollPane5.setPreferredSize(new java.awt.Dimension(250, 403));

        jtblSubjectHrsSummary.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtblSubjectHrsSummary.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Subject", "Mins On Table", "Required Minutes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblSubjectHrsSummary.setMinimumSize(new java.awt.Dimension(45, 400));
        jtblSubjectHrsSummary.setPreferredSize(new java.awt.Dimension(125, 400));
        jtblSubjectHrsSummary.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(jtblSubjectHrsSummary);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel3.add(jScrollPane5, gridBagConstraints);

        jScrollPane4.setViewportView(jPanel3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 6);
        jPanel1.add(jScrollPane4, gridBagConstraints);

        jScrollPane1.setViewportView(jPanel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlScheduleTable.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jpnlScheduleTable, gridBagConstraints);

        jpnlSubmitSchedule.setLayout(new java.awt.GridBagLayout());

        jbtnRemoveEntry.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbtnRemoveEntry.setText("Remove Row");
        jbtnRemoveEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnRemoveEntryActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jpnlSubmitSchedule.add(jbtnRemoveEntry, gridBagConstraints);

        jbtnClearSchedule.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbtnClearSchedule.setText("Clear Form");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jpnlSubmitSchedule.add(jbtnClearSchedule, gridBagConstraints);

        jbtnCreate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbtnCreate.setText("Create Schedule");
        jbtnCreate.setEnabled(false);
        jbtnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCreateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jpnlSubmitSchedule.add(jbtnCreate, gridBagConstraints);

        jbtnAddRow.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbtnAddRow.setText("Add Row");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlSubmitSchedule.add(jbtnAddRow, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jpnlSubmitSchedule, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCreateActionPerformed
    }//GEN-LAST:event_jbtnCreateActionPerformed

    @Override
    public void initGridBagConstraints() {
    }

    @Override
    public void initJCompModelLoaders() {
        schoolYearJCompModelLoader = new SchoolYearJCompModelLoader();
        gradeLevelJCompModelLoader = new GradeLevelJCompModelLoader();
        roomJCompModelLoader = new RoomJCompModelLoader();
    }

    @Override
    public void initRenderers() {
        jcmbSectionType.setRenderer(new Renderer_SectionType_JComboBox());
        jcmbSection.setRenderer(new Renderer_Section_JComboBox());
        jcmbGradeLevel.setRenderer(new Renderer_Schedule_GradeLevel_JComboBox());
        jcmbSchoolYearFrom.setRenderer(new Renderer_SchoolYear_JComboBox());
        jcmbRoom.setRenderer(new Renderer_Room_JComboBox());
        jtblSchedule.setDefaultRenderer(Object.class, new Renderer_Schedule_Conflict_JTableCell());
    }

    @Override
    public void initModels() {
    }

    @Override
    public void initViewComponents() {
        clearScheduleHeader();
        StringBuilder currentSy = new StringBuilder();
        currentSy.append(schoolYearDaoImpl.getCurrentSchoolYear().getYearFrom());
        currentSy.append(" - ");
        currentSy.append(schoolYearDaoImpl.getCurrentSchoolYear().getYearTo());
        jtfSchoolYear.setText(currentSy.toString());
        jcmbSchoolYearFrom.setModel(schoolYearJCompModelLoader.getCurrentSchoolYearId());
        jcmbGradeLevel.setModel(gradeLevelJCompModelLoader.getAllActiveGradeLevel());
        jlblConflictInfo.setText("");
    }

    @Override
    public void initControllers() {
        jtblSchedule.addKeyListener(new KeyListener_LoadSubjectFacultyOnArrowKeyPressed_JTable(this));
        jtblSchedule.getModel().addTableModelListener(new TableModelListener_ScheduleSheet_JTable(this));
        jtblSchedule.addMouseListener(new MouseListener_LoadSubjectFacultyOnClick_JTable(this));
        jcmbGradeLevel.addItemListener(new ItemListener_GradeLevel_JComboBox(this));
        jcmbSection.addItemListener(new ItemListener_Section_JComboBox(this));
        jcmbSectionType.addItemListener(new ItemListener_SectionType_JComboBox(this));
        jcmbRoom.addItemListener(new ItemListener_Room_JComboBox(this));
        jcbMonday.addActionListener(new ActionListener_Day_JCheckBox(this));
        jcbTuesday.addActionListener(new ActionListener_Day_JCheckBox(this));
        jcbWednesday.addActionListener(new ActionListener_Day_JCheckBox(this));
        jcbThursday.addActionListener(new ActionListener_Day_JCheckBox(this));
        jcbFriday.addActionListener(new ActionListener_Day_JCheckBox(this));
        jbtnCreate.addActionListener(new ActionListener_Create_Schedule_JButton(this));
        jbtnAddRow.addActionListener(new Controller_AddRow_JButton(this));
        jbtnRemoveEntry.addActionListener(new Controller_RemoveRow_JButton(this));
    }

    @Override
    public void initDaoImpl() {
        schoolYearDaoImpl = new SchoolYearDaoImpl();
    }
    
    private void clearScheduleHeader(){
        jtfAdviserName.setText("");
        jtfGradeLevel.setText("");
        jtfSchoolYear.setText("");
        jtfSectionName.setText("");
    }

    public JTable getJtblSubjectHrsSummary() {
        return jtblSubjectHrsSummary;
    }
    
    public JComboBox<String> getJcmbSectionType() {
        return jcmbSectionType;
    }
    
    public JLabel getjLabel1() {
        return jLabel1;
    }

    public JPanel getjPanel5() {
        return jPanel5;
    }

    public JPanel getjPanel6() {
        return jPanel6;
    }

    public JScrollPane getjScrollPane1() {
        return jspSchedule;
    }

    public JButton getJbtnClearSchedule() {
        return jbtnClearSchedule;
    }

    public JButton getJbtnCreate() {
        return jbtnCreate;
    }

    public JButton getJbtnRemoveEntry() {
        return jbtnRemoveEntry;
    }

    public JCheckBox getJcbFriday() {
        return jcbFriday;
    }

    public JCheckBox getJcbMonday() {
        return jcbMonday;
    }

    public JCheckBox getJcbThursday() {
        return jcbThursday;
    }

    public JCheckBox getJcbTuesday() {
        return jcbTuesday;
    }

    public JCheckBox getJcbWednesday() {
        return jcbWednesday;
    }

    public JComboBox<String> getJcmbGradeLevel() {
        return jcmbGradeLevel;
    }

    public JComboBox<String> getJcmbRoom() {
        return jcmbRoom;
    }

    public JComboBox<String> getJcmbSchoolYear() {
        return jcmbSchoolYearFrom;
    }

    public JComboBox<String> getJcmbSection() {
        return jcmbSection;
    }

    public JLabel getJlblConflictInfo() {
        return jlblConflictInfo;
    }

    public JLabel getJlblGradeLevel() {
        return jlblGradeLevel;
    }

    public JLabel getJlblSchoolYear() {
        return jlblSchoolYear;
    }

    public JLabel getJlblSection() {
        return jlblSection;
    }

    public JLabel getJlblsubAdviser() {
        return jlblsubAdviser;
    }

    public JTextField getJlblsubAdviserNameText() {
        return jtfAdviserName;
    }

    public JLabel getJlblsubGradeLevel() {
        return jlblsubGradeLevel;
    }

    public JTextField getJlblsubGradeLevelText() {
        return jtfGradeLevel;
    }

    public JLabel getJlblsubSchoolYear() {
        return jlblsubSchoolYear;
    }

    public JTextField getJlblsubSchoolYearText() {
        return jtfSchoolYear;
    }

    public JLabel getJlblsubSection() {
        return jlblsubSection;
    }

    public JTextField getJlblsubSectionNameText() {
        return jtfSectionName;
    }

    public JPanel getJpnlCreateSchedule() {
        return jpnlCreateSchedule;
    }

    public JPanel getJpnlScheduleHeader() {
        return jpnlScheduleHeader;
    }

    public JPanel getJpnlScheduleTable() {
        return jpnlScheduleTable;
    }

    public JPanel getJpnlSubmitSchedule() {
        return jpnlSubmitSchedule;
    }

    public JTable getJtblSchedule() {
        return jtblSchedule;
    }

    public JTextField getJtfAdviserName() {
        return jtfAdviserName;
    }

    public JTextField getJtfGradeLevel() {
        return jtfGradeLevel;
    }

    public JTextField getJtfSchoolYear() {
        return jtfSchoolYear;
    }

    public JTextField getJtfSectionName() {
        return jtfSectionName;
    }
    
    

    private void jbtnRemoveEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnRemoveEntryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnRemoveEntryActionPerformed

    private void jtblScheduleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblScheduleMouseClicked
        
    }//GEN-LAST:event_jtblScheduleMouseClicked

    private void jtblScheduleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblScheduleMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtblScheduleMousePressed

    private void jtblScheduleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtblScheduleKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_TAB){
            jpnlScheduleTable.repaint();
        }
    }//GEN-LAST:event_jtblScheduleKeyPressed

    private void jtfSchoolYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfSchoolYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfSchoolYearActionPerformed

    private void jtfGradeLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfGradeLevelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfGradeLevelActionPerformed

    private void jcbFridayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbFridayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbFridayActionPerformed

    public ButtonGroup getJbtnGrpDays() {
        return jbtnGrpDays;
    }

    public void setJbtnGrpDays(ButtonGroup jbtnGrpDays) {
        this.jbtnGrpDays = jbtnGrpDays;
    }

    public JPanel getJpnlDaysControl() {
        return jpnlDaysControl;
    }

    public void setJpnlDaysControl(JPanel jpnlDaysControl) {
        this.jpnlDaysControl = jpnlDaysControl;
    }

    public JScrollPane getJspSchedule() {
        return jspSchedule;
    }

    public void setJspSchedule(JScrollPane jspSchedule) {
        this.jspSchedule = jspSchedule;
    }

    public JTextArea getJtaWarnings() {
        return jTextArea1;
    }

    public JButton getJbtnAddRow() {
        return jbtnAddRow;
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton jbtnAddRow;
    private javax.swing.JButton jbtnClearSchedule;
    private javax.swing.JButton jbtnCreate;
    private javax.swing.ButtonGroup jbtnGrpDays;
    private javax.swing.JButton jbtnRemoveEntry;
    private javax.swing.JCheckBox jcbFriday;
    private javax.swing.JCheckBox jcbMonday;
    private javax.swing.JCheckBox jcbThursday;
    private javax.swing.JCheckBox jcbTuesday;
    private javax.swing.JCheckBox jcbWednesday;
    private javax.swing.JComboBox<String> jcmbGradeLevel;
    private javax.swing.JComboBox<String> jcmbRoom;
    private javax.swing.JComboBox<String> jcmbSchoolYearFrom;
    public static javax.swing.JComboBox<String> jcmbSection;
    private javax.swing.JComboBox<String> jcmbSectionType;
    private javax.swing.JLabel jlblConflictInfo;
    private javax.swing.JLabel jlblGradeLevel;
    private javax.swing.JLabel jlblSchoolYear;
    private javax.swing.JLabel jlblSection;
    private javax.swing.JLabel jlblsubAdviser;
    private javax.swing.JLabel jlblsubGradeLevel;
    private javax.swing.JLabel jlblsubSchoolYear;
    private javax.swing.JLabel jlblsubSection;
    private javax.swing.JPanel jpnlCreateSchedule;
    private javax.swing.JPanel jpnlDaysControl;
    private javax.swing.JPanel jpnlScheduleHeader;
    private javax.swing.JPanel jpnlScheduleTable;
    private javax.swing.JPanel jpnlSubmitSchedule;
    private javax.swing.JScrollPane jspSchedule;
    public static javax.swing.JTable jtblSchedule;
    private javax.swing.JTable jtblSubjectHrsSummary;
    private javax.swing.JTextField jtfAdviserName;
    private javax.swing.JTextField jtfGradeLevel;
    private javax.swing.JTextField jtfSchoolYear;
    private javax.swing.JTextField jtfSectionName;
    // End of variables declaration//GEN-END:variables
}
