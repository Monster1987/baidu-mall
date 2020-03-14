package com.baidu.mall.service;

import com.baidu.mall.dto.UmsAdminParam;
import com.baidu.mall.model.UmsAdmin;
import com.baidu.mall.model.UmsPermission;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * 后台管理员Service
 */
public interface UmsAdminService {
    /**
     * 根据用户名获取后台管理员
     */
    UmsAdmin getAdminByUsername(String username);
    /**
     * 注册功能
     */
    UmsAdmin register(UmsAdminParam umsAdminParam);

    /**
      * @Description: 登录功能
      * @Date: 2020/3/14
      * @params [username, password]
      * @return 生成的JWT的token
      **/
    String login(String username,String password);

    /**
      * @Description: 刷新token的功能
      * @Date: 2020/3/14
      * @params [oldToken]旧的token
      * @return 
      **/
    String refreshToken(String oldToken);

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     */
    List<UmsPermission> getPermissionList(Long adminId);






}
