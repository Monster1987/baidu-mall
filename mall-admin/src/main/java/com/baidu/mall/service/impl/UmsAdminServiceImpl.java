package com.baidu.mall.service.impl;

import com.baidu.mall.dao.UmsAdminRoleRelationDao;
import com.baidu.mall.dto.UmsAdminParam;
import com.baidu.mall.mapper.UmsAdminLoginLogMapper;
import com.baidu.mall.mapper.UmsAdminMapper;
import com.baidu.mall.model.UmsAdmin;
import com.baidu.mall.model.UmsAdminExample;
import com.baidu.mall.model.UmsAdminLoginLog;
import com.baidu.mall.model.UmsPermission;
import com.baidu.mall.security.util.JwtTokenUtil;
import com.baidu.mall.service.UmsAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * UmsAdminService实现类
 */
public class UmsAdminServiceImpl implements UmsAdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    UmsAdminMapper umsAdminMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UmsAdminRoleRelationDao umsAdminRoleRelationDao;
    @Autowired
    UmsAdminLoginLogMapper loginLogMapper;

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdminExample example=new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> umsAdminList = umsAdminMapper.selectByExample(example);
        if(umsAdminList!=null&&umsAdminList.size()>0){
            return umsAdminList.get(0);
        }
        return null;
    }

    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin=new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam,umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        //查询是否有相同的用户
        UmsAdminExample example=new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
        List<UmsAdmin> umsAdminList = umsAdminMapper.selectByExample(example);
        if(umsAdminList.size()>0){
            return null;
        }
        //对密码进行加密
        String encodePassword=passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        umsAdminMapper.insert(umsAdmin);
        return umsAdmin;
    }

    @Override
    public String login(String username, String password) {
        String token=null;
        //密码需要客户端加密后传递
        try{
            UserDetails userDetails=loadUserByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            token=jwtTokenUtil.generateToken(userDetails);
            insertLoginLog(username);
        }catch (AuthenticationException e){
            LOGGER.warn("登录异常：{}",e.getMessage());
        }
        return token;
    }

    /**
     * 添加登录记录
     * @param username
     */
    private void insertLoginLog(String username){
        UmsAdmin umsAdmin=getAdminByUsername(username);
        UmsAdminLoginLog loginLog=new UmsAdminLoginLog();
        loginLog.setAdminId(umsAdmin.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        loginLog.setIp(request.getRemoteAddr());
        loginLogMapper.insert(loginLog);
    }

    @Override
    public String refreshToken(String oldToken) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        //获取用户信息
        UmsAdmin umsAdmin=getAdminByUsername(username);
        if(umsAdmin!=null){

        }
        return null;
    }

    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return umsAdminRoleRelationDao.getPermissionList(adminId);
    }
}
