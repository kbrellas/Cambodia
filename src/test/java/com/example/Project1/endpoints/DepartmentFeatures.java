package com.example.Project1.endpoints;

import com.example.Project1.Project1Application;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Project1Application.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DepartmentFeatures {
    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp(){
        mockMvc=webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getAllDepartments(){
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/allDepartments").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json(allDepartmentsJson()));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private String allDepartmentsJson() {
        String json="[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"HR1\",\n" +
                "        \"businessUnitName\": \"Business Unit 1\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"name\": \"HR2\",\n" +
                "        \"businessUnitName\": \"Business Unit 2\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 3,\n" +
                "        \"name\": \"HR3\",\n" +
                "        \"businessUnitName\": \"Business Unit 3\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 4,\n" +
                "        \"name\": \"HR4\",\n" +
                "        \"businessUnitName\": \"Business Unit 4\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 5,\n" +
                "        \"name\": \"RnD1\",\n" +
                "        \"businessUnitName\": \"Business Unit 1\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 6,\n" +
                "        \"name\": \"RnD2\",\n" +
                "        \"businessUnitName\": \"Business Unit 2\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 7,\n" +
                "        \"name\": \"RnD3\",\n" +
                "        \"businessUnitName\": \"Business Unit 3\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 8,\n" +
                "        \"name\": \"RnD4\",\n" +
                "        \"businessUnitName\": \"Business Unit 4\"\n" +
                "    }\n" +
                "]";
        return json;
    }
}
