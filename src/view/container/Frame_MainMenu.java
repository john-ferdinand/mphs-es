package view.container;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import utility.component.ImageUtil;


public class Frame_MainMenu extends javax.swing.JFrame {

    private Image imageUser;
   
    public Frame_MainMenu() {
        initComponents();
        ImageUtil imgUtil = new ImageUtil();
        imageUser = imgUtil.getRenderedImageForJPanel("assets/usernameIcon.jpg", panel_image);
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel_toppanel = new javax.swing.JPanel();
        panel_logocontainer = new javax.swing.JPanel();
        lbl_logo = new javax.swing.JLabel();
        lbl_SY = new javax.swing.JLabel();
        lbl_sydisplay = new javax.swing.JLabel();
        lbl_datetoday = new javax.swing.JLabel();
        lbl_datedisplay = new javax.swing.JLabel();
        panel_footer = new javax.swing.JPanel();
        lbl_version = new javax.swing.JLabel();
        panel_navicontainer = new javax.swing.JPanel();
        tabpane_navi = new javax.swing.JTabbedPane();
        panel_home = new javax.swing.JPanel();
        panel_profile = new javax.swing.JPanel();
        panel_hellouser = new javax.swing.JPanel();
        jlblHelloUserNameText = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panel_imagecontainer = new javax.swing.JPanel(){
            public void paintComponent(Graphics g){
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.8f));
            g2d.drawImage(imageUser, 0, 0, getWidth(), getHeight(), null);
            super.repaint();
        }
    };
    panel_image = new javax.swing.JPanel();
    btn_camera = new javax.swing.JButton();
    btn_camera1 = new javax.swing.JButton();
    jpnlAccountInformation = new javax.swing.JPanel();
    jlblStatus = new javax.swing.JLabel();
    jlblLastLoginText = new javax.swing.JLabel();
    jlblUserIdLabel = new javax.swing.JLabel();
    jlblUserIdText = new javax.swing.JLabel();
    jlblLastNameLabel = new javax.swing.JLabel();
    lbl_LastNameText = new javax.swing.JLabel();
    jlblFirstNameLabel = new javax.swing.JLabel();
    lbl_FirstNameText = new javax.swing.JLabel();
    jlblMiddleNameLabel = new javax.swing.JLabel();
    lbl_MiddleNameText = new javax.swing.JLabel();
    lbl_RoleLabel = new javax.swing.JLabel();
    jlblRoleText = new javax.swing.JLabel();
    jlblTimeText = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    btn_updateaccount = new javax.swing.JButton();
    btn_updateaccount1 = new javax.swing.JButton();
    panel_mainmenunavi = new javax.swing.JPanel();
    panel_registration = new javax.swing.JPanel();
    lbl_regicon = new javax.swing.JLabel();
    lbl_registration = new javax.swing.JLabel();
    panel_enrollment = new javax.swing.JPanel();
    lbl_regicon1 = new javax.swing.JLabel();
    lbl_registration1 = new javax.swing.JLabel();
    panel_grading = new javax.swing.JPanel();
    lbl_regicon2 = new javax.swing.JLabel();
    lbl_registration2 = new javax.swing.JLabel();
    panel_settings = new javax.swing.JPanel();
    lbl_regicon3 = new javax.swing.JLabel();
    lbl_registration3 = new javax.swing.JLabel();
    panel_payments = new javax.swing.JPanel();
    lbl_regicon6 = new javax.swing.JLabel();
    lbl_registration6 = new javax.swing.JLabel();
    panel_useraccounts = new javax.swing.JPanel();
    lbl_regicon4 = new javax.swing.JLabel();
    lbl_registration4 = new javax.swing.JLabel();
    panel_reports = new javax.swing.JPanel();
    lbl_regicon5 = new javax.swing.JLabel();
    lbl_registration5 = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Mother of Perpetual Help Enrollment System");
    setMinimumSize(new java.awt.Dimension(1300, 700));
    getContentPane().setLayout(new java.awt.GridBagLayout());

    panel_toppanel.setMinimumSize(new java.awt.Dimension(1300, 700));
    panel_toppanel.setPreferredSize(new java.awt.Dimension(1300, 700));
    panel_toppanel.setLayout(new java.awt.GridBagLayout());

    panel_logocontainer.setBackground(new java.awt.Color(41, 105, 176));
    panel_logocontainer.setMinimumSize(new java.awt.Dimension(1300, 70));
    panel_logocontainer.setPreferredSize(new java.awt.Dimension(1300, 70));
    panel_logocontainer.setLayout(new java.awt.GridBagLayout());

    lbl_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo.png"))); // NOI18N
    lbl_logo.setMaximumSize(new java.awt.Dimension(60, 70));
    lbl_logo.setMinimumSize(new java.awt.Dimension(60, 70));
    lbl_logo.setName(""); // NOI18N
    lbl_logo.setPreferredSize(new java.awt.Dimension(60, 70));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    panel_logocontainer.add(lbl_logo, gridBagConstraints);

    lbl_SY.setFont(new java.awt.Font("Tahoma", 0, 35)); // NOI18N
    lbl_SY.setForeground(new java.awt.Color(230, 240, 250));
    lbl_SY.setText("School Year :");
    lbl_SY.setMaximumSize(new java.awt.Dimension(210, 50));
    lbl_SY.setMinimumSize(new java.awt.Dimension(210, 50));
    lbl_SY.setPreferredSize(new java.awt.Dimension(210, 50));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
    panel_logocontainer.add(lbl_SY, gridBagConstraints);

    lbl_sydisplay.setFont(new java.awt.Font("Tahoma", 0, 35)); // NOI18N
    lbl_sydisplay.setForeground(new java.awt.Color(234, 250, 255));
    lbl_sydisplay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    lbl_sydisplay.setMaximumSize(new java.awt.Dimension(265, 50));
    lbl_sydisplay.setMinimumSize(new java.awt.Dimension(265, 50));
    lbl_sydisplay.setPreferredSize(new java.awt.Dimension(265, 50));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 180);
    panel_logocontainer.add(lbl_sydisplay, gridBagConstraints);

    lbl_datetoday.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
    lbl_datetoday.setForeground(new java.awt.Color(230, 240, 250));
    lbl_datetoday.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_date.png"))); // NOI18N
    lbl_datetoday.setText("Date Today :");
    lbl_datetoday.setMaximumSize(new java.awt.Dimension(230, 50));
    lbl_datetoday.setMinimumSize(new java.awt.Dimension(230, 50));
    lbl_datetoday.setPreferredSize(new java.awt.Dimension(230, 50));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.insets = new java.awt.Insets(0, 100, 0, 0);
    panel_logocontainer.add(lbl_datetoday, gridBagConstraints);

    lbl_datedisplay.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
    lbl_datedisplay.setForeground(new java.awt.Color(234, 250, 255));
    lbl_datedisplay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    lbl_datedisplay.setMaximumSize(new java.awt.Dimension(191, 37));
    lbl_datedisplay.setMinimumSize(new java.awt.Dimension(191, 37));
    lbl_datedisplay.setName(""); // NOI18N
    lbl_datedisplay.setPreferredSize(new java.awt.Dimension(191, 37));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
    panel_logocontainer.add(lbl_datedisplay, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    panel_toppanel.add(panel_logocontainer, gridBagConstraints);

    panel_footer.setBackground(new java.awt.Color(41, 105, 176));
    panel_footer.setForeground(new java.awt.Color(230, 240, 250));
    panel_footer.setMinimumSize(new java.awt.Dimension(1300, 20));
    panel_footer.setPreferredSize(new java.awt.Dimension(1300, 20));
    panel_footer.setLayout(new java.awt.GridBagLayout());

    lbl_version.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    lbl_version.setForeground(new java.awt.Color(230, 240, 250));
    lbl_version.setText("Version 1.00");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.weightx = 0.5;
    gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
    panel_footer.add(lbl_version, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    panel_toppanel.add(panel_footer, gridBagConstraints);

    panel_navicontainer.setBackground(new java.awt.Color(44, 130, 201));
    panel_navicontainer.setMinimumSize(new java.awt.Dimension(1300, 590));
    panel_navicontainer.setPreferredSize(new java.awt.Dimension(1300, 590));
    panel_navicontainer.setLayout(new java.awt.GridBagLayout());

    tabpane_navi.setBackground(new java.awt.Color(44, 130, 201));
    tabpane_navi.setForeground(new java.awt.Color(230, 240, 250));
    tabpane_navi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    tabpane_navi.setMinimumSize(new java.awt.Dimension(1300, 607));
    tabpane_navi.setPreferredSize(new java.awt.Dimension(1300, 607));

    panel_home.setMinimumSize(new java.awt.Dimension(1300, 580));
    panel_home.setPreferredSize(new java.awt.Dimension(1300, 580));
    panel_home.setLayout(new java.awt.GridBagLayout());

    panel_profile.setBackground(new java.awt.Color(1, 82, 138));
    panel_profile.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    panel_profile.setMinimumSize(new java.awt.Dimension(250, 580));
    panel_profile.setPreferredSize(new java.awt.Dimension(250, 580));
    panel_profile.setLayout(new java.awt.GridBagLayout());

    panel_hellouser.setBackground(new java.awt.Color(209, 213, 216));
    panel_hellouser.setMinimumSize(new java.awt.Dimension(250, 30));
    panel_hellouser.setOpaque(false);
    panel_hellouser.setPreferredSize(new java.awt.Dimension(250, 30));
    panel_hellouser.setLayout(new java.awt.GridBagLayout());

    jlblHelloUserNameText.setBackground(new java.awt.Color(209, 213, 216));
    jlblHelloUserNameText.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
    jlblHelloUserNameText.setForeground(new java.awt.Color(230, 240, 250));
    jlblHelloUserNameText.setText("username!");
    jlblHelloUserNameText.setMaximumSize(new java.awt.Dimension(90, 22));
    jlblHelloUserNameText.setMinimumSize(new java.awt.Dimension(90, 22));
    jlblHelloUserNameText.setPreferredSize(new java.awt.Dimension(90, 22));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    panel_hellouser.add(jlblHelloUserNameText, gridBagConstraints);

    jLabel2.setBackground(new java.awt.Color(209, 213, 216));
    jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    jLabel2.setForeground(new java.awt.Color(230, 240, 250));
    jLabel2.setText("Hello,");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    panel_hellouser.add(jLabel2, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
    panel_profile.add(panel_hellouser, gridBagConstraints);

    panel_imagecontainer.setBackground(new java.awt.Color(1, 82, 138));
    panel_imagecontainer.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    panel_imagecontainer.setToolTipText("");
    panel_imagecontainer.setMaximumSize(new java.awt.Dimension(230, 230));
    panel_imagecontainer.setMinimumSize(new java.awt.Dimension(230, 230));
    panel_imagecontainer.setPreferredSize(new java.awt.Dimension(230, 230));
    panel_imagecontainer.setRequestFocusEnabled(false);
    panel_imagecontainer.setLayout(new java.awt.GridBagLayout());

    panel_image.setBackground(new java.awt.Color(230, 240, 250));
    panel_image.setMinimumSize(new java.awt.Dimension(150, 150));
    panel_image.setPreferredSize(new java.awt.Dimension(150, 150));

    javax.swing.GroupLayout panel_imageLayout = new javax.swing.GroupLayout(panel_image);
    panel_image.setLayout(panel_imageLayout);
    panel_imageLayout.setHorizontalGroup(
        panel_imageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 150, Short.MAX_VALUE)
    );
    panel_imageLayout.setVerticalGroup(
        panel_imageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 150, Short.MAX_VALUE)
    );

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
    panel_imagecontainer.add(panel_image, gridBagConstraints);

    btn_camera.setBackground(new java.awt.Color(0, 39, 82));
    btn_camera.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    btn_camera.setForeground(new java.awt.Color(230, 240, 250));
    btn_camera.setText("Camera");
    btn_camera.setBorder(null);
    btn_camera.setMaximumSize(new java.awt.Dimension(80, 40));
    btn_camera.setMinimumSize(new java.awt.Dimension(80, 40));
    btn_camera.setPreferredSize(new java.awt.Dimension(80, 40));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    panel_imagecontainer.add(btn_camera, gridBagConstraints);

    btn_camera1.setBackground(new java.awt.Color(0, 39, 82));
    btn_camera1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    btn_camera1.setForeground(new java.awt.Color(230, 240, 250));
    btn_camera1.setText("Upload");
    btn_camera1.setBorder(null);
    btn_camera1.setMaximumSize(new java.awt.Dimension(80, 40));
    btn_camera1.setMinimumSize(new java.awt.Dimension(80, 40));
    btn_camera1.setPreferredSize(new java.awt.Dimension(80, 40));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
    panel_imagecontainer.add(btn_camera1, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
    panel_profile.add(panel_imagecontainer, gridBagConstraints);

    jpnlAccountInformation.setBackground(new java.awt.Color(44, 130, 201));
    jpnlAccountInformation.setMinimumSize(new java.awt.Dimension(230, 180));
    jpnlAccountInformation.setOpaque(false);
    jpnlAccountInformation.setPreferredSize(new java.awt.Dimension(230, 180));
    jpnlAccountInformation.setLayout(new java.awt.GridBagLayout());

    jlblStatus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jlblStatus.setForeground(new java.awt.Color(0, 137, 202));
    jlblStatus.setText("Last login date :");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    jpnlAccountInformation.add(jlblStatus, gridBagConstraints);

    jlblLastLoginText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jlblLastLoginText.setForeground(new java.awt.Color(230, 240, 250));
    jlblLastLoginText.setText("date text");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    jpnlAccountInformation.add(jlblLastLoginText, gridBagConstraints);

    jlblUserIdLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jlblUserIdLabel.setForeground(new java.awt.Color(0, 137, 202));
    jlblUserIdLabel.setText("Account ID :");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridy = 2;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    jpnlAccountInformation.add(jlblUserIdLabel, gridBagConstraints);

    jlblUserIdText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jlblUserIdText.setForeground(new java.awt.Color(230, 240, 250));
    jlblUserIdText.setText("Account ID Text");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridy = 2;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    jpnlAccountInformation.add(jlblUserIdText, gridBagConstraints);

    jlblLastNameLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jlblLastNameLabel.setForeground(new java.awt.Color(0, 137, 202));
    jlblLastNameLabel.setText("Last Name :");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridy = 3;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    jpnlAccountInformation.add(jlblLastNameLabel, gridBagConstraints);

    lbl_LastNameText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    lbl_LastNameText.setForeground(new java.awt.Color(230, 240, 250));
    lbl_LastNameText.setText("Last Name Text");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridy = 3;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    jpnlAccountInformation.add(lbl_LastNameText, gridBagConstraints);

    jlblFirstNameLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jlblFirstNameLabel.setForeground(new java.awt.Color(0, 137, 202));
    jlblFirstNameLabel.setText("First Name :");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridy = 4;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    jpnlAccountInformation.add(jlblFirstNameLabel, gridBagConstraints);

    lbl_FirstNameText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    lbl_FirstNameText.setForeground(new java.awt.Color(230, 240, 250));
    lbl_FirstNameText.setText("First Name Text");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    jpnlAccountInformation.add(lbl_FirstNameText, gridBagConstraints);

    jlblMiddleNameLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jlblMiddleNameLabel.setForeground(new java.awt.Color(0, 137, 202));
    jlblMiddleNameLabel.setText("Middle Name : ");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 5;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    jpnlAccountInformation.add(jlblMiddleNameLabel, gridBagConstraints);

    lbl_MiddleNameText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    lbl_MiddleNameText.setForeground(new java.awt.Color(230, 240, 250));
    lbl_MiddleNameText.setText("Middle Name Text");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 5;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    jpnlAccountInformation.add(lbl_MiddleNameText, gridBagConstraints);

    lbl_RoleLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    lbl_RoleLabel.setForeground(new java.awt.Color(0, 137, 202));
    lbl_RoleLabel.setText("Role :");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 6;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    jpnlAccountInformation.add(lbl_RoleLabel, gridBagConstraints);

    jlblRoleText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jlblRoleText.setForeground(new java.awt.Color(230, 240, 250));
    jlblRoleText.setText("Role Text");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 6;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    jpnlAccountInformation.add(jlblRoleText, gridBagConstraints);

    jlblTimeText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jlblTimeText.setForeground(new java.awt.Color(230, 240, 250));
    jlblTimeText.setText("time text");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    jpnlAccountInformation.add(jlblTimeText, gridBagConstraints);

    jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel4.setForeground(new java.awt.Color(0, 137, 202));
    jLabel4.setText("Last login time :");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
    gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
    jpnlAccountInformation.add(jLabel4, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
    panel_profile.add(jpnlAccountInformation, gridBagConstraints);

    btn_updateaccount.setBackground(new java.awt.Color(0, 39, 82));
    btn_updateaccount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    btn_updateaccount.setForeground(new java.awt.Color(230, 240, 250));
    btn_updateaccount.setText("Update Account");
    btn_updateaccount.setBorder(null);
    btn_updateaccount.setMinimumSize(new java.awt.Dimension(200, 45));
    btn_updateaccount.setPreferredSize(new java.awt.Dimension(200, 45));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
    panel_profile.add(btn_updateaccount, gridBagConstraints);

    btn_updateaccount1.setBackground(new java.awt.Color(0, 39, 82));
    btn_updateaccount1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    btn_updateaccount1.setForeground(new java.awt.Color(230, 240, 250));
    btn_updateaccount1.setText("Logout");
    btn_updateaccount1.setBorder(null);
    btn_updateaccount1.setMinimumSize(new java.awt.Dimension(200, 45));
    btn_updateaccount1.setPreferredSize(new java.awt.Dimension(200, 45));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
    panel_profile.add(btn_updateaccount1, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    panel_home.add(panel_profile, gridBagConstraints);

    panel_mainmenunavi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    panel_mainmenunavi.setMinimumSize(new java.awt.Dimension(1050, 580));
    panel_mainmenunavi.setPreferredSize(new java.awt.Dimension(1050, 580));
    panel_mainmenunavi.setLayout(new java.awt.GridBagLayout());

    panel_registration.setBackground(new java.awt.Color(71, 101, 171));
    panel_registration.setMinimumSize(new java.awt.Dimension(350, 225));
    panel_registration.setLayout(new java.awt.GridBagLayout());

    lbl_regicon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reg.png"))); // NOI18N
    lbl_regicon.setName(""); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    panel_registration.add(lbl_regicon, gridBagConstraints);

    lbl_registration.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
    lbl_registration.setForeground(new java.awt.Color(16, 29, 62));
    lbl_registration.setText("Registration");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
    panel_registration.add(lbl_registration, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    panel_mainmenunavi.add(panel_registration, gridBagConstraints);

    panel_enrollment.setBackground(new java.awt.Color(147, 101, 184));
    panel_enrollment.setMinimumSize(new java.awt.Dimension(350, 225));
    panel_enrollment.setLayout(new java.awt.GridBagLayout());

    lbl_regicon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_enrollment.png"))); // NOI18N
    lbl_regicon1.setMinimumSize(new java.awt.Dimension(120, 120));
    lbl_regicon1.setName(""); // NOI18N
    lbl_regicon1.setPreferredSize(new java.awt.Dimension(120, 120));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
    panel_enrollment.add(lbl_regicon1, gridBagConstraints);

    lbl_registration1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
    lbl_registration1.setForeground(new java.awt.Color(41, 15, 62));
    lbl_registration1.setText("Enrollment");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
    panel_enrollment.add(lbl_registration1, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    panel_mainmenunavi.add(panel_enrollment, gridBagConstraints);

    panel_grading.setBackground(new java.awt.Color(84, 172, 210));
    panel_grading.setMinimumSize(new java.awt.Dimension(350, 225));
    panel_grading.setPreferredSize(new java.awt.Dimension(350, 225));
    panel_grading.setLayout(new java.awt.GridBagLayout());

    lbl_regicon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_grades.png"))); // NOI18N
    lbl_regicon2.setName(""); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    panel_grading.add(lbl_regicon2, gridBagConstraints);

    lbl_registration2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
    lbl_registration2.setForeground(new java.awt.Color(33, 62, 75));
    lbl_registration2.setText("Grading System");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
    panel_grading.add(lbl_registration2, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    panel_mainmenunavi.add(panel_grading, gridBagConstraints);

    panel_settings.setBackground(new java.awt.Color(97, 189, 109));
    panel_settings.setMinimumSize(new java.awt.Dimension(350, 225));
    panel_settings.setLayout(new java.awt.GridBagLayout());

    lbl_regicon3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_settings.png"))); // NOI18N
    lbl_regicon3.setName(""); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    panel_settings.add(lbl_regicon3, gridBagConstraints);

    lbl_registration3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
    lbl_registration3.setForeground(new java.awt.Color(27, 70, 32));
    lbl_registration3.setText("Settings");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
    panel_settings.add(lbl_registration3, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    panel_mainmenunavi.add(panel_settings, gridBagConstraints);

    panel_payments.setBackground(new java.awt.Color(225, 73, 56));
    panel_payments.setMinimumSize(new java.awt.Dimension(1050, 130));
    panel_payments.setPreferredSize(new java.awt.Dimension(1050, 130));
    panel_payments.setLayout(new java.awt.GridBagLayout());

    lbl_regicon6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_payments.png"))); // NOI18N
    lbl_regicon6.setMinimumSize(new java.awt.Dimension(120, 120));
    lbl_regicon6.setName(""); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.weightx = 0.5;
    gridBagConstraints.insets = new java.awt.Insets(0, 30, 20, 0);
    panel_payments.add(lbl_regicon6, gridBagConstraints);

    lbl_registration6.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
    lbl_registration6.setForeground(new java.awt.Color(65, 23, 18));
    lbl_registration6.setText("Assessment & Payments");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.weightx = 0.5;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 500);
    panel_payments.add(lbl_registration6, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.gridwidth = 3;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    panel_mainmenunavi.add(panel_payments, gridBagConstraints);

    panel_useraccounts.setBackground(new java.awt.Color(247, 218, 100));
    panel_useraccounts.setMinimumSize(new java.awt.Dimension(350, 225));
    panel_useraccounts.setLayout(new java.awt.GridBagLayout());

    lbl_regicon4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_users.png"))); // NOI18N
    lbl_regicon4.setName(""); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    panel_useraccounts.add(lbl_regicon4, gridBagConstraints);

    lbl_registration4.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
    lbl_registration4.setForeground(new java.awt.Color(88, 77, 34));
    lbl_registration4.setText("User Accounts");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
    panel_useraccounts.add(lbl_registration4, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    panel_mainmenunavi.add(panel_useraccounts, gridBagConstraints);

    panel_reports.setBackground(new java.awt.Color(251, 160, 38));
    panel_reports.setMinimumSize(new java.awt.Dimension(350, 225));
    panel_reports.setLayout(new java.awt.GridBagLayout());

    lbl_regicon5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icon_reports.png"))); // NOI18N
    lbl_regicon5.setName(""); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    panel_reports.add(lbl_regicon5, gridBagConstraints);

    lbl_registration5.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
    lbl_registration5.setForeground(new java.awt.Color(72, 54, 31));
    lbl_registration5.setText("Reports");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
    panel_reports.add(lbl_registration5, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 1;
    panel_mainmenunavi.add(panel_reports, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    panel_home.add(panel_mainmenunavi, gridBagConstraints);

    tabpane_navi.addTab("Home", new javax.swing.ImageIcon(getClass().getResource("/img/hometwo.png")), panel_home); // NOI18N

    panel_navicontainer.add(tabpane_navi, new java.awt.GridBagConstraints());

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    panel_toppanel.add(panel_navicontainer, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    getContentPane().add(panel_toppanel, gridBagConstraints);

    pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_camera;
    private javax.swing.JButton btn_camera1;
    private javax.swing.JButton btn_updateaccount;
    private javax.swing.JButton btn_updateaccount1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jlblFirstNameLabel;
    private javax.swing.JLabel jlblHelloUserNameText;
    private javax.swing.JLabel jlblLastLoginText;
    private javax.swing.JLabel jlblLastNameLabel;
    private javax.swing.JLabel jlblMiddleNameLabel;
    private javax.swing.JLabel jlblRoleText;
    private javax.swing.JLabel jlblStatus;
    private javax.swing.JLabel jlblTimeText;
    private javax.swing.JLabel jlblUserIdLabel;
    private javax.swing.JLabel jlblUserIdText;
    private javax.swing.JPanel jpnlAccountInformation;
    private javax.swing.JLabel lbl_FirstNameText;
    private javax.swing.JLabel lbl_LastNameText;
    private javax.swing.JLabel lbl_MiddleNameText;
    private javax.swing.JLabel lbl_RoleLabel;
    private javax.swing.JLabel lbl_SY;
    private javax.swing.JLabel lbl_datedisplay;
    private javax.swing.JLabel lbl_datetoday;
    private javax.swing.JLabel lbl_logo;
    private javax.swing.JLabel lbl_regicon;
    private javax.swing.JLabel lbl_regicon1;
    private javax.swing.JLabel lbl_regicon2;
    private javax.swing.JLabel lbl_regicon3;
    private javax.swing.JLabel lbl_regicon4;
    private javax.swing.JLabel lbl_regicon5;
    private javax.swing.JLabel lbl_regicon6;
    private javax.swing.JLabel lbl_registration;
    private javax.swing.JLabel lbl_registration1;
    private javax.swing.JLabel lbl_registration2;
    private javax.swing.JLabel lbl_registration3;
    private javax.swing.JLabel lbl_registration4;
    private javax.swing.JLabel lbl_registration5;
    private javax.swing.JLabel lbl_registration6;
    private javax.swing.JLabel lbl_sydisplay;
    private javax.swing.JLabel lbl_version;
    private javax.swing.JPanel panel_enrollment;
    private javax.swing.JPanel panel_footer;
    private javax.swing.JPanel panel_grading;
    private javax.swing.JPanel panel_hellouser;
    private javax.swing.JPanel panel_home;
    private javax.swing.JPanel panel_image;
    private javax.swing.JPanel panel_imagecontainer;
    private javax.swing.JPanel panel_logocontainer;
    private javax.swing.JPanel panel_mainmenunavi;
    private javax.swing.JPanel panel_navicontainer;
    private javax.swing.JPanel panel_payments;
    private javax.swing.JPanel panel_profile;
    private javax.swing.JPanel panel_registration;
    private javax.swing.JPanel panel_reports;
    private javax.swing.JPanel panel_settings;
    private javax.swing.JPanel panel_toppanel;
    private javax.swing.JPanel panel_useraccounts;
    private javax.swing.JTabbedPane tabpane_navi;
    // End of variables declaration//GEN-END:variables
}
