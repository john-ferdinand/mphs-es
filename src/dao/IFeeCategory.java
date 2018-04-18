
package dao;

import java.util.List;
import model.feecategory.FeeCategory;

/**
 *
 * @author Acer
 */
public interface IFeeCategory {
    List<FeeCategory> getAllFeeCategory();
    FeeCategory getFeeCategoryById(int aFeeCategoryId);
    FeeCategory getFeeCategoryByName(String feeCategoryName);
    int getFeeCategoryId(FeeCategory aFeeCategory);
}
