package daoimpl;

import dao.IClassType;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.classtype.ClassType;
import utility.database.DBType;
import utility.database.DBUtil;

/**
 *
 * @author Jordan
 */
public class ClassTypeDaoImpl implements IClassType {

    @Override
    public List<ClassType> getAllClassTypesByStatus(boolean isActive) {
        List<ClassType> classTypeList = new ArrayList<>();
        String SQL = "{CALL getAllClassTypesByStatus(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, isActive == true ? 1 : 0);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    ClassType classType = new ClassType();
                    classType.setClassTypeID(rs.getInt("classtype_id"));
                    classType.setClassTypeName(rs.getString("classtype"));
                    classType.setIsActive(rs.getBoolean("is_classtype_active"));
                    classType.setDateAdded(rs.getDate("date_added"));
                    classTypeList.add(classType);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classTypeList;
    }

    @Override
    public ClassType getClassTypeById(int classTypeId) {
        ClassType classType = new ClassType();
        String SQL = "{CALL getClassTypeById(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1,classTypeId);
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    classType.setClassTypeID(rs.getInt("classtype_id"));
                    classType.setClassTypeName(rs.getString("classtype"));
                    classType.setIsActive(rs.getBoolean("is_classtype_active"));
                    classType.setDateAdded(rs.getDate("date_added"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classType;
    }

}
