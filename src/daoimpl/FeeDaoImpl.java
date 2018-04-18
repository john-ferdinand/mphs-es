package daoimpl;

import utility.database.DBType;
import utility.database.DBUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.fee.Fee;
import model.feecategory.FeeCategory;
import model.gradelevel.GradeLevel;
import model.schoolyear.SchoolYear;
import dao.IFee;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class FeeDaoImpl implements IFee {

    private final GradeLevelDaoImpl gradeLevelDaoImpl;
    private final FeeCategoryDaoImpl feeCategoryDaoImpl;

    public FeeDaoImpl(){
        gradeLevelDaoImpl = new GradeLevelDaoImpl();
        feeCategoryDaoImpl = new FeeCategoryDaoImpl();
    }

    @Override
    public Fee getSummerFeePerSubject() {
        Fee fee = new Fee();
        String SQL = "{CALL getSummerFeePerSubject()}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    FeeCategory feeCategory = new FeeCategory();
                    feeCategory.setId(rs.getInt("fee_category_id"));
                    feeCategory.setName(rs.getString("fee_category"));
                    
                    fee.setId(rs.getInt("fee_id"));
                    fee.setName(rs.getString("fee_name"));
                    fee.setFeeCategory(feeCategory);
                    fee.setAmount(rs.getBigDecimal("fee_amount"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fee;
    }
    
    @Override
    public double getSumOfTuitionFeesByGradeLevelId(Integer aGradeLevelId) {
        double tuitionFee = 0.00;
        String SQL = "{CALL getSumOfTuitionFeesByGradeLevelId(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, aGradeLevelId);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    tuitionFee = rs.getDouble("sumOfTuitionFee");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getErrorCode() + "\n" + e.getMessage());
        }
        return tuitionFee;
    }

    @Override
    public double getSumOfOtherFeesByGradeLevelId(Integer aGradeLevelId) {
        double sumOfOtherFees = 0.00;
        String SQL = "{CALL getSumOfOtherFeesByGradeLevelId(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, aGradeLevelId);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    sumOfOtherFees = rs.getDouble("sumOfOtherFees");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sumOfOtherFees;
    }

    @Override
    public double getSumOfMiscellaneousFeesByGradeLevelId(Integer aGradeLevelId) {
        double sumOfMiscellaneousFees = 0.00;
        String SQL = "{CALL getSumOfMiscellaneousFeesByGradeLevelId(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, aGradeLevelId);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    sumOfMiscellaneousFees = rs.getDouble("sumOfMiscellaneousFees");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sumOfMiscellaneousFees;
    }

    @Override
    public boolean delete(int feeId) {
        boolean isDeleted;
        String SQL = "{CALL deleteFee(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, feeId);
            cs.executeUpdate();
            isDeleted = true;
        } catch (SQLException e) {
            isDeleted = false;
            JOptionPane.showMessageDialog(null, e.getErrorCode() + "\n" + e.getMessage());
        }
        return isDeleted;
    }

    @Override
    public double getDownpaymentFeeByGradeLevel(GradeLevel aGradeLevel) {
        double amountOfDownpayment = 0;
        int gradeLevel = aGradeLevel.getLevelNo();
        String SQL = "{CALL getDownpaymentFeeByGradeLevel(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, gradeLevel);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    amountOfDownpayment = rs.getDouble("fee_amount");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getErrorCode() + "\n" + e.getMessage());
        }
        return amountOfDownpayment;
    }

    @Override
    public List<Fee> getFeesByGradeLevelAndCategory(GradeLevel aGradeLevel, FeeCategory aFeeCategory) {
        String feeCategory = aFeeCategory.getName();
        int gradeLevel = aGradeLevel.getLevelNo();

        List<Fee> list = new ArrayList<>();
        String SQL = "{CALL getFeesByGradeLevelAndCategory(?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, gradeLevel);
            cs.setString(2, feeCategory);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Fee fee = new Fee();
                    FeeCategory fc = new FeeCategory();
                    GradeLevel gl = new GradeLevel();
                    SchoolYear sy = new SchoolYear();

                    fc.setId(rs.getInt("fee_category_id"));
                    fc.setName(rs.getString("fee_category"));

                    gl.setGradeLevelID(rs.getInt("gradelevel_id"));
                    gl.setLevelNo(rs.getInt("grade_level"));
                    sy.setYearFrom(rs.getInt("year_created"));

                    fee.setFeeCategory(fc);
                    fee.setGradeLevel(gl);
                    fee.setSchoolYear(sy);

                    fee.setId(rs.getInt("fee_id"));
                    fee.setName(rs.getString("fee_name"));
                    fee.setAmount(rs.getBigDecimal("fee_amount"));
                    fee.setDescription(rs.getString("fee_description"));
                    fee.setIsActive(rs.getBoolean("isActive"));

                    list.add(fee);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getErrorCode() + "\n" + e.getMessage());
        }
        return list;
    }


    /**
     * Checks if a specific fee name already exists in the database.
     * Returns true if already exists, false if doesn't exist.
     * @param aFeeName
     * @return 
     */
    @Override
    public boolean exists(String aFeeName) {
        String SQL = "{CALL feeExists(?)}";
        boolean exists = false;
        int rowCount = 0;
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setString(1, aFeeName);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    rowCount++;
                }
            }
            exists = rowCount > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getErrorCode() + "\n" + e.getMessage());
        }
        return exists;
    }

    
    /**
     * Returns a list of String of all Fee names stored in the database.
     * @return 
     */
    @Override
    public List<String> getNames() {
        List<String> feeNames = new ArrayList<>();
        String SQL = "{CALL getAllFeeNames()}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    feeNames.add(rs.getString("fee_name"));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getErrorCode() + "\n" + e.getMessage());
        }
        return feeNames;
    }

    /**
     * Returns a List of all Fee instances GROUPED BY ID which eliminates duplicates on display
     * Returned Fees contains the following information:
     * - fee category 
     * - fee year creation (yearfrom)
     * - fee name
     * - fee gradelevel assignment
     * - fee amount
     * - fee description
     * - fee id
     * - fee status (Active / Inactive)
     * @return 
     */
    @Override
    public List<Fee> getAllGroupedById() {
        List<Fee> list = new ArrayList<>();
        String SQL = "{CALL getAllFeesInfoGroupedById()}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Fee fee = new Fee();

                    SchoolYear schoolYear = new SchoolYear();
                    schoolYear.setYearFrom(rs.getInt("yearFrom"));
                    fee.setSchoolYear(schoolYear);

                    FeeCategory feeCategory = new FeeCategory();
                    feeCategory.setName(rs.getString("fee_category"));
                    fee.setFeeCategory(feeCategory);

                    GradeLevel gradeLevel = new GradeLevel();
                    gradeLevel.setLevelNo(rs.getInt("grade_level"));
                    fee.setGradeLevel(gradeLevel);

                    fee.setAmount(rs.getBigDecimal("fee_amount"));
                    fee.setFeeCategory(feeCategory);
                    fee.setDescription(rs.getString("fee_description"));
                    fee.setName(rs.getString("fee_name"));
                    fee.setId(rs.getInt("fee_id"));
                    fee.setIsActive(rs.getBoolean("isActive"));
                    list.add(fee);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return list;
    }

    @Override
    public List<Fee> getFeesByGradeLevelId(int gradeLevelId) {
        List<Fee> feeList = new ArrayList<>();
        String SQL = "{CALL getFeesByGradeLevelId(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1,gradeLevelId);
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    Fee fee = new Fee();
                    
                    SchoolYear schoolYear = new SchoolYear();
                    schoolYear.setYearFrom(rs.getInt("yearFrom"));
                    fee.setSchoolYear(schoolYear);

                    FeeCategory feeCategory = new FeeCategory();
                    feeCategory.setName(rs.getString("fee_category"));
                    fee.setFeeCategory(feeCategory);

                    GradeLevel gradeLevel = new GradeLevel();
                    gradeLevel.setLevelNo(rs.getInt("grade_level"));
                    fee.setGradeLevel(gradeLevel);

                    fee.setAmount(rs.getBigDecimal("fee_amount"));
                    fee.setFeeCategory(feeCategory);
                    fee.setDescription(rs.getString("fee_description"));
                    fee.setName(rs.getString("fee_name"));
                    fee.setId(rs.getInt("fee_id"));
                    fee.setIsActive(rs.getBoolean("isActive"));
                    
                    feeList.add(fee);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feeList;
    }
    
    /**
     * Returns a List of all Fee instances containing all information such as
     * - fee category 
     * - fee year creation (yearfrom)
     * - fee name
     * - fee gradelevel assignment
     * - fee amount
     * - fee description
     * - fee id
     * - fee status (Active / Inactive)
     * @return 
     */
    @Override
    public List<Fee> getAll() {
        List<Fee> list = new ArrayList<>();
        String SQL = "{CALL getAllFeesInfo()}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Fee fee = new Fee();

                    SchoolYear schoolYear = new SchoolYear();
                    schoolYear.setYearFrom(rs.getInt("yearFrom"));
                    fee.setSchoolYear(schoolYear);

                    FeeCategory feeCategory = new FeeCategory();
                    feeCategory.setName(rs.getString("fee_category"));
                    fee.setFeeCategory(feeCategory);

                    GradeLevel gradeLevel = new GradeLevel();
                    gradeLevel.setLevelNo(rs.getInt("grade_level"));
                    fee.setGradeLevel(gradeLevel);

                    fee.setAmount(rs.getBigDecimal("fee_amount"));
                    fee.setFeeCategory(feeCategory);
                    fee.setDescription(rs.getString("fee_description"));
                    fee.setName(rs.getString("fee_name"));
                    fee.setId(rs.getInt("fee_id"));
                    fee.setIsActive(rs.getBoolean("isActive"));
                    list.add(fee);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return list;
    }

    @Override
    public List<Fee> getByGradeLevel(GradeLevel aGradeLevel) {
        List<Fee> list = new ArrayList<>();
        String SQL = "{CALL getFeesByGradeLevel(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, aGradeLevel.getLevelNo());

            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Fee fee = new Fee();

                    SchoolYear schoolYear = new SchoolYear();
                    schoolYear.setYearFrom(rs.getInt("year_created"));
                    fee.setSchoolYear(schoolYear);

                    FeeCategory feeCategory = new FeeCategory();
                    feeCategory.setName(rs.getString("fee_category"));
                    fee.setFeeCategory(feeCategory);

                    GradeLevel gradeLevel = new GradeLevel();
                    gradeLevel.setLevelNo(rs.getInt("grade_level"));
                    fee.setGradeLevel(gradeLevel);

                    fee.setAmount(rs.getBigDecimal("fee_amount"));
                    fee.setFeeCategory(feeCategory);
                    fee.setDescription(rs.getString("fee_description"));
                    fee.setName(rs.getString("fee_name"));
                    fee.setId(rs.getInt("fee_id"));
                    fee.setIsActive(rs.getBoolean("isActive"));
                    list.add(fee);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return list;
    }

    @Override
    public double getSumByGradeLevel(GradeLevel aGradeLevel) {
        int gradeLevelId = gradeLevelDaoImpl.getId(aGradeLevel);
        double sumOfFees = 0;
        String SQL = "{CALL getSumOfFeeByGradeLevel(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, gradeLevelId);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    sumOfFees = rs.getDouble("sumOfFees");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sumOfFees;
    }

    @Override
    public List<Fee> getFeesBySchoolYear(SchoolYear aSchoolYear) {
        List<Fee> feeList = new ArrayList<>();
        return feeList;
    }

    @Override
    public List<Fee> getByCategory(String feeCategory) {
        List<Fee> list = new ArrayList<>();
        String SQL = "{CALL getFeesByCategory(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setString(1, feeCategory);

            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Fee fee = new Fee();

                    SchoolYear schoolYear = new SchoolYear();
                    schoolYear.setYearFrom(rs.getInt("year_created"));
                    fee.setSchoolYear(schoolYear);

                    FeeCategory fc = new FeeCategory();
                    fc.setName(rs.getString("fee_category"));
                    fee.setFeeCategory(fc);

                    GradeLevel gradeLevel = new GradeLevel();
                    gradeLevel.setLevelNo(rs.getInt("grade_level"));
                    fee.setGradeLevel(gradeLevel);

                    fee.setAmount(rs.getBigDecimal("fee_amount"));
                    fee.setFeeCategory(fc);
                    fee.setDescription(rs.getString("fee_description"));
                    fee.setName(rs.getString("fee_name"));
                    fee.setId(rs.getInt("fee_id"));
                    fee.setIsActive(rs.getBoolean("isActive"));
                    list.add(fee);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return list;
    }

    @Override
    public Fee getFeeInfoById(int aFeeId) {
        Fee fee = new Fee();
        String SQL = "{CALL getFeeInfoById(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1, aFeeId);
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    SchoolYear schoolYear = new SchoolYear();
                    schoolYear.setYearFrom(rs.getInt("yearFrom"));
                    fee.setSchoolYear(schoolYear);

                    FeeCategory fc = new FeeCategory();
                    fc.setName(rs.getString("fee_category"));
                    fee.setFeeCategory(fc);

                    GradeLevel gradeLevel = new GradeLevel();
                    gradeLevel.setLevelNo(rs.getInt("grade_level"));
                    fee.setGradeLevel(gradeLevel);

                    fee.setAmount(rs.getBigDecimal("fee_amount"));
                    fee.setFeeCategory(fc);
                    fee.setDescription(rs.getString("fee_description"));
                    fee.setName(rs.getString("fee_name"));
                    fee.setId(rs.getInt("fee_id"));
                    fee.setIsActive(rs.getBoolean("isActive"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fee;
    }

    @Override
    public Fee getFeeGradeLevelAssignmentAndAmountById(int feeId) {
        Fee fee = new Fee();
        Map<Integer, BigDecimal> gradeLevelAmountPair = new HashMap<>();
        String SQL = "{CALL getFeeGradeLevelAssignmentAndAmountById(?)}";
        try(Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, feeId);
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    int gradeLevel = rs.getInt("grade_level");
                    double feeAmount = rs.getDouble("fee_amount");
                    gradeLevelAmountPair.put(gradeLevel, BigDecimal.valueOf(feeAmount));
                }
                fee.setGradeLevelAmountPair(gradeLevelAmountPair);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fee;
    }

    @Override
    public int getFeeId(String feeName) {
        String SQL = "{CALL getFeeIdByName(?)}";
        int aFeeId = 0;
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setString(1, feeName);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    aFeeId = rs.getInt("fee_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aFeeId;
    }

    @Override
    public boolean addFee(Fee fee) {
        boolean isAdded = true;
        String SQLa = "{CALL addFee(?,?,?,?)}";
        String SQLb = "{CALL assignFeeToGradeLevel(?,?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);) {
            con.setAutoCommit(false);
            try (CallableStatement csa = con.prepareCall(SQLa);
                    CallableStatement csb = con.prepareCall(SQLb);) {

                FeeCategory feeCategory = fee.getFeeCategory();
                int feeCategoryId = feeCategoryDaoImpl.getFeeCategoryId(feeCategory);
                csa.setString(1, fee.getName());
                csa.setString(2, fee.getDescription());
                csa.setInt(3, feeCategoryId);
                csa.registerOutParameter(4, Types.INTEGER);
                csa.executeUpdate();

                int feeId = csa.getInt(4);

                for (Map.Entry<Integer, BigDecimal> entry : fee.getGradeLevelAmountPair().entrySet()) {
                    Integer level = entry.getKey();
                    BigDecimal feeAmount = entry.getValue();

                    int aGradeLevelId = gradeLevelDaoImpl.getId(level);
                    csb.setInt(1, feeId); System.out.println("Fee ID: "+feeId);
                    csb.setBigDecimal(2, feeAmount); 
                    csb.setInt(3, aGradeLevelId); 
                    csb.setInt(4, fee.getSchoolYear().getSchoolYearId()); 
                    System.out.println("feeAmount@addFee(): "+feeAmount);
                    csb.executeUpdate();
                }
                 con.commit();
            } catch (SQLException e) {
                con.rollback();
                isAdded = false;
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return isAdded;
    }

    @Override
    public List<GradeLevel> getGradeLevelAssignment(int aFeeId) {
        List<GradeLevel> gradeLevelList = new ArrayList<>();
        String SQL = "{CALL getFeeGradeLevelAssignment(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1, aFeeId);
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    GradeLevel g = new GradeLevel();
                    g.setLevelNo(rs.getInt("grade_level"));
                    g.setGradeLevelID(rs.getInt("gradelevel_id"));
                    g.setIsActive(rs.getBoolean("isActive"));
                    gradeLevelList.add(g);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return gradeLevelList;
    }

    @Override
    public List<Fee> getFeesByWildcard(String wildCardChar) {
        String SQL = "{CALL getFeeInfoByWildcard(?)}";
        List<Fee> list = new ArrayList<>();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setString(1, wildCardChar);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    Fee fee = new Fee();

                    SchoolYear schoolYear = new SchoolYear();
                    schoolYear.setYearFrom(rs.getInt("yearFrom"));
                    fee.setSchoolYear(schoolYear);

                    FeeCategory feeCategory = new FeeCategory();
                    feeCategory.setName(rs.getString("fee_category"));
                    fee.setFeeCategory(feeCategory);

                    GradeLevel gradeLevel = new GradeLevel();
                    gradeLevel.setLevelNo(rs.getInt("grade_level"));
                    fee.setGradeLevel(gradeLevel);

                    fee.setAmount(rs.getBigDecimal("fee_amount"));
                    fee.setFeeCategory(feeCategory);
                    fee.setDescription(rs.getString("fee_description"));
                    fee.setName(rs.getString("fee_name"));
                    fee.setId(rs.getInt("fee_id"));
                    fee.setIsActive(rs.getBoolean("isActive"));
                    list.add(fee);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return list;
    }
    
    @Override
    public boolean update(Fee fee) {
        boolean isUpdated = false;
        String SQLa = "{CALL deleteFeeFromFeeSchoolYearById(?)}";
        String SQLb = "{CALL assignFeeToGradeLevel(?,?,?,?)}";
        String SQLc = "{CALL updateFee(?,?,?,?,?)}";
        try(Connection con = DBUtil.getConnection(DBType.MYSQL);){
            con.setAutoCommit(false);
            try(CallableStatement csa = con.prepareCall(SQLa);
                    CallableStatement csb = con.prepareCall(SQLb);
                    CallableStatement csc = con.prepareCall(SQLc);){
                csa.setInt(1, fee.getId());
                csa.executeUpdate();
                
                System.out.println("Fee Name : "+fee.getName());
                System.out.println("Fee ID : "+fee.getId());
                
                for (Map.Entry<Integer, BigDecimal> entry : fee.getGradeLevelAmountPair().entrySet()) {
                    Integer level = entry.getKey();
                    BigDecimal feeAmount = entry.getValue();

                    System.out.println("Level : "+level+"");
                    System.out.println("Fee Amount : "+feeAmount);
                    
                    int aGradeLevelId = gradeLevelDaoImpl.getId(level);
                    csb.setInt(1, fee.getId());
                    csb.setBigDecimal(2, feeAmount);
                    csb.setInt(3, aGradeLevelId);
                    csb.setInt(4, fee.getSchoolYear().getSchoolYearId());

                    csb.executeUpdate();
                }
               
                FeeCategory feeCategory = fee.getFeeCategory();
                int feeCategoryId = feeCategoryDaoImpl.getFeeCategoryId(feeCategory);
                csc.setInt(1, fee.getId());
                csc.setString(2, fee.getName());
                csc.setString(3, fee.getDescription());
                csc.setInt(4, feeCategoryId);
                csc.setBoolean(5,fee.isActive());
                csc.executeUpdate();
                
                con.commit();
                isUpdated = true;
            }catch(SQLException ex){
                con.rollback();
                ex.printStackTrace();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return isUpdated;
    }

    @Override
    public BigDecimal getBasicByGradeLevel(GradeLevel gradeLevel) {
        BigDecimal amount = new BigDecimal(BigInteger.ZERO);
        String SQL = "{CALL getBasicByGradeLevelId(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1, gradeLevel.getGradeLevelId());
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    amount = rs.getBigDecimal("fee_amount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return amount;
    }

    @Override
    public BigDecimal getDownpaymentByGradeLevel(GradeLevel gradeLevel) {
        BigDecimal amount = new BigDecimal(BigInteger.ZERO);
        String SQL = "{CALL getDownpaymentByGradeLevel(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1, gradeLevel.getGradeLevelId());
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    amount = rs.getBigDecimal("fee_amount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return amount;
    }

    @Override
    public BigDecimal getSumOfMiscFeesByGradeLeveLId(GradeLevel gradeLevel) {
        BigDecimal sum = new BigDecimal(BigInteger.ZERO);
        String SQL = "{CALL getSumOfMiscFeesByGradeLeveLId(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1, gradeLevel.getGradeLevelId());
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    sum = rs.getBigDecimal("sum");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }

    @Override
    public BigDecimal getSumOfOthersFeeByGradeLevelId(GradeLevel gradeLevel) {
        BigDecimal sum = new BigDecimal(BigInteger.ZERO);
        String SQL = "{CALL getSumOfOthersFeeByGradeLevelId(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);){
            cs.setInt(1, gradeLevel.getGradeLevelId());
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    sum = rs.getBigDecimal("sum");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }
    
    
    
}
