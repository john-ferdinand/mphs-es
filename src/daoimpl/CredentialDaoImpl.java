package daoimpl;

import utility.database.DBType;
import utility.database.DBUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import model.credential.Credential;
import dao.ICredential;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.gradelevel.GradeLevel;
import model.user.User;

public class CredentialDaoImpl implements ICredential {

    @Override
    public boolean addCredential(Credential credential) {
        boolean isAdded;
        String SQLa = "{CALL addCredential(?,?,?,?)}";
        String SQLb = "{CALL addCredentialGradeLevel(?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);) {
            con.setAutoCommit(false);
            try (CallableStatement csa = con.prepareCall(SQLa);
                    CallableStatement csb = con.prepareCall(SQLb);) {
                csa.setString(1, credential.getCredentialName());
                csa.setString(2, credential.getCredentialDescription());
                csa.setInt(3, credential.getCreatedBy().getUserId());
                csa.registerOutParameter(4, Types.INTEGER);
                csa.executeUpdate();
                int credentialId = csa.getInt(4);// credential_id returned after adding credential by csa

                for (GradeLevel g : credential.getGradeLevelsAssigned()) {
                    int gradeLevelId = g.getGradeLevelId();
                    csb.setInt(1, credentialId);
                    csb.setInt(2, gradeLevelId);
                    csb.setInt(3, credential.getYearCreated().getSchoolYearId());
                    csb.executeUpdate();
                }
                isAdded = true;
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                con.setAutoCommit(true);
                isAdded = false;
                e.printStackTrace();
            }

        } catch (SQLException e) {
            isAdded = false;
            e.printStackTrace();
        }
        return isAdded;
    }

    @Override
    public int getCredentialIdByName(String credentialName) {
        Integer credentialId = null;
        String SQL = "{CALL getCredentialIdByName(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setString(1, credentialName.trim());
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    credentialId = rs.getInt("credential_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return credentialId;
    }
    
    @Override
    public List<Credential> getAllCredentials() {
        List<Credential> credentialList = new ArrayList<>();
        String SQLa = "{CALL getAllCredentials()}";
        String SQLb = "{CALL getCredentialGradeLevels(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement getAllCredentials = con.prepareCall(SQLa);
                CallableStatement getCredentialGradeLevels = con.prepareCall(SQLb);){
            try (ResultSet rsa = getAllCredentials.executeQuery();) {
                while (rsa.next()) {
                    User user = new User();
                    user.setUserID(rsa.getInt("user_id"));
                    user.setUsername(rsa.getString("username"));
                    user.setPassword(rsa.getString("username"));
                    user.setIsActive(rsa.getBoolean("isActive"));
                    user.setLastName(rsa.getString("lastname"));
                    user.setFirstName(rsa.getString("firstname"));
                    user.setMiddleName(rsa.getString("middlename"));
                    
                    Credential credential = new Credential();
                    credential.setCreatedBy(user);
                    credential.setCredentialId(rsa.getInt("credential_id"));
                    credential.setCredentialName(rsa.getString("credential_name"));
                    credential.setDateAdded(rsa.getDate("date_added"));
                    credential.setCredentialDescription(rsa.getString("credential_description"));
                    credential.setIsActive(rsa.getBoolean("is_credential_active"));
                    
                    List<GradeLevel> gradeLevelsAssigned = new ArrayList<>();
                    getCredentialGradeLevels.setInt(1, rsa.getInt("credential_id"));
                    try (ResultSet rsb = getCredentialGradeLevels.executeQuery();) {
                        while (rsb.next()) {
                            GradeLevel gradeLevel = new GradeLevel();
                            gradeLevel.setGradeLevelID(rsb.getInt("gradelevel_id"));
                            gradeLevel.setLevelNo(rsb.getInt("grade_level"));
                            gradeLevelsAssigned.add(gradeLevel);
                        }
                    }
                    credential.setGradeLevelsAssigned(gradeLevelsAssigned);
                    credentialList.add(credential);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return credentialList;
    }

    @Override
    public List<Credential> getCredentialsBy(GradeLevel gradeLevel) {
        List<Credential> credentialList = new ArrayList<>();
        String SQLa = "{CALL getCredentialsByGradeLevel(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement getCredentialsByGradeLevel = con.prepareCall(SQLa);){
            getCredentialsByGradeLevel.setInt(1, gradeLevel.getGradeLevelId());
            try (ResultSet rsa = getCredentialsByGradeLevel.executeQuery();) {
                while (rsa.next()) {
                    User user = new User();
                    user.setUserID(rsa.getInt("user_id"));
                    user.setUsername(rsa.getString("username"));
                    user.setPassword(rsa.getString("username"));
                    user.setIsActive(rsa.getBoolean("isActive"));
                    user.setLastName(rsa.getString("lastname"));
                    user.setFirstName(rsa.getString("firstname"));
                    user.setMiddleName(rsa.getString("middlename"));
                    
                    Credential credential = new Credential();
                    credential.setCreatedBy(user);
                    credential.setCredentialId(rsa.getInt("credential_id"));
                    credential.setCredentialName(rsa.getString("credential_name"));
                    credential.setDateAdded(rsa.getDate("date_added"));
                    credential.setCredentialDescription(rsa.getString("credential_description"));
                    credential.setIsActive(rsa.getBoolean("is_credential_active"));
                    
                    credentialList.add(credential);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return credentialList;
    }
    
    
    
}
