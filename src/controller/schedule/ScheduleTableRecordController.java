/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.schedule;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTable;

/**
 *
 * @author John Ferdinand Antonio
 */
public class ScheduleTableRecordController implements MouseListener{

    private JTable jtblScheduleRecord;
    
    public ScheduleTableRecordController(JTable jtblScheduleRecord){
        this.jtblScheduleRecord = jtblScheduleRecord;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
            if(e.getClickCount() == 2){
                int row = jtblScheduleRecord.getSelectedRow();
                int col = 0;
                int scheduleId = Integer.parseInt(jtblScheduleRecord.getValueAt(row, col).toString());
//                JOptionPane.showMessageDialog(null,"Clicked");
            }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("mouse pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouse released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("mouse entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("mouse exited");
    }
    
}
