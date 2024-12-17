import ProjectPortal.Model.Priority;
import ProjectPortal.Model.Project;
import ProjectPortal.Model.Subproject;
import ProjectPortal.Model.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DynamicValuesTest {

    DynamicValues dynamicValues = new DynamicValues();

    Task task1 = new Task(1, 2, 1, "testingTask", LocalDate.of(2024,10,5), LocalDate.of(2024,10,15), 25000.0, 10,false, "a task for testing functionality",500, Priority.valueOf("HIGH"));
    Task task2 = new Task(1, 2, 2, "testingTask", LocalDate.of(2024,10,5), LocalDate.of(2024,10,15), 25000.0, 10, false, "a task for testing functionality",500, Priority.valueOf("HIGH"));
    Project project = new Project(1, "Testing Project", 1, 1, LocalDate.of(2024,1,1), LocalDate.of(2025,1,1), 1000000.0, 0,false, "A test project");
    Subproject subproject1 = new Subproject(1,"subproject 1", 1, LocalDate.of(2024,2,1),LocalDate.of(2024,3,1), 0, 30000, 15 , false, "description of subproject", 20, Priority.valueOf("HIGH"));
    Subproject subproject2 = new Subproject(1,"subproject 2", 2, LocalDate.of(2024,3,1),LocalDate.of(2024,4,1), 0, 30000, 30, false, "description of subproject 2", 30, Priority.valueOf("HIGH"));

    List<Subproject> subprojectList = Arrays.asList(subproject1,subproject2);
    List<Task> taskList = Arrays.asList(task1, task2);

    @Test
    void totalTaskHours() {
        assertEquals(800,dynamicValues.totalActualTaskHours(task1));
    }

    @Test
    void sufficientHours(){
        assertEquals(false, dynamicValues.sufficientHours(task1));
    }

    @Test
    void calculateTotalAvailableEmployees(){ assertEquals(5,dynamicValues.calculateTotalAvailableEmployees(subprojectList, project)); }

    @Test
    void calculateTotalActualCost(){
        assertEquals(60000.0, dynamicValues.calculateTotalActualCost(subprojectList));
    }

    @Test
    void calculateTotalAvailableEmployeesSubproject(){
        assertEquals(0,dynamicValues.calculateTotalAvailableEmployeesSubproject(taskList, subproject1));
    }
    @Test
    void totalActualSubprojectHours(){
        assertEquals(1600, dynamicValues.totalActualSubprojectHours(taskList));
    }
}