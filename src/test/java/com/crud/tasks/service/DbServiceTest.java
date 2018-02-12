package com.crud.tasks.service;


import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void testGetAllTasks() {
        //Given
        Task task1 = new Task(1l, "title", "desc");
        Task task2 = new Task(2l, "title", "desc");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);

        when(dbService.getAllTasks()).thenReturn(tasks);
        //When
        List<Task> taskList = dbService.getAllTasks();
        //Then
        assertEquals(2, taskList.size());
    }

    @Test
    public void testGetTaskById() {
        //Given
        Task task1 = new Task(1l, "title", "desc");
        Task task2 = new Task(2l, "title", "desc");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);

        when(dbService.getTask(1l)).thenReturn(Optional.ofNullable(task1));
        when(dbService.getTask(2l)).thenReturn(Optional.ofNullable(task2));
        //When
        Optional<Task> TaskById = dbService.getTask(1l);
        //Then
        assertEquals(task1.getId(), TaskById.get().getId());
    }

    @Test
    public void testSaveTask() {
        //Given
        Task task1 = new Task(1l, "test", "test1");
        when(dbService.saveTask(task1)).thenReturn(task1);
        //When
        Task testTask = dbService.saveTask(task1);
        //Then
        assertEquals(task1.getId(), testTask.getId());
        assertEquals(task1.getTitle(), testTask.getTitle());
        assertEquals(task1.getContent(), testTask.getContent());
    }
}