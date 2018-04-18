
package dao;

import java.util.List;
import model.discount.Discount;

/**
 *
 * @author Acer
 */
public interface IDiscount {
    List<Discount> getAllDiscount();
    Discount getDiscountBy(int discountId);
    boolean create(Discount discount);
    boolean update(Discount discount);
}
