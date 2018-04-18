package view.rooms;

import component_model_loader.RoomJCompModelLoader;
import controller.global.Controller_JButton_ExitJDialog;
import controller.room.CreateRoom;
import controller.room.EditRoom;
import daoimpl.RoomDaoImpl;
import model.room.Room;
import utility.initializer.Initializer;

public class Dialog_RoomCreate extends javax.swing.JDialog implements Initializer{
    
    private final String action;
    private int roomIdOfSelected;
    private RoomJCompModelLoader roomJCompModelLoader;
    private  RoomDaoImpl roomDaoImpl;
    
    
        public Dialog_RoomCreate(java.awt.Frame parent, boolean modal,String action) {
        super(parent, modal);
        this.action = action;
        initComponents();
        
        initJCompModelLoaders();
        initRenderers();
        initModels();
        initViewComponents();
        initControllers();
        initDaoImpl();
    }
     public Dialog_RoomCreate(java.awt.Frame parent, boolean modal,String action , int roomIdOfSelected) {
        super(parent, modal);
        
       this.action = action;
       this.roomIdOfSelected = roomIdOfSelected;
       initComponents();
        
        initJCompModelLoaders();
        initRenderers();
        initModels();
        initViewComponents();
        initControllers();
        initDaoImpl();
        
        initForm();
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
        if(action.equalsIgnoreCase("create")){
           lbl_roomstatus.setVisible(false);
           jcmbStatus.setVisible(false);
        }else if(action.equalsIgnoreCase("edit")){
        }else if(action.equalsIgnoreCase("view")){
           jtfRoomName.setVisible(false);
           jtfBldgName.setVisible(false);
           jtfCapacity.setVisible(false);
           jtaNotes.setVisible(false);
           jbtnClear.setVisible(false);
    }
       
        
    }

    @Override
    public void initControllers() {
        
        jbtnCancel.addActionListener(new Controller_JButton_ExitJDialog(this));
            if(action.equalsIgnoreCase("create")){
               jbtnSave.addActionListener(new CreateRoom(this, jtfRoomName, 
                       jtfBldgName, jtfCapacity, 
                       jcmbStatus, jtaNotes));
            }else if(action.equalsIgnoreCase("edit"))
                jbtnSave.addActionListener(new EditRoom(roomIdOfSelected, jtfRoomName,
                        jtfBldgName, jcmbStatus, 
                        jtfCapacity, jtaNotes, this));
      
    }

    @Override
    public void initDaoImpl() {
        
        roomDaoImpl = new RoomDaoImpl();
      
    }
    
    private void initForm(){
        
        Room room = roomDaoImpl.getRoomById(roomIdOfSelected);
        jtfRoomName.setText(room.getRoomName());
        jtfBldgName.setText(room.getBuildingName());
        jtfCapacity.setText(room.getCapacity());
        jtaNotes.setText(room.getDescription());
        jcmbStatus.setSelectedItem(room.getStatus()==true?"Yes":"No");
        
    
    }
    
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel_toppanel = new javax.swing.JPanel();
        panel_roomdetails = new javax.swing.JPanel();
        lbl_roomname = new javax.swing.JLabel();
        jtfRoomName = new javax.swing.JTextField();
        lbl_roombldg = new javax.swing.JLabel();
        jtfBldgName = new javax.swing.JTextField();
        lbl_roomstatus = new javax.swing.JLabel();
        jcmbStatus = new javax.swing.JComboBox<>();
        lbl_capacity = new javax.swing.JLabel();
        jtfCapacity = new javax.swing.JTextField();
        panel_roomnotes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaNotes = new javax.swing.JTextArea();
        panel_footer = new javax.swing.JPanel();
        jbtnCancel = new javax.swing.JButton();
        jbtnClear = new javax.swing.JButton();
        jbtnSave = new javax.swing.JButton();
        jbtnSaveAndNew = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create New Room");
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        panel_toppanel.setMinimumSize(new java.awt.Dimension(555, 380));
        panel_toppanel.setPreferredSize(new java.awt.Dimension(555, 380));
        panel_toppanel.setLayout(new java.awt.GridBagLayout());

        panel_roomdetails.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Room Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_roomdetails.setMinimumSize(new java.awt.Dimension(550, 100));
        panel_roomdetails.setPreferredSize(new java.awt.Dimension(550, 100));
        panel_roomdetails.setLayout(new java.awt.GridBagLayout());

        lbl_roomname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_roomname.setText("Room Name/Number :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        panel_roomdetails.add(lbl_roomname, gridBagConstraints);

        jtfRoomName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtfRoomName.setMinimumSize(new java.awt.Dimension(80, 25));
        jtfRoomName.setPreferredSize(new java.awt.Dimension(80, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 0);
        panel_roomdetails.add(jtfRoomName, gridBagConstraints);

        lbl_roombldg.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_roombldg.setText("Bldg Name/Number :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 10, 0);
        panel_roomdetails.add(lbl_roombldg, gridBagConstraints);

        jtfBldgName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtfBldgName.setMinimumSize(new java.awt.Dimension(80, 25));
        jtfBldgName.setPreferredSize(new java.awt.Dimension(80, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 30);
        panel_roomdetails.add(jtfBldgName, gridBagConstraints);

        lbl_roomstatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_roomstatus.setText("Active :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        panel_roomdetails.add(lbl_roomstatus, gridBagConstraints);

        jcmbStatus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Yes", "No" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        panel_roomdetails.add(jcmbStatus, gridBagConstraints);

        lbl_capacity.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_capacity.setText("Capacity :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        panel_roomdetails.add(lbl_capacity, gridBagConstraints);

        jtfCapacity.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtfCapacity.setMinimumSize(new java.awt.Dimension(80, 25));
        jtfCapacity.setPreferredSize(new java.awt.Dimension(80, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 5);
        panel_roomdetails.add(jtfCapacity, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 3);
        panel_toppanel.add(panel_roomdetails, gridBagConstraints);

        panel_roomnotes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Notes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_roomnotes.setMinimumSize(new java.awt.Dimension(550, 170));
        panel_roomnotes.setPreferredSize(new java.awt.Dimension(550, 170));
        panel_roomnotes.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(530, 140));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(530, 140));

        jtaNotes.setColumns(20);
        jtaNotes.setRows(5);
        jtaNotes.setMinimumSize(new java.awt.Dimension(530, 140));
        jtaNotes.setPreferredSize(new java.awt.Dimension(530, 140));
        jScrollPane1.setViewportView(jtaNotes);

        panel_roomnotes.add(jScrollPane1, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        panel_toppanel.add(panel_roomnotes, gridBagConstraints);

        panel_footer.setMinimumSize(new java.awt.Dimension(550, 50));
        panel_footer.setPreferredSize(new java.awt.Dimension(550, 50));
        panel_footer.setLayout(new java.awt.GridBagLayout());

        jbtnCancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnCancel.setText("Cancel");
        jbtnCancel.setActionCommand("cancel");
        jbtnCancel.setMaximumSize(new java.awt.Dimension(80, 40));
        jbtnCancel.setMinimumSize(new java.awt.Dimension(80, 40));
        jbtnCancel.setPreferredSize(new java.awt.Dimension(80, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_footer.add(jbtnCancel, gridBagConstraints);

        jbtnClear.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnClear.setText("Clear");
        jbtnClear.setActionCommand("clear");
        jbtnClear.setMaximumSize(new java.awt.Dimension(80, 40));
        jbtnClear.setMinimumSize(new java.awt.Dimension(80, 40));
        jbtnClear.setPreferredSize(new java.awt.Dimension(80, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        panel_footer.add(jbtnClear, gridBagConstraints);

        jbtnSave.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnSave.setText("Save");
        jbtnSave.setActionCommand("save");
        jbtnSave.setMaximumSize(new java.awt.Dimension(80, 40));
        jbtnSave.setMinimumSize(new java.awt.Dimension(80, 40));
        jbtnSave.setPreferredSize(new java.awt.Dimension(80, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        panel_footer.add(jbtnSave, gridBagConstraints);

        jbtnSaveAndNew.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnSaveAndNew.setText("Save & New");
        jbtnSaveAndNew.setActionCommand("save_and_new");
        jbtnSaveAndNew.setMaximumSize(new java.awt.Dimension(120, 40));
        jbtnSaveAndNew.setMinimumSize(new java.awt.Dimension(120, 40));
        jbtnSaveAndNew.setPreferredSize(new java.awt.Dimension(120, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        panel_footer.add(jbtnSaveAndNew, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 30, 0);
        panel_toppanel.add(panel_footer, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        getContentPane().add(panel_toppanel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcmbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmbStatusActionPerformed

    /**
     * @param args the command line arguments
     */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnCancel;
    private javax.swing.JButton jbtnClear;
    private javax.swing.JButton jbtnSave;
    private javax.swing.JButton jbtnSaveAndNew;
    private javax.swing.JComboBox<String> jcmbStatus;
    private javax.swing.JTextArea jtaNotes;
    private javax.swing.JTextField jtfBldgName;
    private javax.swing.JTextField jtfCapacity;
    private javax.swing.JTextField jtfRoomName;
    private javax.swing.JLabel lbl_capacity;
    private javax.swing.JLabel lbl_roombldg;
    private javax.swing.JLabel lbl_roomname;
    private javax.swing.JLabel lbl_roomstatus;
    private javax.swing.JPanel panel_footer;
    private javax.swing.JPanel panel_roomdetails;
    private javax.swing.JPanel panel_roomnotes;
    private javax.swing.JPanel panel_toppanel;
    // End of variables declaration//GEN-END:variables
}
