package com.example.Project1.controllers;

import com.example.Project1.Interactors.TaskEmployeeInterractor;
import com.example.Project1.models.Error;
import com.example.Project1.models.*;
import com.example.Project1.services.TaskService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.mockito.Mockito.when;

public class TaskControllerShould {

    private TaskController controller;

    @Mock
    private TaskService service;

    @Mock
    private TaskEmployeeInterractor interactor;

    @Mock
    private TaskResponse taskResponse1;

    @Mock
    private TaskResponse taskResponse2;

    @Mock
    private TaskResponse taskResponse3;

    @Mock
    private Task task;

/*    @Mock
    private Employee employee1;
    @Mock
    private Employee employee2;*/

    @Mock
    private FullTaskInfoResponse fullTaskInfoResponse;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        List<TaskResponse> mockedTaskResponseList=new ArrayList<>();
        mockedTaskResponseList.add(taskResponse1);
        mockedTaskResponseList.add(taskResponse2);
        mockedTaskResponseList.add(taskResponse3);
        GenericResponse<List<TaskResponse>> serviceResponse=new GenericResponse<>(mockedTaskResponseList);
        when(service.getAllTasks()).thenReturn(serviceResponse);
        when(interactor.createNewTask(task)).thenReturn(new GenericResponse<>(task));
        when(interactor.updateTask(task,1)).thenReturn(new GenericResponse<>(task));
        when(interactor.getFullTaskById(1)).thenReturn(new GenericResponse<>(fullTaskInfoResponse));
        controller=new TaskController(service,interactor);
    }

    @Test
    public void returnAllTasks(){
        ResponseEntity<List<TaskResponse>> actual=controller.getAllTasks();
        Assert.assertThat(actual.getBody(),hasItems(taskResponse1,taskResponse2,taskResponse3));
        Assert.assertEquals(HttpStatus.OK,actual.getStatusCode());
    }

    @Test
    public void returnErrorWhenAllTasksServiceFails(){
        Error error=MockServiceFailure();
        ResponseEntity actual= controller.getAllTasks();
        Assert.assertEquals(error,actual.getBody());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,actual.getStatusCode());
    }


    private Error MockServiceFailure() {
        Error error = new Error(0,"Error","Something went wrong");
        when(service.getAllTasks()).thenReturn(new GenericResponse<>(error));
        when(interactor.getFullTaskById(1)).thenReturn(new GenericResponse<>(error));
        when(interactor.createNewTask(task)).thenReturn(new GenericResponse<>(error));
        when(interactor.updateTask(task,1)).thenReturn(new GenericResponse<>(error));
        return error;
    }

    @Test
    public void returnFullTaskInfoWhenFindTaskById() {
        ResponseEntity actual=controller.getTaskById(1);
        Assert.assertEquals(fullTaskInfoResponse,actual.getBody());
        Assert.assertEquals(HttpStatus.OK,actual.getStatusCode());
    }
    @Test
    public void returnErrorWhenFindTaskByIdFails(){
        Error error=MockServiceFailure();
        ResponseEntity actual=controller.getTaskById(1);
        Assert.assertEquals(error,actual.getBody());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,actual.getStatusCode());

    }

    @Test
    public void returnTaskWhenCreateTask(){
        ResponseEntity actual=controller.createTask(task);
        Assert.assertEquals(task,actual.getBody());
        Assert.assertEquals(HttpStatus.CREATED,actual.getStatusCode());
    }
    @Test
    public void returnErrorWhenCreateTaskFails(){
        Error error=MockServiceFailure();
        ResponseEntity actual=controller.createTask(task);
        Assert.assertEquals(error,actual.getBody());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,actual.getStatusCode());
    }
    @Test
    public void returnTaskWhenUpdateTaskById(){
        ResponseEntity actual=controller.updateTask(task,1);
        Assert.assertEquals(task,actual.getBody());
        Assert.assertEquals(HttpStatus.OK,actual.getStatusCode());

    }
    @Test
    public void returnErrorWhenUpdateTaskById(){
        Error error=MockServiceFailure();
        ResponseEntity actual=controller.updateTask(task,1);
        Assert.assertEquals(error,actual.getBody());
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,actual.getStatusCode());
    }

    }
