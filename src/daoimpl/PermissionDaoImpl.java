package daoimpl;

import dao.IPermission;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.permission.dashboardpermission.DashboardPermission;
import utility.database.DBType;
import utility.database.DBUtil;

public class PermissionDaoImpl implements IPermission{

    @Override
    public DashboardPermission getDashBoardPermissionByUserId(int userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DashboardPermission getDashBoardPermissionByRoleId(int roleId) {
        DashboardPermission dbp = new DashboardPermission();
        String SQL = "{CALL getDashboardPermissionByRoleId(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1,roleId);
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    dbp.setId(rs.getInt("dashboardpermission_id"));
                    dbp.setCanAccessRegistration(rs.getBoolean("can_access_registration"));
                    dbp.setCanAccessGrades(rs.getBoolean("can_access_grades"));
                    dbp.setCanAccessPayment(rs.getBoolean("can_access_payment"));
                    dbp.setCanAccessEnrollment(rs.getBoolean("can_access_enrollment"));
                    dbp.setCanAccessSettings(rs.getBoolean("can_access_settings"));
                    dbp.setCanAccessAccounts(rs.getBoolean("can_access_accounts"));
                    dbp.setCanAccessReports(rs.getBoolean("can_access_reports"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbp;
    }

}
