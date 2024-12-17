package ProjectPortal.Model;

import java.util.*;
import java.time.LocalDate;

public class Project {
    private int companyId;
    private String projectName;
    private int userId;
    private int projectId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalEstimatedCost;
    private double totalActualCost;  // Calculated from subprojects
    private int totalAssignedEmployees;
    private boolean isComplete;
    private String projectDescription;

    List<Integer> taskEmployees;
    List<Integer> subprojectEmployees;
    List<Double> totalCostPerTask;
    List<Double> totalCostPerSubproject;

    /**
     * Default constructor for the Project class.
     * Initializes empty lists for taskEmployees, subprojectEmployees, totalCostPerTask, and totalCostPerSubproject.
     */
    public Project() {
        this.taskEmployees = new ArrayList<>();
        this.subprojectEmployees = new ArrayList<>();
        this.totalCostPerTask = new ArrayList<>();
        this.totalCostPerSubproject = new ArrayList<>();
    }

    /**
     * Constructs a Project object with the specified details.
     * @param companyId the unique identifier of the company associated with the project
     * @param projectName the name of the project
     * @param userId the unique identifier of the user managing the project
     * @param projectId the unique identifier of the project
     * @param startDate the start date of the project
     * @param endDate the end date of the project
     * @param totalEstimatedCost the total estimated cost of the project
     * @param totalAssignedEmployees the total number of employees assigned to the project
     * @param isComplete the completion status of the project
     * @param projectDescription a brief description of the project
     */
    public Project(int companyId, String projectName, int userId, int projectId,
                   LocalDate startDate, LocalDate endDate, double totalEstimatedCost,
                   int totalAssignedEmployees, boolean isComplete, String projectDescription) {
        this.companyId = companyId;
        this.projectName = projectName;
        this.userId = userId;
        this.projectId = projectId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalEstimatedCost = totalEstimatedCost;
        this.totalAssignedEmployees = totalAssignedEmployees;
        this.isComplete = isComplete;
        this.projectDescription = projectDescription;
        this.taskEmployees = new ArrayList<>();
        this.subprojectEmployees = new ArrayList<>();
        this.totalCostPerTask = new ArrayList<>();
        this.totalCostPerSubproject = new ArrayList<>();
        this.totalActualCost = 0;
    }

    /**
     * Retrieves the unique identifier of the company associated with the project.
     * @return the company ID as an integer
     */
    public int getCompanyId() {
        return companyId;
    }
    /**
     * Retrieves the name of the project.
     * @return the name of the project as a String
     */
    public String getProjectName() {
        return projectName;
    }
    /**
     * Retrieves the project ID.
     * @return the unique identifier of the project as an integer
     */
    public int getProjectId() {
        return projectId;
    }

    /**
     * Retrieves the unique user identifier associated with the project.
     * @return the user ID as an integer
     */
    public int getUserId(){
        return userId;
    }
    /**
     * Retrieves the start date of the project.
     * @return the start date of the project as a LocalDate
     */
    public LocalDate getStartDate() {
        return startDate;
    }
    /**
     * Retrieves the end date of the project.
     * @return the end date of the project as a LocalDate
     */
    public LocalDate getEndDate() {
        return endDate;
    }
    /**
     * Retrieves the total estimated cost associated with the project.
     * @return the total estimated cost as a double
     */
    public double getTotalEstimatedCost() {
        return totalEstimatedCost;
    }
    /**
     * Retrieves the total actual cost associated with the project.
     * @return the total actual cost as a double
     */
    public double getTotalActualCost() {
        return totalActualCost;
    }
    /**
     * Retrieves the total number of employees assigned to the project.
     * @return the total number of assigned employees as an integer
     */
    public int getTotalAssignedEmployees() {
        return totalAssignedEmployees;
    }
    /**
     * Checks if the project is completed.
     * @return true if the project is complete, false otherwise
     */
    public boolean isComplete() {
        return isComplete;
    }
    /**
     * Retrieves the description of the project.
     * @return the project description as a String
     */
    public String getProjectDescription() {
        return projectDescription;
    }
    /**
     * Retrieves the list of employee IDs assigned to tasks within the project.
     * @return a list of integers representing the employee IDs associated with the tasks.
     */
    public List<Integer> getTaskEmployees(){return taskEmployees;}
    /**
     * Retrieves the list of employee IDs associated with the subprojects.
     * @return a list of integers where each integer represents an employee ID
     *         assigned to subprojects.
     */
    public List<Integer> getSubprojectEmployees(){return subprojectEmployees;}
    /**
     * Retrieves the total costs associated with each task in the project.
     * @return a list of doubles, where each double represents the total cost of a specific task
     */
    public List<Double> getTotalCostPerTask(){return totalCostPerTask;}
    /**
     * Retrieves the list of total costs for each subproject associated with the project.
     * @return a list of total costs for each subproject as a List of Double values
     */
    public List<Double> getTotalCostPerSubproject(){return totalCostPerSubproject;}
    /**
     * Sets the company ID for the project.
     * @param companyId the unique identifier of the company to be associated with the project
     */
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
    /**
     * Sets the name of the project.
     * @param projectName the name of the project to set
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    /**
     * Sets the project ID for this project.
     * @param projectId the unique identifier to be assigned to the project
     */
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    /**
     * Sets the user ID for the Project.
     * @param userId the unique identifier of the user to be associated with the project
     */
    public void setUserId(int userId){
        this.userId = userId;
    }
    /**
     * Sets the start date of the project.
     * @param startDate the start date to be assigned to the project
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    /**
     * Sets the end date of the project.
     * @param endDate the end date to be set for the project
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    /**
     * Sets the total estimated cost for the project.
     * @param totalEstimatedCost the estimated total cost of the project
     */
    public void setTotalEstimatedCost(double totalEstimatedCost) {
        this.totalEstimatedCost = totalEstimatedCost;
    }
    /**
     * Sets the total actual cost for the project.
     * @param actualCost The actual cost incurred for the project.
     */
    public void setTotalActualCost(double actualCost) {
        this.totalActualCost = actualCost;
    }
    /**
     * Sets the total number of employees assigned to the project.
     * @param assignedEmployees the number of employees assigned to the project
     */
    public void setTotalAssignedEmployees(int assignedEmployees) {
        this.totalAssignedEmployees = assignedEmployees;
    }
    /**
     * Sets the description of the project.
     * @param projectDescription the new description of the project
     */
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }
    /**
     * Sets the completion status of the project.
     * @param isComplete a boolean value indicating whether the project is complete (true) or not (false)
     */
    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    /**
     * Sets the list of employee IDs assigned to a task.
     * @param taskEmployees the list of employee IDs to assign to the task
     */
    public void setTaskEmployees(List<Integer> taskEmployees){
        this.taskEmployees = taskEmployees;
    }
    /**
     * Sets the list of employees assigned to subprojects within the project.
     * @param subprojectEmployees a list of integers representing the IDs of employees assigned to the subprojects
     */
    public void setSubprojectEmployees(List<Integer> subprojectEmployees){
        this.subprojectEmployees = subprojectEmployees;
    }
    /**
     * Sets the total cost associated with each task in the project.
     * @param totalCostPerTask a list of doubles representing the total costs for each task
     */
    public void setTotalCostPerTask(List<Double> totalCostPerTask){
        this.totalCostPerTask = totalCostPerTask;
    }

    /**
     * Sets the total cost associated with each subproject.
     * @param totalCostPerSubproject a list of doubles representing the total costs for each subproject
     */
    public void setTotalCostPerSubproject(List<Double> totalCostPerSubproject){
        this.totalCostPerSubproject = totalCostPerSubproject;
    }
}
