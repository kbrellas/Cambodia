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
public class UnitFeatures {
    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp(){
        mockMvc=webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getAllUnits(){
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/allUnits").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json(allUnitsJson()));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private String allUnitsJson() {
        String json ="[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"Unit 1\",\n" +
                "        \"departmentName\": \"HR1\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"name\": \"Unit 2\",\n" +
                "        \"departmentName\": \"HR1\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 3,\n" +
                "        \"name\": \"Unit 3\",\n" +
                "        \"departmentName\": \"HR2\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 4,\n" +
                "        \"name\": \"Unit 4\",\n" +
                "        \"departmentName\": \"HR2\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 5,\n" +
                "        \"name\": \"Unit 5\",\n" +
                "        \"departmentName\": \"HR3\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 6,\n" +
                "        \"name\": \"Unit 6\",\n" +
                "        \"departmentName\": \"HR3\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 7,\n" +
                "        \"name\": \"Unit 7\",\n" +
                "        \"departmentName\": \"HR4\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 8,\n" +
                "        \"name\": \"Unit 8\",\n" +
                "        \"departmentName\": \"HR4\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 9,\n" +
                "        \"name\": \"Unit 9\",\n" +
                "        \"departmentName\": \"RnD1\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 10,\n" +
                "        \"name\": \"Unit 10\",\n" +
                "        \"departmentName\": \"RnD1\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 11,\n" +
                "        \"name\": \"Unit 11\",\n" +
                "        \"departmentName\": \"RnD2\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 12,\n" +
                "        \"name\": \"Unit 12\",\n" +
                "        \"departmentName\": \"RnD2\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 13,\n" +
                "        \"name\": \"Unit 13\",\n" +
                "        \"departmentName\": \"RnD3\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 14,\n" +
                "        \"name\": \"Unit 14\",\n" +
                "        \"departmentName\": \"RnD3\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 15,\n" +
                "        \"name\": \"Unit 15\",\n" +
                "        \"departmentName\": \"RnD4\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 16,\n" +
                "        \"name\": \"Unit 16\",\n" +
                "        \"departmentName\": \"RnD4\"\n" +
                "    }\n" +
                "]";
        return  json;
    }
}
