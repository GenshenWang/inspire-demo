package com.wgs.codedesign.组合模式.hr_tree_v1.dao;

import java.util.*;

import static com.wgs.codedesign.组合模式.hr_tree_v1.DeptEmpIdConst.*;

/**
 * @author: wanggenshen
 * @date: 2020/4/2 17:10.
 * @description: 模拟数据库部门表存储数据
 */
public class DepartmentDao {





    private static Map<Long, List<Long>> deptMap = new HashMap<>();

    static {
        deptMap.put(COMPANY_ID, new ArrayList<>(Arrays.asList(HR_DEPARTMENT_ID, IT_DEPARTMENT_ID, MARKET_DEPARTMENT_ID)));
    }

    public List<Long> getSubDepartmentIds(long departmentId) {
        return deptMap.get(departmentId);
    }
}
