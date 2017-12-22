package com.ssm.admin.mapper;

import com.ssm.admin.bean.Admin;

import java.util.List;

public interface AdminMapper {

    int addAdminSelect(Admin record);

    int addAdmin_Role(int adminId, int roleId);

    int deleteAdmin(int adminId);

    int editAdminInfo(Admin admin);

    List<Admin> findAdminsBySelected(Admin admin);

    List<Admin> findAdminByAdminCode(String adminCode);
}