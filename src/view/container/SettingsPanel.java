
package view.container;


import constants.DashboardMenuItem;
import controller.navigation.UINavigationExit;
import controller.settings.AddSettingsPanelToTabbedPaneOnMouseClick;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.schoolyear.SchoolYear;
import model.user.User;
import utility.component.JInternalFrameUtil;
import utility.initializer.Initializer;


public class SettingsPanel extends javax.swing.JPanel implements Initializer{

    private final SchoolYear currentSchoolYear;
    private final User user;
    
    public SettingsPanel(SchoolYear currentSchoolYear, User user) {
        initComponents();
        this.currentSchoolYear = currentSchoolYear;
        this.user = user;
        
        initRenderers();
        initViewComponents();
        initControllers();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jpnlContent = new javax.swing.JPanel();
        jtpManagementTabbedPane = new javax.swing.JTabbedPane();
        jspSubjectMgmt = new javax.swing.JScrollPane();
        jpnlSubjectMgmt = new javax.swing.JPanel();
        jspSubjectCatMgmt = new javax.swing.JScrollPane();
        jpnlSubjectCatMgmt = new javax.swing.JPanel();
        jspCurriculumMgmt = new javax.swing.JScrollPane();
        jpnlCurriculumMgmt = new javax.swing.JPanel();
        jspFacultyMgmt = new javax.swing.JScrollPane();
        jpnlFacultyMgmt = new javax.swing.JPanel();
        jspSectionMgmt = new javax.swing.JScrollPane();
        jpnlSectionMgmt = new javax.swing.JPanel();
        jspRoomMgmt = new javax.swing.JScrollPane();
        jpnlRoomMgmt = new javax.swing.JPanel();
        jspScheduleMgmt = new javax.swing.JScrollPane();
        jpnlScheduleMgmt = new javax.swing.JPanel();
        jpnlFeesMgmt = new javax.swing.JPanel();
        jspCredentialRequirementsManagement = new javax.swing.JScrollPane();
        jpnlCredentialsMgmt = new javax.swing.JPanel();
        jspPaymentScheduleMgmt = new javax.swing.JScrollPane();
        jpnlPaymentScheduleMgmt = new javax.swing.JPanel();
        jpnlDiscountsMgmt = new javax.swing.JPanel();
        jspLanMgmt = new javax.swing.JScrollPane();
        jpnlLanMgmt = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jspSchoolYearMgmt = new javax.swing.JScrollPane();
        jpnlSchoolYearMgmt = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jmnFile = new javax.swing.JMenu();
        jmiExit = new javax.swing.JMenuItem();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setLayout(new java.awt.GridBagLayout());

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jInternalFrame1.setVisible(true);
        jInternalFrame1.getContentPane().setLayout(new java.awt.GridBagLayout());

        jpnlContent.setLayout(new java.awt.GridBagLayout());

        jtpManagementTabbedPane.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jpnlSubjectMgmt.setLayout(new java.awt.BorderLayout());
        jspSubjectMgmt.setViewportView(jpnlSubjectMgmt);

        jtpManagementTabbedPane.addTab("Subjects", jspSubjectMgmt);

        jpnlSubjectCatMgmt.setLayout(new java.awt.BorderLayout());
        jspSubjectCatMgmt.setViewportView(jpnlSubjectCatMgmt);

        jtpManagementTabbedPane.addTab("Subject Category", jspSubjectCatMgmt);

        jpnlCurriculumMgmt.setLayout(new java.awt.BorderLayout());
        jspCurriculumMgmt.setViewportView(jpnlCurriculumMgmt);

        jtpManagementTabbedPane.addTab("Curriculum", jspCurriculumMgmt);

        jpnlFacultyMgmt.setLayout(new java.awt.BorderLayout());
        jspFacultyMgmt.setViewportView(jpnlFacultyMgmt);

        jtpManagementTabbedPane.addTab("Faculty", jspFacultyMgmt);

        jpnlSectionMgmt.setLayout(new java.awt.BorderLayout());
        jspSectionMgmt.setViewportView(jpnlSectionMgmt);

        jtpManagementTabbedPane.addTab("Section", jspSectionMgmt);

        jpnlRoomMgmt.setLayout(new java.awt.BorderLayout());
        jspRoomMgmt.setViewportView(jpnlRoomMgmt);

        jtpManagementTabbedPane.addTab("Room", jspRoomMgmt);

        jpnlScheduleMgmt.setLayout(new java.awt.BorderLayout());
        jspScheduleMgmt.setViewportView(jpnlScheduleMgmt);

        jtpManagementTabbedPane.addTab("Schedule", jspScheduleMgmt);

        jpnlFeesMgmt.setLayout(new java.awt.BorderLayout());
        jtpManagementTabbedPane.addTab("Fees", jpnlFeesMgmt);

        jpnlCredentialsMgmt.setLayout(new java.awt.BorderLayout());
        jspCredentialRequirementsManagement.setViewportView(jpnlCredentialsMgmt);

        jtpManagementTabbedPane.addTab("Credentials", jspCredentialRequirementsManagement);

        jpnlPaymentScheduleMgmt.setLayout(new java.awt.BorderLayout());
        jspPaymentScheduleMgmt.setViewportView(jpnlPaymentScheduleMgmt);

        jtpManagementTabbedPane.addTab("Payment Schedules", jspPaymentScheduleMgmt);

        jpnlDiscountsMgmt.setLayout(new java.awt.BorderLayout());
        jtpManagementTabbedPane.addTab("Discounts", jpnlDiscountsMgmt);

        jpnlLanMgmt.setLayout(new java.awt.GridBagLayout());

        jPanel8.setLayout(new java.awt.GridBagLayout());
        jpnlLanMgmt.add(jPanel8, new java.awt.GridBagConstraints());

        jspLanMgmt.setViewportView(jpnlLanMgmt);

        jtpManagementTabbedPane.addTab("Lan", jspLanMgmt);

        jpnlSchoolYearMgmt.setLayout(new java.awt.BorderLayout());
        jspSchoolYearMgmt.setViewportView(jpnlSchoolYearMgmt);

        jtpManagementTabbedPane.addTab("School Year", jspSchoolYearMgmt);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlContent.add(jtpManagementTabbedPane, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jInternalFrame1.getContentPane().add(jpnlContent, gridBagConstraints);

        jmnFile.setText("File");

        jmiExit.setText("Exit");
        jmnFile.add(jmiExit);

        jMenuBar1.add(jmnFile);

        jInternalFrame1.setJMenuBar(jMenuBar1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        jPanel1.add(jInternalFrame1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        jPanel4.add(jPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(jPanel4, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void initRenderers() {
    }
    
    @Override
    public void initGridBagConstraints() {
    }

    @Override
    public void initJCompModelLoaders() {
    }

    @Override
    public void initModels() {
    }

    @Override
    public void initViewComponents() {
        JInternalFrameUtil.removeTitleBar(jInternalFrame1);
    }

    @Override
    public void initControllers() {
        jtpManagementTabbedPane.addMouseListener(new AddSettingsPanelToTabbedPaneOnMouseClick(this,currentSchoolYear,user));
        jmiExit.addActionListener(new UINavigationExit(this,DashboardMenuItem.SETTINGS));
    }

    @Override
    public void initDaoImpl() {

    }

    public static JPanel getJpnlContent() {
        return jpnlContent;
    }

    public JPanel getJpnlCredentialsMgmt() {
        return jpnlCredentialsMgmt;
    }

    public JPanel getJpnlCurriculumMgmt() {
        return jpnlCurriculumMgmt;
    }

    public JPanel getJpnlDiscountsMgmt() {
        return jpnlDiscountsMgmt;
    }

    public JPanel getJpnlFacultyMgmt() {
        return jpnlFacultyMgmt;
    }

    public JPanel getJpnlFeesMgmt() {
        return jpnlFeesMgmt;
    }

    public JPanel getJpnlLanMgmt() {
        return jpnlLanMgmt;
    }

    public JPanel getJpnlPaymentScheduleMgmt() {
        return jpnlPaymentScheduleMgmt;
    }

    public JPanel getJpnlRoomMgmt() {
        return jpnlRoomMgmt;
    }

    public JPanel getJpnlScheduleMgmt() {
        return jpnlScheduleMgmt;
    }

    public JPanel getJpnlSchoolYearMgmt() {
        return jpnlSchoolYearMgmt;
    }

    public JPanel getJpnlSectionMgmt() {
        return jpnlSectionMgmt;
    }

    public JPanel getJpnlSubjectCatMgmt() {
        return jpnlSubjectCatMgmt;
    }

    public JPanel getJpnlSubjectMgmt() {
        return jpnlSubjectMgmt;
    }

    public JScrollPane getJspCredentialRequirementsManagement() {
        return jspCredentialRequirementsManagement;
    }

    public JScrollPane getJspCurriculumMgmt() {
        return jspCurriculumMgmt;
    }

    public JScrollPane getJspFacultyMgmt() {
        return jspFacultyMgmt;
    }

    public JScrollPane getJspLanMgmt() {
        return jspLanMgmt;
    }

    public JScrollPane getJspPaymentScheduleMgmt() {
        return jspPaymentScheduleMgmt;
    }

    public JScrollPane getJspRoomMgmt() {
        return jspRoomMgmt;
    }

    public JScrollPane getJspScheduleMgmt() {
        return jspScheduleMgmt;
    }

    public JScrollPane getJspSchoolYearMgmt() {
        return jspSchoolYearMgmt;
    }

    public JScrollPane getJspSectionMgmt() {
        return jspSectionMgmt;
    }

    public JScrollPane getJspSubjectCatMgmt() {
        return jspSubjectCatMgmt;
    }

    public JScrollPane getJspSubjectMgmt() {
        return jspSubjectMgmt;
    }

    public JTabbedPane getJtpManagementTabbedPane() {
        return jtpManagementTabbedPane;
    }
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JMenuItem jmiExit;
    private javax.swing.JMenu jmnFile;
    public static javax.swing.JPanel jpnlContent;
    private javax.swing.JPanel jpnlCredentialsMgmt;
    private javax.swing.JPanel jpnlCurriculumMgmt;
    private javax.swing.JPanel jpnlDiscountsMgmt;
    private javax.swing.JPanel jpnlFacultyMgmt;
    private javax.swing.JPanel jpnlFeesMgmt;
    private javax.swing.JPanel jpnlLanMgmt;
    private javax.swing.JPanel jpnlPaymentScheduleMgmt;
    private javax.swing.JPanel jpnlRoomMgmt;
    private javax.swing.JPanel jpnlScheduleMgmt;
    private javax.swing.JPanel jpnlSchoolYearMgmt;
    private javax.swing.JPanel jpnlSectionMgmt;
    private javax.swing.JPanel jpnlSubjectCatMgmt;
    private javax.swing.JPanel jpnlSubjectMgmt;
    private javax.swing.JScrollPane jspCredentialRequirementsManagement;
    private javax.swing.JScrollPane jspCurriculumMgmt;
    private javax.swing.JScrollPane jspFacultyMgmt;
    private javax.swing.JScrollPane jspLanMgmt;
    private javax.swing.JScrollPane jspPaymentScheduleMgmt;
    private javax.swing.JScrollPane jspRoomMgmt;
    private javax.swing.JScrollPane jspScheduleMgmt;
    private javax.swing.JScrollPane jspSchoolYearMgmt;
    private javax.swing.JScrollPane jspSectionMgmt;
    private javax.swing.JScrollPane jspSubjectCatMgmt;
    private javax.swing.JScrollPane jspSubjectMgmt;
    private javax.swing.JTabbedPane jtpManagementTabbedPane;
    // End of variables declaration//GEN-END:variables
}
