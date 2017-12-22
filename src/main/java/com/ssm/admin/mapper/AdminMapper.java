package com.ssm.admin.mapper;

import com.ssm.admin.bean.Admin;

import java.util.List;

public interface AdminMapper {

    int addAdminSelect(Admin record);

    int addAdmin_Role(int adminId, int roleId);

    int deleteAdmin(int adminId);

    int deleteAdmin_Role(int adminId, int roleId);

    int editAdminInfo(Admin admin);

    Admin findLastAdmin();

    List<Admin> findAdminsByAdminId(int adminId);

    List<Admin> findAdminsBySelected(Admin admin);

    List<Admin> findAdminByAdminCode(String adminCode);

    int findad_rByAdminIdRoleId(int adminId, int roleId);
}