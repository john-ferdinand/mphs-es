
package dao;

import java.math.BigDecimal;
import java.util.List;
import model.fee.Fee;
import model.feecategory.FeeCategory;
import model.gradelevel.GradeLevel;
import model.schoolyear.SchoolYear;

/**
 *
 * @author Acer
 */
public interface IFee {
    List<String> getNames();

    List<Fee> getAll();
    
    List<Fee> getAllGroupedById();

    List<Fee> getFeesByGradeLevelAndCategory(GradeLevel aGradeLevel, FeeCategory aFeeCategory);

    List<Fee> getFeesBySchoolYear(SchoolYear aSchoolYear);

    List<Fee> getByCategory(String feeCategory);

    List<Fee> getByGradeLevel(GradeLevel aGradeLevel);
    
    double getSumByGradeLevel(GradeLevel aGradeLevel);

    double getSumOfTuitionFeesByGradeLevelId(Integer aGradeLevelId);

    double getSumOfOtherFeesByGradeLevelId(Integer aGradeLevelId);

    double getSumOfMiscellaneousFeesByGradeLevelId(Integer aGradeLevelId);

    double getDownpaymentFeeByGradeLevel(GradeLevel aGradeLevel);

    
    
    List<GradeLevel> getGradeLevelAssignment(int aFeeId);
    
    Fee getFeeInfoById(int feeId);
    Fee getSummerFeePerSubject();
    List<Fee> getFeesByWildcard(String wildCardChar);
    List<Fee> getFeesByGradeLevelId(int gradeLevelId);
    Fee getFeeGradeLevelAssignmentAndAmountById(int feeId);
    int getFeeId(String feeName);
    boolean addFee(Fee fee);
    boolean update(Fee fee);
    boolean delete(int feeId);
    boolean exists(String feeName);
    BigDecimal getBasicByGradeLevel(GradeLevel gradeLevel);
    BigDecimal getDownpaymentByGradeLevel(GradeLevel gradeLevel);
    BigDecimal getSumOfMiscFeesByGradeLeveLId(GradeLevel gradeLevel);
    BigDecimal getSumOfOthersFeeByGradeLevelId(GradeLevel gradeLevel);

}
