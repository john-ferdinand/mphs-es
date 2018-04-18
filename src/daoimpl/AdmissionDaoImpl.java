package daoimpl;

import model.admission.Admission;
import dao.IAdmission;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import model.registration.Registration;
import utility.database.DBType;
import utility.database.DBUtil;

public class AdmissionDaoImpl implements IAdmission {

    private final GradeLevelDaoImpl gradeLevelDaoImpl;
    private final SchoolYearDaoImpl schoolYearDaoImpl;
    
    public AdmissionDaoImpl(){
        gradeLevelDaoImpl = new GradeLevelDaoImpl();
        schoolYearDaoImpl = new SchoolYearDaoImpl();
    }

    @Override
    public Admission getAdmissionByStudentId(int studentId) {
        Admission admission = new Admission();
        String SQL = "{CALL getAdmissionByStudentId(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1,studentId);
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    Registration r = new Registration();
                    r.setRegistrationId(rs.getInt("registration_id"));
                    admission.setAdmissionId(rs.getInt("admission_id"));
                    admission.setGradeLevelId(rs.getInt("gradelevel_id"));
                    admission.setSchoolYearId(rs.getInt("schoolyear_id"));
                    admission.setRegistration(r);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admission;
    }
    
    
    
    
    @Override
    public boolean completeAdmission(Admission admission) {
        boolean isSuccessful = false;
        String SQLa = "{CALL addAdmission(?,?,?,?)}";
        int registrationId = admission.getRegistration().getRegistrationId();
        int gradeLevelId = gradeLevelDaoImpl.getId(admission.getRegistration().getGradeLevelNo());
        int schoolYearId = schoolYearDaoImpl.getCurrentSchoolYearId();
        
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);){
            con.setAutoCommit(false);
            try (CallableStatement csa = con.prepareCall(SQLa);){
                csa.setInt(1, registrationId);
                csa.setInt(2, gradeLevelId);
                csa.setInt(3, schoolYearId);
                csa.registerOutParameter(4, Types.INTEGER);
                csa.executeUpdate();
                con.commit();
                isSuccessful = true;
            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccessful;
    }

    @Override
    public boolean isAdmissionCompleteFor(int registrationId) {
        boolean isComplete = false;
        String SQL = "{CALL isAdmissionCompleteFor(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1, registrationId);
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    isComplete = rs.getBoolean("admissionComplete");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isComplete;
    }
    
    

}
