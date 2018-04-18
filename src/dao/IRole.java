/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.role.Role;

/**
 *
 * @author John Ferdinand Antonio
 */
public interface IRole {
    Integer getId(String roleName);
    Role getById(int roleId);
    Role getByUserId(int userId);
    List<Role> getAll();
}
