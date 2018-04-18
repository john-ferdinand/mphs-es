
package celleditor.editschedule;

import component_editor.*;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author John Ferdinand Antonio
 */
public class EditScheduleDayCellEditor extends DefaultCellEditor{
    private JComboBox jcmbDays;
    private DefaultComboBoxModel model;
    
    public EditScheduleDayCellEditor() {
        super(new JComboBox());
        model = new DefaultComboBoxModel(new String[]{"Mon", "Tue", "Wed", "Thu", "Fri"});
        jcmbDays = new JComboBox(model);
//        jcmbDays.setEditable(false);
//        jcmbDays.setSelectedItem(null);

    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        boolean cellEditable = super.isCellEditable(anEvent);
        if (cellEditable && anEvent instanceof MouseEvent) {
            cellEditable = ((MouseEvent) anEvent).getClickCount() == 2;
        }
        return cellEditable;
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return jcmbDays;
    }

    @Override
    public Object getCellEditorValue() {
        return jcmbDays.getSelectedItem(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
