package daoimpl;

import constants.AdmissionTable;
import constants.RegistrationTable;
import utility.database.DBType;
import utility.database.DBUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.registration.Registration;
import model.schoolyear.SchoolYear;
import dao.IRegistration;
import java.sql.Types;
import model.credential.Credential;

public class RegistrationDaoImpl implements IRegistration{

    PaymentTermDaoImpl paymentTermDaoImpl = new PaymentTermDaoImpl();

    @Override
    public boolean isDuplicateRegistration(Registration registration) {
        boolean isDuplicate = false;
        String SQL = "{CALL isDuplicateRegistration(?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setString(1, registration.getFirstName().toLowerCase().trim());
            cs.setString(2, registration.getMiddleName().toLowerCase().trim());
            cs.setString(3, registration.getLastName().toLowerCase().trim());
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    isDuplicate = rs.getBoolean("isDuplicate");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDuplicate;
    }

    @Override
    public List<Registration> getAllRegistrationInfoBy(int syYearFrom, int gradeLevelNo) {
        String SQL = "{CALL getAllRegistrationInfoBy(?,?)}";
        List<Registration> registrationList = new ArrayList<>();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1,syYearFrom);
            cs.setInt(2,gradeLevelNo);
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    Registration reg = new Registration();
                    reg.setRegistrationId(rs.getInt("registration_id"));
                    reg.setStudentType(rs.getString("student_type"));
                    reg.setLastName(rs.getString("lastname") );
                    reg.setFirstName(rs.getString("firstname")); 
                    reg.setMiddleName(rs.getString("middlename"));
                    reg.setBirthday(rs.getDate("dob"));
                    reg.setPlaceOfBirth(rs.getString("pob"));
                    reg.setNationality(rs.getString("nationality"));
                    reg.setReligion(rs.getString("religion"));
                    reg.setGender(rs.getInt("gender")==1?"Male":"Female");
                    reg.setFatherFirstName(rs.getString("father_firstname"));
                    reg.setFatherMiddleName(rs.getString("father_middlename"));
                    reg.setFatherLastName(rs.getString("father_lastname"));
                    reg.setFatherOccupation(rs.getString("father_occupation"));
                    reg.setFatherOfficePhoneNo(rs.getString("father_officephone_no"));
                    reg.setFatherMobileNo(rs.getString("father_mobile_no"));
                    reg.setIsFatherContactInCaseEmergency(rs.getBoolean("isFatherContactInCaseEmergency"));
                    reg.setMotherFirstName(rs.getString("mother_firstname"));
                    reg.setMotherMiddleName(rs.getString("mother_middlename"));
                    reg.setMotherLastName(rs.getString("mother_lastname"));
                    reg.setMotherOccupation(rs.getString("mother_occupation"));
                    reg.setMotherOfficePhoneNo(rs.getString("mother_officephone_no"));
                    reg.setMotherMobileNo(rs.getString("mother_mobile_no"));
                    reg.setIsMotherContactInCaseEmergency(rs.getBoolean("isMotherContactInCaseEmergency"));
                    reg.setGuardianLastName(rs.getString("guardian_lastname"));
                    reg.setGuardianFirstName(rs.getString("guardian_firstname"));
                    reg.setGuardianMiddleName(rs.getString("guardian_middlename"));
                    reg.setGuardianOccupation(rs.getString("guardian_occupation"));
                    reg.setGuardianMobileNo(rs.getString("guardian_mobile_no"));
                    reg.setGuardianRelationToStudent(rs.getString("guardian_relation_to_student"));
                    reg.setIsGuardianContactInCaseEmergency(rs.getBoolean("isGuardianContactInCaseEmergency"));
                    reg.setSchoolLastAttended(rs.getString("school_last_attended"));
                    reg.setSchoolLastAttendedAddress(rs.getString("school_last_attended_address"));
                    reg.setAddressRoomOrHouseNo(rs.getString("room_or_house_no"));
                    reg.setAddressStreet(rs.getString("street"));
                    reg.setAddressBrgyOrSubd(rs.getString("brgy_or_subd"));
                    reg.setAddressCity(rs.getString("city"));
                    reg.setRegion(rs.getString("region"));
                    reg.setGradeLevelNo(rs.getInt("gradelevel_no"));
                    reg.setSchoolYearYearFrom(rs.getInt("schoolyear_yearfrom"));
                    reg.setRegistrationDate(rs.getDate("date_registered"));
                    reg.setStudentNo(rs.getInt("student_no"));
                    
                    String isAdmissionComplete = rs.getString("isAdmissionComplete").trim();
                    reg.setIsAdmissionComplete(isAdmissionComplete.equalsIgnoreCase("Yes")? true : false);
                    
                    registrationList.add(reg);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrationList;
    }
    
    @Override
    public List<Registration> getAllRegistrationInfoBySyYearFrom(int syYearFrom) {
        List<Registration> registrationList = new ArrayList<>();
        String SQL = "{CALL getAllRegistrationInfoBySyYearFrom(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1,syYearFrom);
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    Registration reg = new Registration();
                    reg.setRegistrationId(rs.getInt("registration_id"));
                    reg.setStudentType(rs.getString("student_type"));
                    reg.setLastName(rs.getString("lastname") );
                    reg.setFirstName(rs.getString("firstname")); 
                    reg.setMiddleName(rs.getString("middlename"));
                    reg.setBirthday(rs.getDate("dob"));
                    reg.setPlaceOfBirth(rs.getString("pob"));
                    reg.setNationality(rs.getString("nationality"));
                    reg.setReligion(rs.getString("religion"));
                    reg.setGender(rs.getInt("gender")==1?"Male":"Female");
                    reg.setFatherFirstName(rs.getString("father_firstname"));
                    reg.setFatherMiddleName(rs.getString("father_middlename"));
                    reg.setFatherLastName(rs.getString("father_lastname"));
                    reg.setFatherOccupation(rs.getString("father_occupation"));
                    reg.setFatherOfficePhoneNo(rs.getString("father_officephone_no"));
                    reg.setFatherMobileNo(rs.getString("father_mobile_no"));
                    reg.setIsFatherContactInCaseEmergency(rs.getBoolean("isFatherContactInCaseEmergency"));
                    reg.setMotherFirstName(rs.getString("mother_firstname"));
                    reg.setMotherMiddleName(rs.getString("mother_middlename"));
                    reg.setMotherLastName(rs.getString("mother_lastname"));
                    reg.setMotherOccupation(rs.getString("mother_occupation"));
                    reg.setMotherOfficePhoneNo(rs.getString("mother_officephone_no"));
                    reg.setMotherMobileNo(rs.getString("mother_mobile_no"));
                    reg.setIsMotherContactInCaseEmergency(rs.getBoolean("isMotherContactInCaseEmergency"));
                    reg.setGuardianLastName(rs.getString("guardian_lastname"));
                    reg.setGuardianFirstName(rs.getString("guardian_firstname"));
                    reg.setGuardianMiddleName(rs.getString("guardian_middlename"));
                    reg.setGuardianOccupation(rs.getString("guardian_occupation"));
                    reg.setGuardianMobileNo(rs.getString("guardian_mobile_no"));
                    reg.setGuardianRelationToStudent(rs.getString("guardian_relation_to_student"));
                    reg.setIsGuardianContactInCaseEmergency(rs.getBoolean("isGuardianContactInCaseEmergency"));
                    reg.setSchoolLastAttended(rs.getString("school_last_attended"));
                    reg.setSchoolLastAttendedAddress(rs.getString("school_last_attended_address"));
                    reg.setAddressRoomOrHouseNo(rs.getString("room_or_house_no"));
                    reg.setAddressStreet(rs.getString("street"));
                    reg.setAddressBrgyOrSubd(rs.getString("brgy_or_subd"));
                    reg.setAddressCity(rs.getString("city"));
                    reg.setRegion(rs.getString("region"));
                    reg.setGradeLevelNo(rs.getInt("gradelevel_no"));
                    reg.setSchoolYearYearFrom(rs.getInt("schoolyear_yearfrom"));
                    reg.setRegistrationDate(rs.getDate("date_registered"));
                    reg.setStudentNo(rs.getInt("student_no"));
                    
                    String isAdmissionComplete = rs.getString("isAdmissionComplete").trim();
                    reg.setIsAdmissionComplete(isAdmissionComplete.equalsIgnoreCase("Yes")? true : false);
                    
                    registrationList.add(reg);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrationList;
    }

    @Override
    public List<Registration> getAllRegistrationInfoByWildCard(String wildCardChar, int schoolYearFrom) {
        List<Registration> registrationList = new ArrayList<>();
        String SQL = "{CALL getAllRegistrationInfoByWildCard(?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setString(1,wildCardChar);
            cs.setInt(2,schoolYearFrom);
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    Registration reg = new Registration();
                    reg.setRegistrationId(rs.getInt("registration_id"));
                    reg.setStudentType(rs.getString("student_type"));
                    reg.setLastName(rs.getString("lastname") );
                    reg.setFirstName(rs.getString("firstname")); 
                    reg.setMiddleName(rs.getString("middlename"));
                    reg.setBirthday(rs.getDate("dob"));
                    reg.setPlaceOfBirth(rs.getString("pob"));
                    reg.setNationality(rs.getString("nationality"));
                    reg.setReligion(rs.getString("religion"));
                    reg.setGender(rs.getInt("gender")==1?"Male":"Female");
                    reg.setFatherFirstName(rs.getString("father_firstname"));
                    reg.setFatherMiddleName(rs.getString("father_middlename"));
                    reg.setFatherLastName(rs.getString("father_lastname"));
                    reg.setFatherOccupation(rs.getString("father_occupation"));
                    reg.setFatherOfficePhoneNo(rs.getString("father_officephone_no"));
                    reg.setFatherMobileNo(rs.getString("father_mobile_no"));
                    reg.setIsFatherContactInCaseEmergency(rs.getBoolean("isFatherContactInCaseEmergency"));
                    reg.setMotherFirstName(rs.getString("mother_firstname"));
                    reg.setMotherMiddleName(rs.getString("mother_middlename"));
                    reg.setMotherLastName(rs.getString("mother_lastname"));
                    reg.setMotherOccupation(rs.getString("mother_occupation"));
                    reg.setMotherOfficePhoneNo(rs.getString("mother_officephone_no"));
                    reg.setMotherMobileNo(rs.getString("mother_mobile_no"));
                    reg.setIsMotherContactInCaseEmergency(rs.getBoolean("isMotherContactInCaseEmergency"));
                    reg.setGuardianLastName(rs.getString("guardian_lastname"));
                    reg.setGuardianFirstName(rs.getString("guardian_firstname"));
                    reg.setGuardianMiddleName(rs.getString("guardian_middlename"));
                    reg.setGuardianOccupation(rs.getString("guardian_occupation"));
                    reg.setGuardianMobileNo(rs.getString("guardian_mobile_no"));
                    reg.setGuardianRelationToStudent(rs.getString("guardian_relation_to_student"));
                    reg.setIsGuardianContactInCaseEmergency(rs.getBoolean("isGuardianContactInCaseEmergency"));
                    reg.setSchoolLastAttended(rs.getString("school_last_attended"));
                    reg.setSchoolLastAttendedAddress(rs.getString("school_last_attended_address"));
                    reg.setAddressRoomOrHouseNo(rs.getString("room_or_house_no"));
                    reg.setAddressStreet(rs.getString("street"));
                    reg.setAddressBrgyOrSubd(rs.getString("brgy_or_subd"));
                    reg.setAddressCity(rs.getString("city"));
                    reg.setRegion(rs.getString("region"));
                    reg.setGradeLevelNo(rs.getInt("gradelevel_no"));
                    reg.setSchoolYearYearFrom(rs.getInt("schoolyear_yearfrom"));
                    reg.setRegistrationDate(rs.getDate("date_registered"));
                   
                    String isAdmissionComplete = rs.getString("isAdmissionComplete").trim();
                    reg.setIsAdmissionComplete(isAdmissionComplete.equalsIgnoreCase("Yes")? true : false);
                    
                    registrationList.add(reg);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrationList;
    }

    @Override
    public List<Registration> getAllRegistrationInfoByAdmissionStatus(int admissionStatus, int schoolYearFrom) {
        List<Registration> registrationList = new ArrayList<>();
        String SQL = "{CALL getAllRegistrationInfoByAdmissionStatus(?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1,admissionStatus);
            cs.setInt(2,schoolYearFrom);
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    Registration reg = new Registration();
                    reg.setRegistrationId(rs.getInt("registration_id"));
                    reg.setStudentType(rs.getString("student_type"));
                    reg.setLastName(rs.getString("lastname") );
                    reg.setFirstName(rs.getString("firstname")); 
                    reg.setMiddleName(rs.getString("middlename"));
                    reg.setBirthday(rs.getDate("dob"));
                    reg.setPlaceOfBirth(rs.getString("pob"));
                    reg.setNationality(rs.getString("nationality"));
                    reg.setReligion(rs.getString("religion"));
                    reg.setGender(rs.getInt("gender")==1?"Male":"Female");
                    reg.setFatherFirstName(rs.getString("father_firstname"));
                    reg.setFatherMiddleName(rs.getString("father_middlename"));
                    reg.setFatherLastName(rs.getString("father_lastname"));
                    reg.setFatherOccupation(rs.getString("father_occupation"));
                    reg.setFatherOfficePhoneNo(rs.getString("father_officephone_no"));
                    reg.setFatherMobileNo(rs.getString("father_mobile_no"));
                    reg.setIsFatherContactInCaseEmergency(rs.getBoolean("isFatherContactInCaseEmergency"));
                    reg.setMotherFirstName(rs.getString("mother_firstname"));
                    reg.setMotherMiddleName(rs.getString("mother_middlename"));
                    reg.setMotherLastName(rs.getString("mother_lastname"));
                    reg.setMotherOccupation(rs.getString("mother_occupation"));
                    reg.setMotherOfficePhoneNo(rs.getString("mother_officephone_no"));
                    reg.setMotherMobileNo(rs.getString("mother_mobile_no"));
                    reg.setIsMotherContactInCaseEmergency(rs.getBoolean("isMotherContactInCaseEmergency"));
                    reg.setGuardianLastName(rs.getString("guardian_lastname"));
                    reg.setGuardianFirstName(rs.getString("guardian_firstname"));
                    reg.setGuardianMiddleName(rs.getString("guardian_middlename"));
                    reg.setGuardianOccupation(rs.getString("guardian_occupation"));
                    reg.setGuardianMobileNo(rs.getString("guardian_mobile_no"));
                    reg.setGuardianRelationToStudent(rs.getString("guardian_relation_to_student"));
                    reg.setIsGuardianContactInCaseEmergency(rs.getBoolean("isGuardianContactInCaseEmergency"));
                    reg.setSchoolLastAttended(rs.getString("school_last_attended"));
                    reg.setSchoolLastAttendedAddress(rs.getString("school_last_attended_address"));
                    reg.setAddressRoomOrHouseNo(rs.getString("room_or_house_no"));
                    reg.setAddressStreet(rs.getString("street"));
                    reg.setAddressBrgyOrSubd(rs.getString("brgy_or_subd"));
                    reg.setAddressCity(rs.getString("city"));
                    reg.setRegion(rs.getString("region"));
                    reg.setGradeLevelNo(rs.getInt("gradelevel_no"));
                    reg.setSchoolYearYearFrom(rs.getInt("schoolyear_yearfrom"));
                    reg.setRegistrationDate(rs.getDate("date_registered"));
                   
                    String isAdmissionComplete = rs.getString("isAdmissionComplete").trim();
                    reg.setIsAdmissionComplete(isAdmissionComplete.equalsIgnoreCase("Yes")? true : false);
                    
                    registrationList.add(reg);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrationList;
    }

    @Override
    public List<Registration> getAllRegistrationInfo() {
        List<Registration> list = new ArrayList<>();
        String SQL = "{CALL getAllRegistrationInfo()}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    Registration reg = new Registration();
                    reg.setRegistrationId(rs.getInt(RegistrationTable.REGISTRATION_ID));
                    reg.setStudentType(rs.getString(RegistrationTable.STUDENT_TYPE));
                    reg.setLastName(rs.getString(RegistrationTable.LASTNAME) );
                    reg.setFirstName(rs.getString(RegistrationTable.FIRSTNAME)); 
                    reg.setMiddleName(rs.getString(RegistrationTable.MIDDLENAME));
//                    reg.setBirthday(rs.getString(RegistrationTable.DOB));
                    reg.setPlaceOfBirth(rs.getString(RegistrationTable.POB));
                    reg.setNationality(rs.getString(RegistrationTable.NATIONALITY));
                    reg.setReligion(rs.getString(RegistrationTable.RELIGION));
                    reg.setGender(rs.getInt(RegistrationTable.GENDER)==1?"Male":"Female");
                    reg.setFatherFirstName(rs.getString(RegistrationTable.FATHER_FIRSTNAME));
                    reg.setFatherMiddleName(rs.getString(RegistrationTable.FATHER_MIDDLENAME));
                    reg.setFatherLastName(rs.getString(RegistrationTable.FATHER_LASTNAME));
                    reg.setFatherOccupation(rs.getString(RegistrationTable.FATHER_OCCUPATION));
                    reg.setFatherOfficePhoneNo(rs.getString(RegistrationTable.FATHER_OFFICEPHONE_NO));
                    reg.setFatherMobileNo(rs.getString(RegistrationTable.FATHER_MOBILE_NO));
                    reg.setIsFatherContactInCaseEmergency(rs.getBoolean(RegistrationTable.ISFATHERCONTACTINCASEEMERGENCY));
                    reg.setMotherFirstName(rs.getString(RegistrationTable.MOTHER_FIRSTNAME));
                    reg.setMotherMiddleName(rs.getString(RegistrationTable.MOTHER_MIDDLENAME));
                    reg.setMotherLastName(rs.getString(RegistrationTable.MOTHER_LASTNAME));
                    reg.setMotherOccupation(rs.getString(RegistrationTable.MOTHER_OCCUPATION));
                    reg.setMotherOfficePhoneNo(rs.getString(RegistrationTable.MOTHER_OFFICEPHONE_NO));
                    reg.setMotherMobileNo(rs.getString(RegistrationTable.MOTHER_MOBILE_NO));
                    reg.setIsMotherContactInCaseEmergency(rs.getBoolean(RegistrationTable.ISMOTHERCONTACTINCASEEMERGENCY));
                    reg.setGuardianLastName(rs.getString(RegistrationTable.GUARDIAN_LASTNAME));
                    reg.setGuardianFirstName(rs.getString(RegistrationTable.GUARDIAN_FIRSTNAME));
                    reg.setGuardianMiddleName(rs.getString(RegistrationTable.GUARDIAN_MIDDLENAME));
                    reg.setGuardianOccupation(rs.getString(RegistrationTable.GUARDIAN_OCCUPATION));
                    reg.setGuardianOfficePhoneNo(rs.getString(RegistrationTable.GUARDIAN_OFFICEPHONE_NO));
                    reg.setGuardianMobileNo(rs.getString(RegistrationTable.GUARDIAN_MOBILE_NO));
                    reg.setGuardianRelationToStudent(rs.getString(RegistrationTable.GUARDIAN_RELATION_TO_STUDENT));
                    reg.setIsGuardianContactInCaseEmergency(rs.getBoolean(RegistrationTable.ISGUARDIANCONTACTINCASEEMERGENCY));
                    reg.setSchoolLastAttended(rs.getString(RegistrationTable.SCHOOL_LAST_ATTENDED));
                    reg.setSchoolLastAttendedAddress(rs.getString(RegistrationTable.SCHOOL_LAST_ATTENDED_ADDRESS));
                    reg.setIsAdmissionComplete(rs.getBoolean(AdmissionTable.ISCOMPLETE));
                    reg.setAddressRoomOrHouseNo(rs.getString(RegistrationTable.ROOM_OR_HOUSE_NO));
                    reg.setAddressStreet(rs.getString(RegistrationTable.STREET));
                    reg.setAddressBrgyOrSubd(rs.getString(RegistrationTable.BRGY_OR_SUBD));
                    reg.setAddressCity(rs.getString(RegistrationTable.CITY));
                    
                    SchoolYear schoolYear = new SchoolYear();
                    schoolYear.setYearFrom(rs.getInt("yearFrom"));
                    schoolYear.setYearTo(rs.getInt("yearTo"));
                    
                    reg.setSchoolYear(schoolYear);
                    
                    int gradeLevel = Integer.parseInt( rs.getString(RegistrationTable.GRADELEVEL) );
                    reg.setGradeLevelNo(gradeLevel);
                    
                    reg.setRegistrationDate(rs.getDate("date_registered"));
                    
                    list.add(reg);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"getAllRegistrationInfo(): \n"+ e.getMessage());
        }
        return list;
    }

    @Override
    public Registration getRegistrationInfoById(int registrationId) {
        String SQLa = "{CALL getRegistrationInfoById(?)}";
        String SQLb = "{CALL getRegistrationCredentialsSubmitted(?)}";
        Registration registration = new Registration();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement getRegistrationInfoById = con.prepareCall(SQLa);
                CallableStatement getRegistrationCredentialsSubmitted = con.prepareCall(SQLb);) {
            getRegistrationInfoById.setInt(1, registrationId);
            getRegistrationCredentialsSubmitted.setInt(1, registrationId);
            try (ResultSet rsA = getRegistrationInfoById.executeQuery();) {
                while (rsA.next()) {
                    registration.setRegistrationId(rsA.getInt("registration_id"));
                    registration.setStudentType(rsA.getString("student_type"));
                    registration.setLastName(rsA.getString("lastname") );
                    registration.setFirstName(rsA.getString("firstname")); 
                    registration.setMiddleName(rsA.getString("middlename"));
                    registration.setBirthday(rsA.getDate("dob"));
                    registration.setPlaceOfBirth(rsA.getString("pob"));
                    registration.setNationality(rsA.getString("nationality"));
                    registration.setReligion(rsA.getString("religion"));
                    registration.setGender(rsA.getInt("gender")==1?"Male":"Female");
                    registration.setFatherFirstName(rsA.getString("father_firstname"));
                    registration.setFatherMiddleName(rsA.getString("father_middlename"));
                    registration.setFatherLastName(rsA.getString("father_lastname"));
                    registration.setFatherOccupation(rsA.getString("father_occupation"));
                    registration.setFatherOfficePhoneNo(rsA.getString("father_officephone_no"));
                    registration.setFatherMobileNo(rsA.getString("father_mobile_no"));
                    registration.setIsFatherContactInCaseEmergency(rsA.getBoolean("isFatherContactInCaseEmergency"));
                    registration.setMotherFirstName(rsA.getString("mother_firstname"));
                    registration.setMotherMiddleName(rsA.getString("mother_middlename"));
                    registration.setMotherLastName(rsA.getString("mother_lastname"));
                    registration.setMotherOccupation(rsA.getString("mother_occupation"));
                    registration.setMotherOfficePhoneNo(rsA.getString("mother_officephone_no"));
                    registration.setMotherMobileNo(rsA.getString("mother_mobile_no"));
                    registration.setIsMotherContactInCaseEmergency(rsA.getBoolean("isMotherContactInCaseEmergency"));
                    registration.setGuardianLastName(rsA.getString("guardian_lastname"));
                    registration.setGuardianFirstName(rsA.getString("guardian_firstname"));
                    registration.setGuardianMiddleName(rsA.getString("guardian_middlename"));
                    registration.setGuardianOccupation(rsA.getString("guardian_occupation"));
                    registration.setGuardianMobileNo(rsA.getString("guardian_mobile_no"));
                    registration.setGuardianRelationToStudent(rsA.getString("guardian_relation_to_student"));
                    registration.setIsGuardianContactInCaseEmergency(rsA.getBoolean("isGuardianContactInCaseEmergency"));
                    registration.setSchoolLastAttended(rsA.getString("school_last_attended"));
                    registration.setSchoolLastAttendedAddress(rsA.getString("school_last_attended_address"));
                    registration.setAddressRoomOrHouseNo(rsA.getString("room_or_house_no"));
                    registration.setAddressStreet(rsA.getString("street"));
                    registration.setAddressBrgyOrSubd(rsA.getString("brgy_or_subd"));
                    registration.setAddressCity(rsA.getString("city"));
                    registration.setRegion(rsA.getString("region"));
                    registration.setGradeLevelNo(rsA.getInt("gradelevel_no"));
                    registration.setSchoolYearYearFrom(rsA.getInt("schoolyear_yearfrom"));
                    registration.setRegistrationDate(rsA.getDate("date_registered"));
                   
                    String isAdmissionComplete = rsA.getString("isAdmissionComplete").trim();
                    registration.setIsAdmissionComplete(isAdmissionComplete.equalsIgnoreCase("Yes")? true : false);
                    registration.setIsRegistrationActive(rsA.getBoolean("is_registration_active"));
                    
                    List<Credential> credentialsSubmitted = new ArrayList<>();
                    try(ResultSet rsB = getRegistrationCredentialsSubmitted.executeQuery();){
                        while(rsB.next()){
                            Credential credential = new Credential();
                            credential.setCredentialId(rsB.getInt("credential_id"));
                            credential.setDateReceived(rsB.getDate("date_received"));
                            credential.setDateAdded(rsB.getDate("date_added"));
                            credential.setCredentialName(rsB.getString("credential_name"));
                            credential.setCredentialDescription(rsB.getString("credential_description"));
                            credential.setIsActive(rsB.getBoolean("is_credential_active"));
                            credentialsSubmitted.add(credential);
                        }
                    }
                    
                    registration.setCredentials(credentialsSubmitted);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registration;

    }//end of method

    @Override
    public String getRegistrationPaymentTermByStudentId(Integer studentId) {
        String SQL = "{CALL getRegistrationPaymentTermByStudentId(?)}";
        String paymentTerm = null;
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1, studentId);
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    paymentTerm = rs.getString("paymentterm");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getErrorCode()+"\n"+e.getMessage());
        }
        return paymentTerm;
    }
    
    @Override
    public boolean addRegistration(Registration r) {
        boolean isAdded = false;
        String SQLa = "{"
                + "CALL addRegistration"
                + "("
                + "?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?"
                + ") "
                + "}" ;
        
        String SQLb = "{CALL addRegistrationCredentialsSubmitted(?,?)}";
        
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);) {
            con.setAutoCommit(false);
            try (CallableStatement addRegistration = con.prepareCall(SQLa);
                    CallableStatement addRegistrationCredentialsSubmitted = con.prepareCall(SQLb);) {
                addRegistration.setString(1, r.getStudentType());
                addRegistration.setString(2, r.getLastName());
                addRegistration.setString(3, r.getFirstName());
                addRegistration.setString(4, r.getMiddleName());
                addRegistration.setObject(5, r.getBirthday());
                addRegistration.setString(6, r.getPlaceOfBirth());
                addRegistration.setString(7, r.getNationality());
                addRegistration.setString(8, r.getReligion());
                addRegistration.setInt(9, "Male".equals(r.getGender()) ? 1 : 0);
                addRegistration.setString(10, r.getFatherFirstName());
                addRegistration.setString(11, r.getFatherMiddleName());
                addRegistration.setString(12, r.getFatherLastName());
                addRegistration.setString(13, r.getFatherOccupation());
                addRegistration.setString(14, r.getFatherOfficePhoneNo());
                addRegistration.setString(15, r.getFatherMobileNo());
                addRegistration.setInt(16, r.getIsFatherContactInCaseEmergency() == true ? 1 : 0);
                addRegistration.setString(17, r.getMotherFirstName());
                addRegistration.setString(18, r.getMotherMiddleName());
                addRegistration.setString(19, r.getMotherLastName());
                addRegistration.setString(20, r.getMotherOccupation());
                addRegistration.setString(21, r.getMotherOfficePhoneNo());
                addRegistration.setString(22, r.getMotherMobileNo());
                addRegistration.setInt(23, r.getIsMotherContactInCaseEmergency() == true ? 1 : 0);
                addRegistration.setString(24, r.getGuardianLastName());
                addRegistration.setString(25, r.getGuardianFirstName());
                addRegistration.setString(26, r.getGuardianMiddleName());
                addRegistration.setString(27, r.getGuardianOccupation());
                addRegistration.setString(28, r.getGuardianMobileNo());
                addRegistration.setString(29, r.getGuardianRelationToStudent());
                addRegistration.setInt(30, r.getIsGuardianContactInCaseEmergency() == true ? 1 : 0);
                addRegistration.setString(31, r.getSchoolLastAttended());
                addRegistration.setString(32, r.getSchoolLastAttendedAddress());
                addRegistration.setString(33, r.getAddressRoomOrHouseNo());
                addRegistration.setString(34, r.getAddressStreet());
                addRegistration.setString(35, r.getAddressBrgyOrSubd());
                addRegistration.setString(36, r.getAddressCity());
                addRegistration.setString(37, r.getRegion());
                addRegistration.setInt(38,r.getGradeLevelNo());
                addRegistration.setInt(39, r.getSchoolYearYearFrom());
                addRegistration.registerOutParameter(40, Types.INTEGER);
                addRegistration.executeUpdate();
                int registrationId = addRegistration.getInt(40);
                
                for(Credential c : r.getCredentials()){
                    addRegistrationCredentialsSubmitted.setInt(1, registrationId);
                    addRegistrationCredentialsSubmitted.setInt(2, c.getCredentialId());
                    addRegistrationCredentialsSubmitted.executeUpdate();
                }
                
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
    public boolean updateRegistration(Registration registration) {
        boolean isUpdated = false;
        String SQLa = "{"
                + "CALL updateRegistration"
                + "("
                + "?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?,?"
                + ") "
                + "}";
        String SQLb = "{CALL deleteRegistrationCredentialBy(?)}";
        String SQLc = "{CALL addRegistrationCredentialsSubmitted(?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);) {
            con.setAutoCommit(false);
            try (CallableStatement updateRegistration = con.prepareCall(SQLa);
                    CallableStatement deleteRegistrationCredentialBy = con.prepareCall(SQLb);
                    CallableStatement addRegistrationCredentialsSubmitted = con.prepareCall(SQLc);) {
                updateRegistration.setString(1, registration.getStudentType());
                updateRegistration.setString(2, registration.getLastName());
                updateRegistration.setString(3, registration.getFirstName());
                updateRegistration.setString(4, registration.getMiddleName());
                updateRegistration.setObject(5, registration.getBirthday());
                updateRegistration.setString(6, registration.getPlaceOfBirth());
                updateRegistration.setString(7, registration.getNationality());
                updateRegistration.setString(8, registration.getReligion());
                updateRegistration.setInt(9, "Male".equals(registration.getGender()) ? 1 : 0);
                updateRegistration.setString(10, registration.getFatherFirstName());
                updateRegistration.setString(11, registration.getFatherMiddleName());
                updateRegistration.setString(12, registration.getFatherLastName());
                updateRegistration.setString(13, registration.getFatherOccupation());
                updateRegistration.setString(14, registration.getFatherOfficePhoneNo());
                updateRegistration.setString(15, registration.getFatherMobileNo());
                updateRegistration.setInt(16, registration.getIsFatherContactInCaseEmergency() == true ? 1 : 0);
                updateRegistration.setString(17, registration.getMotherFirstName());
                updateRegistration.setString(18, registration.getMotherMiddleName());
                updateRegistration.setString(19, registration.getMotherLastName());
                updateRegistration.setString(20, registration.getMotherOccupation());
                updateRegistration.setString(21, registration.getMotherOfficePhoneNo());
                updateRegistration.setString(22, registration.getMotherMobileNo());
                updateRegistration.setInt(23, registration.getIsMotherContactInCaseEmergency() == true ? 1 : 0);
                updateRegistration.setString(24, registration.getGuardianLastName());
                updateRegistration.setString(25, registration.getGuardianFirstName());
                updateRegistration.setString(26, registration.getGuardianMiddleName());
                updateRegistration.setString(27, registration.getGuardianOccupation());
                updateRegistration.setString(28, registration.getGuardianMobileNo());
                updateRegistration.setString(29, registration.getGuardianRelationToStudent());
                updateRegistration.setInt(30, registration.getIsGuardianContactInCaseEmergency() == true ? 1 : 0);
                updateRegistration.setString(31, registration.getSchoolLastAttended());
                updateRegistration.setString(32, registration.getSchoolLastAttendedAddress());
                updateRegistration.setString(33, registration.getAddressRoomOrHouseNo());
                updateRegistration.setString(34, registration.getAddressStreet());
                updateRegistration.setString(35, registration.getAddressBrgyOrSubd());
                updateRegistration.setString(36, registration.getAddressCity());
                updateRegistration.setString(37, registration.getRegion());
                updateRegistration.setInt(38,registration.getGradeLevelNo());
                updateRegistration.setInt(39, registration.getSchoolYearYearFrom());
                updateRegistration.setInt(40, registration.getRegistrationId());
                updateRegistration.setInt(41, registration.getIsRegistrationActive() == true? 1:0);
                updateRegistration.executeUpdate();
                
                deleteRegistrationCredentialBy.setInt(1, registration.getRegistrationId());
                deleteRegistrationCredentialBy.executeUpdate();
                
                for(Credential credential : registration.getCredentials()){
                    addRegistrationCredentialsSubmitted.setInt(1, registration.getRegistrationId());
                    addRegistrationCredentialsSubmitted.setInt(2, credential.getCredentialId());
                    addRegistrationCredentialsSubmitted.executeUpdate();
                }
                
                con.commit();
                isUpdated = true;
            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    @Override
    public boolean deleteRegistration(Registration registration) {
        boolean isDeleted = false;
        return isDeleted;
    }
 
     
}//end of class
