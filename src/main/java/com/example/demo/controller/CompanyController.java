package com.example.demo.controller;

import com.example.demo.entity.Company;
import com.example.demo.entity.Employee;
import com.example.demo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 余锡鸿
 * @Description:
 * @Date: Create in 8:29 PM 7/27/2018
 * @Modified By:
 */
@RestController
public class CompanyController {
    @Autowired
    public CompanyService companyService;

    @GetMapping("/companies")
    public List getCompanyList(){
        return companyService.getCompanyList();
    }

    @GetMapping("/companies/{id}")
    public Company findCompanyById(@PathVariable int id){
        System.out.println(companyService.findCompanyById(id));
        return companyService.findCompanyById(id);
    }

    @PostMapping("/companies")
    public List<Company> addCompany(@RequestBody Company employee){
        return companyService.addCompany(employee);
    }

    @DeleteMapping("/companies/{id}")
    public List<Company> deleteEmployee(@PathVariable int id){
        return companyService.deleteCompany(id);
    }

    @PutMapping("/companies/{id}")
    public Company updateEmployee(@PathVariable int id, @RequestBody Company employee) {
        return companyService.updateCompany(id,employee);
    }

    @GetMapping("/companies/{id}/employees")
    public List<Employee> getEmployeesByCompanyId(@PathVariable int id) {
        return companyService.getEmployeesByCompanyId(id);
    }

    @GetMapping("/companies/page/{index}/pageSize/{size}")
    public List<Company> getCompaniesByPage(@PathVariable int index, @PathVariable int size) {
        return companyService.getCompanyByPage(index, size);
    }
}
