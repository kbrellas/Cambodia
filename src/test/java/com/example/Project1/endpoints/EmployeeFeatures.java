package com.example.Project1.endpoints;

import com.example.Project1.Project1Application;
import com.example.Project1.jsons.EmployeeJsons;
import com.example.Project1.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Project1Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeFeatures {
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getAllEmployees() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/allEmployees").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json(EmployeeJsons.allEmployeesJson));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getEmployeesBySearchCriteria() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/getEmployeeBySearchCriteria/unit/3").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json(EmployeeJsons.getEmployeeBySearchCriteriaJson));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createEmployee() {
        try {

            mockMvc.perform(MockMvcRequestBuilders.post("/createEmployee").content(
                    EmployeeJsons.postJson
            ).contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated()).andExpect(content().json(EmployeeJsons.createdEmployeeJson));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void updateEmployee(){
        try{
            mockMvc.perform(MockMvcRequestBuilders.patch("/updateEmployee/1").content(
                    EmployeeJsons.postJson
                    ).contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk()).andExpect(content().json(EmployeeJsons.updatedEmployeeJson));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}


