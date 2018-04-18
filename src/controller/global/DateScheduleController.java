package controller.global;

import component_model_loader.CalendarJCompModelLoader;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author John Ferdinand Antonio
 */
public class DateScheduleController{

    
    private JComboBox month;
    private JComboBox day;
    private JComboBox year;
    
    private ItemListener monthItemListener;
    private ItemListener dayItemListener;
    private ItemListener yearItemListener;
    
    public DateScheduleController(JComboBox month, JComboBox day, JComboBox year){
        this.month = month;
        this.day = day;
        this.year = year;
    }
    
    public void control() {
        monthItemListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (year.getSelectedIndex() > -1 && month.getSelectedIndex() > -1) {
                    int y = Integer.parseInt(year.getSelectedItem().toString());
                    int m = Integer.parseInt(month.getSelectedItem().toString());
                    DefaultComboBoxModel dcm = CalendarJCompModelLoader.getDaysOfMonth(m, y);
                    day.setModel(dcm);
                }
            }
        };
        month.addItemListener(monthItemListener);
        
        dayItemListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        yearItemListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int y = Integer.parseInt(year.getSelectedItem().toString());
                int m = Integer.parseInt(month.getSelectedItem().toString());
                DefaultComboBoxModel dcm = CalendarJCompModelLoader.getDaysOfMonth(m, y);
                day.setModel(dcm);
            }
        };
        year.addItemListener(yearItemListener);
    }
    
    
    

    
}
