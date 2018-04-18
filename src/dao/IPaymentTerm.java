
package dao;

import java.util.List;
import model.paymentterm.PaymentTerm;
import model.paymentterm.PaymentTermPenalty;
import model.period.Period;
import model.schoolyear.SchoolYear;
import model.student.Student;

/**
 *
 * @author Jordan
 */
public interface IPaymentTerm {
    List<Period> getPeriodsByPaymentTermId(int paymentTermId, int schoolYearId);//
    List<PaymentTerm> getAll(); //
    PaymentTerm getPaymentTermByPaymentTermId(int aPaymentTermId); //
    int getPaymentTermIDByName(String aPaymentTerm);//
    boolean add(PaymentTerm aPaymentTerm);//
    boolean addPaymentSchedule(List<PaymentTerm> paymentTerms);//
   
    List<SchoolYear> getSchoolYearsWithPenalty();//
    List<PaymentTermPenalty> getPenaltyInformationBySchoolYearId(int schoolYearId);//
    PaymentTerm getPaymentTermOf(Student student, SchoolYear schoolYear);
    List<PaymentTerm> getAllPaymentTermsSchedulesAndPenaltyOf(SchoolYear schoolYear);
}
