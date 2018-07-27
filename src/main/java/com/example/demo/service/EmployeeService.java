package com.example.demo.service;

import com.example.demo.db.MemoryDB;
import com.example.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 余锡鸿
 * @Description:
 * @Date: Create in 8:27 PM 7/27/2018
 * @Modified By:
 */
@Service
public class EmployeeService {

    public final MemoryDB memoryDB;

    @Autowired
    public EmployeeService(MemoryDB memoryDB) {
        this.memoryDB = memoryDB;
    }

    public List getEmployeeList(){
        return memoryDB.getEmployeeList();
    }

    public Employee findEmployeeById(int id) {
        return memoryDB.getEmployeeList().stream().filter(e->e.getId()==id).collect(Collectors.toList()).get(0);
    }


    public List<Employee> addEmployee(Employee employee) {
        memoryDB.getEmployeeList().add(employee);
        return memoryDB.getEmployeeList();
    }

    public List<Employee> deleteEmployee(int id) {
        memoryDB.getEmployeeList().removeIf(employee -> employee.getId()==id);
        return memoryDB.getEmployeeList();

    }

    public Employee updateEmployee(int id, Employee employee) {
        List<Employee> employeeList = memoryDB.getEmployeeList();
        Employee newEmployee=new Employee();
        for (Employee oldEmployee : employeeList) {
            if (oldEmployee.getId()==id) {
                oldEmployee.setAge(employee.getAge());
                oldEmployee.setGender(employee.getGender());
                oldEmployee.setName(employee.getName());
                oldEmployee.setSalary(employee.getSalary());
                oldEmployee.setCompanyId(employee.getCompanyId());
                newEmployee=oldEmployee;
            }
        }
        return newEmployee;
    }

    public List<Employee> getEmployeesByPage(int index, int size) {

        List<Employee> employeeList = memoryDB.getEmployeeList();
        ArrayList<Employee> newEmployeeList = new ArrayList<>();
        for (int i = 0; i < employeeList.size(); i++) {
            if (i >= index * size && i < (index + 1) * size) {
                newEmployeeList.add(employeeList.get(i));
            }
        }
        return newEmployeeList;
    }

    public List<Employee> getEmployeesByGender(String gender) {
        return memoryDB.getEmployeeList().stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }
}
