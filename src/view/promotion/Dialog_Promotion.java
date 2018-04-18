package view.promotion;

import component_model_loader.SectionJCompModelLoader;
import component_renderers.Renderer_Promotion_Students_JTable;
import component_renderers.Renderer_Section_JComboBox;
import controller.global.Controller_JButton_ExitJDialog;
import controller.global.Controller_JTextField_ClearDefaultSearchText;
import controller.promotion.Controller_ItemListener_Section_JComboBox;
import controller.promotion.Controller_Promote_JButton;
import controller.promotion.Controller_Promotion_Students_JTable_TableModel;
import daoimpl.FacultyDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.faculty.Faculty;
import model.schoolyear.SchoolYear;
import model.user.User;
import utility.initializer.Initializer;
import utility.jtable.JTableUtil;


public class Dialog_Promotion extends javax.swing.JDialog implements Initializer{

    private SectionJCompModelLoader sectionJCompModelLoader;
    private SchoolYearDaoImpl schoolYearDaoImpl;
    private FacultyDaoImpl facultyDaoImpl;
    private final User user;
    private final SchoolYear currentSchoolYear;
    
    public Dialog_Promotion(java.awt.Frame parent, boolean modal, User user, SchoolYear currentSchoolYear) {
        super(parent, modal);
        initComponents();
        
        this.user = user;
        this.currentSchoolYear = currentSchoolYear;
        
        initDaoImpl();
        initJCompModelLoaders();
        initRenderers();
        initControllers();
        initViewComponents();
    }

    @Override
    public void initGridBagConstraints() {
    }

    @Override
    public void initJCompModelLoaders() {
        sectionJCompModelLoader = new SectionJCompModelLoader();
    }

    @Override
    public void initRenderers() {
        jcmbSections.setRenderer(new Renderer_Section_JComboBox());
        jtblStudents.setDefaultRenderer(Object.class, new Renderer_Promotion_Students_JTable());
    }

    @Override
    public void initModels() {
    }

    @Override
    public void initViewComponents() {
        JTableUtil.applyCustomHeaderRenderer(jtblStudents);
        SchoolYear schoolYear = schoolYearDaoImpl.getCurrentSchoolYear();
        Faculty faculty = facultyDaoImpl.getFacultyByUser(user);
        jcmbSections.setModel(sectionJCompModelLoader.getAdvisorySectionsOfFaculty(faculty, schoolYear));
    }

    @Override
    public void initControllers() {
        jbtnRefresh.addActionListener(new Controller_ItemListener_Section_JComboBox(this, currentSchoolYear));
        jbtnCancel.addActionListener(new Controller_JButton_ExitJDialog(this));
        jbtnPromote.addActionListener(new Controller_Promote_JButton(this, currentSchoolYear, user));
        jtblStudents.getModel().addTableModelListener(new Controller_Promotion_Students_JTable_TableModel(this));
        jtfSearchBox.addMouseListener(new Controller_JTextField_ClearDefaultSearchText());
        jcmbSections.addItemListener(new Controller_ItemListener_Section_JComboBox(this,currentSchoolYear));
    }

    @Override
    public void initDaoImpl() {
        schoolYearDaoImpl = new SchoolYearDaoImpl();
        facultyDaoImpl = new FacultyDaoImpl();
    }
    
    
    public JTextArea getJtaWarningConsole() {
        return jtaWarningConsole;
    }

    public JComboBox<String> getJcmbSections() {
        return jcmbSections;
    }

    public JTextField getJtfSearchBox() {
        return jtfSearchBox;
    }

    public JButton getJbtnCancel() {
        return jbtnCancel;
    }

    public JButton getJbtnPromote() {
        return jbtnPromote;
    }

    public JButton getJbtnSearch() {
        return jbtnSearch;
    }

    public JLabel getJlblSection() {
        return jlblSection;
    }

    public JPanel getJpnlControl() {
        return jpnlControl;
    }

    public JPanel getJpnlStudents() {
        return jpnlStudents;
    }

    public JTable getJtblStudents() {
        return jtblStudents;
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel_toppanel = new javax.swing.JPanel();
        jpnlControl = new javax.swing.JPanel();
        jlblSection = new javax.swing.JLabel();
        jcmbSections = new javax.swing.JComboBox<>();
        jtfSearchBox = new javax.swing.JTextField();
        jbtnSearch = new javax.swing.JButton();
        jbtnPromote = new javax.swing.JButton();
        jbtnRefresh = new javax.swing.JButton();
        jpnlStudents = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblStudents = new javax.swing.JTable();
        jspWarningConsole = new javax.swing.JScrollPane();
        jtaWarningConsole = new javax.swing.JTextArea();
        panel_footer = new javax.swing.JPanel();
        jbtnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Student Promotion");
        setMinimumSize(new java.awt.Dimension(1200, 600));
        setModal(true);
        setPreferredSize(new java.awt.Dimension(1200, 600));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        panel_toppanel.setMinimumSize(new java.awt.Dimension(1000, 700));
        panel_toppanel.setPreferredSize(new java.awt.Dimension(1000, 700));
        panel_toppanel.setLayout(new java.awt.GridBagLayout());

        jpnlControl.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Control", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jpnlControl.setMinimumSize(new java.awt.Dimension(1000, 50));
        jpnlControl.setPreferredSize(new java.awt.Dimension(1000, 50));
        jpnlControl.setLayout(new java.awt.GridBagLayout());

        jlblSection.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblSection.setText("Section :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControl.add(jlblSection, gridBagConstraints);

        jcmbSections.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcmbSections.setMinimumSize(new java.awt.Dimension(100, 25));
        jcmbSections.setPreferredSize(new java.awt.Dimension(100, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControl.add(jcmbSections, gridBagConstraints);

        jtfSearchBox.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jtfSearchBox.setText("Search here");
        jtfSearchBox.setMinimumSize(new java.awt.Dimension(150, 25));
        jtfSearchBox.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControl.add(jtfSearchBox, gridBagConstraints);

        jbtnSearch.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnSearch.setText("Search");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControl.add(jbtnSearch, gridBagConstraints);

        jbtnPromote.setBackground(new java.awt.Color(204, 204, 0));
        jbtnPromote.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnPromote.setText("Promote / Recommend for Summer");
        jbtnPromote.setBorderPainted(false);
        jbtnPromote.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControl.add(jbtnPromote, gridBagConstraints);

        jbtnRefresh.setText("Refresh ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControl.add(jbtnRefresh, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toppanel.add(jpnlControl, gridBagConstraints);

        jpnlStudents.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "My Students", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jpnlStudents.setMinimumSize(new java.awt.Dimension(1000, 555));
        jpnlStudents.setPreferredSize(new java.awt.Dimension(1000, 555));
        jpnlStudents.setLayout(new java.awt.GridBagLayout());

        jtblStudents.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtblStudents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student Name", "Student Number", "Grade Level", "Average", "Remarks", "Promoted", "Recommended For"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblStudents.setRowHeight(30);
        jtblStudents.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtblStudents);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlStudents.add(jScrollPane1, gridBagConstraints);

        jspWarningConsole.setBorder(javax.swing.BorderFactory.createTitledBorder("Summary / Warnings"));
        jspWarningConsole.setMinimumSize(new java.awt.Dimension(27, 200));
        jspWarningConsole.setPreferredSize(new java.awt.Dimension(223, 200));

        jtaWarningConsole.setBackground(new java.awt.Color(0, 0, 0));
        jtaWarningConsole.setColumns(20);
        jtaWarningConsole.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtaWarningConsole.setForeground(new java.awt.Color(204, 255, 0));
        jtaWarningConsole.setRows(5);
        jtaWarningConsole.setDisabledTextColor(new java.awt.Color(153, 255, 0));
        jtaWarningConsole.setEnabled(false);
        jspWarningConsole.setViewportView(jtaWarningConsole);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlStudents.add(jspWarningConsole, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toppanel.add(jpnlStudents, gridBagConstraints);

        panel_footer.setMinimumSize(new java.awt.Dimension(550, 40));
        panel_footer.setPreferredSize(new java.awt.Dimension(550, 40));
        panel_footer.setLayout(new java.awt.GridBagLayout());

        jbtnCancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnCancel.setText("Cancel");
        jbtnCancel.setMaximumSize(new java.awt.Dimension(69, 40));
        jbtnCancel.setMinimumSize(new java.awt.Dimension(69, 40));
        jbtnCancel.setPreferredSize(new java.awt.Dimension(120, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_footer.add(jbtnCancel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toppanel.add(panel_footer, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(panel_toppanel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnCancel;
    private javax.swing.JButton jbtnPromote;
    private javax.swing.JButton jbtnRefresh;
    private javax.swing.JButton jbtnSearch;
    private javax.swing.JComboBox<String> jcmbSections;
    private javax.swing.JLabel jlblSection;
    private javax.swing.JPanel jpnlControl;
    private javax.swing.JPanel jpnlStudents;
    private javax.swing.JScrollPane jspWarningConsole;
    private javax.swing.JTextArea jtaWarningConsole;
    private javax.swing.JTable jtblStudents;
    private javax.swing.JTextField jtfSearchBox;
    private javax.swing.JPanel panel_footer;
    private javax.swing.JPanel panel_toppanel;
    // End of variables declaration//GEN-END:variables
}
