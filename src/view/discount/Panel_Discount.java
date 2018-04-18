
package view.discount;

import component_model_loader.SchoolYearJCompModelLoader;
import component_renderers.Renderer_SchoolYear_JComboBox;
import controller.discount.Controller_Display_DiscountCRUD_Dialog;
import controller.global.Controller_JTextField_ClearDefaultSearchText;
import daoimpl.DiscountDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.discount.Discount;
import model.schoolyear.SchoolYear;
import model.user.User;
import utility.initializer.Initializer;
import utility.jtable.JTableUtil;

/**
 *
 * @author Jordan
 */
public class Panel_Discount extends javax.swing.JPanel implements Initializer{

    private final SchoolYear currentSchoolYear;
    private final User user;
    
    private DiscountDaoImpl discountDaoImpl;
    private SchoolYearDaoImpl schoolYearDaoImpl;
    private SchoolYearJCompModelLoader schoolYearJCompModelLoader;
    
    public Panel_Discount(SchoolYear currentSchoolYear, User user) {
        initComponents();
        
        this.currentSchoolYear = currentSchoolYear;
        this.user = user;
        
        initDaoImpl();
        initJCompModelLoaders();
        initViewComponents();
        initRenderers();
        initControllers();
    }

    @Override
    public void initGridBagConstraints() {
    }

    @Override
    public void initJCompModelLoaders() {
        schoolYearJCompModelLoader = new SchoolYearJCompModelLoader();
    }

    @Override
    public void initRenderers() {
        jcmbSchoolYear.setRenderer(new Renderer_SchoolYear_JComboBox());
    }

    @Override
    public void initModels() {
    }

    @Override
    public void initViewComponents() {
        jcmbSchoolYear.setModel(schoolYearJCompModelLoader.getAllSchoolYear());
        jcmbSchoolYear.setSelectedItem(schoolYearDaoImpl.getCurrentSchoolYear());
        loadAllDiscounts();
        JTableUtil.applyCustomHeaderRenderer(jtblDiscountMasterList);
        JTableUtil.resizeColumnWidthsOf(jtblDiscountMasterList);
    }

    @Override
    public void initControllers() {
        jtfSearchBox.addMouseListener(new Controller_JTextField_ClearDefaultSearchText());
        jbtnCreateDiscount.addActionListener(new Controller_Display_DiscountCRUD_Dialog(this));
        jbtnEditDiscount.addActionListener(new Controller_Display_DiscountCRUD_Dialog(this));
    }

    @Override
    public void initDaoImpl() {
        schoolYearDaoImpl = new SchoolYearDaoImpl();
        discountDaoImpl = new DiscountDaoImpl();
    }
    
    public void loadAllDiscounts(){
        DefaultTableModel tableModel = (DefaultTableModel) jtblDiscountMasterList.getModel();
        tableModel.setRowCount(0);
        for(Discount d : discountDaoImpl.getAllDiscount()){
            Object[] rowData = {d.getDiscountID(),d.getDiscountName(),d.getPercent(),d.getProvision(),d.getDescription()};
            tableModel.addRow(rowData);
        }
        jtblDiscountMasterList.setModel(tableModel);
    }

    public SchoolYear getCurrentSchoolYear() {
        return currentSchoolYear;
    }

    public User getUser() {
        return user;
    }

    public JButton getJbtnCreateDiscount() {
        return jbtnCreateDiscount;
    }

    public JButton getJbtnEditDiscount() {
        return jbtnEditDiscount;
    }

    public JButton getJbtnSearch() {
        return jbtnSearch;
    }

    public JComboBox<String> getJcmbSchoolYear() {
        return jcmbSchoolYear;
    }

    public JLabel getJlblFilterBySchoolYear() {
        return jlblFilterBySchoolYear;
    }

    public JPanel getJpnlBody() {
        return jpnlBody;
    }

    public JPanel getJpnlHeader() {
        return jpnlHeader;
    }

    public JPanel getJpnlTop() {
        return jpnlTop;
    }

    public JTable getJtblDiscountMasterList() {
        return jtblDiscountMasterList;
    }

    public JTextField getJtfSearchBox() {
        return jtfSearchBox;
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jpnlTop = new javax.swing.JPanel();
        jpnlHeader = new javax.swing.JPanel();
        jbtnCreateDiscount = new javax.swing.JButton();
        jbtnEditDiscount = new javax.swing.JButton();
        jtfSearchBox = new javax.swing.JTextField();
        jbtnSearch = new javax.swing.JButton();
        jlblFilterBySchoolYear = new javax.swing.JLabel();
        jcmbSchoolYear = new javax.swing.JComboBox<>();
        jpnlBody = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblDiscountMasterList = new javax.swing.JTable();

        setLayout(new java.awt.GridBagLayout());

        jpnlTop.setLayout(new java.awt.GridBagLayout());

        jpnlHeader.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jpnlHeader.setLayout(new java.awt.GridBagLayout());

        jbtnCreateDiscount.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnCreateDiscount.setText("Create Discount");
        jbtnCreateDiscount.setActionCommand("create");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlHeader.add(jbtnCreateDiscount, gridBagConstraints);

        jbtnEditDiscount.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnEditDiscount.setText("Edit Discount");
        jbtnEditDiscount.setActionCommand("edit");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlHeader.add(jbtnEditDiscount, gridBagConstraints);

        jtfSearchBox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtfSearchBox.setText("Search here");
        jtfSearchBox.setMinimumSize(new java.awt.Dimension(150, 25));
        jtfSearchBox.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlHeader.add(jtfSearchBox, gridBagConstraints);

        jbtnSearch.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnSearch.setText("Search");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlHeader.add(jbtnSearch, gridBagConstraints);

        jlblFilterBySchoolYear.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblFilterBySchoolYear.setText("School Year ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlHeader.add(jlblFilterBySchoolYear, gridBagConstraints);

        jcmbSchoolYear.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcmbSchoolYear.setMinimumSize(new java.awt.Dimension(100, 25));
        jcmbSchoolYear.setPreferredSize(new java.awt.Dimension(100, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlHeader.add(jcmbSchoolYear, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlTop.add(jpnlHeader, gridBagConstraints);

        jpnlBody.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jpnlBody.setLayout(new java.awt.GridBagLayout());

        jtblDiscountMasterList.setAutoCreateRowSorter(true);
        jtblDiscountMasterList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtblDiscountMasterList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Discount Name", "Percentage", "Provision", "Description"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblDiscountMasterList.setRowHeight(30);
        jtblDiscountMasterList.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtblDiscountMasterList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlBody.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlTop.add(jpnlBody, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        add(jpnlTop, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnCreateDiscount;
    private javax.swing.JButton jbtnEditDiscount;
    private javax.swing.JButton jbtnSearch;
    private javax.swing.JComboBox<String> jcmbSchoolYear;
    private javax.swing.JLabel jlblFilterBySchoolYear;
    private javax.swing.JPanel jpnlBody;
    private javax.swing.JPanel jpnlHeader;
    private javax.swing.JPanel jpnlTop;
    private javax.swing.JTable jtblDiscountMasterList;
    private javax.swing.JTextField jtfSearchBox;
    // End of variables declaration//GEN-END:variables
}
