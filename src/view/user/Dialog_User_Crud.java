package view.user;

import component_model_loader.FacultyJCompModelLoader;
import component_model_loader.RolesJCompModelLoader;
import component_renderers.Renderer_Faculty_JComboBox;
import component_renderers.Renderer_Role_JComboBox;
import controller.user.ActionListener_Create_User_JButton;
import controller.user.ItemListener_User_Faculty_JComboBox;
import controller.user.ItemListener_User_Role_JComboBox;
import daoimpl.UserDaoImpl;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import utility.initializer.Initializer;

/**
 *
 * @author John Ferdinand Antonio
 */
public class Dialog_User_Crud extends javax.swing.JDialog implements Initializer{

    private RolesJCompModelLoader rolesJCompModelLoader;
    
    public Dialog_User_Crud(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        initDaoImpl();
        initRenderers();
        initJCompModelLoaders();
        initViewComponents();
        initControllers();
    }


    @Override
    public void initGridBagConstraints() {
    }

    @Override
    public void initJCompModelLoaders() {
        rolesJCompModelLoader = new RolesJCompModelLoader();
    }

    @Override
    public void initRenderers() {
        jcmbRole.setRenderer(new Renderer_Role_JComboBox());
        jcmbFaculty.setRenderer(new Renderer_Faculty_JComboBox());
    }

    @Override
    public void initModels() {
    }

    @Override
    public void initViewComponents() {
        jcmbRole.setModel(rolesJCompModelLoader.getAllRoles());
    }

    @Override
    public void initControllers() {
        jcmbRole.addItemListener(new ItemListener_User_Role_JComboBox(this));
        jcmbFaculty.addItemListener(new ItemListener_User_Faculty_JComboBox(this));
        jbtnCreate.addActionListener(new ActionListener_Create_User_JButton(this));
    }

    @Override
    public void initDaoImpl() {
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jpnlInfo = new javax.swing.JPanel();
        jlblUsername = new javax.swing.JLabel();
        jtfUserName = new javax.swing.JTextField();
        jlblPassword = new javax.swing.JLabel();
        jpfPassword = new javax.swing.JPasswordField();
        jlblFirstName = new javax.swing.JLabel();
        jtfFirstName = new javax.swing.JTextField();
        jlblMiddleName = new javax.swing.JLabel();
        jlblLastName = new javax.swing.JLabel();
        jtfMiddleName = new javax.swing.JTextField();
        jtfLastName = new javax.swing.JTextField();
        jlblRole = new javax.swing.JLabel();
        jcmbRole = new javax.swing.JComboBox<>();
        jlblReEnterPassword = new javax.swing.JLabel();
        jpfPasswordReEnter = new javax.swing.JPasswordField();
        jcmbFaculty = new javax.swing.JComboBox<>();
        jpnlControls = new javax.swing.JPanel();
        jbtnCancel = new javax.swing.JButton();
        jbtnCreate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jpnlInfo.setLayout(new java.awt.GridBagLayout());

        jlblUsername.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblUsername.setForeground(new java.awt.Color(0, 0, 0));
        jlblUsername.setText("Username ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlInfo.add(jlblUsername, gridBagConstraints);

        jtfUserName.setColumns(12);
        jtfUserName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtfUserName.setForeground(new java.awt.Color(0, 0, 0));
        jtfUserName.setMinimumSize(new java.awt.Dimension(180, 27));
        jtfUserName.setPreferredSize(new java.awt.Dimension(180, 27));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlInfo.add(jtfUserName, gridBagConstraints);

        jlblPassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblPassword.setForeground(new java.awt.Color(0, 0, 0));
        jlblPassword.setText("Password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlInfo.add(jlblPassword, gridBagConstraints);

        jpfPassword.setColumns(12);
        jpfPassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jpfPassword.setForeground(new java.awt.Color(0, 0, 0));
        jpfPassword.setMinimumSize(new java.awt.Dimension(180, 27));
        jpfPassword.setPreferredSize(new java.awt.Dimension(180, 27));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlInfo.add(jpfPassword, gridBagConstraints);

        jlblFirstName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblFirstName.setForeground(new java.awt.Color(0, 0, 0));
        jlblFirstName.setText("First Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlInfo.add(jlblFirstName, gridBagConstraints);

        jtfFirstName.setColumns(12);
        jtfFirstName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtfFirstName.setForeground(new java.awt.Color(0, 0, 0));
        jtfFirstName.setMinimumSize(new java.awt.Dimension(180, 27));
        jtfFirstName.setPreferredSize(new java.awt.Dimension(180, 27));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlInfo.add(jtfFirstName, gridBagConstraints);

        jlblMiddleName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblMiddleName.setForeground(new java.awt.Color(0, 0, 0));
        jlblMiddleName.setText("Middle Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlInfo.add(jlblMiddleName, gridBagConstraints);

        jlblLastName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblLastName.setForeground(new java.awt.Color(0, 0, 0));
        jlblLastName.setText("Last Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlInfo.add(jlblLastName, gridBagConstraints);

        jtfMiddleName.setColumns(12);
        jtfMiddleName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtfMiddleName.setForeground(new java.awt.Color(0, 0, 0));
        jtfMiddleName.setMinimumSize(new java.awt.Dimension(180, 27));
        jtfMiddleName.setPreferredSize(new java.awt.Dimension(180, 27));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlInfo.add(jtfMiddleName, gridBagConstraints);

        jtfLastName.setColumns(12);
        jtfLastName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtfLastName.setForeground(new java.awt.Color(0, 0, 0));
        jtfLastName.setMinimumSize(new java.awt.Dimension(180, 27));
        jtfLastName.setPreferredSize(new java.awt.Dimension(180, 27));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlInfo.add(jtfLastName, gridBagConstraints);

        jlblRole.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblRole.setForeground(new java.awt.Color(0, 0, 0));
        jlblRole.setText("Role ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlInfo.add(jlblRole, gridBagConstraints);

        jcmbRole.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcmbRole.setForeground(new java.awt.Color(0, 0, 0));
        jcmbRole.setMinimumSize(new java.awt.Dimension(180, 27));
        jcmbRole.setPreferredSize(new java.awt.Dimension(180, 27));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlInfo.add(jcmbRole, gridBagConstraints);

        jlblReEnterPassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblReEnterPassword.setForeground(new java.awt.Color(0, 0, 0));
        jlblReEnterPassword.setText("Re-enter password");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlInfo.add(jlblReEnterPassword, gridBagConstraints);

        jpfPasswordReEnter.setColumns(12);
        jpfPasswordReEnter.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jpfPasswordReEnter.setForeground(new java.awt.Color(0, 0, 0));
        jpfPasswordReEnter.setMinimumSize(new java.awt.Dimension(180, 27));
        jpfPasswordReEnter.setPreferredSize(new java.awt.Dimension(180, 27));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlInfo.add(jpfPasswordReEnter, gridBagConstraints);

        jcmbFaculty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcmbFaculty.setForeground(new java.awt.Color(0, 0, 0));
        jcmbFaculty.setMinimumSize(new java.awt.Dimension(60, 27));
        jcmbFaculty.setPreferredSize(new java.awt.Dimension(60, 27));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlInfo.add(jcmbFaculty, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jpnlInfo, gridBagConstraints);

        jpnlControls.setLayout(new java.awt.GridBagLayout());

        jbtnCancel.setText("Cancel");
        jpnlControls.add(jbtnCancel, new java.awt.GridBagConstraints());

        jbtnCreate.setText("Save");
        jbtnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCreateActionPerformed(evt);
            }
        });
        jpnlControls.add(jbtnCreate, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jpnlControls, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCreateActionPerformed
        
    }//GEN-LAST:event_jbtnCreateActionPerformed

    public JButton getJbtnCancel() {
        return jbtnCancel;
    }

    public void setJbtnCancel(JButton jbtnCancel) {
        this.jbtnCancel = jbtnCancel;
    }

    public JButton getJbtnSave() {
        return jbtnCreate;
    }

    public void setJbtnSave(JButton jbtnSave) {
        this.jbtnCreate = jbtnSave;
    }

    public JComboBox<String> getJcmbFaculty() {
        return jcmbFaculty;
    }

    public void setJcmbFaculty(JComboBox<String> jcmbFaculty) {
        this.jcmbFaculty = jcmbFaculty;
    }

    public JComboBox<String> getJcmbRole() {
        return jcmbRole;
    }

    public void setJcmbRole(JComboBox<String> jcmbRole) {
        this.jcmbRole = jcmbRole;
    }

    public JLabel getJlblFirstName() {
        return jlblFirstName;
    }

    public void setJlblFirstName(JLabel jlblFirstName) {
        this.jlblFirstName = jlblFirstName;
    }

    public JLabel getJlblLastName() {
        return jlblLastName;
    }

    public void setJlblLastName(JLabel jlblLastName) {
        this.jlblLastName = jlblLastName;
    }

    public JLabel getJlblMiddleName() {
        return jlblMiddleName;
    }

    public void setJlblMiddleName(JLabel jlblMiddleName) {
        this.jlblMiddleName = jlblMiddleName;
    }

    public JLabel getJlblPassword() {
        return jlblPassword;
    }

    public void setJlblPassword(JLabel jlblPassword) {
        this.jlblPassword = jlblPassword;
    }

    public JLabel getJlblReEnterPassword() {
        return jlblReEnterPassword;
    }

    public void setJlblReEnterPassword(JLabel jlblReEnterPassword) {
        this.jlblReEnterPassword = jlblReEnterPassword;
    }

    public JLabel getJlblRole() {
        return jlblRole;
    }

    public void setJlblRole(JLabel jlblRole) {
        this.jlblRole = jlblRole;
    }

    public JLabel getJlblUsername() {
        return jlblUsername;
    }

    public void setJlblUsername(JLabel jlblUsername) {
        this.jlblUsername = jlblUsername;
    }

    public JPasswordField getJpfPassword() {
        return jpfPassword;
    }

    public void setJpfPassword(JPasswordField jpfPassword) {
        this.jpfPassword = jpfPassword;
    }

    public JPasswordField getJpfPasswordReEnter() {
        return jpfPasswordReEnter;
    }

    public void setJpfPasswordReEnter(JPasswordField jpfPasswordReEnter) {
        this.jpfPasswordReEnter = jpfPasswordReEnter;
    }

    public JPanel getJpnlControls() {
        return jpnlControls;
    }

    public void setJpnlControls(JPanel jpnlControls) {
        this.jpnlControls = jpnlControls;
    }

    public JPanel getJpnlInfo() {
        return jpnlInfo;
    }

    public void setJpnlInfo(JPanel jpnlInfo) {
        this.jpnlInfo = jpnlInfo;
    }

    public JTextField getJtfFirstName() {
        return jtfFirstName;
    }

    public void setJtfFirstName(JTextField jtfFirstName) {
        this.jtfFirstName = jtfFirstName;
    }

    public JTextField getJtfLastName() {
        return jtfLastName;
    }

    public void setJtfLastName(JTextField jtfLastName) {
        this.jtfLastName = jtfLastName;
    }

    public JTextField getJtfMiddleName() {
        return jtfMiddleName;
    }

    public void setJtfMiddleName(JTextField jtfMiddleName) {
        this.jtfMiddleName = jtfMiddleName;
    }

    public JTextField getJtfUserName() {
        return jtfUserName;
    }

    public void setJtfUserName(JTextField jtfUserName) {
        this.jtfUserName = jtfUserName;
    }

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtnCancel;
    private javax.swing.JButton jbtnCreate;
    private javax.swing.JComboBox<String> jcmbFaculty;
    private javax.swing.JComboBox<String> jcmbRole;
    private javax.swing.JLabel jlblFirstName;
    private javax.swing.JLabel jlblLastName;
    private javax.swing.JLabel jlblMiddleName;
    private javax.swing.JLabel jlblPassword;
    private javax.swing.JLabel jlblReEnterPassword;
    private javax.swing.JLabel jlblRole;
    private javax.swing.JLabel jlblUsername;
    private javax.swing.JPasswordField jpfPassword;
    private javax.swing.JPasswordField jpfPasswordReEnter;
    private javax.swing.JPanel jpnlControls;
    private javax.swing.JPanel jpnlInfo;
    private javax.swing.JTextField jtfFirstName;
    private javax.swing.JTextField jtfLastName;
    private javax.swing.JTextField jtfMiddleName;
    private javax.swing.JTextField jtfUserName;
    // End of variables declaration//GEN-END:variables
}
