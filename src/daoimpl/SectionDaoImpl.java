package daoimpl;

import dao.ISection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.faculty.Faculty;
import model.gradelevel.GradeLevel;
import model.registration.Registration;
import model.schoolyear.SchoolYear;
import model.section.Section;
import model.student.Student;
import utility.database.DBType;
import utility.database.DBUtil;


public class SectionDaoImpl implements ISection {

    private GradeLevelDaoImpl gradeLevelDaoImpl;
    
    public SectionDaoImpl(){
        gradeLevelDaoImpl = new GradeLevelDaoImpl();
    }
    
    //Jordan's
    
    @Override
    public boolean addSection(Section section) {
        boolean isAdded = false;
        String SQLa = "{CALL addSection(?,?)}";
        String SQLb = "{CALL addSectionSetting(?,?,?,?,?,?,?)}"; //add a 5th parameter to store faculty/adviser id when faculty module is finished
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);) {
            con.setAutoCommit(false);
            try (CallableStatement csa = con.prepareCall(SQLa);
                    CallableStatement csb = con.prepareCall(SQLb);) {
                csa.setString(1, section.getSectionName());
                csa.registerOutParameter(2, Types.INTEGER);
                csa.executeUpdate();
                int sectionId = csa.getInt(2);

                csb.setInt(1, sectionId);
                csb.setInt(2, section.getSchoolYear().getSchoolYearId());
                csb.setInt(3, section.getGradeLevel().getGradeLevelId());
                csb.setString(4, section.getSectionSession());
                csb.setInt(5, section.getAdviser().getFacultyID());
                csb.setInt(6, section.getCapacity());
                csb.setString(7, section.getSectionType());
                
                csb.executeUpdate();
                con.commit();
                isAdded = true;
            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAdded;
    }

    
    
    @Override
    public boolean addStudentsToSection(Section section) {
        boolean isSuccessful = false;
        SchoolYearDaoImpl schoolYearDaoImpl = new SchoolYearDaoImpl();
        String SQLa = "{CALL removeStudentsFromSectionBySectionIdandSchoolYearId(?,?) }";
        String SQLb = "{CALL addStudentToSection(?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);){
            con.setAutoCommit(false);
            try (CallableStatement csa = con.prepareCall(SQLa);
                    CallableStatement csb = con.prepareCall(SQLb);){
                csa.setInt(1, section.getSectionId());
                csa.setInt(2, schoolYearDaoImpl.getCurrentSchoolYearId());
                csa.executeUpdate();
                for(Student s : section.getStudents()){
                    csb.setInt(1, s.getStudentId());
                    csb.setInt(2, section.getSectionId());
                    csb.setInt(3, schoolYearDaoImpl.getCurrentSchoolYearId());
                    csb.executeUpdate();
                }
                con.commit();
                isSuccessful = true;
            } catch (SQLException e) {
                con.rollback();
                con.setAutoCommit(true);
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccessful;
    }

    @Override
    public boolean updateSection(Section section) {
        boolean isUpdated = false;
        String SQLa = "{CALL updateSection(?,?,?)}";
        String SQLb = "{CALL updateSectionSettings(?,?,?,?,?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);) {
            con.setAutoCommit(false);
            try (CallableStatement updateSection = con.prepareCall(SQLa);
                    CallableStatement updateSectionSettings = con.prepareCall(SQLb);) {
                
                updateSection.setInt(1,section.getSectionId());
                updateSection.setString(2, section.getSectionName());
                updateSection.setBoolean(3, section.getIsActive());
                updateSection.executeUpdate();
                
                updateSectionSettings.setInt(1, section.getSectionId());
                updateSectionSettings.setInt(2, section.getSchoolYear().getSchoolYearId());
                updateSectionSettings.setInt(3, section.getGradeLevel().getGradeLevelId());
                updateSectionSettings.setString(4, section.getSectionSession().trim());
                updateSectionSettings.setInt(5,section.getAdviser().getFacultyID());
                updateSectionSettings.setInt(6, section.getCapacity());
                updateSectionSettings.setString(7, section.getSectionType());
                updateSectionSettings.executeUpdate();
                
                con.commit();
                isUpdated = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    @Override
    public List<Student> getSectionStudentsOf(Section section) {
//        JOptionPane.showMessageDialog(null,"Section Id: "+section.getSectionId());
//        JOptionPane.showMessageDialog(null,"Section SchoolYearId: "+section.getSchoolYear().getSchoolYearId());
        List<Student> studentList = new ArrayList<>();
        String SQL = "{CALL getSectionStudentsBySectionIdAndSchoolYearId(?,?)}";
        int sectionId = section.getSectionId();
        int schoolYearId = section.getSchoolYear().getSchoolYearId();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, sectionId);
            cs.setInt(2, schoolYearId);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Registration r = new Registration();
                    r.setLastName(rs.getString("lastname"));
                    r.setFirstName(rs.getString("firstname"));
                    r.setMiddleName(rs.getString("middlename"));

                    Student s = new Student();
                    s.setRegistration(r);
                    s.setStudentId(rs.getInt("student_id"));
                    s.setStudentNo(rs.getInt("student_no"));
                    s.setStudentType(rs.getString("finalStudentType").equalsIgnoreCase("O") == true ? 0 : 1);
                    s.setGradeLevelNo(rs.getInt("currentGradeLevel"));

                    studentList.add(s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

    @Override
    public List<Section> getAllSections() {
        List<Section> sectionList = new ArrayList<>();
        String SQL = "{CALL getAllSections()}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Section section = new Section();
                    section.setSectionId(rs.getInt("section_id"));
                    SchoolYear schoolYear = new SchoolYear();
                    schoolYear.setYearFrom(rs.getInt("yearFrom"));
                    section.setSchoolYear(schoolYear);
                    section.setSectionName(rs.getString("sectionName"));
                    section.setIsActive(rs.getBoolean("isActive"));
                    section.setDateCreated(rs.getString("date_created"));
                    
                    GradeLevel gradeLevel = new GradeLevel();
                    gradeLevel.setGradeLevelID(rs.getInt("gradelevel_id"));
                    gradeLevel.setLevelNo(rs.getInt("grade_level"));

                    Faculty adviser = new Faculty();
                    adviser.setFacultyID(rs.getInt("faculty_id"));
                    adviser.setLastName(rs.getString("lastName"));
                    adviser.setFirstName(rs.getString("firstName"));
                    adviser.setMiddleName(rs.getString("middleName"));
                    
                    section.setGradeLevel(gradeLevel);
                    section.setAdviser(adviser);
                    section.setSectionSession(rs.getString("session"));
                    section.setCapacity(rs.getInt("capacity"));
                    section.setSectionType(rs.getString("section_type"));
                    
                    sectionList.add(section);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sectionList;
    }

    @Override
    public List<Section> getSectionsWithNoAssignedScheduleByStatusAndSchoolYearId(boolean isActive, int schoolyearId) {
        String SQL = "{CALL getSectionsWithNoAssignedScheduleByStatusAndSchoolYearId(?,?)}";
        List<Section> sectionList = new ArrayList<>();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, isActive == true ? 1 : 0);
            cs.setInt(2, schoolyearId);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Section section = new Section();
                    section.setSectionId(rs.getInt("section_id"));
                    section.setSectionName(rs.getString("sectionName"));
                    section.setIsActive(rs.getBoolean("isActive"));
                    section.setDateCreated(rs.getString("date_created"));
                    section.setSectionSession(rs.getString("session"));
                    sectionList.add(section);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sectionList;
    }
    
    
    @Override
    public List<Section> getSectionsWithNoAssignedScheduleBy_Status_SchoolYearId_GradeLevelId(boolean isActive, int schoolyearId, int gradeLevelId) {
        String SQL = "{CALL getSectionsWithNoAssignedScheduleBySchoolYearIdGradeLevelId(?,?,?)}";
        List<Section> sectionList = new ArrayList<>();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, isActive == true ? 1 : 0);
            cs.setInt(2, schoolyearId);
            cs.setInt(3, gradeLevelId);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Section section = new Section();
                    section.setSectionId(rs.getInt("section_id"));
                    section.setSectionName(rs.getString("sectionName"));
                    section.setIsActive(rs.getBoolean("isActive"));
                    section.setDateCreated(rs.getString("date_created"));
                    section.setSectionSession(rs.getString("session"));
                    section.setSectionType(rs.getString("section_type"));
                    sectionList.add(section);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sectionList;
    }
            
            
    @Override
    public List<Section> getSectionsBy(boolean status, int schoolYearId) {
        List<Section> sectionList = new ArrayList<>();
        String SQL = "{CALL getAllSectionsByStatusAndSchoolYear(?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, status==true? 1: 0);
            cs.setInt(2, schoolYearId);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Section s = new Section();
                    s.setSectionId(rs.getInt("section_id"));
                    SchoolYear schoolYear = new SchoolYear();
                    schoolYear.setYearFrom(rs.getInt("yearFrom"));
                    s.setSchoolYear(schoolYear);
                    s.setSectionName(rs.getString("sectionName"));
                    s.setIsActive(rs.getBoolean("isActive"));
                    s.setDateCreated(rs.getString("date_created"));
                    
                    GradeLevel gradeLevel = new GradeLevel();
                    gradeLevel.setLevelNo(rs.getInt("grade_level"));

                    Faculty adviser = new Faculty();
                    adviser.setFacultyID(rs.getInt("faculty_id"));
                    adviser.setLastName(rs.getString("lastName"));
                    adviser.setFirstName(rs.getString("firstName"));
                    adviser.setMiddleName(rs.getString("middleName"));
                    
                    s.setGradeLevel(gradeLevel);
                    s.setAdviser(adviser);
                    s.setSectionSession(rs.getString("session"));
                    s.setCapacity(rs.getInt("capacity"));
                    
                    sectionList.add(s);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sectionList;
    }
    
    @Override
    public List<Section> getSectionsBy(String wildCardChar) {
        List<Section> sectionList = new ArrayList<>();
        String SQL = "{CALL getSectionsByWildCard(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setString(1,wildCardChar);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Section section = new Section();
                    section.setSectionId(rs.getInt("section_id"));
                    SchoolYear schoolYear = new SchoolYear();
                    schoolYear.setYearFrom(rs.getInt("yearFrom"));
                    section.setSchoolYear(schoolYear);
                    section.setSectionName(rs.getString("sectionName"));
                    section.setIsActive(rs.getBoolean("isActive"));
                    section.setDateCreated(rs.getString("date_created"));
                    
                    GradeLevel gradeLevel = new GradeLevel();
                    gradeLevel.setLevelNo(rs.getInt("grade_level"));
                    
                    Faculty adviser = new Faculty();
                    adviser.setFacultyID(rs.getInt("faculty_id"));
                    adviser.setLastName(rs.getString("lastName"));
                    adviser.setFirstName(rs.getString("firstName"));
                    adviser.setMiddleName(rs.getString("middleName"));
                    
                    section.setGradeLevel(gradeLevel);
                    section.setSectionSession(rs.getString("session"));
                    section.setAdviser(adviser);
                    section.setCapacity(rs.getInt("capacity"));
                    section.setSectionType(rs.getString("section_type"));
                    
                    sectionList.add(section);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sectionList;
    }

    @Override
    public List<Section> getSectionsBy(int gradeLevelNo, int schoolYearId) {
        List<Section> sectionList = new ArrayList<>();
        String SQL = "{CALL getSectionsByGradeLevelNoAndSchoolYearId(?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1,gradeLevelNo);
            cs.setInt(2,schoolYearId);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Section s = new Section();
                    s.setSectionId(rs.getInt("section_id"));
                    SchoolYear schoolYear = new SchoolYear();
                    schoolYear.setYearFrom(rs.getInt("yearFrom"));
                    s.setSchoolYear(schoolYear);
                    s.setSectionName(rs.getString("sectionName"));
                    s.setIsActive(rs.getBoolean("isActive"));
                    s.setDateCreated(rs.getString("date_created"));
                    
                    GradeLevel gradeLevel = new GradeLevel();
                    gradeLevel.setLevelNo(rs.getInt("grade_level"));
                    
                    Faculty adviser = new Faculty();
                    adviser.setFacultyID(rs.getInt("faculty_id"));
                    adviser.setLastName(rs.getString("lastName"));
                    adviser.setFirstName(rs.getString("firstName"));
                    adviser.setMiddleName(rs.getString("middleName"));
                    
                    s.setGradeLevel(gradeLevel);
                    s.setSectionSession(rs.getString("session"));
                    s.setAdviser(adviser);
                    s.setCapacity(rs.getInt("capacity"));
                    s.setSectionType(rs.getString("section_type"));
                    
                    sectionList.add(s);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sectionList;
    }
    

    @Override
    public Section getSectionById(int sectionId) {
        Section section = new Section();
        String SQL = "{CALL getSectionById(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, sectionId);
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    section.setSectionId(rs.getInt("section_id"));
                    SchoolYear schoolYear = new SchoolYear();
                    schoolYear.setYearFrom(rs.getInt("yearFrom"));
                    section.setSchoolYear(schoolYear);
                    section.setSectionName(rs.getString("sectionName"));
                    section.setIsActive(rs.getBoolean("isActive"));
                    section.setDateCreated(rs.getString("date_created"));

                    GradeLevel gradeLevel = new GradeLevel();
                    gradeLevel.setLevelNo(rs.getInt("grade_level"));
                    
                    Faculty adviser = new Faculty();
                    adviser.setFacultyID(rs.getInt("faculty_id"));
                    adviser.setLastName(rs.getString("lastName"));
                    adviser.setFirstName(rs.getString("firstName"));
                    adviser.setMiddleName(rs.getString("middleName"));
                    
                    section.setGradeLevel(gradeLevel);
                    section.setAdviser(adviser);
                    section.setSectionSession(rs.getString("session"));
                    section.setCapacity(rs.getInt("capacity"));
                    section.setSectionType(rs.getString("section_type"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return section;
    }
    
    
    
    //End of Jordan's

    @Override
    public boolean sectionExists(String sectionName) {
        boolean exists = false;
        String SQL = "{CALL sectionExists(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setString(1, sectionName.toLowerCase().trim());
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    exists = rs.getBoolean("sectionExists");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    @Override
    public List<Section> getNonAdvisorySectionsOf(Faculty faculty, SchoolYear schoolYear) {
        String SQL = "{CALL getSectionsHandledByFacultyUsingFacultyIdAndSyId(?,?)}";
        List<Section> sectionList = new ArrayList<>();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1,faculty.getFacultyID());
            cs.setInt(2,schoolYear.getSchoolYearId());
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Section section = new Section();
                    section.setSectionId(rs.getInt("section_id"));
                    section.setSectionName(rs.getString("sectionName"));
                    section.setIsActive(rs.getBoolean("isActive"));
                    section.setDateCreated(rs.getString("date_created"));
                    
                    GradeLevel gradeLevel = new GradeLevel();
                    gradeLevel.setGradeLevelID(rs.getInt("gradelevel_id"));
                    gradeLevel.setLevelNo(rs.getInt("grade_level"));
                    
                    Faculty adviser = new Faculty();
                    adviser.setFacultyID(rs.getInt("faculty_id"));
                    adviser.setLastName(rs.getString("lastName"));
                    adviser.setFirstName(rs.getString("firstName"));
                    adviser.setMiddleName(rs.getString("middleName"));
                    
                    section.setGradeLevel(gradeLevel);
                    section.setSectionSession(rs.getString("session"));
                    section.setAdviser(adviser);
                    section.setCapacity(rs.getInt("capacity"));
                    
                    sectionList.add(section);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sectionList;
    }

    @Override
    public List<Section> getAdvisorySectionsOf(Faculty faculty, SchoolYear schoolYear) {
        String SQL = "{CALL getAdvisorySectionsOfFaculty(?,?)}";
        List<Section> sectionList = new ArrayList<>();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1, schoolYear.getSchoolYearId());
            cs.setInt(2, faculty.getFacultyID());
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    GradeLevel gradeLevel = new GradeLevel();
                    gradeLevel.setGradeLevelID(rs.getInt("gradelevel_id"));
                    gradeLevel.setLevelNo(rs.getInt("grade_level"));
                    gradeLevel.setAgeFrom(rs.getInt("ageFrom"));
                    gradeLevel.setAgeTo(rs.getInt("ageTo"));
                    
                    Section section = new Section();
                    section.setSectionId(rs.getInt("section_id"));
                    section.setSectionName(rs.getString("sectionName"));
                    section.setIsActive(rs.getBoolean("isActive"));
                    section.setDateCreated(rs.getString("date_created"));
                    section.setSectionSession(rs.getString("session"));
                    section.setCapacity(rs.getInt("capacity"));
                    section.setSectionType(rs.getString("section_type"));
                    section.setSchoolYear(schoolYear);
                    section.setGradeLevel(gradeLevel);
                    sectionList.add(section);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sectionList;
    }
    
    
    @Override
    public Section getSectionOf(Student student, SchoolYear schoolYear) {
        String SQL ="{CALL getSectionByStudentIdAndSchoolYear(?,?)}";
//        JOptionPane.showMessageDialog(null,"StudentId: "+student.getStudentId());
//        JOptionPane.showMessageDialog(null,"SchoolYearId: "+schoolYear.getSchoolYearId());
        Section section = new Section();
        try(Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1,student.getStudentId());
            cs.setInt(2,schoolYear.getSchoolYearId());
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    section.setSectionId(rs.getInt("section_id"));
                    section.setSectionName(rs.getString("sectionName"));
                    section.setIsActive(rs.getBoolean("isActive"));
                    section.setDateCreated(rs.getString("date_created"));
                    
                    GradeLevel gradeLevel = new GradeLevel();
                    gradeLevel.setGradeLevelID(rs.getInt("gradelevel_id"));
                    gradeLevel.setLevelNo(rs.getInt("grade_level"));
                    
                    Faculty adviser = new Faculty();
                    adviser.setFacultyID(rs.getInt("faculty_id"));
                    adviser.setLastName(rs.getString("lastName"));
                    adviser.setFirstName(rs.getString("firstName"));
                    adviser.setMiddleName(rs.getString("middleName"));
                    
                    section.setGradeLevel(gradeLevel);
                    section.setSectionSession(rs.getString("session"));
                    section.setAdviser(adviser);
                    section.setCapacity(rs.getInt("capacity"));
                    section.setSectionType(rs.getString("section_type"));
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return section;
    }

    @Override
    public List<Section> getSectionsBy(GradeLevel gradeLevel, String sectionType, SchoolYear schoolYear) {
        String SQL = "{CALL getSectionsByGradeLevelNoSectionTypeAndSchoolYear(?,?,?)}";
        List<Section> sectionList = new ArrayList<>();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1,gradeLevel.getLevelNo());
            cs.setString(2,sectionType);
            cs.setInt(3, schoolYear.getSchoolYearId());
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Section section = new Section();
                    section.setSectionId(rs.getInt("section_id"));
                    SchoolYear sy = new SchoolYear();
                    sy.setYearFrom(rs.getInt("yearFrom"));
                    section.setSchoolYear(sy);
                    section.setSectionName(rs.getString("sectionName"));
                    section.setIsActive(rs.getBoolean("isActive"));
                    section.setDateCreated(rs.getString("date_created"));
                    
                    GradeLevel g = new GradeLevel();
                    g.setLevelNo(rs.getInt("grade_level"));
                    
                    Faculty adviser = new Faculty();
                    adviser.setFacultyID(rs.getInt("faculty_id"));
                    adviser.setLastName(rs.getString("lastName"));
                    adviser.setFirstName(rs.getString("firstName"));
                    adviser.setMiddleName(rs.getString("middleName"));
                    
                    section.setGradeLevel(g);
                    section.setSectionSession(rs.getString("session"));
                    section.setAdviser(adviser);
                    section.setCapacity(rs.getInt("capacity"));
                    section.setSectionType(rs.getString("section_type"));
                    
                    sectionList.add(section);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sectionList;
    }

    @Override
    public List<Section> getSectionsWithNoAssignedSchedBySchoolYearGradeLevelAndSectionType(SchoolYear sy, GradeLevel g, String sectionType) {
        List<Section> sectionList = new ArrayList<>();
        String SQL = "{CALL getSectionsWithNoAssignedSchedBySYIdGradeLevelAndSectionType(?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1,sy.getSchoolYearId());
            cs.setInt(2,g.getGradeLevelId());
            cs.setString(3, sectionType);
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    Section section = new Section();
                    section.setSectionId(rs.getInt("section_id"));
                    section.setSectionName(rs.getString("sectionName"));
                    section.setIsActive(rs.getBoolean("isActive"));
                    section.setDateCreated(rs.getString("date_created"));
                    section.setSectionSession(rs.getString("session"));
                    section.setCapacity(rs.getInt("capacity"));
                    section.setSectionType(rs.getString("section_type"));
                    section.setGradeLevel(g);
                    section.setSchoolYear(sy);
                    
                    sectionList.add(section);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sectionList;
    }

    @Override
    public List<Section> getSectionsBy(int gradeLevelNo) {
        String SQL = "{CALL getSectionsByGradeLevelNo(?)}";
        List<Section> sectionList = new ArrayList<>();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1,gradeLevelNo);
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    GradeLevel g = new GradeLevel();
                    g.setLevelNo(rs.getInt("grade_level"));
                    
                    SchoolYear sy = new SchoolYear();
                    sy.setYearFrom(rs.getInt("yearFrom"));
                    
                    Faculty adviser = new Faculty();
                    adviser.setFacultyID(rs.getInt("faculty_id"));
                    adviser.setLastName(rs.getString("lastName"));
                    adviser.setFirstName(rs.getString("firstName"));
                    adviser.setMiddleName(rs.getString("middleName"));
                    
                    Section section = new Section();
                    section.setSectionId(rs.getInt("section_id"));
                    section.setSectionName(rs.getString("sectionName"));
                    section.setIsActive(rs.getBoolean("isActive"));
                    section.setDateCreated(rs.getString("date_created"));
                    section.setSectionSession(rs.getString("session"));
                    section.setCapacity(rs.getInt("capacity"));
                    section.setSectionType(rs.getString("section_type"));
                    section.setGradeLevel(g);
                    section.setSchoolYear(sy);
                    section.setAdviser(adviser);
                    
                    sectionList.add(section);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sectionList;
    }

    @Override
    public Section getSectionOf(Student student, GradeLevel gradeLevel, SchoolYear schoolYear, String sectionType) {
        Section section = new Section();
        String SQL = "{CALL getSectionOf(?,?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1,student.getStudentId());
            cs.setInt(2,gradeLevel.getGradeLevelId());
            cs.setInt(3,schoolYear.getSchoolYearId());
            cs.setString(4,sectionType.trim());
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    Faculty adviser = new Faculty();
                    adviser.setFacultyID(rs.getInt("adviser_id"));
                    adviser.setLastName(rs.getString("lastName"));
                    adviser.setFirstName(rs.getString("firstName"));
                    adviser.setMiddleName(rs.getString("middleName"));
                    
                    section.setSectionId(rs.getInt("section_id"));
                    section.setSectionName(rs.getString("sectionName"));
                    section.setAdviser(adviser);
                    section.setSectionSession(rs.getString("session"));
                    section.setCapacity(rs.getInt("capacity"));
                    section.setSectionType(rs.getString("section_type"));
                    
                    
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return section;
    }
    
    
}
