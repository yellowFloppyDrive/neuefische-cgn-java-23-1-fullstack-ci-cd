package de.neuefische.timemanagement.backend.controller;

import de.neuefische.timemanagement.backend.model.Task;
import de.neuefische.timemanagement.backend.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/")
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @PostMapping("/")
    public Task addTask(@RequestBody Task task){
        return taskService.addTask(task);
    }
}
