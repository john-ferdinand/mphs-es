package dao;

import java.util.List;
import model.faculty.Faculty;
import model.user.User;

/**
 *
 * @author Acer
 */
public interface IUser {
    Integer getIdByUsername(String username);
    User getById(int userId);
    boolean add(User user);
    boolean addFacultyAsUser(Faculty faculty);
    boolean update(User user);
    List<User> getAll();
}
