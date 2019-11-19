package com.example.Project1.mappers;

import com.example.Project1.models.Difficulty;
import com.example.Project1.models.Task;
import com.example.Project1.models.TaskResponse;
import org.springframework.stereotype.Component;

import java.util.*;

/*
{Υλοποιήστε όλα τα CRUD operations για αυτό το Entity που θα επιστρέφουν την πληροφορία(
    id,
    title,
    desc,
    difficulty,
    status). Αν ο μέσος όρος των 3 estimations είναι <2 diffculty = EASY, <=4 MEDIUM, >5 HARD.}

    To NA ίσως χρειαστεί για να ελέγξουμε παράλογες τιμές στα estimations π.χ. estimation <= 0
 */


@Component
public class TaskMapper {

    public List<TaskResponse> mapAllTasks (Iterable<Task> AllTasks){
        List<TaskResponse> allTaskResponses = new ArrayList<>();
            for (Task task : AllTasks){
                allTaskResponses.add(mapTaskToTaskResponse(task));
            }
            return allTaskResponses;

    }


    //id, title, desc, difficulty, status

    public TaskResponse mapTaskToTaskResponse (Task task){
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDesc(),
                getDifficulty(task),
                task.getTaskStatus()
        );
    }

    //{Αν ο μέσος όρος των 3 estimations είναι <2 diffculty = EASY, <=4 MEDIUM, >5 HARD.}

    // Difficulty.NA για την περίπτωση που κάποιο estimation έχει παράλογες τιμές π.χ. <=0

    private Difficulty getDifficulty(Task task) {
        double average = (task.getEstimationA()+task.getEstimationB()+task.getEstimationC())/3.0;
        if (average<=0)
            return Difficulty.NA;
        if (average<2)
            return Difficulty.EASY;
        if (average<=4)
            return Difficulty.MEDIUM;
        if (average>5)
            return Difficulty.HARD;

        //Unreachable Statement not detected by the compiler:
        else
            return Difficulty.NA;
    }
}
