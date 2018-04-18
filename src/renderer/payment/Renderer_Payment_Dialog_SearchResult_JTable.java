
package renderer.payment;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Jordan
 */
public class Renderer_Payment_Dialog_SearchResult_JTable extends DefaultTableCellRenderer{

    private final int studentNoCellIndex;
    private final int admissionIdCellIndex;
    private final int studentTypeCellIndex;

    public Renderer_Payment_Dialog_SearchResult_JTable(int studentNoCellIndex, int admissionIdCellIndex, int studentTypeCellIndex) {
        this.studentNoCellIndex = studentNoCellIndex;
        this.admissionIdCellIndex = admissionIdCellIndex;
        this.studentTypeCellIndex = studentTypeCellIndex;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent =  super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
        
        if(column == studentNoCellIndex){
            if(value instanceof Integer){
                int studentNo = (Integer) value;
                if(studentNo == 0){
                    ((JLabel) cellComponent).setText("Unassigned");
                }
            }
        }
        
        if (column == admissionIdCellIndex) {
            if (value instanceof Integer) {
                int admissionId = (Integer) value;
                if (admissionId == 0) {
                    ((JLabel) cellComponent).setText("N/A");
                }
            }
        }
        
        if(column == studentTypeCellIndex){
            if(value instanceof Integer){
                int studentType = (Integer) value;
                if(studentType == 1){
                    ((JLabel) cellComponent).setText("New");
                }else{
                    ((JLabel) cellComponent).setText("Old");
                }
            }
        }

        return this;
    }
    
    
    
}
