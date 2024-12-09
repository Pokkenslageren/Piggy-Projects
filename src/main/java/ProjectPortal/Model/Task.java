package ProjectPortal.Model;

import java.time.LocalDate;
import ProjectPortal.Model.Priority;

public class Task {
    private int projectId;
    private int subprojectId;
    private int taskId;
    private String taskName;
    private int assignedEmployees;
    private double estimatedCost;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isComplete;
    private String taskDescription;

    private Priority priority;

    int hoursAllocated;
    int totalTaskDays;

    public Task(int projectId, int subprojectId, int taskId, String taskName, int assignedEmployees, double estimatedCost, LocalDate startDate, LocalDate endDate, boolean isComplete, String taskDescription, Priority priority, int hoursAllocated) {
        this.projectId = projectId;
        this.subprojectId = subprojectId;
        this.taskId = taskId;
        this.taskName = taskName;
        this.assignedEmployees = assignedEmployees;
        this.estimatedCost = estimatedCost;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isComplete = isComplete;
        this.taskDescription = taskDescription;
        this.priority = priority;
        this.hoursAllocated = hoursAllocated;
    }

    public Task() {}

    public int getProjectId() {
        return projectId;
    }
    public int getSubprojectId() {
        return subprojectId;
    }
    public int getTaskId() {
        return taskId;
    }
    public String getTaskName() {
        return taskName;
    }
    public int getAssignedEmployees() {
        return assignedEmployees;
    }
    public double getEstimatedCost() {
        return estimatedCost;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public boolean getIsComplete() {
        return isComplete;
    }
    public String getTaskDescription() {
        return taskDescription;
    }
    public Priority getPriority(){
        return priority;
    }
    public int getHoursAllocated(){
        return hoursAllocated;
    }

    public int getTotalTaskDays() {return totalTaskDays;}

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    public void setSubprojectId(int subprojectId) {
        this.subprojectId = subprojectId;
    }
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public void setAssignedEmployees(int assignedEmployees) {
        this.assignedEmployees = assignedEmployees;
    }
    public void setEstimatedCost(double estimatedCost) {
        this.estimatedCost = estimatedCost;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
    public void setPriority(String priority){
        this.priority = Priority.valueOf(priority);
    }
    public void setHoursAllocated(int hoursAllocated){
        this.hoursAllocated = hoursAllocated;
    }

    public void setTotalTaskDays(int totalTaskDays) {this.totalTaskDays = totalTaskDays;}
}

