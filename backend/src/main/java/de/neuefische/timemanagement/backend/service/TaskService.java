package de.neuefische.timemanagement.backend.service;

import de.neuefische.timemanagement.backend.model.Task;
import de.neuefische.timemanagement.backend.repository.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepo taskRepo;
    private final IdService idService;

    public List<Task> getAllTasks(){
        return taskRepo.getAllTasks();
    }

    public Task addTask(Task newTask){
        if(newTask.title()==null ||newTask.title().equals("") ||newTask.dateTime()==null){
            throw new IllegalArgumentException("missing title or date");
        }
        String id= idService.generateId();
        Task newTaskWithId = new Task(id, newTask.title(), newTask.dateTime());
        return taskRepo.addTask(newTaskWithId);
    }


}
