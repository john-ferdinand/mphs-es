
package view.fees;

import component_model_loader.FeeJCompModelLoader;
import controller.feesetting.DisplayFeeGradeLevelAssignmentOnKeyPress;
import controller.feesetting.DisplayFeeGradeLevelAssignmentOnMouseClick;
import controller.feesetting.FeeDisplayDialog;
import controller.feesetting.SearchFeeOnFeeMasterTable;
import controller.global.Controller_JTextField_ClearDefaultSearchText;
import javax.swing.table.DefaultTableModel;
import utility.initializer.Initializer;

/**
 *
 * 
 */
public class Panel_FeeRecord extends javax.swing.JPanel implements Initializer {

    private FeeJCompModelLoader feeJCompModelLoader;
    private DefaultTableModel dtmFeeRecord;
    
    public Panel_FeeRecord() {
        initComponents();
        
        initGridBagConstraints();
        initJCompModelLoaders();
        initRenderers();
        initModels();
        initViewComponents();
        initControllers();
    }

    @Override
    public void initRenderers() {
    }
    
    @Override
    public final void initGridBagConstraints() {
    }

    @Override
    public void initJCompModelLoaders() {
        feeJCompModelLoader = new FeeJCompModelLoader();
    }
    
    @Override
    public final void initModels() {
        dtmFeeRecord = feeJCompModelLoader.getAllFeesGroupedByIdAsModel(jtblFeeRecord);
    }
    
    @Override
    public final void initViewComponents() {
        jtblFeeRecord.setModel(dtmFeeRecord);
    }

    @Override
    public final void initControllers() {
        jtfSearchBox.addMouseListener(new Controller_JTextField_ClearDefaultSearchText());
        btn_Search.addActionListener(new SearchFeeOnFeeMasterTable(jtfSearchBox, jtblFeeRecord));
        jtblFeeRecord.addKeyListener(new DisplayFeeGradeLevelAssignmentOnKeyPress(jtblFeeRecord, jtblFeeGradeLevelAssignment));
        jtblFeeRecord.addMouseListener(new DisplayFeeGradeLevelAssignmentOnMouseClick(jtblFeeRecord, jtblFeeGradeLevelAssignment));
        jbtnCreateFee.addActionListener(new FeeDisplayDialog(jtblFeeRecord));
        jbtnEdit.addActionListener(new FeeDisplayDialog(jtblFeeRecord));
        jbtnView.addActionListener(new FeeDisplayDialog(jtblFeeRecord));
    }

    @Override
    public void initDaoImpl() {
        
    }

    public void refreshFeeRecord(){
        jtblFeeRecord.setModel(dtmFeeRecord);
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel_toppanel = new javax.swing.JPanel();
        panel_control = new javax.swing.JPanel();
        jbtnCreateFee = new javax.swing.JButton();
        jbtnEdit = new javax.swing.JButton();
        jbtnView = new javax.swing.JButton();
        jbtnPrint = new javax.swing.JButton();
        jtfSearchBox = new javax.swing.JTextField();
        btn_Search = new javax.swing.JButton();
        lbl_show = new javax.swing.JLabel();
        combo_filter = new javax.swing.JComboBox<>();
        panel_masterrecord = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblFeeRecord = new javax.swing.JTable();
        panel_levelassignment = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblFeeGradeLevelAssignment = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(1200, 600));
        setPreferredSize(new java.awt.Dimension(1200, 600));
        setLayout(new java.awt.GridBagLayout());

        panel_toppanel.setMinimumSize(new java.awt.Dimension(1200, 600));
        panel_toppanel.setPreferredSize(new java.awt.Dimension(1200, 600));
        panel_toppanel.setLayout(new java.awt.GridBagLayout());

        panel_control.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel_control.setMinimumSize(new java.awt.Dimension(1200, 40));
        panel_control.setPreferredSize(new java.awt.Dimension(1200, 40));
        panel_control.setLayout(new java.awt.GridBagLayout());

        jbtnCreateFee.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnCreateFee.setText("Create New Fee");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        panel_control.add(jbtnCreateFee, gridBagConstraints);

        jbtnEdit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnEdit.setText("Edit");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        panel_control.add(jbtnEdit, gridBagConstraints);

        jbtnView.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnView.setText("View");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        panel_control.add(jbtnView, gridBagConstraints);

        jbtnPrint.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnPrint.setText("Print");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        panel_control.add(jbtnPrint, gridBagConstraints);

        jtfSearchBox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtfSearchBox.setText("Search here");
        jtfSearchBox.setMinimumSize(new java.awt.Dimension(150, 25));
        jtfSearchBox.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 450, 0, 0);
        panel_control.add(jtfSearchBox, gridBagConstraints);

        btn_Search.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_Search.setText("Search");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        panel_control.add(btn_Search, gridBagConstraints);

        lbl_show.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_show.setText("Show  :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        panel_control.add(lbl_show, gridBagConstraints);

        combo_filter.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        combo_filter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        combo_filter.setMinimumSize(new java.awt.Dimension(100, 25));
        combo_filter.setPreferredSize(new java.awt.Dimension(100, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 10);
        panel_control.add(combo_filter, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toppanel.add(panel_control, gridBagConstraints);

        panel_masterrecord.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fee Master List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_masterrecord.setMinimumSize(new java.awt.Dimension(1200, 290));
        panel_masterrecord.setPreferredSize(new java.awt.Dimension(1200, 290));
        panel_masterrecord.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane1.setMinimumSize(new java.awt.Dimension(1185, 265));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1185, 265));

        jtblFeeRecord.setAutoCreateRowSorter(true);
        jtblFeeRecord.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtblFeeRecord.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fee Id", "Fee Name", "Category", "Year Created", "Status", "Notes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblFeeRecord.setMinimumSize(new java.awt.Dimension(1185, 10000));
        jtblFeeRecord.setPreferredSize(new java.awt.Dimension(1185, 10000));
        jtblFeeRecord.setRowHeight(20);
        jtblFeeRecord.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtblFeeRecord);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_masterrecord.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toppanel.add(panel_masterrecord, gridBagConstraints);

        panel_levelassignment.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Grade Level Assignment", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_levelassignment.setMinimumSize(new java.awt.Dimension(1200, 260));
        panel_levelassignment.setPreferredSize(new java.awt.Dimension(1200, 260));
        panel_levelassignment.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane2.setMinimumSize(new java.awt.Dimension(1185, 230));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(1185, 230));

        jtblFeeGradeLevelAssignment.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtblFeeGradeLevelAssignment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Grade Level", "Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblFeeGradeLevelAssignment.setMinimumSize(new java.awt.Dimension(1185, 10000));
        jtblFeeGradeLevelAssignment.setPreferredSize(new java.awt.Dimension(1185, 10000));
        jtblFeeGradeLevelAssignment.setRowHeight(20);
        jtblFeeGradeLevelAssignment.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jtblFeeGradeLevelAssignment);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_levelassignment.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toppanel.add(panel_levelassignment, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        add(panel_toppanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Search;
    private javax.swing.JComboBox<String> combo_filter;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtnCreateFee;
    private javax.swing.JButton jbtnEdit;
    private javax.swing.JButton jbtnPrint;
    private javax.swing.JButton jbtnView;
    private javax.swing.JTable jtblFeeGradeLevelAssignment;
    public static javax.swing.JTable jtblFeeRecord;
    private javax.swing.JTextField jtfSearchBox;
    private javax.swing.JLabel lbl_show;
    private javax.swing.JPanel panel_control;
    private javax.swing.JPanel panel_levelassignment;
    private javax.swing.JPanel panel_masterrecord;
    private javax.swing.JPanel panel_toppanel;
    // End of variables declaration//GEN-END:variables
}
