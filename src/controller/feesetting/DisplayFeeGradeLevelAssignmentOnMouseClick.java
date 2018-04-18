package controller.feesetting;

import component_model_loader.FeeJCompModelLoader;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jordan
 */
public class DisplayFeeGradeLevelAssignmentOnMouseClick implements MouseListener {

    private JTable jtblFeeRecord;
    private JTable jtblFeeGradeLevelAssignment;
    private FeeJCompModelLoader feeJCompModelLoader;
    
    public DisplayFeeGradeLevelAssignmentOnMouseClick(JTable jtblFeeRecord,JTable jtblFeeGradeLevelAssignment){
        feeJCompModelLoader = new FeeJCompModelLoader();
        this.jtblFeeRecord = jtblFeeRecord;
        this.jtblFeeGradeLevelAssignment = jtblFeeGradeLevelAssignment;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (jtblFeeRecord.getSelectedRow() > -1) {
            DefaultTableModel tableModel = 
                    feeJCompModelLoader.getFeeGradeLevelAssignmentAndAmountById(jtblFeeRecord,jtblFeeGradeLevelAssignment);
            jtblFeeGradeLevelAssignment.setModel(tableModel);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
