package daoimpl;

import dao.IEnrollment;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import model.enrollment.Enrollment;
import model.faculty.Faculty;
import model.gradelevel.GradeLevel;
import model.registration.Registration;
import model.schoolyear.SchoolYear;
import model.section.Section;
import model.student.Student;
import utility.database.DBType;
import utility.database.DBUtil;

/**
 *
 * @author John Ferdinand Antonio
 */
public class EnrollmentDaoImpl implements IEnrollment {

    private final SchoolYearDaoImpl schoolYearDaoImpl;

    public EnrollmentDaoImpl() {
        this.schoolYearDaoImpl = new SchoolYearDaoImpl();
    }

    @Override
    public boolean enroll(Student student) {
        boolean isEnrolled = false;
        GradeLevelDaoImpl gradeLevelDaoImpl = new GradeLevelDaoImpl();
        String SQLa = "{CALL addEnrollment(?,?,?,?,?)}";
        String SQLb = "{CALL activateStudent(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);) {
            try (CallableStatement csa = con.prepareCall(SQLa);
                    CallableStatement csb = con.prepareCall(SQLb);) {
                con.setAutoCommit(false);
                csa.setInt(1, student.getEnrollment().getSchoolYearId());
                csa.setInt(2, student.getStudentId());
                csa.setString(3, student.getEnrollment().getEnrollmentType().trim());
                csa.setInt(4, gradeLevelDaoImpl.getId(student.getGradeLevelNo()));
                csa.registerOutParameter(5, Types.INTEGER);
                csa.executeUpdate();
                int enrollmentId = csa.getInt(5);

                csb.setInt(1, student.getStudentId());
                csb.executeUpdate();
                con.commit();
                isEnrolled = true;
            } catch (SQLException e) {
                con.rollback();
                con.setAutoCommit(true);
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isEnrolled;
    }

    
    /**
     * Returns information of students who are enrolled (ACTIVE) in a specific schoolyear . 
     * Which means that, the information returned doesn't include students that are (NOT ACTIVE)
     * Information returned include ENROLLMENT details, REGISTRATION details, and STUDENT details.
     * All these information are encapsulated to instances of STUDENT class where ENROLLMENT and REGISTRATION
     * are fields of STUDENT class
     * @param schoolYearId
     * @return 
     * a List of Students. <code>List<Student></code>
     */
    @Override
    public List<Student> getAllEnrolledBySchoolYearId(int schoolYearId) {
        List<Student> studentList = new ArrayList<>();
        String SQL = "{CALL getAllEnrolledBySchoolYearId(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, schoolYearId);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Student student = new Student();
                    Registration registration = new Registration();
                    registration.setRegistrationId(rs.getInt("registration_id"));
                    registration.setStudentType(rs.getString("student_type"));
                    registration.setLastName(rs.getString("lastname"));
                    registration.setFirstName(rs.getString("firstname"));
                    registration.setMiddleName(rs.getString("middlename"));
                    registration.setBirthday(rs.getDate("dob"));
                    registration.setPlaceOfBirth(rs.getString("pob"));
                    registration.setNationality(rs.getString("nationality"));
                    registration.setReligion(rs.getString("religion"));
                    registration.setGender(rs.getInt("gender") == 1 ? "Male" : "Female");
                    registration.setFatherFirstName(rs.getString("father_firstname"));
                    registration.setFatherMiddleName(rs.getString("father_middlename"));
                    registration.setFatherLastName(rs.getString("father_lastname"));
                    registration.setFatherOccupation(rs.getString("father_occupation"));
                    registration.setFatherOfficePhoneNo(rs.getString("father_officephone_no"));
                    registration.setFatherMobileNo(rs.getString("father_mobile_no"));
                    registration.setIsFatherContactInCaseEmergency(rs.getBoolean("isFatherContactInCaseEmergency"));
                    registration.setMotherFirstName(rs.getString("mother_firstname"));
                    registration.setMotherMiddleName(rs.getString("mother_middlename"));
                    registration.setMotherLastName(rs.getString("mother_lastname"));
                    registration.setMotherOccupation(rs.getString("mother_occupation"));
                    registration.setMotherOfficePhoneNo(rs.getString("mother_officephone_no"));
                    registration.setMotherMobileNo(rs.getString("mother_mobile_no"));
                    registration.setIsMotherContactInCaseEmergency(rs.getBoolean("isMotherContactInCaseEmergency"));
                    registration.setGuardianLastName(rs.getString("guardian_lastname"));
                    registration.setGuardianFirstName(rs.getString("guardian_firstname"));
                    registration.setGuardianMiddleName(rs.getString("guardian_middlename"));
                    registration.setGuardianOccupation(rs.getString("guardian_occupation"));
                    registration.setGuardianMobileNo(rs.getString("guardian_mobile_no"));
                    registration.setGuardianRelationToStudent(rs.getString("guardian_relation_to_student"));
                    registration.setIsGuardianContactInCaseEmergency(rs.getBoolean("isGuardianContactInCaseEmergency"));
                    registration.setSchoolLastAttended(rs.getString("school_last_attended"));
                    registration.setSchoolLastAttendedAddress(rs.getString("school_last_attended_address"));
                    registration.setAddressRoomOrHouseNo(rs.getString("room_or_house_no"));
                    registration.setAddressStreet(rs.getString("street"));
                    registration.setAddressBrgyOrSubd(rs.getString("brgy_or_subd"));
                    registration.setAddressCity(rs.getString("city"));
                    registration.setRegion(rs.getString("region"));
                    registration.setGradeLevelNo(rs.getInt("gradelevel_no"));
                    registration.setSchoolYearYearFrom(rs.getInt("schoolyear_yearfrom"));
                    registration.setRegistrationDate(rs.getDate("date_registered"));

                    String isAdmissionComplete = rs.getString("isAdmissionComplete").trim();
                    registration.setIsAdmissionComplete(isAdmissionComplete.equalsIgnoreCase("Yes") ? true : false);
                    
                    Enrollment enrollment = new Enrollment();
                    enrollment.setEnrollmentId(rs.getInt("enrollment_id"));
                    enrollment.setSchoolYearId(rs.getInt("enrolledSchoolYearId"));
                    enrollment.setEnrollmentDate(rs.getDate("dateEnrolled"));
                    enrollment.setIsWithdrawn(rs.getBoolean("isEnrollmentWithdrawn"));
                    enrollment.setEnrollmentType(rs.getString("enrollment_type"));
                    
                    Faculty adviser = new Faculty();
                    adviser.setFacultyID(rs.getInt("adviser_id"));
                    adviser.setLastName(rs.getString("facultyLastName"));
                    adviser.setFirstName(rs.getString("facultyFirstName"));
                    adviser.setMiddleName(rs.getString("facultyMiddleName"));
                    
                    Section section = new Section();
                    section.setSectionName(rs.getString("sectionname"));
                    section.setAdviser(adviser);
                    
                    student.setStudentId(rs.getInt("student_id"));
                    student.setStudentNo(rs.getInt("student_no"));
                    student.setIsActive(rs.getBoolean("isStudentActive"));
                    student.setStudentType(rs.getString("finalStudentType").equalsIgnoreCase("O") == true ? 0 : 1);
                    student.setGradeLevelNo(rs.getInt("currentGradeLevel"));
                    student.setRegistration(registration);
                    student.setEnrollment(enrollment);
                    student.setSection(section);

                    studentList.add(student);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }
    
    @Override
    public List<Student> getAllEnrolledBySchoolYearIdAndGradeLevelId(int schoolYearId, int gradeLevelId) {
        List<Student> studentList = new ArrayList<>();
        String SQL = "{CALL getAllEnrolledBySchoolYearIdAndGradeLevelId(?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, schoolYearId);
            cs.setInt(2, gradeLevelId);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Student student = new Student();
                    Registration r = new Registration();
                    r.setRegistrationId(rs.getInt("registration_id"));
                    r.setStudentType(rs.getString("student_type"));
                    r.setLastName(rs.getString("lastname"));
                    r.setFirstName(rs.getString("firstname"));
                    r.setMiddleName(rs.getString("middlename"));
                    r.setBirthday(rs.getDate("dob"));
                    r.setPlaceOfBirth(rs.getString("pob"));
                    r.setNationality(rs.getString("nationality"));
                    r.setReligion(rs.getString("religion"));
                    r.setGender(rs.getInt("gender") == 1 ? "Male" : "Female");
                    r.setFatherFirstName(rs.getString("father_firstname"));
                    r.setFatherMiddleName(rs.getString("father_middlename"));
                    r.setFatherLastName(rs.getString("father_lastname"));
                    r.setFatherOccupation(rs.getString("father_occupation"));
                    r.setFatherOfficePhoneNo(rs.getString("father_officephone_no"));
                    r.setFatherMobileNo(rs.getString("father_mobile_no"));
                    r.setIsFatherContactInCaseEmergency(rs.getBoolean("isFatherContactInCaseEmergency"));
                    r.setMotherFirstName(rs.getString("mother_firstname"));
                    r.setMotherMiddleName(rs.getString("mother_middlename"));
                    r.setMotherLastName(rs.getString("mother_lastname"));
                    r.setMotherOccupation(rs.getString("mother_occupation"));
                    r.setMotherOfficePhoneNo(rs.getString("mother_officephone_no"));
                    r.setMotherMobileNo(rs.getString("mother_mobile_no"));
                    r.setIsMotherContactInCaseEmergency(rs.getBoolean("isMotherContactInCaseEmergency"));
                    r.setGuardianLastName(rs.getString("guardian_lastname"));
                    r.setGuardianFirstName(rs.getString("guardian_firstname"));
                    r.setGuardianMiddleName(rs.getString("guardian_middlename"));
                    r.setGuardianOccupation(rs.getString("guardian_occupation"));
                    r.setGuardianMobileNo(rs.getString("guardian_mobile_no"));
                    r.setGuardianRelationToStudent(rs.getString("guardian_relation_to_student"));
                    r.setIsGuardianContactInCaseEmergency(rs.getBoolean("isGuardianContactInCaseEmergency"));
                    r.setSchoolLastAttended(rs.getString("school_last_attended"));
                    r.setSchoolLastAttendedAddress(rs.getString("school_last_attended_address"));
                    r.setAddressRoomOrHouseNo(rs.getString("room_or_house_no"));
                    r.setAddressStreet(rs.getString("street"));
                    r.setAddressBrgyOrSubd(rs.getString("brgy_or_subd"));
                    r.setAddressCity(rs.getString("city"));
                    r.setRegion(rs.getString("region"));
                    r.setGradeLevelNo(rs.getInt("gradelevel_no"));
                    r.setSchoolYearYearFrom(rs.getInt("schoolyear_yearfrom"));
                    r.setRegistrationDate(rs.getDate("date_registered"));

                    String isAdmissionComplete = rs.getString("isAdmissionComplete").trim();
                    r.setIsAdmissionComplete(isAdmissionComplete.equalsIgnoreCase("Yes") ? true : false);
                    
                    Enrollment enrollment = new Enrollment();
                    enrollment.setEnrollmentId(rs.getInt("enrollment_id"));
                    enrollment.setSchoolYearId(rs.getInt("enrolledSchoolYearId"));
                    enrollment.setEnrollmentDate(rs.getDate("dateEnrolled"));
                    enrollment.setIsWithdrawn(rs.getBoolean("isEnrollmentWithdrawn"));
                    enrollment.setEnrollmentType(rs.getString("enrollment_type"));
                    
                    Faculty faculty = new Faculty();
                    faculty.setLastName(rs.getString("faculty_lastName"));
                    faculty.setFirstName(rs.getString("faculty_firstName"));
                    faculty.setMiddleName(rs.getString("faculty_middleName"));
                    
                    Section section = new Section();
                    section.setAdviser(faculty);
                    section.setSectionName(rs.getString("sectionName"));
                    
                    student.setStudentId(rs.getInt("student_id"));
                    student.setStudentNo(rs.getInt("student_no"));
                    student.setIsActive(rs.getBoolean("isStudentActive"));
                    student.setStudentType(rs.getString("finalStudentType").equalsIgnoreCase("O") == true ? 0 : 1);
                    student.setGradeLevelNo(rs.getInt("currentGradeLevel"));
                    student.setRegistration(r);
                    student.setEnrollment(enrollment);
                    student.setSection(section);

                    studentList.add(student);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }
    
    @Override
    public List<Student> getAllEnrolledBy(SchoolYear schoolYear, String wildCardChar) {
        List<Student> studentList = new ArrayList<>();
        String SQL = "{CALL getAllEnrolledBySchoolYearIdAndWildCard(?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, schoolYear.getSchoolYearId());
            cs.setString(2, wildCardChar);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Student student = new Student();
                    Registration r = new Registration();
                    r.setRegistrationId(rs.getInt("registration_id"));
                    r.setStudentType(rs.getString("student_type"));
                    r.setLastName(rs.getString("lastname"));
                    r.setFirstName(rs.getString("firstname"));
                    r.setMiddleName(rs.getString("middlename"));
                    r.setBirthday(rs.getDate("dob"));
                    r.setPlaceOfBirth(rs.getString("pob"));
                    r.setNationality(rs.getString("nationality"));
                    r.setReligion(rs.getString("religion"));
                    r.setGender(rs.getInt("gender") == 1 ? "Male" : "Female");
                    r.setFatherFirstName(rs.getString("father_firstname"));
                    r.setFatherMiddleName(rs.getString("father_middlename"));
                    r.setFatherLastName(rs.getString("father_lastname"));
                    r.setFatherOccupation(rs.getString("father_occupation"));
                    r.setFatherOfficePhoneNo(rs.getString("father_officephone_no"));
                    r.setFatherMobileNo(rs.getString("father_mobile_no"));
                    r.setIsFatherContactInCaseEmergency(rs.getBoolean("isFatherContactInCaseEmergency"));
                    r.setMotherFirstName(rs.getString("mother_firstname"));
                    r.setMotherMiddleName(rs.getString("mother_middlename"));
                    r.setMotherLastName(rs.getString("mother_lastname"));
                    r.setMotherOccupation(rs.getString("mother_occupation"));
                    r.setMotherOfficePhoneNo(rs.getString("mother_officephone_no"));
                    r.setMotherMobileNo(rs.getString("mother_mobile_no"));
                    r.setIsMotherContactInCaseEmergency(rs.getBoolean("isMotherContactInCaseEmergency"));
                    r.setGuardianLastName(rs.getString("guardian_lastname"));
                    r.setGuardianFirstName(rs.getString("guardian_firstname"));
                    r.setGuardianMiddleName(rs.getString("guardian_middlename"));
                    r.setGuardianOccupation(rs.getString("guardian_occupation"));
                    r.setGuardianMobileNo(rs.getString("guardian_mobile_no"));
                    r.setGuardianRelationToStudent(rs.getString("guardian_relation_to_student"));
                    r.setIsGuardianContactInCaseEmergency(rs.getBoolean("isGuardianContactInCaseEmergency"));
                    r.setSchoolLastAttended(rs.getString("school_last_attended"));
                    r.setSchoolLastAttendedAddress(rs.getString("school_last_attended_address"));
                    r.setAddressRoomOrHouseNo(rs.getString("room_or_house_no"));
                    r.setAddressStreet(rs.getString("street"));
                    r.setAddressBrgyOrSubd(rs.getString("brgy_or_subd"));
                    r.setAddressCity(rs.getString("city"));
                    r.setRegion(rs.getString("region"));
                    r.setGradeLevelNo(rs.getInt("gradelevel_no"));
                    r.setSchoolYearYearFrom(rs.getInt("schoolyear_yearfrom"));
                    r.setRegistrationDate(rs.getDate("date_registered"));

                    String isAdmissionComplete = rs.getString("isAdmissionComplete").trim();
                    r.setIsAdmissionComplete(isAdmissionComplete.equalsIgnoreCase("Yes") ? true : false);
                    
                    Enrollment enrollment = new Enrollment();
                    enrollment.setEnrollmentId(rs.getInt("enrollment_id"));
                    enrollment.setSchoolYearId(rs.getInt("enrolledSchoolYearId"));
                    enrollment.setEnrollmentDate(rs.getDate("dateEnrolled"));
                    enrollment.setIsWithdrawn(rs.getBoolean("isEnrollmentWithdrawn"));
                    enrollment.setEnrollmentType(rs.getString("enrollment_type"));
                    
                    Faculty faculty = new Faculty();
                    faculty.setLastName(rs.getString("faculty_lastName"));
                    faculty.setFirstName(rs.getString("faculty_firstName"));
                    faculty.setMiddleName(rs.getString("faculty_middleName"));
                    
                    Section section = new Section();
                    section.setAdviser(faculty);
                    section.setSectionName(rs.getString("sectionName"));
                    
                    student.setStudentId(rs.getInt("student_id"));
                    student.setStudentNo(rs.getInt("student_no"));
                    student.setIsActive(rs.getBoolean("isStudentActive"));
                    student.setStudentType(rs.getString("finalStudentType").equalsIgnoreCase("O") == true ? 0 : 1);
                    student.setGradeLevelNo(rs.getInt("currentGradeLevel"));
                    student.setRegistration(r);
                    student.setEnrollment(enrollment);
                    student.setSection(section);

                    studentList.add(student);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }
    

    /**
     * Gets all student record of <b>ACTIVE</b> students who doesn't have any SECTION yet 
     * for the supplied schoolyearId.
     * Students who has already been assigned with section are stored in <code>student_section</code>
     * table of the database. 
     * @param gradelevelId
     * @param schoolYearId
     * @return 
     */
    @Override
    public List<Student> getAllEnrolledUnsectionedByGradeLevelIdAndSchoolYearId(int gradelevelId, int schoolYearId) {
        String SQL = "{CALL getAllEnrolledUnsectionedByGradeLevelIdAndSchoolYearId(?,?)}";
        List<Student> studentList = new ArrayList<>();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, gradelevelId);
            cs.setInt(2, schoolYearId);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Student student = new Student();
                    Registration r = new Registration();
                    r.setRegistrationId(rs.getInt("registration_id"));
                    r.setStudentType(rs.getString("student_type"));
                    r.setLastName(rs.getString("lastname"));
                    r.setFirstName(rs.getString("firstname"));
                    r.setMiddleName(rs.getString("middlename"));
                    r.setBirthday(rs.getDate("dob"));
                    r.setPlaceOfBirth(rs.getString("pob"));
                    r.setNationality(rs.getString("nationality"));
                    r.setReligion(rs.getString("religion"));
                    r.setGender(rs.getInt("gender") == 1 ? "Male" : "Female");
                    r.setFatherFirstName(rs.getString("father_firstname"));
                    r.setFatherMiddleName(rs.getString("father_middlename"));
                    r.setFatherLastName(rs.getString("father_lastname"));
                    r.setFatherOccupation(rs.getString("father_occupation"));
                    r.setFatherOfficePhoneNo(rs.getString("father_officephone_no"));
                    r.setFatherMobileNo(rs.getString("father_mobile_no"));
                    r.setIsFatherContactInCaseEmergency(rs.getBoolean("isFatherContactInCaseEmergency"));
                    r.setMotherFirstName(rs.getString("mother_firstname"));
                    r.setMotherMiddleName(rs.getString("mother_middlename"));
                    r.setMotherLastName(rs.getString("mother_lastname"));
                    r.setMotherOccupation(rs.getString("mother_occupation"));
                    r.setMotherOfficePhoneNo(rs.getString("mother_officephone_no"));
                    r.setMotherMobileNo(rs.getString("mother_mobile_no"));
                    r.setIsMotherContactInCaseEmergency(rs.getBoolean("isMotherContactInCaseEmergency"));
                    r.setGuardianLastName(rs.getString("guardian_lastname"));
                    r.setGuardianFirstName(rs.getString("guardian_firstname"));
                    r.setGuardianMiddleName(rs.getString("guardian_middlename"));
                    r.setGuardianOccupation(rs.getString("guardian_occupation"));
                    r.setGuardianMobileNo(rs.getString("guardian_mobile_no"));
                    r.setGuardianRelationToStudent(rs.getString("guardian_relation_to_student"));
                    r.setIsGuardianContactInCaseEmergency(rs.getBoolean("isGuardianContactInCaseEmergency"));
                    r.setSchoolLastAttended(rs.getString("school_last_attended"));
                    r.setSchoolLastAttendedAddress(rs.getString("school_last_attended_address"));
                    r.setAddressRoomOrHouseNo(rs.getString("room_or_house_no"));
                    r.setAddressStreet(rs.getString("street"));
                    r.setAddressBrgyOrSubd(rs.getString("brgy_or_subd"));
                    r.setAddressCity(rs.getString("city"));
                    r.setRegion(rs.getString("region"));
                    r.setGradeLevelNo(rs.getInt("gradelevel_no"));
                    r.setSchoolYearYearFrom(rs.getInt("schoolyear_yearfrom"));
                    r.setRegistrationDate(rs.getDate("date_registered"));

                    String isAdmissionComplete = rs.getString("isAdmissionComplete").trim();
                    r.setIsAdmissionComplete(isAdmissionComplete.equalsIgnoreCase("Yes") ? true : false);
                    
                    Enrollment enrollment = new Enrollment();
                    enrollment.setEnrollmentId(rs.getInt("enrollment_id"));
                    enrollment.setSchoolYearId(rs.getInt("enrolledSchoolYearId"));
                    enrollment.setEnrollmentDate(rs.getDate("dateEnrolled"));
                    enrollment.setIsWithdrawn(rs.getBoolean("isEnrollmentWithdrawn"));
                    enrollment.setEnrollmentType(rs.getString("enrollment_type"));
                    
                    student.setStudentId(rs.getInt("student_id"));
                    student.setStudentNo(rs.getInt("student_no"));
                    student.setIsActive(rs.getBoolean("isStudentActive"));
                    student.setStudentType(rs.getString("finalStudentType").equalsIgnoreCase("O") == true ? 0 : 1);
                    student.setGradeLevelNo(rs.getInt("currentGradeLevel"));
                    student.setRegistration(r);
                    student.setEnrollment(enrollment);

                    studentList.add(student);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    @Override
    public List<Student> getAllUnsectionedSummerEnrolleesBy(GradeLevel gradeLevel, SchoolYear schoolYear, String sectionType) {
        String SQL = "{CALL getAllUnsectionedSummerEnrolleesBy(?,?,?)}";
        List<Student> studentList = new ArrayList<>();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, gradeLevel.getGradeLevelId());
            cs.setInt(2, schoolYear.getSchoolYearId());
            cs.setString(3, sectionType.trim());
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Student student = new Student();
                    Registration r = new Registration();
                    r.setRegistrationId(rs.getInt("registration_id"));
                    r.setStudentType(rs.getString("student_type"));
                    r.setLastName(rs.getString("lastname"));
                    r.setFirstName(rs.getString("firstname"));
                    r.setMiddleName(rs.getString("middlename"));
                    r.setBirthday(rs.getDate("dob"));
                    r.setPlaceOfBirth(rs.getString("pob"));
                    r.setNationality(rs.getString("nationality"));
                    r.setReligion(rs.getString("religion"));
                    r.setGender(rs.getInt("gender") == 1 ? "Male" : "Female");
                    r.setFatherFirstName(rs.getString("father_firstname"));
                    r.setFatherMiddleName(rs.getString("father_middlename"));
                    r.setFatherLastName(rs.getString("father_lastname"));
                    r.setFatherOccupation(rs.getString("father_occupation"));
                    r.setFatherOfficePhoneNo(rs.getString("father_officephone_no"));
                    r.setFatherMobileNo(rs.getString("father_mobile_no"));
                    r.setIsFatherContactInCaseEmergency(rs.getBoolean("isFatherContactInCaseEmergency"));
                    r.setMotherFirstName(rs.getString("mother_firstname"));
                    r.setMotherMiddleName(rs.getString("mother_middlename"));
                    r.setMotherLastName(rs.getString("mother_lastname"));
                    r.setMotherOccupation(rs.getString("mother_occupation"));
                    r.setMotherOfficePhoneNo(rs.getString("mother_officephone_no"));
                    r.setMotherMobileNo(rs.getString("mother_mobile_no"));
                    r.setIsMotherContactInCaseEmergency(rs.getBoolean("isMotherContactInCaseEmergency"));
                    r.setGuardianLastName(rs.getString("guardian_lastname"));
                    r.setGuardianFirstName(rs.getString("guardian_firstname"));
                    r.setGuardianMiddleName(rs.getString("guardian_middlename"));
                    r.setGuardianOccupation(rs.getString("guardian_occupation"));
                    r.setGuardianMobileNo(rs.getString("guardian_mobile_no"));
                    r.setGuardianRelationToStudent(rs.getString("guardian_relation_to_student"));
                    r.setIsGuardianContactInCaseEmergency(rs.getBoolean("isGuardianContactInCaseEmergency"));
                    r.setSchoolLastAttended(rs.getString("school_last_attended"));
                    r.setSchoolLastAttendedAddress(rs.getString("school_last_attended_address"));
                    r.setAddressRoomOrHouseNo(rs.getString("room_or_house_no"));
                    r.setAddressStreet(rs.getString("street"));
                    r.setAddressBrgyOrSubd(rs.getString("brgy_or_subd"));
                    r.setAddressCity(rs.getString("city"));
                    r.setRegion(rs.getString("region"));
                    r.setGradeLevelNo(rs.getInt("gradelevel_no"));
                    r.setSchoolYearYearFrom(rs.getInt("schoolyear_yearfrom"));
                    r.setRegistrationDate(rs.getDate("date_registered"));

                    String isAdmissionComplete = rs.getString("isAdmissionComplete").trim();
                    r.setIsAdmissionComplete(isAdmissionComplete.equalsIgnoreCase("Yes") ? true : false);
                    
                    Enrollment enrollment = new Enrollment();
                    enrollment.setEnrollmentId(rs.getInt("enrollment_id"));
                    enrollment.setSchoolYearId(rs.getInt("enrolledSchoolYearId"));
                    enrollment.setEnrollmentDate(rs.getDate("dateEnrolled"));
                    enrollment.setIsWithdrawn(rs.getBoolean("isEnrollmentWithdrawn"));
                    enrollment.setEnrollmentType(rs.getString("enrollment_type"));
                    
                    student.setStudentId(rs.getInt("student_id"));
                    student.setStudentNo(rs.getInt("student_no"));
                    student.setIsActive(rs.getBoolean("isStudentActive"));
                    student.setStudentType(rs.getString("finalStudentType").equalsIgnoreCase("O") == true ? 0 : 1);
                    student.setGradeLevelNo(rs.getInt("currentGradeLevel"));
                    student.setRegistration(r);
                    student.setEnrollment(enrollment);

                    studentList.add(student);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

}
