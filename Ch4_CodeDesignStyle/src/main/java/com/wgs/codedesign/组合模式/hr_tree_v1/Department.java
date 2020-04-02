package com.wgs.codedesign.组合模式.hr_tree_v1;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author: wanggenshen
 * @date: 2020/4/2 15:58.
 * @description: XXX
 */
public class Department extends HumanResource {

    private List<HumanResource> subNodes = Lists.newArrayList();

    public Department(long id) {
        super(id);
    }

    @Override
    public double calculateSalary() {
        if (CollectionUtils.isEmpty(subNodes)) {
            return 0;
        }
        double totalSalary = 0;
        for (HumanResource department : subNodes) {
            totalSalary += department.calculateSalary();
        }
        this.salary = totalSalary;
        return salary;
    }

    public void addNode(HumanResource node) {
        subNodes.add(node);
    }
}
