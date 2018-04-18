
package model.fee;

import java.math.BigDecimal;
import model.schoolyear.SchoolYear;
import java.util.Map;
import model.feecategory.FeeCategory;
import model.gradelevel.GradeLevel;

/**
 *
 * @author Jordan
 */
public class Fee {
    private int id;
    private String name;
    private BigDecimal amount;
    private FeeCategory feeCategory; // miscellaneous, other, tuition, etc
    private GradeLevel gradeLevel;
    private Map<Integer,BigDecimal> gradeLevelAmountPair;
    private SchoolYear schoolYear;
    private String description;
    private boolean isActive;
    private boolean exists;

    public Map<Integer, BigDecimal> getGradeLevelAmountPair() {
        return gradeLevelAmountPair;
    }

    public void setGradeLevelAmountPair(Map<Integer, BigDecimal> gradeLevelAmountPair) {
        this.gradeLevelAmountPair = gradeLevelAmountPair;
    }
    
    public boolean exists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    
    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    
    
    public FeeCategory getFeeCategory() {
        return feeCategory;
    }

    public void setFeeCategory(FeeCategory feeCategory) {
        this.feeCategory = feeCategory;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GradeLevel getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(GradeLevel gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public SchoolYear getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(SchoolYear schoolYear) {
        this.schoolYear = schoolYear;
    }

    
    
    
}
