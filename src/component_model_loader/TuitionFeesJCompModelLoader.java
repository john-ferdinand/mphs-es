
package component_model_loader;

import daoimpl.TuitionFeeDaoImpl;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.balancebreakdownfee.BalanceBreakDownFee;
import model.tuitionfee.Tuition;

/**
 *
 * @author John Ferdinand Antonio
 */
public class TuitionFeesJCompModelLoader {
    private final TuitionFeeDaoImpl tuitionFeeDaoImpl;
    
    public TuitionFeesJCompModelLoader(){
        tuitionFeeDaoImpl = new TuitionFeeDaoImpl();
    }
    
    public DefaultTableModel getTuitionByStudentIdAndSchoolYearId(JTable table, int studentId, int schoolYearId){
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        Tuition tuition = tuitionFeeDaoImpl.getBy(studentId, schoolYearId);
        for(BalanceBreakDownFee b : tuition.getBalanceBreakDownFees()){
//            Object[] rowData = {
//                b.getName(),
//                b.getAmount(),
//                b.getBalance(),
//                b.getDeadline(),
//                b.isFullyPaid() == true? "Yes" : "No",
//                b.getCategory(),
//                b.hasPenalty() == true? "Yes" : "No",
//            };
                Object[] rowData = {
                b.getName(),
                b.getAmountDue(),
                b.getBalance(),
                b.getDeadline(),
                b.isFullyPaid() == true ? "Yes" : "No",
                b.getCategory(),
                };
            System.out.println(b.getName()+" : "+b.getCategory());
            tableModel.addRow(rowData);
        }
        return tableModel;
    }    
    
}
