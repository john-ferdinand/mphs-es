package view.grades;

import component_model_loader.StudentJCompModelLoader;
import controller.global.Controller_JTextField_ClearDefaultSearchText;
import controller.grade.ActionListener_Display_Dialog_InputGrade_JButton;
import controller.grade.ActionListener_Display_Dialog_ViewReportCard_JButton;
import controller.grade.Controller_JButton_DisplayDialogPromotion;
import controller.grade.Controller_TableModel_GradingSystem_MyAdvisoryGradesList;
import daoimpl.FacultyDaoImpl;
import daoimpl.GradeDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import daoimpl.StudentDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import model.faculty.Faculty;
import model.grade.Grade;
import model.quarter.Quarter;
import model.schoolyear.SchoolYear;
import model.student.Student;
import model.user.User;
import renderer.grade.Renderer_MyAdvisory_MasterList;
import utility.initializer.Initializer;
import utility.jtable.JTableUtil;

/**
 *
 * @author Jordan
 */
public class View_Panel_GradingSystem extends javax.swing.JPanel implements Initializer{

    private final User user;
    private final SchoolYear currentSchoolYear;
    private StudentJCompModelLoader studentJCompModelLoader;
    private SchoolYearDaoImpl schoolYearDaoImpl;
    private StudentDaoImpl studentDaoImpl;
    private FacultyDaoImpl facultyDaoImpl;
    
    public View_Panel_GradingSystem(User user, SchoolYear currentSchoolYear) {
        initComponents();
        this.user = user;
        this.currentSchoolYear = currentSchoolYear;
        
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
        studentJCompModelLoader = new StudentJCompModelLoader(studentDaoImpl);
    }

    @Override
    public void initRenderers() {
        jtblAdvisoryGradesList.setDefaultRenderer(Object.class, new Renderer_MyAdvisory_MasterList(10));
    }

    @Override
    public void initModels() {
    }

    @Override
    public void initViewComponents() {
        jtpTop.remove(jpnlHS);
        jtpTop.remove(jpnlSUMMER);
        jlblAdviserName.setText(""+user.getLastName()+", "+user.getFirstName()+" "+user.getMiddleName());
        JTableUtil.applyCustomHeaderRenderer(jtblAdvisoryGradesList);
        if(user.getRole().getRoleName().trim().equalsIgnoreCase("Faculty")){
            Date dateToday = Calendar.getInstance().getTime();
            jtpTop.remove(jpnlADMIN);
            loadFacultyStudents();
            loadStudentGrades(5,6,7,8);
        }else if(user.getRole().getRoleName().trim().equalsIgnoreCase("Administrator")){
            jtpTop.remove(jpnlMA);
            jtpTop.remove(jpnlHS);
        }
    }

    private void loadFacultyStudents() {
        Faculty faculty = facultyDaoImpl.getFacultyByUser(user);
        SchoolYear schoolYear = schoolYearDaoImpl.getCurrentSchoolYear();
        jtblAdvisoryGradesList.setModel(studentJCompModelLoader.getStudentsOfAdviser(jtblAdvisoryGradesList, faculty, schoolYear));
    }
    
    private void loadStudentGrades(int qtr1Column, int qtr2Column, int qtr3Column, int qtr4Column) {
        GradeDaoImpl gradeDaoImpl = new GradeDaoImpl();
        SchoolYearDaoImpl schoolYearDaoImpl = new SchoolYearDaoImpl();
        SchoolYear schoolYear = schoolYearDaoImpl.getCurrentSchoolYear();
        if (jtblAdvisoryGradesList.getRowCount() > 0) {
            for (int tRow = 0; tRow < jtblAdvisoryGradesList.getRowCount(); tRow++) {
                Student student = new Student();
                student.setStudentId(Integer.parseInt(jtblAdvisoryGradesList.getValueAt(tRow, 0).toString().trim()));
                int generalAverage = 0;
                for (int tCol = 0; tCol < jtblAdvisoryGradesList.getColumnCount(); tCol++) {
                    ArrayList<Integer> quarterCols = new ArrayList<>(Arrays.asList(qtr1Column, qtr2Column, qtr3Column, qtr4Column));
                    for (int colNo : quarterCols) {
                        if (tCol == colNo) {
                            int gradingPeriod = (colNo - 4);
                            Grade grade = gradeDaoImpl.getGradeByStudentGradingPeriodAndSchoolYear(student, gradingPeriod, schoolYear);
                            jtblAdvisoryGradesList.setValueAt(grade.getValue(), tRow, tCol);
                            generalAverage += grade.getValue();
                        }
                    }
                }
                if (Integer.parseInt(jtblAdvisoryGradesList.getValueAt(tRow, 8).toString().trim()) != 0) {
                    if ((generalAverage / 4) > 0) {
                        jtblAdvisoryGradesList.setValueAt(generalAverage / 4, tRow, 9);
                    }
                }
            }
        }
    }
    
    @Override
    public void initControllers() {
        jbtnAdvisoryPromotion.addActionListener(new Controller_JButton_DisplayDialogPromotion(user,currentSchoolYear));
        jtfAdvisorySearchBox.addMouseListener(new Controller_JTextField_ClearDefaultSearchText());
        jbtnAdvisoryViewReportCard.addActionListener(new ActionListener_Display_Dialog_ViewReportCard_JButton(this));
        jbtnAdvisoryInputGrades.addActionListener(new ActionListener_Display_Dialog_InputGrade_JButton(user,currentSchoolYear,this));
        jtblAdvisoryGradesList.getModel().addTableModelListener(new Controller_TableModel_GradingSystem_MyAdvisoryGradesList(this));
        jbtnAdvisoryRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFacultyStudents();
                loadStudentGrades(5,6,7,8);
            }
        });
    }
    
    public void refreshRecord() {
        loadFacultyStudents();
        loadStudentGrades(5, 6, 7, 8);
    }

    @Override
    public void initDaoImpl() {
        schoolYearDaoImpl = new SchoolYearDaoImpl();
        studentDaoImpl = new StudentDaoImpl();
        facultyDaoImpl = new FacultyDaoImpl();
    }

    public JButton getJbtnRefresh() {
        return jbtnAdvisoryRefresh;
    }
    
    public JButton getjButton1() {
        return jbtnAdvisoryPromotion;
    }

    public JButton getjButton2() {
        return jbtnAdvisoryViewReportCard;
    }

    public JPanel getjPanel1() {
        return jpnlMA;
    }

    public JPanel getjPanel2() {
        return jpnlHS;
    }

    public JPanel getjPanel3() {
        return jPanel3;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public JTabbedPane getjTabbedPane1() {
        return jtpTop;
    }

    public JButton getJbtnInputGrades() {
        return jbtnAdvisoryInputGrades;
    }

    public JButton getJbtnSearch() {
        return jbtnAdvisorySearch;
    }

    public JPanel getJpnlMyAdvisory() {
        return jpnlAdvisoryMyAdvisory;
    }

    public JTable getJtblAdvisoryGradesList() {
        return jtblAdvisoryGradesList;
    }

    public JTextField getJtfSearchBox() {
        return jtfAdvisorySearchBox;
    }

    public JPanel getPanel_control() {
        return jpnlAdvisoryControl;
    }

    public JPanel getPanel_toppanel() {
        return jpnlTopMyAdvisory;
    }

    
    
        

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jtpTop = new javax.swing.JTabbedPane();
        jpnlMA = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jpnlTopMyAdvisory = new javax.swing.JPanel();
        jpnlAdvisoryControl = new javax.swing.JPanel();
        jbtnAdvisoryInputGrades = new javax.swing.JButton();
        jtfAdvisorySearchBox = new javax.swing.JTextField();
        jbtnAdvisorySearch = new javax.swing.JButton();
        jbtnAdvisoryPromotion = new javax.swing.JButton();
        jbtnAdvisoryViewReportCard = new javax.swing.JButton();
        jbtnAdvisoryRefresh = new javax.swing.JButton();
        jlblAdviserName = new javax.swing.JLabel();
        jpnlAdvisoryMyAdvisory = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblAdvisoryGradesList = new javax.swing.JTable();
        jpnlHS = new javax.swing.JPanel();
        jpnlNonAdvisoryControl = new javax.swing.JPanel();
        jbtnNonAdvisoryInputGrades = new javax.swing.JButton();
        jbtnNonAdvisoryViewAll = new javax.swing.JButton();
        jbtnNonAdvisoryPrint = new javax.swing.JButton();
        jtfNonAdvisorySearchBox = new javax.swing.JTextField();
        jbtnNonAdvisorySearch = new javax.swing.JButton();
        lbl_show1 = new javax.swing.JLabel();
        jcmbNonAdvisorySearchBy = new javax.swing.JComboBox<>();
        jbtnNonAdvisoryOpen = new javax.swing.JButton();
        jbtnNonAdvisoryRefresh = new javax.swing.JButton();
        jpnlNonAdvisory = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblNonAdvisoryGradesList = new javax.swing.JTable();
        jpnlSUMMER = new javax.swing.JPanel();
        jpnlADMIN = new javax.swing.JPanel();

        setLayout(new java.awt.GridBagLayout());

        jtpTop.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jpnlMA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jpnlMA.setLayout(new java.awt.GridBagLayout());

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jpnlTopMyAdvisory.setLayout(new java.awt.GridBagLayout());

        jpnlAdvisoryControl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jpnlAdvisoryControl.setMinimumSize(new java.awt.Dimension(1200, 60));
        jpnlAdvisoryControl.setPreferredSize(new java.awt.Dimension(1200, 60));
        jpnlAdvisoryControl.setLayout(new java.awt.GridBagLayout());

        jbtnAdvisoryInputGrades.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnAdvisoryInputGrades.setText("Input Grades");
        jbtnAdvisoryInputGrades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAdvisoryInputGradesActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlAdvisoryControl.add(jbtnAdvisoryInputGrades, gridBagConstraints);

        jtfAdvisorySearchBox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtfAdvisorySearchBox.setText("Search here");
        jtfAdvisorySearchBox.setMinimumSize(new java.awt.Dimension(150, 25));
        jtfAdvisorySearchBox.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlAdvisoryControl.add(jtfAdvisorySearchBox, gridBagConstraints);

        jbtnAdvisorySearch.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnAdvisorySearch.setText("Search");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlAdvisoryControl.add(jbtnAdvisorySearch, gridBagConstraints);

        jbtnAdvisoryPromotion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnAdvisoryPromotion.setText("Promotion");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlAdvisoryControl.add(jbtnAdvisoryPromotion, gridBagConstraints);

        jbtnAdvisoryViewReportCard.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnAdvisoryViewReportCard.setText("View Subject Grades");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlAdvisoryControl.add(jbtnAdvisoryViewReportCard, gridBagConstraints);

        jbtnAdvisoryRefresh.setText("Refresh");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlAdvisoryControl.add(jbtnAdvisoryRefresh, gridBagConstraints);

        jlblAdviserName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblAdviserName.setText("Adviser Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlAdvisoryControl.add(jlblAdviserName, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlTopMyAdvisory.add(jpnlAdvisoryControl, gridBagConstraints);

        jpnlAdvisoryMyAdvisory.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "My Advisory", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jpnlAdvisoryMyAdvisory.setMinimumSize(new java.awt.Dimension(1200, 555));
        jpnlAdvisoryMyAdvisory.setPreferredSize(new java.awt.Dimension(1200, 555));
        jpnlAdvisoryMyAdvisory.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane1.setMinimumSize(new java.awt.Dimension(1185, 530));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1185, 530));

        jtblAdvisoryGradesList.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtblAdvisoryGradesList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Student No", "Student Name", "Grade Level", "Section", "1st Quarter", "2nd Quarter", "3rd Quarter", "4th Quarter", "Gen. Average", "Remarks"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblAdvisoryGradesList.setMinimumSize(new java.awt.Dimension(1185, 550));
        jtblAdvisoryGradesList.setPreferredSize(new java.awt.Dimension(1185, 550));
        jtblAdvisoryGradesList.setRowHeight(40);
        jtblAdvisoryGradesList.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtblAdvisoryGradesList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlAdvisoryMyAdvisory.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlTopMyAdvisory.add(jpnlAdvisoryMyAdvisory, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel3.add(jpnlTopMyAdvisory, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlMA.add(jPanel3, gridBagConstraints);

        jtpTop.addTab("My Advisory", jpnlMA);

        jpnlHS.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jpnlHS.setLayout(new java.awt.GridBagLayout());

        jpnlNonAdvisoryControl.setBorder(javax.swing.BorderFactory.createTitledBorder("Control"));
        jpnlNonAdvisoryControl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jpnlNonAdvisoryControl.setMinimumSize(new java.awt.Dimension(1200, 60));
        jpnlNonAdvisoryControl.setPreferredSize(new java.awt.Dimension(1200, 60));
        jpnlNonAdvisoryControl.setLayout(new java.awt.GridBagLayout());

        jbtnNonAdvisoryInputGrades.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnNonAdvisoryInputGrades.setText("Input Grades");
        jbtnNonAdvisoryInputGrades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnNonAdvisoryInputGradesActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jpnlNonAdvisoryControl.add(jbtnNonAdvisoryInputGrades, gridBagConstraints);

        jbtnNonAdvisoryViewAll.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnNonAdvisoryViewAll.setText("View All");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jpnlNonAdvisoryControl.add(jbtnNonAdvisoryViewAll, gridBagConstraints);

        jbtnNonAdvisoryPrint.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnNonAdvisoryPrint.setText("Print");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlNonAdvisoryControl.add(jbtnNonAdvisoryPrint, gridBagConstraints);

        jtfNonAdvisorySearchBox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtfNonAdvisorySearchBox.setText("Search here");
        jtfNonAdvisorySearchBox.setMinimumSize(new java.awt.Dimension(150, 25));
        jtfNonAdvisorySearchBox.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlNonAdvisoryControl.add(jtfNonAdvisorySearchBox, gridBagConstraints);

        jbtnNonAdvisorySearch.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnNonAdvisorySearch.setText("Search");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlNonAdvisoryControl.add(jbtnNonAdvisorySearch, gridBagConstraints);

        lbl_show1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_show1.setText("Search by :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlNonAdvisoryControl.add(lbl_show1, gridBagConstraints);

        jcmbNonAdvisorySearchBy.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcmbNonAdvisorySearchBy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Grade Level", "Sebject Code", "Section" }));
        jcmbNonAdvisorySearchBy.setMinimumSize(new java.awt.Dimension(100, 25));
        jcmbNonAdvisorySearchBy.setPreferredSize(new java.awt.Dimension(100, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlNonAdvisoryControl.add(jcmbNonAdvisorySearchBy, gridBagConstraints);

        jbtnNonAdvisoryOpen.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnNonAdvisoryOpen.setText("Open");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jpnlNonAdvisoryControl.add(jbtnNonAdvisoryOpen, gridBagConstraints);

        jbtnNonAdvisoryRefresh.setText("Refresh");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlNonAdvisoryControl.add(jbtnNonAdvisoryRefresh, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weighty = 0.5;
        jpnlHS.add(jpnlNonAdvisoryControl, gridBagConstraints);

        jpnlNonAdvisory.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Non - Advisory Class List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jpnlNonAdvisory.setMinimumSize(new java.awt.Dimension(1200, 555));
        jpnlNonAdvisory.setPreferredSize(new java.awt.Dimension(1200, 555));
        jpnlNonAdvisory.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane2.setMinimumSize(new java.awt.Dimension(1185, 530));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(1185, 530));

        jtblNonAdvisoryGradesList.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtblNonAdvisoryGradesList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Student No", "Student Name", "Grade Level", "Section", "1st Quarter", "2nd Quarter", "3rd Quarter", "4th Quarter", "Final Grade", "Gen. Average", "Remarks"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblNonAdvisoryGradesList.setMinimumSize(new java.awt.Dimension(1185, 550));
        jtblNonAdvisoryGradesList.setPreferredSize(new java.awt.Dimension(1185, 550));
        jtblNonAdvisoryGradesList.setRowHeight(40);
        jtblNonAdvisoryGradesList.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jtblNonAdvisoryGradesList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        jpnlNonAdvisory.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jpnlHS.add(jpnlNonAdvisory, gridBagConstraints);

        jtpTop.addTab("Non-Advisory", jpnlHS);

        jpnlSUMMER.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jpnlSUMMERLayout = new javax.swing.GroupLayout(jpnlSUMMER);
        jpnlSUMMER.setLayout(jpnlSUMMERLayout);
        jpnlSUMMERLayout.setHorizontalGroup(
            jpnlSUMMERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1218, Short.MAX_VALUE)
        );
        jpnlSUMMERLayout.setVerticalGroup(
            jpnlSUMMERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 639, Short.MAX_VALUE)
        );

        jtpTop.addTab("Summer Class Students", jpnlSUMMER);

        jpnlADMIN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jpnlADMINLayout = new javax.swing.GroupLayout(jpnlADMIN);
        jpnlADMIN.setLayout(jpnlADMINLayout);
        jpnlADMINLayout.setHorizontalGroup(
            jpnlADMINLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1218, Short.MAX_VALUE)
        );
        jpnlADMINLayout.setVerticalGroup(
            jpnlADMINLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 639, Short.MAX_VALUE)
        );

        jtpTop.addTab("Admin View of Student Master List", jpnlADMIN);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        add(jtpTop, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnAdvisoryInputGradesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAdvisoryInputGradesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnAdvisoryInputGradesActionPerformed

    private void jbtnNonAdvisoryInputGradesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnNonAdvisoryInputGradesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnNonAdvisoryInputGradesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtnAdvisoryInputGrades;
    private javax.swing.JButton jbtnAdvisoryPromotion;
    private javax.swing.JButton jbtnAdvisoryRefresh;
    private javax.swing.JButton jbtnAdvisorySearch;
    private javax.swing.JButton jbtnAdvisoryViewReportCard;
    private javax.swing.JButton jbtnNonAdvisoryInputGrades;
    private javax.swing.JButton jbtnNonAdvisoryOpen;
    private javax.swing.JButton jbtnNonAdvisoryPrint;
    private javax.swing.JButton jbtnNonAdvisoryRefresh;
    private javax.swing.JButton jbtnNonAdvisorySearch;
    private javax.swing.JButton jbtnNonAdvisoryViewAll;
    private javax.swing.JComboBox<String> jcmbNonAdvisorySearchBy;
    private javax.swing.JLabel jlblAdviserName;
    private javax.swing.JPanel jpnlADMIN;
    private javax.swing.JPanel jpnlAdvisoryControl;
    private javax.swing.JPanel jpnlAdvisoryMyAdvisory;
    private javax.swing.JPanel jpnlHS;
    private javax.swing.JPanel jpnlMA;
    private javax.swing.JPanel jpnlNonAdvisory;
    private javax.swing.JPanel jpnlNonAdvisoryControl;
    private javax.swing.JPanel jpnlSUMMER;
    private javax.swing.JPanel jpnlTopMyAdvisory;
    private javax.swing.JTable jtblAdvisoryGradesList;
    private javax.swing.JTable jtblNonAdvisoryGradesList;
    private javax.swing.JTextField jtfAdvisorySearchBox;
    private javax.swing.JTextField jtfNonAdvisorySearchBox;
    private javax.swing.JTabbedPane jtpTop;
    private javax.swing.JLabel lbl_show1;
    // End of variables declaration//GEN-END:variables
}
