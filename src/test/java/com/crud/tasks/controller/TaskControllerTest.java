package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskMapper taskMapper;

    private TaskMapper taskMapperNotAMock = new TaskMapper();

    @MockBean
    private DbService service;

    @Test
    public void shouldGetTasks() throws Exception {
        // Given
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(new TaskDto(1l, "test title", "test content"));

        when(taskMapper.mapTaskDtoList(anyList())).thenReturn(taskDtoList);

        // When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("test title")))
                .andExpect(jsonPath("$[0].content", is("test content")));
    }

    @Test
    public void shouldGetTask() throws Exception {
        // Given
        Task task = new Task(1l, "test title", "test content");
        TaskDto taskDto = taskMapperNotAMock.mapToTaskDto(task);

        when(service.getTask(anyLong())).thenReturn(Optional.ofNullable(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        // When & Then
        mockMvc.perform(get("/v1/task/getTask").param("taskId","1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test title")))
                .andExpect(jsonPath("$.content", is("test content")));
    }

    @Test
    public void shouldGetOneTask ()throws Exception {
        // Given
        Task task = new Task(1l, "test title", "test content");
        TaskDto taskDto = taskMapperNotAMock.mapToTaskDto(task);

        when(service.getTask(anyLong())).thenReturn(Optional.ofNullable(task));
        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);

        // When & Then
        mockMvc.perform(get("/v1/task/getTask").param("taskId", "1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test title")))
                .andExpect(jsonPath("$.content", is("test content")));
    }

    @Test
    public void shouldUpdateTask()throws Exception{
        // Given
        TaskDto taskDto = new TaskDto(1l, "test title", "test content");
        TaskDto updatedTask = new TaskDto(1l, "updated title", "updated content");

        when(taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskDto)))).thenReturn(updatedTask);

        Gson gson = new Gson();
        String cont = gson.toJson(taskDto);

        // When &  Then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(cont))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("updated title")))
                .andExpect(jsonPath("$.content", is("updated content")));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        Task task = new Task(1l, "test title", "test content");
        TaskDto taskDto =  taskMapperNotAMock.mapToTaskDto(task);

        when(service.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When and Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}