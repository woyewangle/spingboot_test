package com.example.demo.db;

import com.example.demo.entity.Company;
import com.example.demo.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @Author: 余锡鸿
 * @Description:
 * @Date: Create in 8:21 PM 7/27/2018
 * @Modified By:
 */
@Component
public class MemoryDB {
    public ArrayList<Employee> EmployeeList=new ArrayList<>();
    public ArrayList<Company> companyList=new ArrayList<>();

    public MemoryDB(){
        companyList.add(new Company(1L,"小米",2));
        companyList.add(new Company(2L,"大米",3));
        EmployeeList.add(new Employee(0L,"小明",20,"male",1000,1));
        EmployeeList.add(new Employee(1L,"小红",19,"female",2000,2));
//        EmployeeList.add(new Employee(2,"小智",15,"男",3000));
//        EmployeeList.add(new Employee(3,"小刚",16,"男",4000));
//        EmployeeList.add(new Employee(4,"小霞",15,"女",5000));

    }


    public ArrayList<Employee> getEmployeeList() {
        return EmployeeList;
    }

    public void setEmployeeList(ArrayList<Employee> EmployeeList) {
        this.EmployeeList = EmployeeList;
    }

    public ArrayList<Company> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(ArrayList<Company> companyList) {
        this.companyList = companyList;
    }
}
