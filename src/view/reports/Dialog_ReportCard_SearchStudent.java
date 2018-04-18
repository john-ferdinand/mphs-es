
package view.reports;

import component_model_loader.GradeLevelJCompModelLoader;
import component_model_loader.SchoolYearJCompModelLoader;
import component_renderers.Renderer_GradeLevel_JComboBox;
import component_renderers.Renderer_SchoolYear_JComboBox;
import controller.global.Controller_JButton_ExitJDialog;
import controller.reports.Controller_Reports_ReportCard_LoadReportCard_JButton;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.student.Student;
import utility.initializer.Initializer;
import utility.jtable.JTableUtil;

/**
 *
 * @author Jordan
 */
public class Dialog_ReportCard_SearchStudent extends javax.swing.JDialog implements Initializer{

    private final Panel_Reports panelReports;
    private final List<Student> students;
    private SchoolYearJCompModelLoader schoolYearJCompModelLoader;
    private GradeLevelJCompModelLoader gradeLevelJCompModelLoader;
    
    public Dialog_ReportCard_SearchStudent(java.awt.Frame parent, boolean modal, Panel_Reports panelReports, List<Student> students) {
        super(parent, modal);
        initComponents();
        
        this.panelReports = panelReports;
        this.students = students;
        
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
        gradeLevelJCompModelLoader = new GradeLevelJCompModelLoader();
    }

    @Override
    public void initRenderers() {
        jmcbReportCardFilterBySchoolYear.setRenderer(new Renderer_SchoolYear_JComboBox());
        jcmbGradeLevel.setRenderer(new Renderer_GradeLevel_JComboBox());
    }

    @Override
    public void initModels() {
    }

    @Override
    public void initViewComponents() {
        jmcbReportCardFilterBySchoolYear.setModel(schoolYearJCompModelLoader.getAllSchoolYear());
        jcmbGradeLevel.setModel(gradeLevelJCompModelLoader.getAllGradeLevelsAsModel());
        loadSearchResultsToTable();
    }
    
    private void loadSearchResultsToTable(){
        DefaultTableModel tableModel = (DefaultTableModel) jtblSearchResult.getModel();
        tableModel.setRowCount(0);
        for(Student s : students){
            Object[] rowData = {
                s.getStudentId(),s.getStudentNo(),
                s.getRegistration().getLastName(),s.getRegistration().getFirstName(),s.getRegistration().getMiddleName()
            };
            if(s.getStudentNo() > 0){
                tableModel.addRow(rowData);
            }
        }
        JTableUtil.applyCustomHeaderRenderer(jtblSearchResult);
    }

    @Override
    public void initControllers() {
        jbtnCancel.addActionListener(new Controller_JButton_ExitJDialog(this));
        jbtnLoadSelected.addActionListener(new Controller_Reports_ReportCard_LoadReportCard_JButton(panelReports, this));
    }

    @Override
    public void initDaoImpl() {
    }

    public JButton getJbtnCancel() {
        return jbtnCancel;
    }

    public JButton getJbtnLoadSelected() {
        return jbtnLoadSelected;
    }

    public JComboBox<String> getJcmbGradeLevel() {
        return jcmbGradeLevel;
    }

    public JComboBox<String> getJcmbReportCardFilterBySchoolYear() {
        return jmcbReportCardFilterBySchoolYear;
    }

    public JPanel getJpnlControl() {
        return jpnlControl;
    }

    public JTable getJtblSearchResult() {
        return jtblSearchResult;
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        jtblSearchResult = new javax.swing.JTable();
        jpnlControl = new javax.swing.JPanel();
        jbtnLoadSelected = new javax.swing.JButton();
        jbtnCancel = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jmcbReportCardFilterBySchoolYear = new javax.swing.JComboBox<>();
        lbl_name31 = new javax.swing.JLabel();
        jcmbGradeLevel = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jtblSearchResult.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtblSearchResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Student No", "Last Name", "First Name", "Middle Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblSearchResult.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtblSearchResult);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jScrollPane1, gridBagConstraints);

        jpnlControl.setLayout(new java.awt.GridBagLayout());

        jbtnLoadSelected.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnLoadSelected.setText("Load");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControl.add(jbtnLoadSelected, gridBagConstraints);

        jbtnCancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnCancel.setText("Cancel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControl.add(jbtnCancel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jpnlControl, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Grade Level :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jLabel1, gridBagConstraints);

        jmcbReportCardFilterBySchoolYear.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jmcbReportCardFilterBySchoolYear.setMinimumSize(new java.awt.Dimension(100, 25));
        jmcbReportCardFilterBySchoolYear.setPreferredSize(new java.awt.Dimension(100, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jmcbReportCardFilterBySchoolYear, gridBagConstraints);

        lbl_name31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_name31.setText("School Year :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(lbl_name31, gridBagConstraints);

        jcmbGradeLevel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcmbGradeLevel.setMaximumRowCount(11);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jcmbGradeLevel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnCancel;
    private javax.swing.JButton jbtnLoadSelected;
    private javax.swing.JComboBox<String> jcmbGradeLevel;
    private javax.swing.JComboBox<String> jmcbReportCardFilterBySchoolYear;
    private javax.swing.JPanel jpnlControl;
    private javax.swing.JTable jtblSearchResult;
    private javax.swing.JLabel lbl_name31;
    // End of variables declaration//GEN-END:variables
}
