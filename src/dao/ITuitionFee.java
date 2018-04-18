
package dao;

import java.math.BigDecimal;
import model.schoolyear.SchoolYear;
import model.student.Student;
import model.tuitionfee.Tuition;

/**
 *
 * @author John Ferdinand Antonio
 */
public interface ITuitionFee{
    boolean add(Tuition tuitionFee);
    Tuition getBy(int studentId, int schoolyearId);
    boolean payBalanceOf(Tuition tuition);
    boolean payPrimary(Tuition tuition);
    boolean payNewSchoolYear(Tuition tuition);
    boolean paySummerFees(Tuition tuitionFee);
    boolean hasTuitionBalance(int studentId);
    boolean hasTuitionOn(int studentId, int schoolYearId);
    int getBalanceBreakDownId(String balancebreakdownName, int studentId, int schoolyearId);
    SchoolYear getSchoolYearWithTuitionBalanceFor(int studentId);
    BigDecimal getTuitionBalanceOf(Student student, SchoolYear schoolYear);
}
