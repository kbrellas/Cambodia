package com.example.Project1.endpoints;

import com.example.Project1.Project1Application;
import com.example.Project1.jsons.UnitJsons;
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
                    .andExpect(content().json(UnitJsons.allUnitsJson));
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
