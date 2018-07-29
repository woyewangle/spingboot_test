package com.example.demo.controller;

import com.example.demo.entity.Company;
import com.example.demo.entity.Employee;
import com.example.demo.service.CompanyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Author: 余锡鸿
 * @Description:
 * @Date: Create in 8:44 PM 7/27/2018
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;

    @Autowired
    private ObjectMapper mapper;


    @Test
    public void should_return_right_companyList_given_a_company_when_call_getCompanyList() throws Exception {
        ArrayList<Company> companyList = new ArrayList<>();
        companyList.add(new Company(1l, "小米", 2));
        given(companyService.getCompanyList()).willReturn(companyList);
        mockMvc.perform(get("/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("小米"))
                .andExpect(jsonPath("$[0].employeeNumber").value(2));

    }

    @Test
    public void should_add_company_succeed_given_a_company_when_call_addcompany() throws Exception {
        //given
        Company company = new Company(1l, "小米", 2);
        given(companyService.addCompany(any(Company.class))).willReturn(company);
        //when
        mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(company)))
                .andExpect(jsonPath("$.name").value("小米"))
                .andExpect(jsonPath("$.employeeNumber").value(2));
    }

    @Test
    public void should_get_right_comapany_given_id_when_call_findById() throws Exception {
        //given
        ArrayList<Company> companyList = new ArrayList<>();
        Company company = new Company(1l, "小米", 2);
        companyList.add(company);
        given(companyService.findCompanyById(1l)).willReturn(company);
        //when
        mockMvc.perform(get("/companies/{0}", 1L))
                .andExpect(jsonPath("$.name").value("小米"))
                .andExpect(jsonPath("$.employeeNumber").value(2));
        //then
    }

    @Test
    public void should_update_company_succeed_given_a_new_company_when_call_updateCompany() throws Exception {
        //given
        ArrayList<Company> companyList = new ArrayList<>();
        Company company = new Company(1l, "小米", 2);
        Company newcompany = new Company(1l, "大米", 2);
        companyList.add(company);
        //直接传1L报错，需要使用Matchers.eq（+具体值）
        given(companyService.updateCompany(Matchers.eq(1L), any(Company.class))).willReturn(newcompany);
        mockMvc.perform(put("/companies/{0}", 1L)
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(newcompany)))
                .andExpect(jsonPath("$.name").value("大米"));
    }

    @Test
    public void should_return_true_given_id_when_call_deleteCompany() throws Exception {
        //given
        ArrayList<Company> companyList = new ArrayList<>();
        Company company = new Company(1l, "小米", 2);
        given(companyService.deleteCompany(1L)).willReturn(company);
        mockMvc.perform(delete("/companies/{0}", 1L))
                .andExpect(jsonPath("$.name").value("小米"))
                .andExpect(jsonPath("$.employeeNumber").value(2));
        //when
        //then
    }

    @Test
     public void should_getEmployeeList_given_a_companyId_when_call_getEmployeesByCompanyId() throws Exception{
        //given
        ArrayList<Company> companyList = new ArrayList<>();
        Company company = new Company(1l, "小米", 2);
        companyList.add(company);
        ArrayList<Employee> employeeList = new ArrayList<>();
        Employee employee = new Employee(1L,"小红",19,"female",2000,2);
        employeeList.add(employee);
        given(companyService.getEmployeesByCompanyId(1L)).willReturn(employeeList);
        mockMvc.perform(get("/companies/{0}/employees",1L))
                .andExpect(jsonPath("$[0].name").value("小红"));


        }
    @Test
     public void should_return_companyList_given_index_and_size_when_call_getCompanyByPage() throws Exception{
        //given
        ArrayList<Company> companyList = new ArrayList<>();
        Company company = new Company(1l, "小米", 2);
        Company company1 = new Company(1l, "大米", 2);
        companyList.add(company);
        companyList.add(company1);
        //when
        given(companyService.getCompanyByPage(0,2)).willReturn(companyList);
        mockMvc.perform(get("/companies/page/{0}/pageSize/{0}",0,2))
                .andExpect(jsonPath("$[0].name").value("小米"))
                .andExpect(jsonPath("$[1].name").value("大米"));

        //then

        }




}