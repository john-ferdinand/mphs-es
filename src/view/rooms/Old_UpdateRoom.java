 
package view.rooms;

import daoimpl.RoomDaoImpl;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.room.Room;

 
public class Old_UpdateRoom extends javax.swing.JDialog {

    private int aRoomID;
    public Old_UpdateRoom(java.awt.Frame parent, boolean modal,int RoomID) {
        super(parent, modal);
        this.aRoomID = RoomID;
        initComponents();
        loadToRooms();
    }
    
    
    public void loadToRooms() {
    
        RoomDaoImpl rdi = new RoomDaoImpl();
        Room room = rdi.getRoomById(this.aRoomID);
        
        jtf_roomName.setText(room.getRoomName());
        jtf_buildingName.setText(room.getBuildingName());
        jtf_capacity.setText(room.getCapacity());
        
        if(room.getStatus()==true)
        {
            cb_status.setSelected(room.setStatus(true));
        }
    
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jtf_roomName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtf_buildingName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtf_capacity = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cb_status = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        addRoom = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setPreferredSize(new java.awt.Dimension(400, 246));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Room Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trebuchet MS", 0, 14))); // NOI18N
        jPanel6.setPreferredSize(new java.awt.Dimension(392, 160));
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
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel6.add(jtf_capacity, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel1.setText("Status:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel6.add(jLabel1, gridBagConstraints);

        cb_status.setText("Active");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel6.add(cb_status, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel4.add(jPanel6, gridBagConstraints);

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.setPreferredSize(new java.awt.Dimension(392, 70));
        jPanel7.setLayout(new java.awt.GridBagLayout());

        jButton1.setText("Cancel");
        jButton1.setPreferredSize(new java.awt.Dimension(100, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel7.add(jButton1, gridBagConstraints);

        addRoom.setText("Update");
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

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRoomActionPerformed

        RoomDaoImpl rdi = new RoomDaoImpl();
        String roomName = jtf_roomName.getText().trim();
        String buildingName = jtf_buildingName.getText().trim();
        String capacity = jtf_capacity.getText().trim();
        
        int roomID = this.aRoomID;
        
        Room room = new Room();
        
        room.setRoomID(roomID);
        room.setRoomName(roomName);
        room.setBuildingName(buildingName);
        room.setCapacity(capacity);
        
        boolean isActive;
        if(cb_status.isSelected())
        {
            isActive = true;
        }
        else
        {
            isActive = false;
        }
        room.setStatus(isActive);
        
        int update = JOptionPane.showConfirmDialog(null,"Update Rooms?","SUBMIT",JOptionPane.YES_NO_OPTION);
        if(update== JOptionPane.YES_OPTION)
        {
            boolean isUpdated = rdi.updateRoom(room);
            if(isUpdated)
            {
                
                jtf_roomName.setText("");
                jtf_buildingName.setText("");
                jtf_capacity.setText("");
                JOptionPane.showMessageDialog(null, "Updated Successfully");
                loadToRooms();
                
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Error Occured during updating");
        }
        
    }//GEN-LAST:event_addRoomActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addRoom;
    private javax.swing.JCheckBox cb_status;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public static javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JTextField jtf_buildingName;
    private javax.swing.JTextField jtf_capacity;
    private javax.swing.JTextField jtf_roomName;
    // End of variables declaration//GEN-END:variables
}
