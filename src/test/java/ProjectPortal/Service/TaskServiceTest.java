package ProjectPortal.Service;

import ProjectPortal.Model.Task;
import ProjectPortal.Model.Priority;
import ProjectPortal.Repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task sampleTask;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sampleTask = new Task();
        sampleTask.setTaskId(1);
        sampleTask.setTaskName("Test Task");
        sampleTask.setStartDate(LocalDate.now());
        sampleTask.setEndDate(LocalDate.now().plusDays(7));
        sampleTask.setEstimatedCost(1000.0);
        sampleTask.setAssignedEmployees(2);
        sampleTask.setIsComplete(false);
        sampleTask.setTaskDescription("Test Description");
        sampleTask.setHoursAllocated(40);
        sampleTask.setPriority("HIGH");
    }

    @Test
    void createTask_ShouldCallRepositoryCreate() {

        doNothing().when(taskRepository).createTask(any(Task.class));
        Task result = taskService.createTask(sampleTask);
        verify(taskRepository, times(1)).createTask(sampleTask);
        assertEquals(sampleTask, result);
    }

    @Test
    void getTaskById_ShouldReturnTask_WhenTaskExists() {

        when(taskRepository.getTaskById(1)).thenReturn(sampleTask);
        Optional<Task> result = taskService.getTaskById("1");
        assertTrue(result.isPresent());
        assertEquals("Test Task", result.get().getTaskName());
    }

    @Test
    void getTaskById_ShouldReturnEmpty_WhenTaskDoesNotExist() {
        when(taskRepository.getTaskById(999)).thenReturn(null);
        Optional<Task> result = taskService.getTaskById("999");
        assertFalse(result.isPresent());
    }

    @Test
    void readTasksBySubprojectId_ShouldReturnListOfTasks() {
        List<Task> expectedTasks = Arrays.asList(sampleTask);
        when(taskRepository.readTasksBySubprojectId(anyInt())).thenReturn(expectedTasks);
        List<Task> result = taskService.readTasksBySubprojectId(1);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Task", result.get(0).getTaskName());
    }

    @Test
    void markComplete_ShouldCallRepository() {
        doNothing().when(taskRepository).markComplete(anyInt());
        taskService.markComplete(1);
        verify(taskRepository, times(1)).markComplete(1);
    }

    @Test
    void updateTask_ShouldCallRepository_WhenTaskExists() {
        when(taskRepository.getTaskById(1)).thenReturn(sampleTask);
        doNothing().when(taskRepository).updateTask(
                anyString(), anyInt(), anyInt(), anyInt(),
                any(LocalDate.class), any(LocalDate.class),
                anyBoolean(), anyString()
        );

        taskService.updateTask(1, sampleTask);

        verify(taskRepository, times(1)).updateTask(
                eq(sampleTask.getTaskName()), eq(1),
                eq(sampleTask.getAssignedEmployees()),
                eq((int)sampleTask.getEstimatedCost()),
                eq(sampleTask.getStartDate()),
                eq(sampleTask.getEndDate()),
                eq(sampleTask.getIsComplete()),
                eq(sampleTask.getTaskDescription())
        );
    }

    @Test
    void updateTask_ShouldThrowException_WhenTaskDoesNotExist() {
        when(taskRepository.getTaskById(999)).thenReturn(null);
        assertThrows(IllegalArgumentException.class, () -> {
            taskService.updateTask(999, sampleTask);
        });
    }

    @Test
    void deleteTask_ShouldCallRepository() {

        doNothing().when(taskRepository).deleteTaskById(anyInt());
        taskService.deleteTask(1);
        verify(taskRepository, times(1)).deleteTaskById(1);
    }
}