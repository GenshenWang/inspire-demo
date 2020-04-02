package com.wgs.codedesign.组合模式.hr_tree_v1.dao;

import java.util.*;

import static com.wgs.codedesign.组合模式.hr_tree_v1.DeptEmpIdConst.*;

/**
 * @author: wanggenshen
 * @date: 2020/4/2 17:10.
 * @description: 模拟数据库员工表存储、查询数据
 */
public class EmployeeDao {

    private static Map<Long, List<Long>> empMap = new HashMap<>();
    private static Map<Long, Double> empSalaryMap = new HashMap<>();


    static {
        empMap.put(COMPANY_ID, new ArrayList<>(Arrays.asList(ctoEmpId)));
        empMap.put(HR_DEPARTMENT_ID, new ArrayList<>(Arrays.asList(hr1EmpId, hr2EmpId, hr3EmpId)));
        empMap.put(IT_DEPARTMENT_ID, new ArrayList<>(Arrays.asList(it1EmpId)));
        empMap.put(MARKET_DEPARTMENT_ID, new ArrayList<>(Arrays.asList(market1EmpId, market2EmpId, market3EmpId)));
    }

    static {
        empSalaryMap.put(ctoEmpId, Double.valueOf(1000000));
        empSalaryMap.put(hr1EmpId, Double.valueOf(5000));
        empSalaryMap.put(hr2EmpId, Double.valueOf(5500));
        empSalaryMap.put(hr3EmpId, Double.valueOf(6000));
        empSalaryMap.put(it1EmpId, Double.valueOf(12000));
        empSalaryMap.put(market1EmpId, Double.valueOf(8000));
        empSalaryMap.put(market2EmpId, Double.valueOf(8500));
        empSalaryMap.put(market3EmpId, Double.valueOf(9500));

    }

    public List<Long> getEmpByDeptId(long deptId) {
        return empMap.get(deptId);
    }

    public double getSalaryByEmpId(long empId) {
        return empSalaryMap.get(empId);
    }
}
