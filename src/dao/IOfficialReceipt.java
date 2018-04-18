
package dao;

import java.util.List;
import model.officialreceipt.OfficialReceipt;

/**
 *
 * @author Jordan
 */
public interface IOfficialReceipt {
    String getNextAvailableOrNoForPaymentBySchoolYearId(int schoolyearId);
    int getIdByOrNo(int orNo);
    boolean markOrNoAsUsed(int orNo);
    
    OfficialReceipt getOfficialReceiptByOrNo(int orNo);
    List<OfficialReceipt> getAllOfficialReceiptsByStudentIdandSchoolYearId(int studentId, int schoolyearId);
    List<OfficialReceipt> getAllOfficialReceiptsByStudentId(int studentId);
}
