package com.example.task_time_tracker.service;

import com.example.task_time_tracker.exception.ResourceNotFoundException;
import com.example.task_time_tracker.mapper.TaskMapper;
import com.example.task_time_tracker.model.Task;
import com.example.task_time_tracker.model.TaskStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskService taskService;

    @Test
    void createTask_ShouldSetStatusNewAndReturnTask() {

        Task task = new Task(null, "Test Task", "Desc", null);

        Task createdTask = taskService.createTask(task);

        assertEquals(TaskStatus.NEW, createdTask.getStatus());
        verify(taskMapper, times(1)).save(task);
    }

    @Test
    void getTaskById_ShouldReturnTask_WhenTaskExists() {

        Task mockTask = new Task(1L, "Task", "Desc", TaskStatus.NEW);
        when(taskMapper.findById(1L)).thenReturn(mockTask);

        Task result = taskService.getTaskById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void getTaskById_ShouldThrowException_WhenTaskNotFound() {
        when(taskMapper.findById(1L)).thenReturn(null);
        assertThrows(ResourceNotFoundException.class, () -> taskService.getTaskById(1L));
    }
}