
package daoimpl;

import utility.database.DBType;
import utility.database.DBUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.discount.Discount;
import dao.IDiscount;
import model.user.User;

/**
 *
 * @author Acer
 */
public class DiscountDaoImpl implements IDiscount {

    @Override
    public List<Discount> getAllDiscount() {
        String SQL = "{CALL getAllDiscount()}";
        List<Discount> discountsList = new ArrayList<>();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    User createdBy = new User();
                    createdBy.setUserID(rs.getInt("user_id"));
                    createdBy.setUsername(rs.getString("username"));
                    createdBy.setPassword(rs.getString("password"));
                    createdBy.setLastName(rs.getString("lastname"));
                    createdBy.setFirstName(rs.getString("firstname"));
                    createdBy.setMiddleName(rs.getString("middlename"));
                    
                    Discount discount = new Discount();
                    discount.setDiscountID(rs.getInt("discount_id"));
                    discount.setDiscountName(rs.getString("discount_name"));
                    discount.setDescription(rs.getString("description"));
                    discount.setDateCreated(rs.getDate("date_created"));
                    discount.setPercent(rs.getInt("percentage"));
                    discount.setCreatedBy(createdBy);
                    discount.setIsActive(rs.getBoolean("isActive"));
                    discount.setProvision(rs.getString("provision"));
                    
                    discountsList.add(discount);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discountsList;
    }

    @Override
    public Discount getDiscountBy(int discountId) {
        String SQL = "{CALL getDiscountById(?)}";
        Discount discount = new Discount();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1, discountId);
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    User createdBy = new User();
                    createdBy.setUserID(rs.getInt("created_by_user_id"));
                    createdBy.setLastName(rs.getString("lastname"));
                    createdBy.setFirstName(rs.getString("firstname"));
                    createdBy.setMiddleName(rs.getString("middlename"));
                    
                    discount.setDiscountID(rs.getInt("discount_id"));
                    discount.setDiscountName(rs.getString("discount_name"));
                    discount.setPercent(rs.getInt("percentage"));
                    discount.setDescription(rs.getString("description"));
                    discount.setDateCreated(rs.getDate("date_created"));
                    discount.setProvision(rs.getString("provision"));
                    discount.setIsActive(rs.getBoolean("isDiscountActive"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discount;
    }

    @Override
    public boolean create(Discount discount) {
        boolean isSuccessful = false;
        String SQL = "{CALL createDiscount(?,?,?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setString(1, discount.getDiscountName());
            cs.setInt(2, discount.getPercent());
            cs.setString(3, discount.getDescription());
            cs.setInt(4, discount.getCreatedBy().getUserId());
            cs.setString(5, discount.getProvision());
            cs.executeUpdate();
            isSuccessful = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSuccessful;
    }

    @Override
    public boolean update(Discount discount) {
        boolean isSuccessful = false;
        String SQL = "{CALL updateDiscount(?,?,?,?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setString(1, discount.getDiscountName());
            cs.setInt(2,discount.getPercent());
            cs.setString(3, discount.getDescription());
            cs.setInt(4, discount.getIsActive() == true? 1 : 0);
            cs.setString(5, discount.getProvision());
            cs.setInt(6, discount.getDiscountID());
            cs.executeUpdate();
            isSuccessful = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSuccessful;
    }
    
    
}
