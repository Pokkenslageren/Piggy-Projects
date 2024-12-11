import ProjectPortal.Model.Priority;
import ProjectPortal.Model.Project;
import ProjectPortal.Model.Subproject;
import ProjectPortal.Model.Task;

import java.time.*;
import java.util.List;



public class DynamicValues {

    public int calculateTotalAvailableEmployees(List<Subproject> listOfSubprojects, Project project){
        System.out.println(listOfSubprojects.size());
        var iterator = listOfSubprojects.iterator();
        int totalProjectEmployees = project.getAssignedEmployees();
        int totalEmployeesInUse = 0;
        while(iterator.hasNext()){
            totalEmployeesInUse = totalEmployeesInUse + iterator.next().getTotalAssignedEmployees();
        }
        return totalProjectEmployees - totalEmployeesInUse;
    }

    public double calculateTotalActualCost(List<Subproject> listOfSubprojects){
        var iterator = listOfSubprojects.iterator();
        double totalActualCost = 0.0;
        while(iterator.hasNext()){
            totalActualCost += iterator.next().getTotalActualCost();
        }
        return totalActualCost;
    }

    public int calculateTotalAvailableEmployeesSubproject(List<Task> listOfTasks, Subproject subproject){
        var iterator = listOfTasks.iterator();
        int totalSubprojectEmployees = subproject.getTotalAssignedEmployees();
        int totalEmployeesInUse = 0;
        while(iterator.hasNext()){
            totalEmployeesInUse = totalEmployeesInUse + iterator.next().getAssignedEmployees();
        }
        return totalSubprojectEmployees - totalEmployeesInUse;
    }

    public int totalActualTaskHours(Task task){
        int numberOfEmployees = task.getAssignedEmployees();
        return (task.getEndDate().getDayOfYear() - task.getStartDate().getDayOfYear()) * numberOfEmployees * 8;
    }

    public int totalActualSubprojectHours(List<Task> listOfTasks){
        var iterator = listOfTasks.iterator();
        int totalActualSubprojectHours = 0;
        while(iterator.hasNext()){
            totalActualSubprojectHours += totalActualTaskHours(iterator.next());
        }
        return totalActualSubprojectHours;
    }

    public boolean sufficientHours(Task task){
        int totalTaskHours = totalActualTaskHours(task);
        return (task.getHoursAllocated() > totalTaskHours );
    }

    public void setDynamicValuesSubproject(List<Subproject> listOfSubprojects){
        var iterator = listOfSubprojects.iterator();
        while(iterator.hasNext()){
            List<Task> listOfTasks = //readAllTasks
            iterator.next().setTotalAvailiableEmployees();
        }
    }



}
