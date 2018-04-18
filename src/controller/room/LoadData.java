
package controller.room;

import javax.swing.JTable;
import javax.swing.JTextField;
import component_model_loader.RoomJCompModelLoader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Paul
 */
public class LoadData implements ActionListener{
    
    private JTable jtblRoomMasterList;
    private JTextField jtfSearchBox;
    
    private RoomJCompModelLoader roomJCompModelLoader;
    
    public LoadData(JTable jtblRoomMasterList, JTextField jtfSearchBox ){
        roomJCompModelLoader = new RoomJCompModelLoader();
        this.jtblRoomMasterList = jtblRoomMasterList;
        this.jtfSearchBox = jtfSearchBox;
        
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        loadToTable();
       
    }
    
    private void loadToTable(){
        if(jtfSearchBox.getText().isEmpty()){
            DefaultTableModel tableModel = roomJCompModelLoader.getAllRoomsInfo(jtblRoomMasterList);
            jtblRoomMasterList.setModel(tableModel);
        }else{ DefaultTableModel tableModel = roomJCompModelLoader.getAllRoomsInfoByWildCard(jtfSearchBox, jtblRoomMasterList);
            jtblRoomMasterList.setModel(tableModel);
        }
    }
    
}
