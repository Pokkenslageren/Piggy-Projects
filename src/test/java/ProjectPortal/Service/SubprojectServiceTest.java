package ProjectPortal.Service;

import ProjectPortal.Model.Task;
import ProjectPortal.Repository.SubprojectRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class SubprojectServiceTest {

    @Mock
    private SubprojectRepository subprojectRepository;

    @Mock
    private TaskService taskService;

    @InjectMocks
    private SubprojectService subprojectService;

    @Test
    void testReadAllTasksBySubproject() {
        int subprojectId = 1;
        Task task1 = new Task(1, subprojectId, 101, "Task 1",
                null, null, 100.0, 5, false,
                "Task Description 1", 10, null);
        Task task2 = new Task(1, subprojectId, 102, "Task 2",
                null, null, 200.0, 10, true,
                "Task Description 2", 20, null);

        List<Task> mockTasks = Arrays.asList(task1, task2);
        Mockito.when(subprojectRepository.readAllTasksBySubproject(subprojectId))
                .thenReturn(mockTasks);

        List<Task> tasks = subprojectService.readAllTasksBySubproject(subprojectId);

        assertEquals(2, tasks.size());
        assertEquals(task1, tasks.get(0));
        assertEquals(task2, tasks.get(1));
    }

    @Test
    void testReadAllTasksBySubprojectEmptyList() {
        int subprojectId = 1;

        Mockito.when(subprojectRepository.readAllTasksBySubproject(subprojectId))
                .thenReturn(Collections.emptyList());

        List<Task> tasks = subprojectService.readAllTasksBySubproject(subprojectId);

        assertEquals(0, tasks.size());
    }

    @Test
    void testReadAllTasksBySubproject_ReturnsNullWhenRepositoryReturnsNull() {
        int subprojectId = 1;

        Mockito.when(subprojectRepository.readAllTasksBySubproject(subprojectId))
                .thenReturn(null);

        List<Task> tasks = subprojectService.readAllTasksBySubproject(subprojectId);

        assertEquals(null, tasks);
    }
}