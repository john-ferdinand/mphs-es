
package component_model_loader;

import daoimpl.OfficialReceiptDaoImpl;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.officialreceipt.OfficialReceipt;

/**
 *
 * @author Jordan
 */
public class OfficialReceiptJCompModelLoader {

    private final OfficialReceiptDaoImpl officialReceiptDaoImpl;

    public OfficialReceiptJCompModelLoader() {
        this.officialReceiptDaoImpl = new OfficialReceiptDaoImpl();
    }

    public DefaultTableModel getAllOfficialReceiptsByStudentIdandSchoolYearId(JTable table, int studentId, int schoolYearId){
        List<OfficialReceipt> orList = officialReceiptDaoImpl.getAllOfficialReceiptsByStudentIdandSchoolYearId(studentId, schoolYearId);
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        for(OfficialReceipt o : orList){
            Object[] rowData = {
                o.getPayment().getOrNoAttached(),o.getPayment().getDateOfPayment(),
                o.getSchoolYear().getYearFrom()+"-"+o.getSchoolYear().getYearTo()};
            tableModel.addRow(rowData);
        }
        return tableModel;
    }
    
    public DefaultTableModel getAllOfficialReceiptsByStudentId(JTable table, int studentId){
        List<OfficialReceipt> orList = officialReceiptDaoImpl.getAllOfficialReceiptsByStudentId(studentId);
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        for(OfficialReceipt o : orList){
            Object[] rowData = {
                o.getPayment().getOrNoAttached(),o.getPayment().getDateOfPayment(),
                o.getSchoolYear().getYearFrom()+"-"+o.getSchoolYear().getYearTo()};
            tableModel.addRow(rowData);
        }
        return tableModel;
    }
}
