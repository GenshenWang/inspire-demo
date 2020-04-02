package com.wgs.codedesign.组合模式.hr_tree_v1;

/**
 * @author: wanggenshen
 * @date: 2020/4/2 16:17.
 * @description: XXX
 */
public class Main3 {

    /**
     *             公司
     *       /    |    |    \
     *     CTO   人力  IT  市场
     *         / | \   |  / | \
     *       P1 P2 P3 P4 P5 P6 P7
     *
     *
     * @param args
     */
    public static void main(String[] args) {

        // 部门
        Department companyNode = new Department(1);
        Department dpHr = new Department(101);
        Department dpIt = new Department(102);
        Department dpMarket = new Department(103);
        // 职工
        Employee cto = new Employee(90001, 1000000);
        Employee hr1 = new Employee(91001, 5000);
        Employee hr2 = new Employee(91002, 5500);
        Employee hr3 = new Employee(91002, 6000);
        Employee it4 = new Employee(92001, 12000);
        Employee market1 = new Employee(93001, 8000);
        Employee market2 = new Employee(93002, 8500);
        Employee market3 = new Employee(93003, 9500);

        companyNode.addNode(dpHr);
        companyNode.addNode(dpIt);
        companyNode.addNode(dpMarket);
        companyNode.addNode(cto);

        dpHr.addNode(hr1);
        dpHr.addNode(hr2);
        dpHr.addNode(hr3);

        dpIt.addNode(it4);

        dpMarket.addNode(market1);
        dpMarket.addNode(market2);
        dpMarket.addNode(market3);

        System.out.println("cto工资:" + cto.calculateSalary());
        System.out.println("hr1工资:" + hr1.calculateSalary());
        System.out.println("hr2工资:" + hr2.calculateSalary());
        System.out.println("hr3工资:" + hr3.calculateSalary());
        System.out.println("it4工资:" + it4.calculateSalary());
        System.out.println("market1工资:" + market1.calculateSalary());
        System.out.println("market2工资:" + market2.calculateSalary());
        System.out.println("market3工资:" + market3.calculateSalary());

        System.out.println("Hr部门工资:" + dpHr.calculateSalary());
        System.out.println("IT部门工资:" + dpIt.calculateSalary());
        System.out.println("市场部门工资:" + dpMarket.calculateSalary());
        System.out.println("公司所有工资:" + companyNode.calculateSalary());





    }
}
