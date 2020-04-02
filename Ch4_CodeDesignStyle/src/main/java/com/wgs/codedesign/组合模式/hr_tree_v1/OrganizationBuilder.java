package com.wgs.codedesign.组合模式.hr_tree_v1;

import com.wgs.codedesign.组合模式.hr_tree_v1.dao.DepartmentDao;
import com.wgs.codedesign.组合模式.hr_tree_v1.dao.EmployeeDao;

import java.util.List;

/**
 * @author: wanggenshen
 * @date: 2020/4/2 17:07.
 * @description: 构建树形数据结构
 */
public class OrganizationBuilder {

    public static final int COMPANY_CODE = 1;

    private DepartmentDao departmentDao;
    private EmployeeDao employeeDao;

    public OrganizationBuilder(DepartmentDao departmentDao, EmployeeDao employeeDao) {
        this.departmentDao = departmentDao;
        this.employeeDao = employeeDao;
    }

    public void buildOrganization() {
        // 根目录
        Department department = new Department(COMPANY_CODE);
        // 构建子树
        buildOrganization(department);
    }

    private void buildOrganization(Department department) {
        List<Long> subDeptIds = departmentDao.getSubDepartmentIds(department.getId());
        for (Long subDeptId : subDeptIds) {
            Department dept = new Department(subDeptId);
            buildOrganization(dept);
        }

        List<Long> empIds = employeeDao.getEmpByDeptId(department.getId());
        for (Long empId : empIds) {
            double salary = employeeDao.getSalaryByEmpId(empId);
            Employee employee = new Employee(empId, salary);
            department.addNode(employee);
        }
    }
}
