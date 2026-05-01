package com.example.task_time_tracker.service;

import com.example.task_time_tracker.exception.ResourceNotFoundException;
import com.example.task_time_tracker.mapper.TaskMapper;
import com.example.task_time_tracker.model.Task;
import com.example.task_time_tracker.model.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskMapper taskMapper;

    public Task createTask(Task task) {
        task.setStatus(TaskStatus.NEW);
        taskMapper.save(task);
        return task;
    }

    public Task getTaskById(Long id) {
        Task task = taskMapper.findById(id);
        if (task == null) {
            throw new ResourceNotFoundException("Задача с id = " + id + " не найдена");
        }
        return task;
    }

    public Task updateTaskStatus(Long id, TaskStatus status) {
        Task task = taskMapper.findById(id);
        if (task == null) {
            throw new ResourceNotFoundException("Задача с id = " + id + " не найдена");
        }
        task.setStatus(status);
        taskMapper.updateStatus(id, status);
        return task;
    }
}