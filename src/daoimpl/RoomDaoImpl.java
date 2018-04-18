
package daoimpl;

import dao.IRoom;
import utility.database.DBType;
import utility.database.DBUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.room.Room;
import model.schoolyear.SchoolYear;


public class RoomDaoImpl implements IRoom {

    @Override
    public int getRoomId(String roomName) {
        int roomId = 0;
        String SQL = "{CALL getRoomIdByName(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setString(1, roomName.trim());
            try (ResultSet rs = cs.executeQuery();) {
                while (rs.next()) {
                    roomId = rs.getInt("room_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomId;
    }

    @Override
    public boolean addRoom(Room aRoom) {
        boolean isAdded;
        String SQL = "{CALL addRoom(?,?,?,?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
            
            CallableStatement cs = con.prepareCall(SQL);) {
            cs.setString(1, aRoom.getRoomName());
            cs.setString(2, aRoom.getBuildingName());
            cs.setString(3, aRoom.getCapacity());
            cs.setString(4, aRoom.getDescription());
            cs.executeUpdate();
            isAdded = true;
        } catch (SQLException e) {
            isAdded = false;
            e.printStackTrace();
        }
        return isAdded;
    }

    @Override
    public List<Room> getAllRoomInfo() {
        List<Room> list = new ArrayList();
        String SQL = "{CALL getRooms()}";
        
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Room room = new Room();
                    room.setRoomID(rs.getInt("room_id"));
                    room.setRoomName(rs.getString("room_name_or_num"));
                    room.setBuildingName(rs.getString("bldg_name_or_num"));
                    room.setCapacity(rs.getString("capacity"));
                    room.setStatus(rs.getBoolean("status"));
                    room.setDateCreated(rs.getString("date_created"));
                    room.setDescription(rs.getString("notes"));
                    list.add(room);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Room> getAllActiveRooms() {
        String SQL = "{CALL getAllActiveRooms()}";
        List<Room> list = new ArrayList();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Room room = new Room();
                    room.setRoomID(rs.getInt("room_id"));
                    room.setRoomName(rs.getString("room_name_or_num"));
                    room.setBuildingName(rs.getString("bldg_name_or_num"));
                    room.setCapacity(rs.getString("capacity"));
                    room.setStatus(rs.getBoolean("status"));
                    room.setDateCreated(rs.getString("date_created"));
                    room.setDescription(rs.getString("notes"));
                    list.add(room);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    @Override
    public List<Room> getRoomsAvailableFor(String sectionSession, SchoolYear schoolYear) {
        String SQL = "{CALL getRoomsAvailableForSesssion(?,?)}";
        List<Room> list = new ArrayList();
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setString(1,sectionSession.trim());
            cs.setInt(2,schoolYear.getSchoolYearId());
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Room room = new Room();
                    room.setRoomID(rs.getInt("room_id"));
                    room.setRoomName(rs.getString("room_name_or_num"));
                    room.setBuildingName(rs.getString("bldg_name_or_num"));
                    room.setCapacity(rs.getString("capacity"));
                    room.setStatus(rs.getBoolean("status"));
                    room.setDateCreated(rs.getString("date_created"));
                    room.setDescription(rs.getString("notes"));
                    list.add(room);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean updateRoom(Room aRoom) {
        boolean isUpdated;
        String SQL = "{CALL updateRooms(?,?,?,?,?,?)}";

        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, aRoom.getRoomID());
            cs.setString(2, aRoom.getRoomName());
            cs.setString(3, aRoom.getBuildingName());
            cs.setString(4, aRoom.getCapacity());
            cs.setBoolean(5, aRoom.getStatus());
            cs.setString(6, aRoom.getDescription());

            cs.executeUpdate();
            isUpdated = true;
        } catch (SQLException e) {
            isUpdated = false;
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return isUpdated;
    }

    @Override
    public Room getRoomById(int aRoomID) {
        Room room = new Room();
        String SQL = "{CALL getRoomsInfoByID(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setInt(1, aRoomID);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    room.setRoomID(rs.getInt("room_id"));
                    room.setRoomName(rs.getString("room_name_or_num"));
                    room.setBuildingName(rs.getString("bldg_name_or_num"));
                    room.setCapacity(rs.getString("capacity"));
                    room.setStatus(rs.getBoolean("status"));
                    room.setDescription(rs.getString("notes"));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return room;
    }

    @Override
    public int getRoomID(String roomName, String BuildingName, String capacity) {
        int roomID = 0;
        String SQL = "{CALL getRoomID}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
                CallableStatement cs = con.prepareCall(SQL);) {
            cs.setString(1, roomName);
            cs.setString(2, BuildingName);
            cs.setString(3, capacity);

            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    roomID = rs.getInt("room_id");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return roomID;
    }

    @Override
    public List<Room> getAllRoomsInfoByWildCard(String wildCardChar) {
        List<Room> roomList = new ArrayList<>();
        String SQL = "{CALL GetAllRoomsInfoByWildCard(?)}";
        try (Connection con = DBUtil.getConnection(DBType.MYSQL);
            CallableStatement cs = con.prepareCall(SQL);){
            cs.setString(1, wildCardChar);
            try(ResultSet rs = cs.executeQuery();){
                while(rs.next()){
                    Room room = new Room();
                    room.setRoomID(rs.getInt("room_id"));
                    room.setRoomName(rs.getString("room_name_or_num"));
                    room.setBuildingName(rs.getString("bldg_name_or_num"));
                    room.setCapacity(rs.getString("capacity"));
                    room.setStatus(rs.getBoolean("status"));
                    room.setDateCreated(rs.getString("date_created"));
                    room.setDescription(rs.getString("notes"));
                    roomList.add(room);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

}
