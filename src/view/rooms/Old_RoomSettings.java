
package view.rooms;

import daoimpl.RoomDaoImpl;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.room.Room;

public class Old_RoomSettings extends javax.swing.JPanel {

    public Old_RoomSettings() {
        initComponents();
        loadRoomsTOJtable();
        
    }

   
   
    @SuppressWarnings("unchecked")
    
    
  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jtf_roomName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtf_buildingName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtf_capacity = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        addRoom = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbl_getRoomInfo = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel4.setPreferredSize(new java.awt.Dimension(400, 240));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Room Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trebuchet MS", 0, 14))); // NOI18N
        jPanel6.setPreferredSize(new java.awt.Dimension(392, 154));
        jPanel6.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel3.setText("Room Number /  Name:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 3, 3, 3);
        jPanel6.add(jLabel3, gridBagConstraints);

        jtf_roomName.setPreferredSize(new java.awt.Dimension(140, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 3, 3, 3);
        jPanel6.add(jtf_roomName, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Building Number / Name:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel6.add(jLabel4, gridBagConstraints);

        jtf_buildingName.setPreferredSize(new java.awt.Dimension(140, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel6.add(jtf_buildingName, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel5.setText("Capacity:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel6.add(jLabel5, gridBagConstraints);

        jtf_capacity.setPreferredSize(new java.awt.Dimension(140, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel6.add(jtf_capacity, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(jPanel6, gridBagConstraints);

        jPanel7.setPreferredSize(new java.awt.Dimension(392, 70));
        jPanel7.setLayout(new java.awt.GridBagLayout());

        jButton1.setText("Cancel");
        jButton1.setPreferredSize(new java.awt.Dimension(100, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel7.add(jButton1, gridBagConstraints);

        addRoom.setText("ADD");
        addRoom.setPreferredSize(new java.awt.Dimension(100, 30));
        addRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRoomActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel7.add(addRoom, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(jPanel7, gridBagConstraints);

        jPanel2.add(jPanel4, new java.awt.GridBagConstraints());

        jTabbedPane1.addTab("Create Rooms", jPanel2);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel8.setLayout(new java.awt.GridBagLayout());

        jPanel9.setPreferredSize(new java.awt.Dimension(985, 40));
        jPanel9.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel1.setText("Search:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel9.add(jLabel1, gridBagConstraints);

        search.setPreferredSize(new java.awt.Dimension(140, 30));
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel9.add(search, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel8.add(jPanel9, gridBagConstraints);

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.setPreferredSize(new java.awt.Dimension(985, 480));
        jPanel10.setLayout(new java.awt.BorderLayout());

        jPanel11.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setBorder(null);

        jtbl_getRoomInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Room ID", "Room Name/Number", "Building Name/Number", "Capacity", "Status", "DateCreated"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtbl_getRoomInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbl_getRoomInfoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtbl_getRoomInfo);
        if (jtbl_getRoomInfo.getColumnModel().getColumnCount() > 0) {
            jtbl_getRoomInfo.getColumnModel().getColumn(0).setResizable(false);
            jtbl_getRoomInfo.getColumnModel().getColumn(1).setResizable(false);
            jtbl_getRoomInfo.getColumnModel().getColumn(2).setResizable(false);
            jtbl_getRoomInfo.getColumnModel().getColumn(3).setResizable(false);
            jtbl_getRoomInfo.getColumnModel().getColumn(4).setResizable(false);
            jtbl_getRoomInfo.getColumnModel().getColumn(5).setResizable(false);
        }

        jPanel11.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jScrollPane1.setViewportView(jPanel11);

        jPanel10.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel8.add(jPanel10, gridBagConstraints);

        jPanel3.add(jPanel8, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("View Rooms", jPanel3);

        jPanel1.add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

     private void search(String query){
        DefaultTableModel dtm = (DefaultTableModel)jtbl_getRoomInfo.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(dtm);
        jtbl_getRoomInfo.setRowSorter(tr);
        
        tr.setRowFilter(RowFilter.regexFilter(query));
    
    }
    
    public void loadRoomsTOJtable(){
        RoomDaoImpl rdi = new RoomDaoImpl();
        Object[]roomData = rdi.getAllRoomInfo().toArray();
        DefaultTableModel dtm = (DefaultTableModel)jtbl_getRoomInfo.getModel();
        
        dtm.setRowCount(0);
        for(Object room : roomData)
        {   
           Room r = (Room)room;
           Object[] o = 
           {
               r.getRoomID(),
               r.getRoomName(),
               r.getBuildingName(),
               r.getCapacity(),
               r.getStatus()==true?"Active":"Inactive" ,  
               r.getDateCreated(),
                        
           };
          dtm.addRow(o);
        }
    }
    
    private void jtbl_getRoomInfoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbl_getRoomInfoMouseClicked
        if (evt.getClickCount() == 2) {
            int selectedRow = jtbl_getRoomInfo.getSelectedRow();
            String ValueOfFirstColumn = jtbl_getRoomInfo.getValueAt(selectedRow, 0).toString();
            int RoomID = Integer.parseInt(ValueOfFirstColumn);
            Old_UpdateRoom updateRoom = new Old_UpdateRoom(null, true, RoomID);
            updateRoom.setLocationRelativeTo(null);
            updateRoom.pack();
            updateRoom.setVisible(true);
        }
    }//GEN-LAST:event_jtbl_getRoomInfoMouseClicked

    private void addRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRoomActionPerformed
        
        Room room = new Room();
        room.setRoomName(jtf_roomName.getText().trim());
        room.setBuildingName(jtf_buildingName.getText().trim());
        room.setCapacity(jtf_capacity.getText().trim());
        room.setStatus(true);
        
        int addRoom = JOptionPane.showConfirmDialog(null, "Submit new Room?","SUBMIT",JOptionPane.YES_NO_OPTION);
        
        if(addRoom == JOptionPane.YES_OPTION)
        {
            RoomDaoImpl rdi = new RoomDaoImpl();
            boolean isAdded = rdi.addRoom(room);
           if (isAdded) {

                jtf_roomName.setText("");
                jtf_buildingName.setText("");
                jtf_capacity.setText("");
               
                loadRoomsTOJtable();
                JOptionPane.showMessageDialog(null, "Successfully added");
                

            } else {
                JOptionPane.showMessageDialog(null, "Error occured during Adding ");
            }
        }         
    }//GEN-LAST:event_addRoomActionPerformed

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        String query = search.getText().toLowerCase().toUpperCase();
           search(query); 
    }//GEN-LAST:event_searchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addRoom;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    public static javax.swing.JTable jtbl_getRoomInfo;
    private javax.swing.JTextField jtf_buildingName;
    private javax.swing.JTextField jtf_capacity;
    private javax.swing.JTextField jtf_roomName;
    private javax.swing.JTextField search;
    // End of variables declaration//GEN-END:variables
}
