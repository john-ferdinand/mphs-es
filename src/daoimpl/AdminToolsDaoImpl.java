package daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import model.registration.Registration;
import utility.database.DBType;
import utility.database.DBUtil;
import dao.IAdminTools;
import model.testdata.SubjectTestDataModel;

/**
 *
 * @author John Ferdinand Antonio
 */
public class AdminToolsDaoImpl implements IAdminTools {

    @Override
    public boolean generateStudent(Registration registration) {
        boolean isAdded = false;
        String addRegistration = "{CALL addRegistration(?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?,?,?) }";
        String addAdmission = "{CALL addAdmission(?)}";
        String setAdmissionGradeLevel = "{CALL setAdmissionGradeLevel(?,?)}";
        String addAsStudent = "{CALL addAsStudent(?,?)}";
        String completeAdmission = "{CALL completeAdmission(?)}";
        String enrollStudent = "{CALL enrollStudent(?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);) {
            con.setAutoCommit(false);
            try (CallableStatement csA = con.prepareCall(addRegistration);
                    CallableStatement csB = con.prepareCall(addAdmission);
                    CallableStatement csC = con.prepareCall(setAdmissionGradeLevel);
                    CallableStatement csD = con.prepareCall(addAsStudent);
                    CallableStatement csE = con.prepareCall(completeAdmission);
                    CallableStatement csF = con.prepareCall(enrollStudent);) {
                csA.setString(1, registration.getStudentType());
                csA.setString(2, registration.getLastName());
                csA.setString(3, registration.getFirstName());
                csA.setString(4, registration.getMiddleName());
                csA.setObject(5, registration.getBirthday());
                csA.setString(6, registration.getPlaceOfBirth());
                csA.setString(7, registration.getNationality());
                csA.setString(8, registration.getReligion());
                csA.setInt(9, "Male".equals(registration.getGender()) ? 1 : 0);
                csA.setString(10, registration.getFatherFirstName());
                csA.setString(11, registration.getFatherMiddleName());
                csA.setString(12, registration.getFatherLastName());
                csA.setString(13, registration.getFatherOccupation());
                csA.setString(14, registration.getFatherOfficePhoneNo());
                csA.setString(15, registration.getFatherMobileNo());
                csA.setInt(16, registration.getIsFatherContactInCaseEmergency() == true ? 1 : 0);
                csA.setString(17, registration.getMotherFirstName());
                csA.setString(18, registration.getMotherMiddleName());
                csA.setString(19, registration.getMotherLastName());
                csA.setString(20, registration.getMotherOccupation());
                csA.setString(21, registration.getMotherOfficePhoneNo());
                csA.setString(22, registration.getMotherMobileNo());
                csA.setInt(23, registration.getIsMotherContactInCaseEmergency() == true ? 1 : 0);
                csA.setString(24, registration.getGuardianLastName());
                csA.setString(25, registration.getGuardianFirstName());
                csA.setString(26, registration.getGuardianMiddleName());
                csA.setString(27, registration.getGuardianOccupation());
                csA.setString(28, registration.getGuardianOfficePhoneNo());
                csA.setString(29, registration.getGuardianMobileNo());
                csA.setString(30, registration.getGuardianRelationToStudent());
                csA.setInt(31, registration.getIsGuardianContactInCaseEmergency() == true ? 1 : 0);
                csA.setString(32, registration.getSchoolLastAttended());
                csA.setString(33, registration.getSchoolLastAttendedAddress());
                csA.setString(34, registration.getAddressRoomOrHouseNo());
                csA.setString(35, registration.getAddressStreet());
                csA.setString(36, registration.getAddressBrgyOrSubd());
                csA.setString(37, registration.getAddressCity());
                csA.setString(38, registration.getRegion());
                csA.setInt(39, registration.getGradeLevelNo());
                csA.setInt(40, registration.getSchoolYearYearFrom());
                csA.registerOutParameter(42, Types.INTEGER);
                csA.executeUpdate();
                int registrationId = csA.getInt(42);

                csB.setInt(1, registrationId);
                csB.executeUpdate();

                csC.setInt(1,registration.getGradeLevelNo());
                csC.setInt(2,registrationId);
                csC.executeUpdate();
                
                csD.setInt(1, registrationId);
                csD.registerOutParameter(2, Types.INTEGER);
                csD.executeUpdate();
                int studentId = csD.getInt(2);
                
                csE.setInt(1, registrationId);
                csE.executeUpdate();

                SchoolYearDaoImpl sydi = new SchoolYearDaoImpl();

                csF.setInt(1, sydi.getCurrentSchoolYearId());
                csF.setInt(2, studentId);
                csF.setInt(3, registration.getGradeLevelNo());
                csF.executeUpdate();

                con.commit();
                isAdded = true;
            } catch (SQLException e) {
                con.rollback();
                con.setAutoCommit(true);
                e.printStackTrace();
            }
        } catch (SQLException e) {
            isAdded = false;
            e.printStackTrace();
        }
        return isAdded;
    }

    @Override
    public boolean deactivateAllStudents() {
        boolean isDeactivated = false;
        String SQL = "{CALL `spUtil_deactivate_all_students`()}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.executeUpdate();
            isDeactivated = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDeactivated;
    }

    @Override
    public boolean deleteAllPaymentAndTuitionRecord() {
        boolean isDeleted = false;
        String SQL = "{CALL `spUtil_wipeout_all_payment_and_tuition_record`()}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.executeUpdate();
            isDeleted = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDeleted;
    }

    @Override
    public boolean deleteAllStudentRecord() {
        boolean isDeleted = false;
        String SQL = "{CALL `spUtil_wipeout_allstudent_record`()}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.executeUpdate();
            isDeleted = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDeleted;
    }

    @Override
    public boolean addSubjects(SubjectTestDataModel s) {
        boolean added = false;
        GradeLevelDaoImpl gldi = new GradeLevelDaoImpl();
        String SQL = "{CALL spUtil_add_subjects(?,?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            Integer prefixNoStart = 0;
            String codePrefix;
            for (int level = 0; level <= 10; level++) {
                for (int i = 0; i < s.getSubject().size(); i++) {
                    if (level == 0) {
                        codePrefix = "K";
                        cs.setString(1, codePrefix + s.getCode().get(i));
                    } else {
                        codePrefix = "G";
                        cs.setString(1, codePrefix +prefixNoStart + s.getCode().get(i));
                    }
                    
                    cs.setString(2, s.getSubject().get(i));
                    cs.setString(3, s.getDescription().get(i));
                    cs.setInt(4, gldi.getId(level));
                    cs.executeUpdate();
                }
                prefixNoStart++;
            }
            
            added = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return added;
    }

    @Override
    public boolean addFacultyWithSpecialization() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
