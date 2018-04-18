
package controller.feesetting;

import component_model_loader.FeeJCompModelLoader;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jordan
 */
public class DisplayFeeGradeLevelAssignmentOnKeyPress implements KeyListener{

    private JTable jtblFeeRecord;
    private JTable jtblFeeGradeLevelAssignment;
    private FeeJCompModelLoader feeJCompModelLoader;
    
    public DisplayFeeGradeLevelAssignmentOnKeyPress(JTable jtblFeeRecord,JTable jtblFeeGradeLevelAssignment){
        feeJCompModelLoader = new FeeJCompModelLoader();
        this.jtblFeeRecord = jtblFeeRecord;
        this.jtblFeeGradeLevelAssignment = jtblFeeGradeLevelAssignment;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP) {
            if (jtblFeeRecord.getSelectedRow() > -1) {
                DefaultTableModel tableModel
                        = feeJCompModelLoader.getFeeGradeLevelAssignmentAndAmountById(jtblFeeRecord, jtblFeeGradeLevelAssignment);
                jtblFeeGradeLevelAssignment.setModel(tableModel);
            }
        }
    }
    
}
