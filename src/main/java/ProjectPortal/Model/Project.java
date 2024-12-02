package ProjectPortal.Model;

import java.util.*;

public class Project {
    private int companyId;
    private String projectName;
    private int projectId;
    private int startDate;
    private int endDate;
    private double totalEstimatedCost;
    private double totalActualCost;
    private int totalAvailableEmployees;
    private int totalAssignedEmployees;
    private boolean isComplete;
    private String projectDescription;

    List<Integer> taskEmployees;
    List<Integer> subprojectEmployees;
    List<Double> totalCostPerTask;
    List<Double> totalCostPerSubproject;


    public Project(int companyId,String projectName, int projectId, int startDate, int endDate, double totalEstimatedCost, double actualCost, int availableEmployees, int assignedEmployees, boolean isComplete, String projectDescription) {
        this.companyId = companyId;
        this.projectName = projectName;
        this.projectId = projectId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalEstimatedCost = totalEstimatedCost;
        this.totalActualCost = actualCost;
        this.totalAvailableEmployees = availableEmployees;
        this.totalAssignedEmployees = assignedEmployees;
        this.isComplete = false;
        this.projectDescription = projectDescription;
        this.taskEmployees = new ArrayList<>();
        this.subprojectEmployees = new ArrayList<>();
        this.totalCostPerTask = new ArrayList<>();
        this.totalCostPerSubproject = new ArrayList<>();
    }

    public Project(){}
    public int getCompanyId() {
        return companyId;
    }
    public String getProjectName() {
        return projectName;
    }
    public int getProjectId() {
        return projectId;
    }
    public int getStartDate() {
        return startDate;
    }
    public int getEndDate() {
        return endDate;
    }
    public double getTotalEstimatedCost() {
        return totalEstimatedCost;
    }
    public double getActualCost() {
        return totalActualCost;
    }
    public int getAvailableEmployees() {
        return totalAvailableEmployees;
    }
    public int getAssignedEmployees() {
        return totalAssignedEmployees;
    }
    public boolean isComplete() {
        return isComplete;
    }
    public String getProjectDescription() {
        return projectDescription;
    }
    public List<Integer> getTaskEmployees(){return taskEmployees;}
    public List<Integer> getSubprojectEmployees(){return subprojectEmployees;}
    public List<Double> getTotalCostPerTask(){return totalCostPerTask;}
    public List<Double> getTotalCostPerSubproject(){return totalCostPerSubproject;}
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }
    public void setTotalEstimatedCost(double totalEstimatedCost) {
        this.totalEstimatedCost = totalEstimatedCost;
    }
    public void setActualCost(double actualCost) {
        this.totalActualCost = actualCost;
    }
    public void setAvailableEmployees(int availableEmployees) {
        this.totalAvailableEmployees = availableEmployees;
    }
    public void setAssignedEmployees(int assignedEmployees) {
        this.totalAssignedEmployees = assignedEmployees;
    }
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }
    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public void setTaskEmployees(List<Integer> taskEmployees){
        this.taskEmployees = taskEmployees;
    }
    public void setSubprojectEmployees(List<Integer> subprojectEmployees){
        this.subprojectEmployees = subprojectEmployees;
    }
    public void setTotalCostPerTask(List<Double> totalCostPerTask){
        this.totalCostPerTask = totalCostPerTask;
    }

    public void setTotalCostPerSubproject(List<Double> totalCostPerSubproject){
        this.totalCostPerSubproject = totalCostPerSubproject;
    }
}
