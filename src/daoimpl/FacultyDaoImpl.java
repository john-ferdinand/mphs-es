package daoimpl;

import dao.IFaculty;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.classtype.ClassType;
import model.faculty.Faculty;
import model.schoolyear.SchoolYear;
import model.section.Section;
import model.subjectcategory.SubjectCategory;
import model.user.User;
import utility.database.DBType;
import utility.database.DBUtil;

/**
 *
 * @author Jordan
 */
public class FacultyDaoImpl implements IFaculty {

    private final SchoolYearDaoImpl schoolYearDaoImpl;

    public FacultyDaoImpl() {
        this.schoolYearDaoImpl = new SchoolYearDaoImpl();
    }

    @Override
    public boolean createFaculty(Faculty faculty) {
        boolean isCreated = false;
        String select = "{call checkFacultyExist(?,?,?,?)}";
        String create = "{call addFaculty(?,?,?,?,?)}";
        String result = "";

        try (Connection con = DBUtil.getConnection(DBType.MYSQL);) {
            try (CallableStatement cs = con.prepareCall(select);
                    CallableStatement cs1 = con.prepareCall(create);) {
                con.setAutoCommit(false);
                cs.setString(1, faculty.getLastName());
                cs.setString(2, faculty.getFirstName());
                cs.setString(3, faculty.getMiddleName());
                cs.registerOutParameter(4, java.sql.Types.VARCHAR);
                cs.executeUpdate();
                result = cs.getString(4);

                if (!result.equals("Duplicate")) {
                    cs1.setString(1, faculty.getFirstName());
                    cs1.setString(2, faculty.getLastName());
                    cs1.setString(3, faculty.getMiddleName());
                    cs1.setString(4, faculty.getEmail());
                    cs1.setString(5, faculty.getContactNo());
                    cs1.executeUpdate();
                } else {
                    JOptionPane.showMessageDialog(null, "Faculty already exist!");
                }
                con.commit();
                isCreated = true;
            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isCreated;
    }

    @Override
    public List<Faculty> getFacultyByName(Faculty faculty) {
        List<Faculty> list = new ArrayList<Faculty>();
        String select = "{call getFacultyByName(?)}";
        String SQLb = "{CALL facultyHasAMSchedule(?,?)}";
        String SQLc = "{CALL facultyHasPMSchedule(?,?)}";

        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement csa = con.prepareCall(select);
                CallableStatement csb = con.prepareCall(SQLb);
                CallableStatement csc = con.prepareCall(SQLc);) {
            csa.setString(1, faculty.getLastName());
            try (ResultSet rsa = csa.executeQuery()) {
                while (rsa.next()) {
                    faculty = new Faculty();
                    faculty.setFacultyID(rsa.getInt(1));
                    faculty.setLastName(rsa.getString(2));
                    faculty.setFirstName(rsa.getString(3));
                    faculty.setMiddleName(rsa.getString(4));
                    faculty.setContactNo(rsa.getString(5));
                    faculty.setEmail(rsa.getString(6));
                    faculty.setStatus(rsa.getBoolean(7));

                    csb.setInt(1, schoolYearDaoImpl.getCurrentSchoolYearId());
                    csb.setInt(2, rsa.getInt(1));
                    ResultSet rsb = csb.executeQuery();
                    rsb.next();
                    faculty.setHasAMSchedule(rsb.getBoolean("hasAMSchedule"));

                    csc.setInt(1, schoolYearDaoImpl.getCurrentSchoolYearId());
                    csc.setInt(2, rsa.getInt(1));
                    ResultSet rsc = csc.executeQuery();
                    rsc.next();
                    faculty.setHasPMSchedule(rsc.getBoolean("hasPMSchedule"));

                    list.add(faculty);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Faculty> getFacultyInfoById(Faculty faculty) {
        List<Faculty> list = new ArrayList<Faculty>();
        String select = "{call getFacultyInfoById(?,?)}";
        String SQLb = "{CALL facultyHasAMSchedule(?,?)}";
        String SQLc = "{CALL facultyHasPMSchedule(?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(select);
                CallableStatement csb = con.prepareCall(SQLb);
                CallableStatement csc = con.prepareCall(SQLc);) {
            cs.setInt(1, faculty.getFacultyID());
            cs.setInt(2, schoolYearDaoImpl.getCurrentSchoolYearId());
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    faculty = new Faculty();
                    faculty.setFacultyID(rs.getInt("faculty_id"));
                    faculty.setLastName(rs.getString("lastName"));
                    faculty.setFirstName(rs.getString("firstName"));
                    faculty.setMiddleName(rs.getString("middleName"));
                    faculty.setContactNo(rs.getString("contactNo"));
                    faculty.setEmail(rs.getString("email"));
                    faculty.setStatus(rs.getBoolean("status"));

                    ClassType classType = new ClassType();
                    classType.setClassTypeID(rs.getInt("classtype_id"));
                    classType.setClassTypeName(rs.getString("classtype"));
                    classType.setIsActive(rs.getBoolean("is_classtype_active"));
                    classType.setDateAdded(rs.getDate("date_added"));
                    faculty.setClassType(classType);

                    csb.setInt(1, schoolYearDaoImpl.getCurrentSchoolYearId());
                    csb.setInt(2, faculty.getFacultyID());
                    ResultSet rsb = csb.executeQuery();
                    rsb.next();
                    faculty.setHasAMSchedule(rsb.getBoolean("hasAMSchedule"));

                    csc.setInt(1, schoolYearDaoImpl.getCurrentSchoolYearId());
                    csc.setInt(2, faculty.getFacultyID());
                    ResultSet rsc = csc.executeQuery();
                    rsc.next();
                    faculty.setHasPMSchedule(rsc.getBoolean("hasPMSchedule"));

                    list.add(faculty);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int countFacultySpecialization(Faculty faculty) {
        String select = "{call countFacultySpecialization(?,?)}";
        int count = 0;
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);) {
            CallableStatement cs = con.prepareCall(select);
            cs.setInt(1, faculty.getFacultyID());
            cs.registerOutParameter(2, java.sql.Types.INTEGER);
            cs.executeUpdate();
            count = cs.getInt(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public void createFacultySpecialization(Faculty faculty, SubjectCategory subjectCategory) {
        String add = "{call addFacultyAndSpecialization(?,?,?)}";
        SchoolYearDaoImpl schoolYearDaoImpl = new SchoolYearDaoImpl();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);) {
            CallableStatement cs = con.prepareCall(add);
            cs.setInt(1, faculty.getFacultyID());
            cs.setInt(2, subjectCategory.getSubjectCategoryId());
            cs.setInt(3, schoolYearDaoImpl.getCurrentSchoolYearId());
            cs.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFacultySpecialization(Faculty faculty) {
        String delete = "{call deleteFacultySpecialization(?)}";

        try (Connection con = DBUtil.getConnection(DBType.MYSQL);) {
            CallableStatement cs = con.prepareCall(delete);

            cs.setInt(1, faculty.getFacultyID());

            cs.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<SubjectCategory> loadFacultySpecialization(Faculty faculty, SubjectCategory subjectCategory) {
        List<SubjectCategory> list = new ArrayList<SubjectCategory>();
        String select = "{call loadFacultySpecialization(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);) {
            CallableStatement cs = con.prepareCall(select);
            cs.setInt(1, faculty.getFacultyID());
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    subjectCategory = new SubjectCategory();
                    subjectCategory.setSubjectCategoryId(rs.getInt(1));
                    subjectCategory.setSubjectCategoryName(rs.getString(2));
                    subjectCategory.setDescription(rs.getString(3));
                    subjectCategory.setIsActive(rs.getBoolean(4));

                    list.add(subjectCategory);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean updateFaculty(Faculty faculty) {
        boolean isUpdated = false;
        String update = "{call updateFaculty(?,?,?,?,?,?,?)}";
        String deleteFacultyClassHandled = "{CALL deleteFacultyClassHandledByFacultyIdSchoolYearId(?,?)}";//facultyid schoolyearid
        String addFacultyClassHandled = "{CALL addFacultyClassHandled(?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);) {
            con.setAutoCommit(false);
            try (CallableStatement csa = con.prepareCall(update);
                    CallableStatement csb = con.prepareCall(deleteFacultyClassHandled);
                    CallableStatement csc = con.prepareCall(addFacultyClassHandled)) {
                csa.setInt(1, faculty.getFacultyID());
                csa.setString(2, faculty.getLastName());
                csa.setString(3, faculty.getFirstName());
                csa.setString(4, faculty.getMiddleName());
                csa.setString(5, faculty.getContactNo());
                csa.setString(6, faculty.getEmail());
                csa.setBoolean(7, faculty.getStatus());
                csa.executeUpdate();

                csb.setInt(1, faculty.getFacultyID());
                csb.setInt(2, schoolYearDaoImpl.getCurrentSchoolYearId());
                csb.executeUpdate();

                csc.setInt(1, faculty.getFacultyID());
                csc.setInt(2, faculty.getClassType().getClassTypeID());
                csc.setInt(3, schoolYearDaoImpl.getCurrentSchoolYearId());
                csc.executeUpdate();

                con.commit();
                isUpdated = true;
            } catch (SQLException e) {
                con.rollback();
                con.setAutoCommit(true);
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    @Override
    public List<Faculty> getAllFaculty(Faculty faculty) {
        String select = "{call getAllFaculty()}";
        String SQLb = "{CALL facultyHasAMSchedule(?,?)}";
        String SQLc = "{CALL facultyHasPMSchedule(?,?)}";
        List<Faculty> list = new ArrayList<Faculty>();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(select);
                CallableStatement csb = con.prepareCall(SQLb);
                CallableStatement csc = con.prepareCall(SQLc);) {

            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    faculty = new Faculty();
                    faculty.setFacultyID(rs.getInt(1));
                    faculty.setLastName(rs.getString(2));
                    faculty.setFirstName(rs.getString(3));
                    faculty.setMiddleName(rs.getString(4));
                    faculty.setContactNo(rs.getString(5));
                    faculty.setEmail(rs.getString(6));
                    faculty.setStatus(rs.getBoolean(7));

                    csb.setInt(1, schoolYearDaoImpl.getCurrentSchoolYearId());
                    csb.setInt(2, faculty.getFacultyID());
                    ResultSet rsb = csb.executeQuery();
                    rsb.next();
                    faculty.setHasAMSchedule(rsb.getBoolean("hasAMSchedule"));

                    csc.setInt(1, schoolYearDaoImpl.getCurrentSchoolYearId());
                    csc.setInt(2, faculty.getFacultyID());
                    ResultSet rsc = csc.executeQuery();
                    rsc.next();
                    faculty.setHasPMSchedule(rsc.getBoolean("hasPMSchedule"));

                    list.add(faculty);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Faculty> getAllFacultyHandlingAdvisory(SchoolYear schoolYear) {
        List<Faculty> facultyList = new ArrayList<>();
        String SQL = "{CALL getAllFacultyHandlingAdvisory(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, schoolYear.getSchoolYearId());
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Faculty f = new Faculty();
                    f.setFacultyID(rs.getInt("faculty_id"));
                    f.setLastName(rs.getString("lastName"));
                    f.setFirstName(rs.getString("firstName"));
                    f.setMiddleName(rs.getString("middleName"));
                    f.setContactNo(rs.getString("contactNo"));
                    f.setEmail(rs.getString("email"));
                    f.setStatus(rs.getBoolean("status"));
                    facultyList.add(f);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facultyList;
    }

    @Override
    public List<Faculty> getAllFacultyByStatus(boolean isActive) {
        String SQLa = "{CALL getAllFacultyByStatus(?)}";
        String SQLb = "{CALL facultyHasAMSchedule(?,?)}";
        String SQLc = "{CALL facultyHasPMSchedule(?,?)}";

        List<Faculty> facultyList = new ArrayList<Faculty>();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQLa);
                CallableStatement csb = con.prepareCall(SQLb);
                CallableStatement csc = con.prepareCall(SQLc);) {
            cs.setInt(1, isActive == true ? 1 : 0);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Faculty faculty = new Faculty();
                    faculty.setFacultyID(rs.getInt("faculty_id"));
                    faculty.setLastName(rs.getString("lastName"));
                    faculty.setFirstName(rs.getString("firstName"));
                    faculty.setMiddleName(rs.getString("middleName"));
                    faculty.setContactNo(rs.getString("contactNo"));
                    faculty.setEmail(rs.getString("email"));
                    faculty.setStatus(rs.getBoolean("status"));

                    csb.setInt(1, schoolYearDaoImpl.getCurrentSchoolYearId());
                    csb.setInt(2, faculty.getFacultyID());
                    ResultSet rsb = csb.executeQuery();
                    rsb.next();
                    faculty.setHasAMSchedule(rsb.getBoolean("hasAMSchedule"));

                    csc.setInt(1, schoolYearDaoImpl.getCurrentSchoolYearId());
                    csc.setInt(2, faculty.getFacultyID());
                    ResultSet rsc = csc.executeQuery();
                    rsc.next();
                    faculty.setHasPMSchedule(rsc.getBoolean("hasPMSchedule"));

                    facultyList.add(faculty);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facultyList;
    }

    @Override
    public List<Faculty> getAllFacultyWithNoAdvisory() {
        String SQLa = "{CALL getAllFacultyWithNoAdvisory()}";
        String SQLb = "{CALL facultyHasAMSchedule(?,?)}";
        String SQLc = "{CALL facultyHasPMSchedule(?,?)}";

        List<Faculty> list = new ArrayList<>();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQLa);
                CallableStatement csb = con.prepareCall(SQLb);
                CallableStatement csc = con.prepareCall(SQLc);) {

            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Faculty faculty = new Faculty();
                    faculty.setFacultyID(rs.getInt(1));
                    faculty.setLastName(rs.getString(2));
                    faculty.setFirstName(rs.getString(3));
                    faculty.setMiddleName(rs.getString(4));
                    faculty.setContactNo(rs.getString(5));
                    faculty.setEmail(rs.getString(6));
                    faculty.setStatus(rs.getBoolean(7));

                    csb.setInt(1, schoolYearDaoImpl.getCurrentSchoolYearId());
                    csb.setInt(2, faculty.getFacultyID());
                    ResultSet rsb = csb.executeQuery();
                    rsb.next();
                    faculty.setHasAMSchedule(rsb.getBoolean("hasAMSchedule"));

                    csc.setInt(1, schoolYearDaoImpl.getCurrentSchoolYearId());
                    csc.setInt(2, faculty.getFacultyID());
                    ResultSet rsc = csc.executeQuery();
                    rsc.next();
                    faculty.setHasPMSchedule(rsc.getBoolean("hasPMSchedule"));

                    list.add(faculty);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Faculty> getAllFacultyHandlingSubjectBySubjectCode(String subjectCode, int schoolyearId) {
        String SQL = "{CALL getAllFacultyHandlingSubjectBySubjectCode(?,?)}";
        List<Faculty> list = new ArrayList<>();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);) {
            CallableStatement cs = con.prepareCall(SQL);
            cs.setString(1, subjectCode.trim());
            cs.setInt(2, schoolyearId);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Faculty faculty = new Faculty();
                    faculty.setFacultyID(rs.getInt("faculty_id"));
                    faculty.setLastName(rs.getString("lastName"));
                    faculty.setFirstName(rs.getString("firstName"));
                    faculty.setMiddleName(rs.getString("middleName"));
                    faculty.setContactNo(rs.getString("contactNo"));
                    faculty.setEmail(rs.getString("email"));
                    faculty.setStatus(rs.getBoolean("status"));
                    list.add(faculty);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Faculty> getAllFaculty() {
        List<Faculty> list = new ArrayList<>();
        String SQL = "{CALL getAllFaculty()}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);) {
            CallableStatement cs = con.prepareCall(SQL);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Faculty faculty = new Faculty();
                    faculty.setFacultyID(rs.getInt(1));
                    faculty.setLastName(rs.getString(2));
                    faculty.setFirstName(rs.getString(3));
                    faculty.setMiddleName(rs.getString(4));
                    faculty.setContactNo(rs.getString(5));
                    faculty.setEmail(rs.getString(6));
                    faculty.setStatus(rs.getBoolean(7));
                    list.add(faculty);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean isFacultyAvailableAdviserFor(String sectionSession, Faculty faculty, SchoolYear schoolYear) {
        boolean isAvailable = false;
        String SQL = "{CALL getAdviserAvailabilityBySession(?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setString(1, sectionSession);
            cs.setInt(2, faculty.getFacultyID());
            cs.setInt(3, schoolYear.getSchoolYearId());
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    isAvailable = rs.getBoolean("isAvailable");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAvailable;
    }

    @Override
    public boolean isFacultyAvailableAdviserFor(Section section, Faculty faculty, SchoolYear schoolYear) {
        boolean isAvailable = false;
        String SQL = "{CALL isFacultyAvailableAdviserFor(?,?,?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, section.getSectionId());
            cs.setString(2, section.getSectionSession().trim());
            cs.setString(3, section.getSectionType().trim());
            cs.setInt(4, faculty.getFacultyID());
            cs.setInt(5, schoolYear.getSchoolYearId());
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    isAvailable = rs.getBoolean("isFacultyAvailable");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isAvailable;
    }

    @Override
    public Faculty getFacultyById(int facultyId) {
        Faculty faculty = new Faculty();
        String SQL = "{CALL getFacultyById(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, facultyId);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    ClassType classType = new ClassType();
                    classType.setClassTypeID(rs.getInt("classtype_id"));
                    classType.setClassTypeName(rs.getString("classtype"));
                    classType.setIsActive(rs.getBoolean("is_classtype_active"));
                    classType.setDateAdded(rs.getDate("date_added"));
                    faculty.setClassType(classType);

                    faculty.setFacultyID(rs.getInt("faculty_id"));
                    faculty.setLastName(rs.getString("lastName"));
                    faculty.setFirstName(rs.getString("firstName"));
                    faculty.setMiddleName(rs.getString("middleName"));
                    faculty.setContactNo(rs.getString("contactNo"));
                    faculty.setEmail(rs.getString("email"));
                    faculty.setStatus(rs.getBoolean("status"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return faculty;
    }

    @Override
    public Faculty getFacultyByUser(User user) {
        String SQL = "{CALL getFacultyByUserId(?)}";
        Faculty faculty = new Faculty();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, user.getUserId());
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    faculty.setFacultyID(rs.getInt("faculty_id"));
                    faculty.setLastName(rs.getString("lastName"));
                    faculty.setFirstName(rs.getString("firstName"));
                    faculty.setMiddleName(rs.getString("middleName"));
                    faculty.setContactNo(rs.getString("contactNo"));
                    faculty.setEmail(rs.getString("email"));
                    faculty.setStatus(rs.getBoolean("status"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return faculty;
    }

}
