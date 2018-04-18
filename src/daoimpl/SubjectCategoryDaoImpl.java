
package daoimpl;

import dao.ISubjectCategory;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.subjectcategory.SubjectCategory;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.subject.Subject;
import utility.database.DBType;
import utility.database.DBUtil;

/**
 *
 * @author Jordan
 */
public class SubjectCategoryDaoImpl implements ISubjectCategory {

    @Override
    public boolean subjectCategoryNameExists(String subjectCategoryName) {
        boolean exists = false;
        String SQL = "{CALL subjectCategoryNameExists(?)}";
        int rowCount = 0;
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setString(1, subjectCategoryName.trim());
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    rowCount = rs.getInt("rowCount");
                }
                exists = rowCount > 0 ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }
    
    @Override
    public boolean addSubjectCategory(SubjectCategory subjectCategory) {
        boolean isAdded = true;
        String SQLa = "{CALL addSubjectCategory(?,?,?)}";
        String SQLb = "{CALL addSubjectCategoryAssignedSubjects(?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);){
            con.setAutoCommit(false);
            try(CallableStatement csA = con.prepareCall(SQLa);
                    CallableStatement csB = con.prepareCall(SQLb);){
                csA.setString(1, subjectCategory.getSubjectCategoryName());
                csA.setString(2, subjectCategory.getDescription());
                csA.registerOutParameter(3, Types.INTEGER);
                csA.executeUpdate();
                int subjectCategoryId = csA.getInt(3);
                
                csB.setInt(1,subjectCategoryId);
                for (Object o : subjectCategory.getSubjectsAssigned().toArray()) {
                    Subject s = (Subject) o;
                    csB.setInt(2, s.getSubjectId());
                    csB.executeUpdate();
                }
                con.commit();
            }catch(SQLException e){
                con.rollback();
                isAdded = false;
                e.printStackTrace();
            }
        } catch (Exception ex) {
            isAdded = false;
            ex.printStackTrace();
        }
        return isAdded;
    }

    @Override
    public boolean updateSubjectCategory(SubjectCategory subjectCategory) {
        boolean isUpdated = true;
        String SQLa = "{CALL updateSubjectCategoryInfo(?,?,?,?)}";
        String SQLb = "{CALL removeAllSubjectCategoryAssignedSubjects(?)}";
        String SQLc = "{CALL addSubjectCategoryAssignedSubjects(?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);){
            con.setAutoCommit(false);
            try (CallableStatement csA = con.prepareCall(SQLa);
                    CallableStatement csB = con.prepareCall(SQLb);
                    CallableStatement csC = con.prepareCall(SQLc);){
                csA.setInt(1, subjectCategory.getSubjectCategoryId());
                csA.setString(2,subjectCategory.getSubjectCategoryName());
                csA.setBoolean(3, subjectCategory.getIsActive());
                csA.setString(4,subjectCategory.getDescription());
                csA.executeUpdate();
                
                csB.setInt(1,subjectCategory.getSubjectCategoryId());
                csB.executeUpdate();
                
                csC.setInt(1, subjectCategory.getSubjectCategoryId());
                Object[] subjectsAssigned = subjectCategory.getSubjectsAssigned().toArray();
                for(Object o : subjectsAssigned){
                    Subject s = (Subject)o;
                    csC.setInt(2, s.getSubjectId());
                    csC.executeUpdate();
                }
                
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                isUpdated = false;
                e.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return isUpdated;
    }
    
    @Override
    public List<SubjectCategory> getAllSubjectCategoryInfo() {
        List<SubjectCategory> subjectCtgyList = new ArrayList<>();
        String SQL = "{CALL getAllSubjectCategoryInfo()}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    SubjectCategory subjectCategory = new SubjectCategory();
                    subjectCategory.setDescription(rs.getString("description"));
                    subjectCategory.setIsActive(rs.getBoolean("isActive"));
                    subjectCategory.setSubjectCategoryId(rs.getInt("subjectcategory_id"));
                    subjectCategory.setSubjectCategoryName(rs.getString("subjectcategory_name"));
                    subjectCtgyList.add(subjectCategory);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjectCtgyList;
    }

    @Override
    public List<Subject> getSubjectCategoryAssignedSubjectsById(int subjectCategoryId) {
        List<Subject> assignedSubjects = new ArrayList<>();
        String SQL = "{CALL getSubjectCategoryAssignedSubjectsById(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1,subjectCategoryId);
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    Subject s = new Subject();
                    s.setSubjectId(rs.getInt("subject_id"));
                    s.setSubjectCode(rs.getString("code"));
                    s.setSubjectTitle(rs.getString("title"));
                    s.setSubjectDescription(rs.getString("description"));
                    s.setIsActive(rs.getBoolean("isActive"));
                    assignedSubjects.add(s);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assignedSubjects;
    }

    @Override
    public List<SubjectCategory> getAllSubjectCategoryInfoByWildCard(String wildCardChar) {
        List<SubjectCategory> subjectCtgyList = new ArrayList<>();
        String SQL = "{CALL getAllSubjectCategoryInfoByWildCard(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setString(1, wildCardChar);
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    SubjectCategory subjectCategory = new SubjectCategory();
                    subjectCategory.setDescription(rs.getString("description"));
                    subjectCategory.setIsActive(rs.getBoolean("isActive"));
                    subjectCategory.setSubjectCategoryId(rs.getInt("subjectcategory_id"));
                    subjectCategory.setSubjectCategoryName(rs.getString("subjectcategory_name"));
                    subjectCtgyList.add(subjectCategory);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjectCtgyList;
    }

    @Override
    public SubjectCategory getSubjectCategoryInfoById(int subjectCategoryId) {
        SubjectCategory subjectCategory = new SubjectCategory();
        String SQL = "{CALL getAllSubjectCategoryInfoById(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1, subjectCategoryId);
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    subjectCategory.setDescription(rs.getString("description"));
                    subjectCategory.setIsActive(rs.getBoolean("isActive"));
                    subjectCategory.setSubjectCategoryId(rs.getInt("subjectcategory_id"));
                    subjectCategory.setSubjectCategoryName(rs.getString("subjectcategory_name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjectCategory;
    }
    
    
    
}
