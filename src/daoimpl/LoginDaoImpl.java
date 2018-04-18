package daoimpl;

import utility.database.DBType;
import utility.database.DBUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import utility.puzzler.Puzzler;

public class LoginDaoImpl {

    private static String username = null;

    public static String getUsername() {
        return username;
    }

    public static int getUserId() {
        int userId = 0;
        String SQL = "{CALL getUserId(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setString(1, username);
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    userId = rs.getInt("id");
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getErrorCode() + e.getMessage());
        }
        return userId;
    }

    public static Boolean isValid(String aUsername, String aPassword) {
        boolean isValid = false;
        LoginDaoImpl.username = aUsername; // SETS THE username to STATIC username VARIABLE
        String SQLa = "SELECT password FROM user_mt WHERE username=?";
        String userStoredPassword = "";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                PreparedStatement ps = con.prepareStatement(SQLa);) {
            ps.setString(1, aUsername);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    userStoredPassword = rs.getString("password");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(aPassword.equals(userStoredPassword)){
            isValid = true;
        }
//        Puzzler puzzler = new Puzzler(userStoredPassword);
//        System.out.println("User entered password : " + aPassword);
//        System.out.println("User stored password : " + puzzler.getDecrypted());
//        if (puzzler.getDecrypted().equals(aPassword)) {
//            isValid = true;
//        }

        return isValid;
    }

    public static Boolean isLocked(String aUsername) {
        Boolean bool = null;
        String SQL = "Select isLocked From user_mt WHERE username = ?";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                PreparedStatement ps = con.prepareStatement(SQL);) {
            ps.setString(1, aUsername);
            try (ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    bool = rs.getBoolean("isLocked");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getClass() + " " + e.getMessage());
        }
        return bool;
    }

    public static void lockUser(String aUsername) {
        String SQL = "UPDATE user_mt SET isLocked = ? WHERE username = ?";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                PreparedStatement ps = con.prepareStatement(SQL);) {
            ps.setInt(1, 1);
            ps.setString(2, aUsername);
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getClass() + " " + e.getMessage());
        }
    }

    public static Boolean exists(String aUsername) {
        Boolean bool = null;
        String SQL = "SELECT * FROM user_mt WHERE username = ?";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                PreparedStatement ps = con.prepareStatement(SQL);) {
            ps.setString(1, aUsername);
            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    bool = true;
                } else {
                    bool = false;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getClass() + " " + e.getMessage());
        }
        return bool;
    }

    public static void setLastLoginDate(String aUsername) {
        DateTime rawDateTime = new DateTime();
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = fmt.print(rawDateTime);
//        JOptionPane.showMessageDialog(null, formattedDateTime);

        String SQL = "UPDATE user_mt SET lastLoginDate = ? WHERE username = ?";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                PreparedStatement ps = con.prepareStatement(SQL);) {
            ps.setString(1, formattedDateTime);
            ps.setString(2, aUsername);
            ps.executeUpdate();
            System.out.println("Successfully recorded login timestamp");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getClass() + " " + e.getMessage());
        }
    }

    public static String getCompleteName(String aUsername) {
        String SQL = "{CALL getUserCompleteName(?)}";
        String myName = null;
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setString(1, aUsername.trim());
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    myName = rs.getString("completeName");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getClass() + " " + e.getMessage());
        }
        return myName;
    }
}
