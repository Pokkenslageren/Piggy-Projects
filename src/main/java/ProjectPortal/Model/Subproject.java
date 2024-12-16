package ProjectPortal.Model;

import java.time.LocalDate;
import java.util.*;

public class Subproject {
    private int projectId;
    private String subprojectName;
    private int subprojectId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalEstimatedCost;
    private double totalActualCost;  // Added to match DB
    private int totalAssignedEmployees;
    private boolean isComplete;
    private String subprojectDescription;
    private int hoursAllocated;
    private Priority priority;
    private List<Task> tasks;
    private int totalActualHours;

    public Subproject() {}

    public Subproject(int projectId, String subprojectName, int subprojectId,
                      LocalDate startDate, LocalDate endDate, double totalEstimatedCost,
                      double totalActualCost, int totalAssignedEmployees, boolean isComplete,
                      String subprojectDescription, int hoursAllocated, Priority priority) {
        this.projectId = projectId;
        this.subprojectName = subprojectName;
        this.subprojectId = subprojectId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalEstimatedCost = totalEstimatedCost;
        this.totalActualCost = totalActualCost;
        this.totalAssignedEmployees = totalAssignedEmployees;
        this.isComplete = isComplete;
        this.subprojectDescription = subprojectDescription;
        this.hoursAllocated = hoursAllocated;
        this.priority = priority;
    }
    public int getProjectId() {
        return projectId;
    }
    public String getSubprojectName() {
        return subprojectName;
    }
    public int getSubprojectId() {
        return subprojectId;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public double getTotalEstimatedCost() {
        return totalEstimatedCost;
    }
    public double getTotalActualCost() {
        return totalActualCost;
    }
    public int getTotalAssignedEmployees() {
        return totalAssignedEmployees;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public boolean isComplete() {
        return isComplete;
    }
    public String getSubprojectDescription() {
        return subprojectDescription;
    }
    public Priority getPriority(){
        return priority;
    }
    public int getHoursAllocated(){
        return hoursAllocated;
    }
    public List<Task> getTasks() { return tasks; }
    public int getTotalActualHours() {
        return totalActualHours;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    public void setSubprojectName(String subprojectName) {
        this.subprojectName = subprojectName;
    }
    public void setSubprojectId(int subprojectId) {
        this.subprojectId = subprojectId;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public void setTotalEstimatedCost(double totalEstimatedCost) {
        this.totalEstimatedCost = totalEstimatedCost;
    }
    public void setTotalActualCost(double totalActualCost) {
        this.totalActualCost = totalActualCost;
    }

    public void setPriority(String priority){
        this.priority =Priority.valueOf(priority);
    }

    public void setHoursAllocated(int hoursAllocated){
        this.hoursAllocated = hoursAllocated;
    }
    public void setTotalAssignedEmployees(int totalAssignedEmployees) {
        this.totalAssignedEmployees = totalAssignedEmployees;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }
    public void setSubprojectDescription(String subprojectDescription) {
        this.subprojectDescription = subprojectDescription;
    }
    public void setTasks(List<Task> tasks) { this.tasks = tasks; }
    public void setTotalActualHours(int totalActualHours) {
        this.totalActualHours = totalActualHours;
    }

    public double getProgressPercentage() {
        if (hoursAllocated == 0) return 0;
        return (double) totalActualHours / hoursAllocated * 100;
    }
}
