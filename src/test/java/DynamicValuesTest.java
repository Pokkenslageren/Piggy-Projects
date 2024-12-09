import ProjectPortal.Model.Priority;
import ProjectPortal.Model.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DynamicValuesTest {

    DynamicValues dynamicValues = new DynamicValues();

    Task task = new Task(1, 2, 1, "testingTask",10, 25000.0, LocalDate.of(12,10,5), LocalDate.of(12,10,15), false, "a task for testing functionality", Priority.valueOf("HIGH"),500);



    @Test
    void totalTaskHours() {

        assertEquals(800,dynamicValues.totalTaskHours(task));
    }

    @Test
    void sufficientHours(){
        assertEquals(false, dynamicValues.sufficientHours(task));
    }


}