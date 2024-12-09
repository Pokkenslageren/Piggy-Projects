import ProjectPortal.Model.Priority;
import ProjectPortal.Model.Task;

import java.time.*;

public class DynamicValues {



/*    LocalDate startDate = LocalDate.of(2024, 12,5);
    LocalDate endDate = LocalDate.of(2024, 12, 15);*/


    public int totalTaskHours(Task task){

        int numberOfEmployees = 10;
        return (task.getEndDate().getDayOfYear() - task.getStartDate().getDayOfYear()) * numberOfEmployees * 8;
    }

    public boolean sufficientHours(Task task){
        int totalTaskHours = totalTaskHours(task);
        return (task.getHoursAllocated() > totalTaskHours );
    }


}
