package ProjectPortal.Service;

import ProjectPortal.Model.Project;
import ProjectPortal.Repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private SubprojectService subprojectService;

    @InjectMocks
    private ProjectService projectService;

    private Project sampleProject;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sampleProject = new Project();
        sampleProject.setProjectId(1);
        sampleProject.setProjectName("Test Project");
        sampleProject.setStartDate(LocalDate.now());
        sampleProject.setEndDate(LocalDate.now().plusMonths(1));
        sampleProject.setTotalEstimatedCost(5000.0);
        sampleProject.setTotalAssignedEmployees(5);
        sampleProject.setComplete(false);
        sampleProject.setProjectDescription("Test Description");
    }

    @Test
    void createProject_ShouldCallRepository() {

        doNothing().when(projectRepository).createProject(any(Project.class));
        projectService.createProject(sampleProject);
        verify(projectRepository, times(1)).createProject(sampleProject);
    }

    @Test
    void readProject_ShouldReturnProject_WhenProjectExists() {

        when(projectRepository.readProject(1)).thenReturn(sampleProject);
        Project result = projectService.readProject(1);
        assertNotNull(result);
        assertEquals("Test Project", result.getProjectName());
    }

    @Test
    void readAllProjects_ShouldReturnListOfProjects() {

        List<Project> expectedProjects = Arrays.asList(sampleProject);
        when(projectRepository.readAllProjects()).thenReturn(expectedProjects);
        List<Project> result = projectService.readAllProjects();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Project", result.get(0).getProjectName());
    }

    @Test
    void markComplete_ShouldCallRepository() {

        doNothing().when(projectRepository).markComplete(anyInt());
        projectService.markComplete(1);
        verify(projectRepository, times(1)).markComplete(1);
    }

    @Test
    void deleteProject_ShouldCallRepository() {

        doNothing().when(projectRepository).deleteProject(anyInt());
        projectService.deleteProject(1);
        verify(projectRepository, times(1)).deleteProject(1);
    }
}