package com.example.Project1.endpoints;

import com.example.Project1.Project1Application;
import com.example.Project1.jsons.TasksJsons;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Project1Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskFeatures {
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getAllTasks() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/allTasks").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json(TasksJsons.allTasksJson));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getTaskById() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/findTaskById/1").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json(TasksJsons.getTaskById));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createTask() {
        try {

            mockMvc.perform(MockMvcRequestBuilders.post("/createTask").content(
                    TasksJsons.postJson
            ).contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated()).andExpect(content().json(TasksJsons.createdTaskJson));
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
    @Test
    @Transactional
    public void updateTask(){
        try{
            mockMvc.perform(MockMvcRequestBuilders.patch("/updateTask/1").content(
                    TasksJsons.updateTaskJson
            ).contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk()).andExpect(content().json(TasksJsons.updatedTaskJson));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
