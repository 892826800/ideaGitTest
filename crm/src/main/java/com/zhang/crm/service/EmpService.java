package com.zhang.crm.service;

import com.zhang.crm.mapper.EmpMapper;
import com.zhang.crm.pojo.Emp;
import com.zhang.crm.pojo.EmpExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpService {
    @Autowired
    private EmpMapper empMapper;

    public List<Emp> findEmpsWithDept() {
        List<Emp> emps = empMapper.selectByExampleWithDept(null);
        return emps;
    }

    public void saveEmp(Emp emp) {
        empMapper.insertSelective(emp);
    }

    public boolean checkNameStatus(String empName) {
        EmpExample empExample = new EmpExample();
        empExample.createCriteria().andEmpNameEqualTo(empName);
        List<Emp> emps = empMapper.selectByExample(empExample);
        if (emps.size() > 0 && !emps.isEmpty()) {
            return false;//有人
        } else {
            return true;//没人
        }
    }

    public void updeteEmp(Emp emp) {
        empMapper.updateByPrimaryKeySelective(emp);
    }

    public Emp getEmp(Integer id) {
        Emp emp=empMapper.selectByPrimaryWithDept(id);
        return  emp;
    }

    public void delEmpByID(Integer empId) {
        empMapper.deleteByPrimaryKey(empId);
    }

    public void deleteBatch(List<Integer> del_ids) {
        EmpExample example = new EmpExample();
        EmpExample.Criteria criteria = example.createCriteria();
        //delete from xxx where emp_id in(1,2,3)
        criteria.andEmpIdIn(del_ids);
        empMapper.deleteByExample(example);
    }
}
