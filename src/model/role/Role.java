/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.role;

import model.permission.dashboardpermission.DashboardPermission;

/**
 *
 * @author John Ferdinand Antonio
 */
public class Role {
    private int id;
    private String roleName;
    private boolean isActive;
    private DashboardPermission dashboardPermission;

    public DashboardPermission getDashboardPermission() {
        return dashboardPermission;
    }

    public void setDashboardPermission(DashboardPermission dashboardPermission) {
        this.dashboardPermission = dashboardPermission;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    
}
