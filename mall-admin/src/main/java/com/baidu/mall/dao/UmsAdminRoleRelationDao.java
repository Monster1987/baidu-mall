package com.baidu.mall.dao;

import com.baidu.mall.model.UmsAdminRoleRelation;
import com.baidu.mall.model.UmsPermission;
import com.baidu.mall.model.UmsRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsAdminRoleRelationDao {
    /**
     * 批量插入用户关系角色
     */
    int insertList(@Param("list")List<UmsAdminRoleRelation> adminRoleRelationList);
    int insert();

    /**
     * 获取用户的所有角色
     */
    List<UmsRole> getRoleList(@Param("adminId") Long adminId);

    /**
     * 获取用户的所有权限，包括+-权限(一个参数可以不使用@Param？)
     */
    List<UmsPermission> getPermissionList(Long adminId);


}
