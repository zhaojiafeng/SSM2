package com.ssm.admin.mapper;

import com.ssm.admin.bean.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {

    int addAdminSelect(Admin record);

    int addAdmin_Role(int adminId, int roleId);

    int deleteAdmin(int adminId);

    int deleteAdmin_Role(int adminId, int roleId);

    int editAdminInfo(Admin admin);

    int resetPwd(List list);

    Admin findLastAdmin();

    List<Admin> findAdminByAdminId(int adminId);

    List<Admin> findAdminsInAdminIds(List list);

    List<Admin> findAdminsBySelected(Admin admin);

    List<Admin> findAdminByAdminCode(String adminCode);

    List<Admin> findAdminIdByRolenameModuleId(@Param("rolename") String rolename, @Param("moduleId") Integer moduleId);

    int findad_rByAdminIdRoleId(int adminId, int roleId);
}