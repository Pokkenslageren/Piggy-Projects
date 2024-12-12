package ProjectPortal.Model;

import java.time.LocalDate;
import ProjectPortal.Model.Priority;
import java.util.*;

public class Subproject {

    private int projectID;
    private String subprojectName;
    private int subprojectId;
    private LocalDate startDate;
    private double estimatedCost;
    private double totalActualCost;

    private int availiableEmployees;
    private int assignedEmployees;
    private LocalDate endDate;

    private Priority priority;

    private int hoursAllocated;
    private boolean isComplete;
    private String subprojectDescription;

    private List<Task> tasks;

    public Subproject(int projectID, String subprojectName, int subprojectId, LocalDate startDate, LocalDate endDate, int totalEstimatedCost, int totalActualCost, int totalAvailiableEmployees, int totalAssignedEmployees, boolean isComplete, Priority priority, int hoursAllocated) {
        this.projectID = projectID;
        this.subprojectName = subprojectName;
        this.subprojectId = subprojectId;
        this.startDate = startDate;
        this.totalActualCost = totalActualCost;
        this.estimatedCost = totalEstimatedCost;
        this.availiableEmployees = totalAvailiableEmployees;
        this.assignedEmployees = totalAssignedEmployees;
        this.endDate = endDate;
        this.isComplete = isComplete;
        this.subprojectDescription = "";
        this.priority = priority;
        this.hoursAllocated = hoursAllocated;
    }

    public Subproject() {}

    public int getParentProjectID() {
        return projectID;
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
        return estimatedCost;
    }
    public double getTotalActualCost() {
        return totalActualCost;
    }
    public int getTotalAvailiableEmployees() {return availiableEmployees;}
    public int getTotalAssignedEmployees() {
        return assignedEmployees;
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

    public void setParentProjectID(int projectID) {
        this.projectID = projectID;
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
        this.estimatedCost = totalEstimatedCost;
    }
    public void setTotalActualCost(double totalActualCost) {
        this.totalActualCost = totalActualCost;
    }
    public void setTotalAvailiableEmployees(int totalAvailiableEmployees) {
        this.availiableEmployees = totalAvailiableEmployees;
    }
    public void setPriority(String priority){
        this.priority =Priority.valueOf(priority);
    }

    public void setHoursAllocated(int hoursAllocated){
        this.hoursAllocated = hoursAllocated;
    }
    public void setTotalAssignedEmployees(int totalAssignedEmployees) {
        this.assignedEmployees = totalAssignedEmployees;
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
}
