package com.example.Project1.services;

import com.example.Project1.mappers.TaskMapper;
import com.example.Project1.models.*;
import com.example.Project1.models.Error;
import com.example.Project1.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class TaskService {
    @Autowired
    private
    TaskMapper mapper;

    @Autowired
    private
    TaskRepository repository;

    public GenericResponse<List<TaskResponse>> getAllTasks () {
        try{
            Iterable<Task> tasks = repository.findAll();
            List<TaskResponse> retrievedTasks = mapper.mapAllTasks(tasks);
            if(retrievedTasks == null){
                return new GenericResponse<>(new Error(0,"Empty Repository","There are no Tasks stored"));
            }
            return new GenericResponse<>(retrievedTasks);
        }catch(Exception e){
            e.printStackTrace();
            return new GenericResponse<>(new Error(0,"Internal Server Error", "Unable to retrieve data"));
        }
    }

    public GenericResponse<List<Task>> getAllTasksByDifficulty(String difficulty) {
        try {
            Iterable<Task> tasks = repository.findAll();
            List<Task> retrievedTasks = mapper.mapTasksByDifficulty(tasks, difficulty);
            if (retrievedTasks.isEmpty()) {
                return new GenericResponse<>(new Error(0, "Empty Repository", "There are no Tasks with difficulty " + difficulty + " stored"));
            }
            return new GenericResponse<>(retrievedTasks);
        } catch (Exception e){
            e.printStackTrace();
            return new GenericResponse<>(new Error(0,"Internal Server Error", "Unable to retrieve data"));
        }
    }

    public GenericResponse<List<Task>> getAllTasksByNumberOfEmployees(long numberOfEmployees) {
        Iterable<Task> tasks = repository.findAll();
        List<Task> retrievedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getEmployees().size() == numberOfEmployees)
                retrievedTasks.add(task);
        }
        if (retrievedTasks.isEmpty()) {
            return new GenericResponse<>(new Error(0, "Empty Repository", "There are no Tasks with number of employees " + numberOfEmployees + " stored"));
        }

        return new GenericResponse<>(retrievedTasks);
    }

    public GenericResponse<Task> getTaskById(long id){
        Optional<Task> fetchedTask= repository.findById(id);
        if(!fetchedTask.isPresent()){
            return new GenericResponse<>(new Error(0,"Wrong input error","Task with id : "+id+" does not exist."));
        }
        Task task= fetchedTask.get();
        return new GenericResponse<>(task);

    }

   public GenericResponse<FullTaskInfoResponse> getFullTaskInfoResponse(Task task,List<EmployeeResponse> employees){
        return new GenericResponse<>(mapper.mapFullTaskInfoResponse(task, employees));
   }

    public GenericResponse<Task> assignEmployeeToTask(Employee employee, Task task) {
        List<Employee> listOfEmployees=task.getEmployees();
        listOfEmployees.add(employee);
        task.setEmployees(listOfEmployees);
        repository.save(task);
        return new GenericResponse<>(task);
    }



    public GenericResponse<Task> createNewTask(Task task, @Nullable List<Employee> employees) {
        if(employees==null) {
            repository.save(task);
            return new GenericResponse<>(task);
        }
        Unit firstUnit=employees.get(0).getUnit();
        for (Employee employee: employees
             ) {
            if(employee.getUnit()!=firstUnit){
                return new GenericResponse<>(new Error(0,"Wrong employees input for task","Cannot add employees " +
                        "that belong to different Units to one task"));
            }
        }
        task.setEmployees(employees);
        repository.save(task);
        return new GenericResponse<>(task);
    }

    public GenericResponse<Task> updateTask(Task partialTask, long taskId, @Nullable List<Employee> employees) {

        Map<String, Object> taskMap = new HashMap<>();
        Field[] allFields = partialTask.getClass().getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            try {

                if(!field.getName().equalsIgnoreCase("id")) {
                    Object value = field.get(partialTask);
                    if (value != null&& !value.equals(0)) {
                        taskMap.put(field.getName(), value);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return new GenericResponse<>(new Error(0, "Cannot Access field", "Field : "+field));
            }
            field.setAccessible(false);
        }
        Task retrievedTask=repository.findById(taskId).get();
        taskMap.forEach((k, v) -> {
            // use reflection to get field k on retrievedEmployee and set it to value k
            Field field = ReflectionUtils.findField(Task.class, k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, retrievedTask, v);
            field.setAccessible(false);
        });
        if(employees!=null){
            Unit firstUnit=employees.get(0).getUnit();
            for (Employee employee: employees
                 ) {
                if(employee.getUnit()!=firstUnit){
                    return new GenericResponse<>(new Error(0,"Wrong employees input for task","Cannot add employees " +
                            "that belong to different Units to one task"));
                }

            }
            retrievedTask.setEmployees(employees);

        }
        repository.save(retrievedTask);
        return new GenericResponse<>(retrievedTask);

    }

    public GenericResponse<TaskResponse> deleteTask(long taskId) {
        Optional<Task> fetchedTask= repository.findById(taskId);
        if(fetchedTask.isPresent()){
            repository.deleteById(taskId);
            return new GenericResponse<>(mapper.mapTaskToTaskResponse(fetchedTask.get()));
        }
        return new GenericResponse<>(new Error(0,"Wrong input","Id does not exist"));
    }
}
