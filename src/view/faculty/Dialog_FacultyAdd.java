
package view.faculty;

import controller.faculty.AddFacultyDialogListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Dialog_FacultyAdd extends javax.swing.JDialog {

    public Dialog_FacultyAdd(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        btn_savennew.addActionListener(new AddFacultyDialogListener(this));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public JPanel getPanel_facultydetails() {
        return panel_facultydetails;
    }
    
    public JButton getBtnSaveNew(){
        return btn_savennew;
    }
    
    public JTextField getTfLastname(){
        return tf_lastname;
    }
    
    public JTextField getTfFirstname(){
        return tf_firstname;
    }
    
    public JTextField getTfMiddlename(){
        return tf_middlename;
    }
    
    public JTextField getTfEmail(){
        return tf_email;
    }
    
    public JTextField getTfContact(){
        return tf_mobile;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel_toppanel = new javax.swing.JPanel();
        panel_facultydetails = new javax.swing.JPanel();
        lbl_lastname = new javax.swing.JLabel();
        tf_lastname = new javax.swing.JTextField();
        lbl_firstname = new javax.swing.JLabel();
        tf_firstname = new javax.swing.JTextField();
        lbl_middlename = new javax.swing.JLabel();
        tf_middlename = new javax.swing.JTextField();
        lbl_email = new javax.swing.JLabel();
        tf_email = new javax.swing.JTextField();
        lbl_mobile = new javax.swing.JLabel();
        tf_mobile = new javax.swing.JTextField();
        panel_footer = new javax.swing.JPanel();
        btn_cancel = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        btn_savennew = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add New Faculty");
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        panel_toppanel.setMinimumSize(new java.awt.Dimension(555, 250));
        panel_toppanel.setPreferredSize(new java.awt.Dimension(555, 250));
        panel_toppanel.setLayout(new java.awt.GridBagLayout());

        panel_facultydetails.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Faculty Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_facultydetails.setMinimumSize(new java.awt.Dimension(600, 140));
        panel_facultydetails.setPreferredSize(new java.awt.Dimension(600, 140));
        panel_facultydetails.setLayout(new java.awt.GridBagLayout());

        lbl_lastname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_lastname.setText("Last Name :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        panel_facultydetails.add(lbl_lastname, gridBagConstraints);

        tf_lastname.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_lastname.setMinimumSize(new java.awt.Dimension(150, 25));
        tf_lastname.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 0);
        panel_facultydetails.add(tf_lastname, gridBagConstraints);

        lbl_firstname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_firstname.setText("First Name :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        panel_facultydetails.add(lbl_firstname, gridBagConstraints);

        tf_firstname.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_firstname.setMinimumSize(new java.awt.Dimension(150, 25));
        tf_firstname.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 0);
        panel_facultydetails.add(tf_firstname, gridBagConstraints);

        lbl_middlename.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_middlename.setText("Middle Name :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        panel_facultydetails.add(lbl_middlename, gridBagConstraints);

        tf_middlename.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_middlename.setMinimumSize(new java.awt.Dimension(150, 25));
        tf_middlename.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 0);
        panel_facultydetails.add(tf_middlename, gridBagConstraints);

        lbl_email.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_email.setText("Email Address :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 10, 0);
        panel_facultydetails.add(lbl_email, gridBagConstraints);

        tf_email.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_email.setMinimumSize(new java.awt.Dimension(150, 25));
        tf_email.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 10);
        panel_facultydetails.add(tf_email, gridBagConstraints);

        lbl_mobile.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_mobile.setText("Mobile :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        panel_facultydetails.add(lbl_mobile, gridBagConstraints);

        tf_mobile.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_mobile.setMinimumSize(new java.awt.Dimension(150, 25));
        tf_mobile.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 0);
        panel_facultydetails.add(tf_mobile, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toppanel.add(panel_facultydetails, gridBagConstraints);

        panel_footer.setMinimumSize(new java.awt.Dimension(550, 50));
        panel_footer.setPreferredSize(new java.awt.Dimension(550, 50));
        panel_footer.setLayout(new java.awt.GridBagLayout());

        btn_cancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_cancel.setText("Cancel");
        btn_cancel.setMaximumSize(new java.awt.Dimension(80, 40));
        btn_cancel.setMinimumSize(new java.awt.Dimension(80, 40));
        btn_cancel.setPreferredSize(new java.awt.Dimension(80, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_footer.add(btn_cancel, gridBagConstraints);

        btn_clear.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_clear.setText("Clear");
        btn_clear.setMaximumSize(new java.awt.Dimension(80, 40));
        btn_clear.setMinimumSize(new java.awt.Dimension(80, 40));
        btn_clear.setPreferredSize(new java.awt.Dimension(80, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_footer.add(btn_clear, gridBagConstraints);

        btn_save.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_save.setText("Save");
        btn_save.setMaximumSize(new java.awt.Dimension(80, 40));
        btn_save.setMinimumSize(new java.awt.Dimension(80, 40));
        btn_save.setPreferredSize(new java.awt.Dimension(80, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_footer.add(btn_save, gridBagConstraints);

        btn_savennew.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_savennew.setText("Save & New");
        btn_savennew.setMaximumSize(new java.awt.Dimension(120, 40));
        btn_savennew.setMinimumSize(new java.awt.Dimension(120, 40));
        btn_savennew.setPreferredSize(new java.awt.Dimension(120, 40));
        btn_savennew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_savennewActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_footer.add(btn_savennew, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
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

    private void btn_savennewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_savennewActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_savennewActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_savennew;
    private javax.swing.JLabel lbl_email;
    private javax.swing.JLabel lbl_firstname;
    private javax.swing.JLabel lbl_lastname;
    private javax.swing.JLabel lbl_middlename;
    private javax.swing.JLabel lbl_mobile;
    private javax.swing.JPanel panel_facultydetails;
    private javax.swing.JPanel panel_footer;
    private javax.swing.JPanel panel_toppanel;
    private javax.swing.JTextField tf_email;
    private javax.swing.JTextField tf_firstname;
    private javax.swing.JTextField tf_lastname;
    private javax.swing.JTextField tf_middlename;
    private javax.swing.JTextField tf_mobile;
    // End of variables declaration//GEN-END:variables
}
