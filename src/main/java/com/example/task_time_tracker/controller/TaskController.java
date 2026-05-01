package com.example.task_time_tracker.controller;

import com.example.task_time_tracker.dto.TaskRequest;
import com.example.task_time_tracker.model.Task;
import com.example.task_time_tracker.model.TaskStatus;
import com.example.task_time_tracker.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public Task saveRecord(@Valid @RequestBody TaskRequest taskRequest) {
        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        return taskService.createTask(task);
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PatchMapping("/{id}/status")
    public Task updateTaskStatus(
            @PathVariable Long id,
            @RequestParam TaskStatus taskStatus) {
        return taskService.updateTaskStatus(id, taskStatus);
    }
}