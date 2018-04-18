/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import dao.IRole;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.role.Role;
import utility.database.DBType;
import utility.database.DBUtil;

/**
 *
 * @author John Ferdinand Antonio
 */
public class RoleDaoImpl implements IRole{

    @Override
    public Integer getId(String roleName) {
        Integer roleId = null;
        String SQL = "{CALL getRoleIdByRoleName(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setString(1,roleName.trim());
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    roleId = rs.getInt("role_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roleId;
    }

    @Override
    public Role getById(int roleId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Role getByUserId(int userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Role> getAll() {
        List<Role> roleList = new ArrayList<>();
        String SQLa = "{CALL getAllRoles()}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQLa);){
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    Role role = new Role();
                    role.setId(rs.getInt("role_id"));
                    role.setRoleName(rs.getString("role"));
                    roleList.add(role);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roleList;
    }
    
}
