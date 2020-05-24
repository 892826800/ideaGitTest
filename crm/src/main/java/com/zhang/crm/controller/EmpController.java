package com.zhang.crm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhang.crm.pojo.Emp;
import com.zhang.crm.pojo.Msg;
import com.zhang.crm.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmpController {
    @Autowired
    private EmpService empService;

    @RequestMapping("/toIndex")
    public String toIndex() {
        return "EmpsAjax";
    }

    @RequestMapping("/findAllEmps")
    public String findAllEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
                              Model model) {
        PageHelper.startPage(pn, 5);
        List<Emp> emps = empService.findEmpsWithDept();
        PageInfo<Emp> pageInfo = new PageInfo<>(emps, 5);
        model.addAttribute("emps", pageInfo);
        return "AllEmps";
    }

    /*对象转JSON必须实现其Getter和Setter*/
    @GetMapping("/allEmpsAjax")
    @ResponseBody
    public Msg allEmps(Integer pn) {
        PageHelper.startPage(pn, 5);
        List<Emp> emps = empService.findEmpsWithDept();
        PageInfo<Emp> pageInfo = new PageInfo<>(emps, 5);
        return Msg.success().add("pageInfo", pageInfo);
    }

    /*添加员工*/
    @GetMapping("/saveEmp")
    @ResponseBody
    public Msg saveEmp(Emp emp) {
        empService.saveEmp(emp);
        return Msg.success();
    }

    /*检查用户名*/
    @GetMapping("/checkName/emp/{empName}")
    @ResponseBody
    public Msg checkName(@PathVariable("empName") String empName) {
        boolean nameStatus = empService.checkNameStatus(empName);
        if (nameStatus) {
            return Msg.success();
        } else {
            return Msg.fail().add("nameStatus", "用户名已经注册");
        }


    }

    /*更新员工*/
    @PostMapping("/saveEmp/emp/{empId}")
    @ResponseBody
    public Msg updateEmp(Emp emp) {
        empService.updeteEmp(emp);
        return Msg.success();
    }

    /*通过id获取员工*/
    @GetMapping("/getEmp/{id}")
    @ResponseBody
    public Msg getEmp(@PathVariable(value = "id") Integer id) {
        Emp emp = empService.getEmp(id);
        return Msg.success().add("empInfo", emp);
    }

    @DeleteMapping("/saveEmp/emp/{empId}")
    @ResponseBody
    public Msg deleteEmp(@PathVariable("empId") Integer empId) {
        empService.delEmpByID(empId);
        return Msg.success();
    }

    @DeleteMapping("/deleteAllEmp/emp/{strIds}")
    @ResponseBody
    public Msg deleteAllEmp(@PathVariable("strIds") String strIds) {
        if (strIds.contains("-")) {
            List<Integer> del_ids = new ArrayList<>();
            String[] str_ids = strIds.split("-");
            //组装id的集合
            for (String string : str_ids) {
                del_ids.add(Integer.valueOf(string));
            }
            empService.deleteBatch(del_ids);
        }else {
            empService.delEmpByID(Integer.valueOf(strIds));
        }

        return Msg.success();
    }
}

