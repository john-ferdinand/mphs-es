package view.grades;

import component_model_loader.ClassTypeJCompModelLoader;
import component_renderers.Renderer_ClassType_JComboBox;
import component_renderers.Renderer_GradeLevel_JComboBox;
import component_renderers.Renderer_Grade_Dialog_InputGrade_GradingSheet_JTable;
import component_renderers.Renderer_Grade_Dialog_OverrideGrading_SubjectCode_JComboBox;
import component_renderers.Renderer_Section_JComboBox;
import controller.global.Controller_JButton_ExitJDialog;
import controller.grade.ActionListener_Dialog_InputGrade_Save_JButton;
import controller.grade.ItemListener_Dialog_InputGrade_Section_JComboBox;
import controller.grade.ItemListener_Dialog_InputGrade_SubjectCode_JComboBox;
import controller.grade.ItemListener_Dialog_InputGrade_ClassHandled_JComboBox;
import controller.grade.TableModelListener_Dialog_InputGrade_GradingSheet_JTable;
import daoimpl.ClassTypeDaoImpl;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.classtype.ClassType;
import model.schoolyear.SchoolYear;
import model.user.User;
import utility.initializer.Initializer;
import utility.jtable.JTableUtil;


public class View_Dialog_InputGrade extends javax.swing.JDialog implements Initializer{

    private final View_Panel_GradingSystem panelGradingSystem;
    private final User user;
    private final SchoolYear currentSchoolYear;
    private ClassTypeDaoImpl classTypeDaoImpl;
    private ClassTypeJCompModelLoader classTypeJCompModelLoader;
        
    public View_Dialog_InputGrade(java.awt.Frame parent, boolean modal, User user, SchoolYear currentSchoolYear, View_Panel_GradingSystem panelGradingSystem) {
        super(parent, modal);
        initComponents();
        this.user = user;
        this.currentSchoolYear = currentSchoolYear;
        this.panelGradingSystem = panelGradingSystem;
        
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
        classTypeJCompModelLoader = new ClassTypeJCompModelLoader();
    }

    @Override
    public void initRenderers() {
        jtblGradingSheet.setDefaultRenderer(Object.class, new Renderer_Grade_Dialog_InputGrade_GradingSheet_JTable());
        jcmbSection.setRenderer(new Renderer_Section_JComboBox());
        jcmbGradeLevel.setRenderer(new Renderer_GradeLevel_JComboBox());
        jcmbSubjectCode.setRenderer(new Renderer_Grade_Dialog_OverrideGrading_SubjectCode_JComboBox());
        jcmbClassHandled.setRenderer(new Renderer_ClassType_JComboBox());
    }

    @Override
    public void initModels() {
    }

    @Override
    public void initViewComponents() {
        DefaultComboBoxModel originalComboModel = classTypeJCompModelLoader.getAllClassTypeIDsByStatus(true);
        for(int i = 0; i < originalComboModel.getSize(); i++){
            ClassType ct = classTypeDaoImpl.getClassTypeById(Integer.parseInt(originalComboModel.getElementAt(i).toString().trim()));
            if(ct.getClassTypeName().contains("&")){
                originalComboModel.removeElementAt(i);
            }
        }
        jcmbClassHandled.setModel(originalComboModel);
        JTableUtil.applyCustomHeaderRenderer(jtblGradingSheet);
        JTableUtil.resizeColumnWidthsOf(jtblGradingSheet);
    }

    @Override
    public void initControllers() {
        jbtnCancel.addActionListener(new Controller_JButton_ExitJDialog(this));
        jcmbSubjectCode.addItemListener(new ItemListener_Dialog_InputGrade_SubjectCode_JComboBox(this,user,currentSchoolYear));
        jbtnSaveAndClose.addActionListener(new ActionListener_Dialog_InputGrade_Save_JButton(this, user,currentSchoolYear,panelGradingSystem));
        jcmbClassHandled.addItemListener(new ItemListener_Dialog_InputGrade_ClassHandled_JComboBox(this, user));
        jcmbSection.addItemListener(new ItemListener_Dialog_InputGrade_Section_JComboBox(this, user));
        jtblGradingSheet.getModel().addTableModelListener(new TableModelListener_Dialog_InputGrade_GradingSheet_JTable(this));
    }

    @Override
    public void initDaoImpl() {
        classTypeDaoImpl = new ClassTypeDaoImpl();
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public JButton getJbtnCancel() {
        return jbtnCancel;
    }

    public JButton getJbtnRefresh() {
        return jbtnRefresh;
    }

    public JButton getJbtnSaveAndClose() {
        return jbtnSaveAndClose;
    }

    
    public JComboBox<String> getJcmbGradeLevel() {
        return jcmbGradeLevel;
    }

    public JComboBox<String> getJcmbSection() {
        return jcmbSection;
    }

    public JComboBox<String> getJcmbSubjectCode() {
        return jcmbSubjectCode;
    }

    public JComboBox<String> getJcmbType() {
        return jcmbClassHandled;
    }

    public JPanel getJpnlControl() {
        return jpnlControl;
    }

    public JPanel getJpnlStudentsList() {
        return jpnlStudentsList;
    }

    public JTable getJtblGradingSheet() {
        return jtblGradingSheet;
    }

    public JLabel getJlblType() {
        return jlblType;
    }

    public JLabel getJlblSection() {
        return jlblSection;
    }

    public JLabel getJlblGradeLevel() {
        return jlblGradeLevel;
    }

    public JLabel getJlblSubjectCode() {
        return jlblSubjectCode;
    }

    public JPanel getJpnlFooter() {
        return jpnlFooter;
    }

    public JPanel getJpnlTopPanel() {
        return jpnlTopPanel;
    }
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane2 = new javax.swing.JScrollPane();
        jpnlTopPanel = new javax.swing.JPanel();
        jpnlControl = new javax.swing.JPanel();
        jlblType = new javax.swing.JLabel();
        jcmbClassHandled = new javax.swing.JComboBox<>();
        jbtnRefresh = new javax.swing.JButton();
        jlblSection = new javax.swing.JLabel();
        jcmbSection = new javax.swing.JComboBox<>();
        jlblGradeLevel = new javax.swing.JLabel();
        jcmbGradeLevel = new javax.swing.JComboBox<>();
        jlblSubjectCode = new javax.swing.JLabel();
        jcmbSubjectCode = new javax.swing.JComboBox<>();
        jpnlStudentsList = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblGradingSheet = new javax.swing.JTable();
        jpnlFooter = new javax.swing.JPanel();
        jbtnCancel = new javax.swing.JButton();
        jbtnSaveAndClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Input Grades Per Subject");
        setMinimumSize(new java.awt.Dimension(1000, 350));
        setModal(true);
        setPreferredSize(new java.awt.Dimension(1009, 350));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jpnlTopPanel.setMinimumSize(new java.awt.Dimension(1000, 700));
        jpnlTopPanel.setPreferredSize(new java.awt.Dimension(1000, 700));
        jpnlTopPanel.setLayout(new java.awt.GridBagLayout());

        jpnlControl.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Control", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jpnlControl.setMinimumSize(new java.awt.Dimension(990, 50));
        jpnlControl.setPreferredSize(new java.awt.Dimension(990, 50));
        jpnlControl.setLayout(new java.awt.GridBagLayout());

        jlblType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblType.setText("Class Handled :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControl.add(jlblType, gridBagConstraints);

        jcmbClassHandled.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcmbClassHandled.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Advisory", "Handled" }));
        jcmbClassHandled.setSelectedIndex(-1);
        jcmbClassHandled.setMinimumSize(new java.awt.Dimension(130, 25));
        jcmbClassHandled.setPreferredSize(new java.awt.Dimension(130, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControl.add(jcmbClassHandled, gridBagConstraints);

        jbtnRefresh.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnRefresh.setText("Refresh");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControl.add(jbtnRefresh, gridBagConstraints);

        jlblSection.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblSection.setText("Section :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControl.add(jlblSection, gridBagConstraints);

        jcmbSection.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcmbSection.setMinimumSize(new java.awt.Dimension(130, 25));
        jcmbSection.setPreferredSize(new java.awt.Dimension(130, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControl.add(jcmbSection, gridBagConstraints);

        jlblGradeLevel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblGradeLevel.setText("Grade Level :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControl.add(jlblGradeLevel, gridBagConstraints);

        jcmbGradeLevel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcmbGradeLevel.setEnabled(false);
        jcmbGradeLevel.setMinimumSize(new java.awt.Dimension(130, 25));
        jcmbGradeLevel.setPreferredSize(new java.awt.Dimension(130, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControl.add(jcmbGradeLevel, gridBagConstraints);

        jlblSubjectCode.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblSubjectCode.setText("Subject Code :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControl.add(jlblSubjectCode, gridBagConstraints);

        jcmbSubjectCode.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcmbSubjectCode.setMinimumSize(new java.awt.Dimension(130, 25));
        jcmbSubjectCode.setPreferredSize(new java.awt.Dimension(130, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControl.add(jcmbSubjectCode, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlTopPanel.add(jpnlControl, gridBagConstraints);

        jpnlStudentsList.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Students List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jpnlStudentsList.setMinimumSize(new java.awt.Dimension(1000, 550));
        jpnlStudentsList.setPreferredSize(new java.awt.Dimension(1000, 550));
        jpnlStudentsList.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(990, 520));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(990, 520));

        jtblGradingSheet.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtblGradingSheet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Student No", "Student Name", "Ist (TG)", "2nd (TG)", "3rd (TG)", "4th (TG)", "Final Grade", "Remarks"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblGradingSheet.setRowHeight(20);
        jtblGradingSheet.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtblGradingSheet);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlStudentsList.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlTopPanel.add(jpnlStudentsList, gridBagConstraints);

        jScrollPane2.setViewportView(jpnlTopPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jScrollPane2, gridBagConstraints);

        jpnlFooter.setMinimumSize(new java.awt.Dimension(1000, 50));
        jpnlFooter.setPreferredSize(new java.awt.Dimension(1000, 50));
        jpnlFooter.setLayout(new java.awt.GridBagLayout());

        jbtnCancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnCancel.setText("Cancel");
        jbtnCancel.setMaximumSize(new java.awt.Dimension(69, 40));
        jbtnCancel.setMinimumSize(new java.awt.Dimension(69, 40));
        jbtnCancel.setPreferredSize(new java.awt.Dimension(150, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlFooter.add(jbtnCancel, gridBagConstraints);

        jbtnSaveAndClose.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnSaveAndClose.setText("Save");
        jbtnSaveAndClose.setMaximumSize(new java.awt.Dimension(120, 40));
        jbtnSaveAndClose.setMinimumSize(new java.awt.Dimension(120, 40));
        jbtnSaveAndClose.setPreferredSize(new java.awt.Dimension(150, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlFooter.add(jbtnSaveAndClose, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jpnlFooter, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtnCancel;
    private javax.swing.JButton jbtnRefresh;
    private javax.swing.JButton jbtnSaveAndClose;
    private javax.swing.JComboBox<String> jcmbClassHandled;
    private javax.swing.JComboBox<String> jcmbGradeLevel;
    private javax.swing.JComboBox<String> jcmbSection;
    private javax.swing.JComboBox<String> jcmbSubjectCode;
    private javax.swing.JLabel jlblGradeLevel;
    private javax.swing.JLabel jlblSection;
    private javax.swing.JLabel jlblSubjectCode;
    private javax.swing.JLabel jlblType;
    private javax.swing.JPanel jpnlControl;
    private javax.swing.JPanel jpnlFooter;
    private javax.swing.JPanel jpnlStudentsList;
    private javax.swing.JPanel jpnlTopPanel;
    private javax.swing.JTable jtblGradingSheet;
    // End of variables declaration//GEN-END:variables
}
