package com.zhang.crm;

import com.zhang.crm.mapper.DeptMapper;
import com.zhang.crm.mapper.EmpMapper;
import com.zhang.crm.pojo.Dept;
import com.zhang.crm.pojo.DeptExample;
import com.zhang.crm.pojo.Emp;
import com.zhang.crm.pojo.EmpExample;
import com.zhang.crm.service.EmpService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CrmApplicationTests {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpService empService;

    @Test
    void empSqlTest() {
        EmpExample empExample = new EmpExample();
        EmpExample.Criteria criteria = empExample.createCriteria();
         criteria.andEmpNameEqualTo("Tom");
         List<Emp> emps=empMapper.selectByExampleWithDept(null);
        System.out.println(emps);
    }

    @Test
    void contextLoads() {
       Dept dept=deptMapper.selectByPrimaryKeyWithEmp(1);
        System.out.println("结果"+dept);
    }

    @Test
    void checkTest(){
        System.out.println(empService.checkNameStatus("张5丰"));
    }

    @Test
    void deptSqlTest() {
        DeptExample deptExample=new DeptExample();
       DeptExample.Criteria criteria=deptExample.createCriteria();
        criteria.andDeptNameEqualTo("部门A");
        List<Dept> depts=deptMapper.selectByExampleWithEmp(deptExample);
        System.out.println(depts);
    }




}
