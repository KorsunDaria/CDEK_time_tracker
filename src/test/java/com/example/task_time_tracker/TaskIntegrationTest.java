package com.example.task_time_tracker;

import com.example.task_time_tracker.controller.TaskController;
import com.example.task_time_tracker.dto.TaskRequest;
import com.example.task_time_tracker.model.Task;
import com.example.task_time_tracker.model.TaskStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("docker")
@Testcontainers
class TaskIntegrationTest {

    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:15-alpine")
                    .withDatabaseName("tracker")
                    .withUsername("admin")
                    .withPassword("password");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Autowired
    private TaskController taskController;

    @Test
    void saveRecord_ShouldCreateNewTask() {
        TaskRequest request = new TaskRequest();
        request.setTitle("Интеграционная задача");
        request.setDescription("Описание интеграционной задачи");

        Task savedTask = taskController.saveRecord(request);

        assertNotNull(savedTask.getId(), "Задача должна сохраниться в базу данных и получить ID");
        assertEquals("Интеграционная задача", savedTask.getTitle());
        assertEquals(TaskStatus.NEW, savedTask.getStatus());
    }
}