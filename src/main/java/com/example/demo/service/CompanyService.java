package com.example.demo.service;

import com.example.demo.db.MemoryDB;
import com.example.demo.entity.Company;
import com.example.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 余锡鸿
 * @Description:
 * @Date: Create in 8:24 PM 7/27/2018
 * @Modified By:
 */
@Service
public class CompanyService {
    public final MemoryDB memoryDB;

    @Autowired
    public CompanyService(MemoryDB memoryDB) {
        this.memoryDB = memoryDB;
    }

    public List getCompanyList(){
        return memoryDB.getCompanyList();
    }

    public Company findCompanyById(Long id) {
        return memoryDB.getCompanyList().stream().filter(e->e.getId()==id).collect(Collectors.toList()).get(0);
    }

    public Company addCompany(Company company) {
        memoryDB.getCompanyList().add(company);
        return company;
    }

    public Company deleteCompany(Long id) {
        Company company=memoryDB.getCompanyList().stream().filter(e->e.getId()==id).collect(Collectors.toList()).get(0);
        if(company==null){
            return null;
        }else {
            memoryDB.getCompanyList().remove(company);
            return company ;
        }

    }

    public Company updateCompany(long id, Company company) {
        List<Company> companyList = memoryDB.getCompanyList();
        Company newCompany=new Company();
        for (Company oldCompany : companyList) {
            if (oldCompany.getId()==id) {
                oldCompany.setName(company.getName());
                oldCompany.setEmployeeNumber(company.getEmployeeNumber());
                newCompany=oldCompany;
            }
        }
        return newCompany;
    }


    public List<Employee> getEmployeesByCompanyId(Long id) {
        return memoryDB.getEmployeeList().stream()
                .filter(employee -> employee.getCompanyId()==id)
                .collect(Collectors.toList());
    }

    public List<Company> getCompanyByPage(int index, int size) {

        List<Company> companyList = memoryDB.getCompanyList();
        ArrayList<Company> newCompanyList = new ArrayList<>();
        for (int i = 0; i < companyList.size(); i++) {
            if (i >= index * size && i < (index + 1) * size) {
                newCompanyList.add(companyList.get(i));
            }
        }
        return newCompanyList;
    }
}
