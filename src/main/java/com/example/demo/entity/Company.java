package com.example.demo.entity;

/**
 * @Author: 余锡鸿
 * @Description:
 * @Date: Create in 8:17 PM 7/27/2018
 * @Modified By:
 */

public class Company {
    public Long id;
    public String name;
    public int employeeNumber;


    public Company(){}

    public Company(Long id, String name, int employeeNumber) {
        this.id = id;
        this.name = name;
        this.employeeNumber = employeeNumber;
    }

    public Company(String name, int employeeNumber) {
        this.name = name;
        this.employeeNumber = employeeNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int EmployeeNumber) {
        this.employeeNumber = EmployeeNumber;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", EmployeeNumber=" + employeeNumber +
                '}';
    }
}
