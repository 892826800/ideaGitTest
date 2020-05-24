package com.zhang.crm.controller;

import com.zhang.crm.mapper.DeptMapper;
import com.zhang.crm.pojo.Dept;
import com.zhang.crm.pojo.Msg;
import com.zhang.crm.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DeptController {
    @Autowired
    private DeptService deptService;

    @RequestMapping("/getAllDept")
    @ResponseBody
    public Msg getDept(){
        List<Dept> depts=deptService.getAllDept();
        return Msg.success().add("depts" ,depts);
    }
   @ResponseBody
public String tsr(){
        return "123456";
}
}
