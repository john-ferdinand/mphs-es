package view.payment;

import controller.payment.Controller_Dialog_AddDiscount_ApplyDiscount_JButton;
import daoimpl.DiscountDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import model.discount.Discount;
import utility.initializer.Initializer;
import utility.jtable.JTableUtil;

/**
 *
 * @author Jordan
 */
public class Dialog_AddDiscount extends javax.swing.JDialog implements Initializer {

    private DiscountDaoImpl discountDaoImpl;
    private final Panel_Payment panelPayment;

    public Dialog_AddDiscount(java.awt.Frame parent, boolean modal, Panel_Payment panelPayment) {
        super(parent, modal);
        initComponents();
        this.panelPayment = panelPayment;
        initDaoImpl();
        initJCompModelLoaders();
        initRenderers();
        initViewComponents();
        initControllers();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jpnlHeader = new javax.swing.JPanel();
        jpnlBody = new javax.swing.JPanel();
        jpnlDiscountsList = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblDiscountList = new javax.swing.JTable();
        jpnlDiscountsApplied = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblAppliedDiscount = new javax.swing.JTable();
        jpnlDiscountControl = new javax.swing.JPanel();
        jbtnMoveToAppliedDiscount = new javax.swing.JButton();
        jbtnRemoveFromAppliedDiscount = new javax.swing.JButton();
        jpnlSummary = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfTotalDiscountPercent = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jpnlFooter = new javax.swing.JPanel();
        jbtnApplyDiscount = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Apply Discount");
        setMinimumSize(new java.awt.Dimension(1070, 400));
        setPreferredSize(new java.awt.Dimension(1070, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jpnlHeader.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jpnlHeaderLayout = new javax.swing.GroupLayout(jpnlHeader);
        jpnlHeader.setLayout(jpnlHeaderLayout);
        jpnlHeaderLayout.setHorizontalGroup(
            jpnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1081, Short.MAX_VALUE)
        );
        jpnlHeaderLayout.setVerticalGroup(
            jpnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jpnlHeader, gridBagConstraints);

        jpnlBody.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jpnlBody.setLayout(new java.awt.GridBagLayout());

        jpnlDiscountsList.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Discounts List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jpnlDiscountsList.setLayout(new java.awt.GridBagLayout());

        jtblDiscountList.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtblDiscountList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Discount ", "%", "Description", "Provision", "Active"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblDiscountList.setRowHeight(30);
        jtblDiscountList.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtblDiscountList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlDiscountsList.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlBody.add(jpnlDiscountsList, gridBagConstraints);

        jpnlDiscountsApplied.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Applied Discount", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jpnlDiscountsApplied.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jpnlDiscountsApplied.setLayout(new java.awt.GridBagLayout());

        jtblAppliedDiscount.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtblAppliedDiscount.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Discount ", "%", "Description", "Provision", "Active"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblAppliedDiscount.setRowHeight(30);
        jtblAppliedDiscount.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jtblAppliedDiscount);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlDiscountsApplied.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlBody.add(jpnlDiscountsApplied, gridBagConstraints);

        jpnlDiscountControl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jpnlDiscountControl.setLayout(new java.awt.GridBagLayout());

        jbtnMoveToAppliedDiscount.setText(">");
        jpnlDiscountControl.add(jbtnMoveToAppliedDiscount, new java.awt.GridBagConstraints());

        jbtnRemoveFromAppliedDiscount.setText("<");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlDiscountControl.add(jbtnRemoveFromAppliedDiscount, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 6);
        jpnlBody.add(jpnlDiscountControl, gridBagConstraints);

        jpnlSummary.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Total Discount % Applied :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlSummary.add(jLabel1, gridBagConstraints);

        jtfTotalDiscountPercent.setColumns(2);
        jtfTotalDiscountPercent.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtfTotalDiscountPercent.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jtfTotalDiscountPercent.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlSummary.add(jtfTotalDiscountPercent, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("%");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlSummary.add(jLabel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlBody.add(jpnlSummary, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jpnlBody, gridBagConstraints);

        jpnlFooter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jpnlFooter.setLayout(new java.awt.GridBagLayout());

        jbtnApplyDiscount.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbtnApplyDiscount.setText("Apply Discount");
        jbtnApplyDiscount.setActionCommand("applydiscount");
        jbtnApplyDiscount.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlFooter.add(jbtnApplyDiscount, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jpnlFooter, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void initGridBagConstraints() {
    }

    @Override
    public void initJCompModelLoaders() {
    }

    @Override
    public void initRenderers() {
    }

    @Override
    public void initModels() {
    }

    @Override
    public void initViewComponents() {
        loadDiscountsList();
        loadAppliedDiscounts();
        JTableUtil.applyCustomHeaderRenderer(jtblDiscountList);
        JTableUtil.applyCustomHeaderRenderer(jtblAppliedDiscount);
        JTableUtil.resizeColumnWidthsOf(jtblDiscountList);
        JTableUtil.resizeColumnWidthsOf(jtblAppliedDiscount);
    }

    @Override
    public void initControllers() {
        jbtnMoveToAppliedDiscount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jtblDiscountList.getRowCount() > 0) {
                    JTableUtil.moveRowData(jtblDiscountList, jtblAppliedDiscount);
                } else {
                    JOptionPane.showMessageDialog(null, "Nothing to move.");
                }
            }
        });
        jbtnRemoveFromAppliedDiscount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == jbtnRemoveFromAppliedDiscount) {
                    if (jtblAppliedDiscount.getRowCount() > 0 && jtblAppliedDiscount.getSelectedRowCount() > 0) {
                        JTableUtil.moveRowData(jtblAppliedDiscount, jtblDiscountList);
                    } else {
                        JOptionPane.showMessageDialog(null, "Nothing is selected.");
                    }
                }
            }
        });
        jtblAppliedDiscount.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int totalPercent = 0;
                for (int row = 0; row < jtblAppliedDiscount.getRowCount(); row++) {
                    if (jtblAppliedDiscount.getValueAt(row, 2) != null) {
                        totalPercent += Integer.parseInt(jtblAppliedDiscount.getValueAt(row, 2).toString().trim());
                    }
                }
                jtfTotalDiscountPercent.setText("" + totalPercent);
                jbtnApplyDiscount.setEnabled(jtblAppliedDiscount.getRowCount() > 0);
                JTableUtil.resizeColumnWidthsOf(jtblDiscountList);
                JTableUtil.resizeColumnWidthsOf(jtblAppliedDiscount);
            }
        });
        jbtnApplyDiscount.addActionListener(new Controller_Dialog_AddDiscount_ApplyDiscount_JButton(panelPayment, this));
    }

    @Override
    public void initDaoImpl() {
        discountDaoImpl = new DiscountDaoImpl();
    }

    private void loadDiscountsList() {
        DefaultTableModel tableModel = (DefaultTableModel) jtblDiscountList.getModel();
        tableModel.setRowCount(0);
        List<Discount> discounts = discountDaoImpl.getAllDiscount();
        for (Discount d : discounts) {
            Object[] rowData = {
                d.getDiscountID(), d.getDiscountName(), d.getPercent(),
                d.getDescription(), d.getProvision(), d.getIsActive() == true ? "Yes" : "No"
            };
            tableModel.addRow(rowData);
        }
        jtblDiscountList.setModel(tableModel);
    }
    
    private void loadAppliedDiscounts(){
        if(panelPayment.getIsStudent()){
            loadForStudent();
        }else{
        }
    }
    
    private void loadForStudent(){
        
    }

    public JButton getJbtnApplyDiscount() {
        return jbtnApplyDiscount;
    }

    public JButton getJbtnMoveToAppliedDiscount() {
        return jbtnMoveToAppliedDiscount;
    }

    public JButton getJbtnRemoveFromAppliedDiscount() {
        return jbtnRemoveFromAppliedDiscount;
    }

    public JPanel getJpnlBody() {
        return jpnlBody;
    }

    public JPanel getJpnlDiscountControl() {
        return jpnlDiscountControl;
    }

    public JPanel getJpnlDiscountsApplied() {
        return jpnlDiscountsApplied;
    }

    public JPanel getJpnlDiscountsList() {
        return jpnlDiscountsList;
    }

    public JPanel getJpnlFooter() {
        return jpnlFooter;
    }

    public JPanel getJpnlHeader() {
        return jpnlHeader;
    }

    public JTable getJtblAppliedDiscount() {
        return jtblAppliedDiscount;
    }

    public JTable getJtblDiscountList() {
        return jtblDiscountList;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtnApplyDiscount;
    private javax.swing.JButton jbtnMoveToAppliedDiscount;
    private javax.swing.JButton jbtnRemoveFromAppliedDiscount;
    private javax.swing.JPanel jpnlBody;
    private javax.swing.JPanel jpnlDiscountControl;
    private javax.swing.JPanel jpnlDiscountsApplied;
    private javax.swing.JPanel jpnlDiscountsList;
    private javax.swing.JPanel jpnlFooter;
    private javax.swing.JPanel jpnlHeader;
    private javax.swing.JPanel jpnlSummary;
    private javax.swing.JTable jtblAppliedDiscount;
    private javax.swing.JTable jtblDiscountList;
    private javax.swing.JTextField jtfTotalDiscountPercent;
    // End of variables declaration//GEN-END:variables
}
