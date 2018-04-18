package daoimpl;

import utility.database.DBType;
import utility.database.DBUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.registration.Registration;
import model.student.Student;
import dao.IStudent;
import java.util.ArrayList;
import java.util.List;
import model.admission.Admission;
import model.faculty.Faculty;
import model.schoolyear.SchoolYear;
import model.section.Section;

public class StudentDaoImpl implements IStudent {

    private final AdmissionDaoImpl admissionDaoImpl;
    private final RegistrationDaoImpl registrationDaoImpl;
    private final GradeLevelDaoImpl gradeLevelDaoImpl;
    private final SchoolYearDaoImpl schoolYearDaoImpl;

    public StudentDaoImpl() {
        admissionDaoImpl = new AdmissionDaoImpl();
        registrationDaoImpl = new RegistrationDaoImpl();
        gradeLevelDaoImpl = new GradeLevelDaoImpl();
        schoolYearDaoImpl = new SchoolYearDaoImpl();
    }

    @Override
    public boolean studentExist(int studentNo) {
        boolean exists = false;
        String SQL = "{CALL studentExist(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, studentNo);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    exists = (rs.getInt("exist") == 1) ? true : false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    @Override
    public boolean hasTuitionRecord(int studentNo, int schoolyearId) {
        boolean hasTuitionRecord = false;
        String SQL = "{CALL hasTuitionRecord(?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, studentNo);
            cs.setInt(2, schoolyearId);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    hasTuitionRecord = rs.getBoolean("hasTuition");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hasTuitionRecord;
    }

    @Override
    public List<Student> getStudentsWithTuitionBalance() {
        List<Student> students = new ArrayList<>();
        String SQL = "{CALL getStudentsWithTuitionBalance()}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    Registration r = new Registration();
                    r.setLastName(rs.getString("lastname"));
                    r.setFirstName(rs.getString("firstname"));
                    r.setMiddleName(rs.getString("middlename"));
                    
                    Student s = new Student();
                    s.setStudentId(rs.getInt("student_id"));
                    s.setStudentNo(rs.getInt("student_no"));
                    s.setRegistration(r);
                    s.setHasTuitionBalance(true);
                    students.add(s);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    
    
    @Override
    public List<Student> getStudentsByKeyword(String keyword) {
        List<Student> students = new ArrayList<>();
        String SQL = "{CALL getStudentsByKeyword(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);) {
            try (CallableStatement csA = con.prepareCall(SQL);) {
                csA.setString(1, keyword);
                try (ResultSet rs = csA.executeQuery();) {
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
                        registration.setIsRegistrationActive(rs.getBoolean("is_registration_active"));

                        String isAdmissionComplete = rs.getString("isAdmissionComplete").trim();
                        registration.setIsAdmissionComplete(isAdmissionComplete.equalsIgnoreCase("1") ? true : false);

                        Admission admission = new Admission();
                        admission.setAdmissionId(rs.getInt("admission_id"));
                        admission.setIsCompleted(rs.getBoolean("isAdmissionComplete"));
                        
                        student.setStudentId(rs.getInt("student_id"));
                        student.setStudentNo(rs.getInt("student_no"));
                        student.setIsActive(rs.getBoolean("isStudentActive"));
                        student.setStudentType(rs.getString("finalStudentType").equalsIgnoreCase("O") == true ? 0 : 1);
                        student.setGradeLevelNo(rs.getInt("currentGradeLevel"));
                        student.setRegistration(registration);
                        student.setAdmission(admission);
                        
                        students.add(student);
                    }
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return students;
    }

    @Override
    public Student getStudentByStudentId(int studentId) {
        String SQLa = "{CALL getStudentByStudentId(?)}";
        Student student = new Student();
        Registration registration = new Registration();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);) {
            try (CallableStatement csA = con.prepareCall(SQLa);) {
                csA.setInt(1, studentId);
                try (ResultSet rs = csA.executeQuery();) {
                    while (rs.next()) {
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

                        student.setStudentId(rs.getInt("student_id"));
                        student.setStudentNo(rs.getInt("student_no"));
                        student.setIsActive(rs.getBoolean("isStudentActive"));
                        student.setStudentType(rs.getString("finalStudentType").equalsIgnoreCase("O") == true ? 0 : 1);
                        student.setGradeLevelNo(rs.getInt("currentGradeLevel"));
                        student.setRegistration(registration);
                        
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public Student getStudentByStudentNo(int studentNo) {
        String SQLa = "{CALL getStudentByStudentNo(?)}";
        String SQLb = "{CALL isStudentRecommendedToTakeSummer(?,?)}";
        String SQLc = "{CALL hasTuitionBalance(?)}";
        Student student = new Student();
        int studentId = 0;
        Registration r = new Registration();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);) {
            try (CallableStatement csA = con.prepareCall(SQLa);
                    CallableStatement csB = con.prepareCall(SQLb);
                    CallableStatement csC = con.prepareCall(SQLc);) {
                csA.setInt(1, studentNo);
                try (ResultSet rs = csA.executeQuery();) {
                    while (rs.next()) {
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

                        student.setStudentId(rs.getInt("student_id"));
                        student.setStudentNo(rs.getInt("student_no"));
                        student.setIsActive(rs.getBoolean("isStudentActive"));
                        student.setStudentType(rs.getString("finalStudentType").equalsIgnoreCase("O") == true ? 0 : 1);
                        student.setGradeLevelNo(rs.getInt("currentGradeLevel"));
                        student.setRegistration(r);
                        
                        studentId = rs.getInt("student_id");
                    }
                }
                
                csB.setInt(1, studentNo);
                csB.setInt(2, schoolYearDaoImpl.getCurrentSchoolYearId());
                try(ResultSet rsb = csB.executeQuery();){
                    while(rsb.next()){
                        student.setIsRecommendedToTakeSummer(rsb.getBoolean("isRecommendedToTakeSummer"));
                    }
                }
                
                csC.setInt(1, studentId);
                try(ResultSet rsc = csC.executeQuery();){
                    while(rsc.next()){
                        student.setHasTuitionBalance(rsc.getBoolean("hasTuitionBalance"));
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public List<Student> getStudentsBySectionAndSchoolYear(Section section, SchoolYear schoolYear) {
        String SQLa = "{CALL getStudentsBySectionIdAndSchoolYearId(?,?)}";
        List<Student> studentList = new ArrayList<>();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQLa);){
            cs.setInt(1,section.getSectionId());
            cs.setInt(2, schoolYear.getSchoolYearId());
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    Registration r = new Registration();
                    r.setLastName(rs.getString("lastname"));
                    r.setFirstName(rs.getString("firstname"));
                    r.setMiddleName(rs.getString("middlename"));
                    Student s = new Student();
                    s.setStudentId(rs.getInt("student_id"));
                    s.setStudentNo(rs.getInt("student_no"));
                    s.setRegistration(r);
                    
                    studentList.add(s);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    @Override
    public List<Student> getStudentsOfAdviser(Faculty faculty, SchoolYear schoolYear) {
        List<Student> studentList = new ArrayList<>();
        String SQL = "{CALL getStudentsOfAdviser(?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1,faculty.getFacultyID());
            cs.setInt(2,schoolYear.getSchoolYearId());
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    Registration r = new Registration();
                    r.setLastName(rs.getString("lastname"));
                    r.setFirstName(rs.getString("firstname"));
                    r.setMiddleName(rs.getString("middlename"));
                    
                    Section section = new Section();
                    section.setSectionId(rs.getInt("section_id"));
                    section.setSectionName(rs.getString("sectionName"));
                    
                    Student student = new Student();
                    student.setRegistration(r);
                    student.setSection(section);
                    student.setStudentId(rs.getInt("student_id"));
                    student.setGradeLevelNo(rs.getInt("grade_level"));
                    studentList.add(student);
                    student.setStudentNo(rs.getInt("student_no"));
                    
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }
    
    

}//end of class
