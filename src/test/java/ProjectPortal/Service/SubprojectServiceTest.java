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

    /**
     * Tests the functionality of the {@code readAllTasksBySubproject} method in the service
     * by verifying that it retrieves all tasks associated with a specific subproject.
     * The test uses mocked data to simulate tasks belonging to a given subproject ID.
     * It ensures that:
     * 1. The service method retrieves the correct number of tasks.
     * 2. Each task in the returned list matches the expected task details.
     * The repository is mocked to return a predefined list of tasks, and assertions
     * are performed to confirm that the response matches the expected output.
     */
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
    /**
     * This test mocks the behavior of the repository to return an empty list for the
     * given subproject ID. It verifies that the service method `readAllTasksBySubproject`
     * correctly returns an empty list and that the size of the result is zero.
     * The test ensures proper handling of cases where no tasks are found for the provided subproject ID.
     */
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
