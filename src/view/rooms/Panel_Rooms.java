
package view.rooms;

import component_model_loader.RoomJCompModelLoader;
import controller.global.Controller_JTextField_ClearDefaultSearchText;
import controller.room.DisplayCreateRoomDialog;
import controller.room.LoadData;
import utility.initializer.Initializer;


public class Panel_Rooms extends javax.swing.JPanel implements Initializer {

    private RoomJCompModelLoader roomJCompModelLoader;
   
    public Panel_Rooms() {
        initComponents();
        
        initJCompModelLoaders();
        initRenderers();
        initModels();
        initViewComponents();
        initControllers();
        initDaoImpl();
    }

    @Override
    public void initRenderers() {
    }
    
    @Override
    public void initGridBagConstraints() {
         
    }

    @Override
    public void initJCompModelLoaders() {
        
        roomJCompModelLoader = new RoomJCompModelLoader();
        
    }

    @Override
    public void initModels() {
         
    }

    @Override
    public void initViewComponents() {
        jtblRoomMasterList.setModel(roomJCompModelLoader.getAllRoomsInfo(jtblRoomMasterList));
        
    }

    @Override
    public void initControllers() {
        jtfSearchBox.addMouseListener(new Controller_JTextField_ClearDefaultSearchText());
        jbtnSearch.addActionListener(new LoadData(jtblRoomMasterList,jtfSearchBox));
        jbtnCreateRoom.addActionListener(new DisplayCreateRoomDialog(jtblRoomMasterList,jbtnCreateRoom.getActionCommand()));
        jbtnEdit.addActionListener(new DisplayCreateRoomDialog(jtblRoomMasterList, jbtnEdit.getActionCommand()));
        jbtnView.addActionListener(new DisplayCreateRoomDialog(jtblRoomMasterList,jbtnView.getActionCommand()));
    }

    @Override
    public void initDaoImpl() {
       
    }
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel_toppanel = new javax.swing.JPanel();
        panel_control = new javax.swing.JPanel();
        jbtnCreateRoom = new javax.swing.JButton();
        jbtnEdit = new javax.swing.JButton();
        jbtnView = new javax.swing.JButton();
        jbtnPrint = new javax.swing.JButton();
        jtfSearchBox = new javax.swing.JTextField();
        jbtnSearch = new javax.swing.JButton();
        lbl_show = new javax.swing.JLabel();
        combo_filter = new javax.swing.JComboBox<>();
        panel_masterrecord = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblRoomMasterList = new javax.swing.JTable();

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

        jbtnCreateRoom.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnCreateRoom.setText("Create New Room");
        jbtnCreateRoom.setActionCommand("create");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        panel_control.add(jbtnCreateRoom, gridBagConstraints);

        jbtnEdit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnEdit.setText("Edit");
        jbtnEdit.setActionCommand("edit");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        panel_control.add(jbtnEdit, gridBagConstraints);

        jbtnView.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnView.setText("View");
        jbtnView.setActionCommand("view");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        panel_control.add(jbtnView, gridBagConstraints);

        jbtnPrint.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnPrint.setText("Print");
        jbtnPrint.setActionCommand("print");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 60);
        panel_control.add(jbtnPrint, gridBagConstraints);

        jtfSearchBox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtfSearchBox.setText("Search here");
        jtfSearchBox.setMinimumSize(new java.awt.Dimension(150, 25));
        jtfSearchBox.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 300, 0, 0);
        panel_control.add(jtfSearchBox, gridBagConstraints);

        jbtnSearch.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnSearch.setText("Search");
        jbtnSearch.setActionCommand("search");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        panel_control.add(jbtnSearch, gridBagConstraints);

        lbl_show.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_show.setText("Show  :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        panel_control.add(lbl_show, gridBagConstraints);

        combo_filter.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        combo_filter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        combo_filter.setMinimumSize(new java.awt.Dimension(80, 25));
        combo_filter.setPreferredSize(new java.awt.Dimension(80, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 100);
        panel_control.add(combo_filter, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toppanel.add(panel_control, gridBagConstraints);

        panel_masterrecord.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rooms Master List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_masterrecord.setMinimumSize(new java.awt.Dimension(1200, 555));
        panel_masterrecord.setPreferredSize(new java.awt.Dimension(1200, 555));
        panel_masterrecord.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane1.setMinimumSize(new java.awt.Dimension(1185, 530));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1185, 530));

        jtblRoomMasterList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtblRoomMasterList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Room ID", "Name/Number", "Bldg. No./Name", "Capacity", "Status", "Date Created", "Notes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblRoomMasterList.setMinimumSize(new java.awt.Dimension(1185, 10000));
        jtblRoomMasterList.setPreferredSize(new java.awt.Dimension(1185, 10000));
        jtblRoomMasterList.setRowHeight(20);
        jtblRoomMasterList.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtblRoomMasterList);

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

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        add(panel_toppanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnCreateRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCreateRoomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnCreateRoomActionPerformed

    private void jbtnCreateRoomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnCreateRoomMouseClicked
      
    }//GEN-LAST:event_jbtnCreateRoomMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> combo_filter;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnCreateRoom;
    private javax.swing.JButton jbtnEdit;
    private javax.swing.JButton jbtnPrint;
    private javax.swing.JButton jbtnSearch;
    private javax.swing.JButton jbtnView;
    private javax.swing.JTable jtblRoomMasterList;
    private javax.swing.JTextField jtfSearchBox;
    private javax.swing.JLabel lbl_show;
    private javax.swing.JPanel panel_control;
    private javax.swing.JPanel panel_masterrecord;
    private javax.swing.JPanel panel_toppanel;
    // End of variables declaration//GEN-END:variables
}
