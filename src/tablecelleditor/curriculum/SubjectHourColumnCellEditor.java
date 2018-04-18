
package tablecelleditor.curriculum;

import java.awt.Component;
import java.text.DecimalFormat;
import java.util.EventObject;
import javax.swing.DefaultCellEditor;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Jordan
 */
public class SubjectHourColumnCellEditor extends DefaultCellEditor{

    private JSpinner spinnerHours;
    private SpinnerNumberModel spinnerNumberModel;
    private JSpinner.NumberEditor spinnerEditor;
    private DecimalFormat decimalFormat;

    public SubjectHourColumnCellEditor(){
        super(new JTextField());
        decimalFormat = new DecimalFormat("0.00");
        spinnerNumberModel = new SpinnerNumberModel(1, 1, 300, 1);
        spinnerHours = new JSpinner(spinnerNumberModel);
        spinnerEditor = (JSpinner.NumberEditor)spinnerHours.getEditor();
        
        spinnerHours.setEditor(spinnerEditor);
        
        initController();
    }
    
    private void initController(){
        spinnerHours.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
            }
        });
    }
    
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return spinnerHours;
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        return super.isCellEditable(anEvent); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getCellEditorValue() {
        int value = Integer.parseInt(spinnerHours.getValue().toString().trim());
        return value;
    }

    
    
}
