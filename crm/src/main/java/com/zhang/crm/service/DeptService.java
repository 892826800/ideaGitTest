package com.zhang.crm.service;

import com.zhang.crm.mapper.DeptMapper;
import com.zhang.crm.pojo.Dept;
import com.zhang.crm.pojo.DeptExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptService {
    @Autowired
    private DeptMapper deptMapper;
    public List<Dept> getAllDept() {
        DeptExample deptExample=new DeptExample();
        deptExample.setOrderByClause("dept_Name asc");//字段为数据库里面字段
        deptExample.createCriteria().andDeptIdIsNotNull();
        List<Dept> depts=deptMapper.selectByExample(deptExample);
        return depts;
    }
}
