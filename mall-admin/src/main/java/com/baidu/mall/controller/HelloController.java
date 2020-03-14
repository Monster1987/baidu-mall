package com.baidu.mall.controller;

import com.baidu.mall.dao.UmsAdminRoleRelationDao;
import com.baidu.mall.model.UmsAdminRoleRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class HelloController {
    @Autowired
    UmsAdminRoleRelationDao umsAdminRoleRelationDao;
    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable String name){
        return name;
    }

    @RequestMapping("/insert")
    public String insert(){
        int i=umsAdminRoleRelationDao.insert();
        return "success："+i;
    }
}
