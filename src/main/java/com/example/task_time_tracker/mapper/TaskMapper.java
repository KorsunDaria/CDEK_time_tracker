package com.example.task_time_tracker.mapper;

import com.example.task_time_tracker.model.Task;
import com.example.task_time_tracker.model.TaskStatus;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TaskMapper {
    @Insert("INSERT INTO tasks (title, description, status) VALUES (#{title}, #{description}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Task task);

    @Select("SELECT id, title, description, status FROM tasks WHERE id = #{id}")
    Task findById(Long id);

    @Update("UPDATE tasks SET status = #{status} WHERE id = #{id}")
    void updateStatus(@Param("id") Long id, @Param("status") TaskStatus status);

}