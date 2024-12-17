package ProjectPortal.Model;

import java.time.LocalDate;
import ProjectPortal.Model.Priority;

public class Task {
    private int projectId;
    private int subprojectId;
    private int taskId;
    private String taskName;
    private LocalDate startDate;
    private LocalDate endDate;
    private double estimatedCost;
    private int assignedEmployees;
    private boolean isComplete;
    private String taskDescription;
    private int hoursAllocated;
    private Priority priority;

    /**
     * Default constructor for the Task class.
     * Initializes a new instance of Task with no parameters.
     * Useful for setting up a Task object before assigning specific properties.
     */
    public Task() {}

    /**
     * Initializes a Task object with the specified details.
     *
     * @param projectId the unique identifier for the project
     * @param subprojectId the unique identifier for the subproject
     * @param taskId the unique identifier for the task
     * @param taskName the name of the task
     * @param startDate the start date of the task
     * @param endDate the end date of the task
     * @param estimatedCost the estimated cost associated with the task
     * @param assignedEmployees the number of employees assigned to the task
     * @param isComplete the status of whether the task is completed
     * @param taskDescription the detailed description of the task
     * @param hoursAllocated the number of hours allocated for the task
     * @param priority the priority level of the task
     */
    public Task(int projectId, int subprojectId, int taskId, String taskName,
                LocalDate startDate, LocalDate endDate, double estimatedCost,
                int assignedEmployees, boolean isComplete, String taskDescription,
                int hoursAllocated, Priority priority) {
        this.projectId = projectId;
        this.subprojectId = subprojectId;
        this.taskId = taskId;
        this.taskName = taskName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.estimatedCost = estimatedCost;
        this.assignedEmployees = assignedEmployees;
        this.isComplete = isComplete;
        this.taskDescription = taskDescription;
        this.hoursAllocated = hoursAllocated;
        this.priority = priority;
    }


    /**
     * Retrieves the unique identifier of the project associated with this task.
     *
     * @return the project ID as an integer
     */
    public int getProjectId() {
        return projectId;
    }
    /**
     * Retrieves the ID of the subproject associated with the task.
     *
     * @return the subproject ID as an integer
     */
    public int getSubprojectId() {
        return subprojectId;
    }
    /**
     * Retrieves the unique identifier of the task.
     *
     * @return the task ID as an integer.
     */
    public int getTaskId() {
        return taskId;
    }
    /**
     * Retrieves the name of the task.
     *
     * @return the name of the task as a String
     */
    public String getTaskName() {
        return taskName;
    }
    /**
     * Retrieves the number of employees assigned to the task.
     *
     * @return the number of assigned employees as an integer
     */
    public int getAssignedEmployees() {
        return assignedEmployees;
    }
    /**
     * Retrieves the estimated cost associated with the task.
     *
     * @return the estimated cost as a double
     */
    public double getEstimatedCost() {
        return estimatedCost;
    }
    /**
     * Retrieves the start date of the task.
     *
     * @return the start date as a LocalDate
     */
    public LocalDate getStartDate() {
        return startDate;
    }
    /**
     * Retrieves the end date of the task.
     *
     * @return the end date of the task as a LocalDate
     */
    public LocalDate getEndDate() {
        return endDate;
    }
    /**
     * Retrieves the completion status of the task.
     *
     * @return true if the task is complete, false otherwise
     */
    public boolean getIsComplete() {
        return isComplete;
    }
    /**
     * Retrieves the description of the task.
     *
     * @return the task description as a String
     */
    public String getTaskDescription() {
        return taskDescription;
    }
    /**
     * Retrieves the priority level of the task.
     *
     * @return the priority of the task as a {@link Priority} enumeration value
     */
    public Priority getPriority(){
        return priority;
    }
    /**
     * Retrieves the number of hours allocated to this task.
     *
     * @return the hours allocated to the task as an integer
     */
    public int getHoursAllocated(){
        return hoursAllocated;
    }

    /**
     * Sets the project ID for the task.
     *
     * @param projectId the unique identifier of the project to which the task belongs
     */
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    /**
     * Sets the identifier for the subproject associated with this task.
     *
     * @param subprojectId the unique identifier of the subproject to set
     */
    public void setSubprojectId(int subprojectId) {
        this.subprojectId = subprojectId;
    }
    /**
     * Sets the unique identifier for the task.
     *
     * @param taskId the task ID to be assigned
     */
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
    /**
     * Sets the name of the task.
     *
     * @param taskName the name of the task to set
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    /**
     * Sets the number of employees assigned to the task.
     *
     * @param assignedEmployees the number of employees to assign to the task
     */
    public void setAssignedEmployees(int assignedEmployees) {
        this.assignedEmployees = assignedEmployees;
    }
    /**
     * Sets the estimated cost for the task.
     *
     * @param estimatedCost the estimated cost to set for this task
     */
    public void setEstimatedCost(double estimatedCost) {
        this.estimatedCost = estimatedCost;
    }
    /**
     * Sets the start date for the task.
     *
     * @param startDate the start date of the task to set
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    /**
     * Sets the end date of the task.
     *
     * @param endDate the end date to be set for the task
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    /**
     * Sets the completion status of the task.
     *
     * @param isComplete the completion status to set; true if the task
     *                   is complete, false otherwise
     */
    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }
    /**
     * Updates the task description for a task.
     *
     * @param taskDescription the new description of the task
     */
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
    /**
     * Sets the priority level for the task.
     * Converts the provided string to a corresponding value of the Priority enum.
     *
     * @param priority the priority level to set for the task, which must match
     *                 one of the defined values in the Priority enum (e.g., "LOW", "MEDIUM", "HIGH").
     * @throws IllegalArgumentException if the specified string does not match any of the
     *                                  defined values in the Priority enum.
     */
    public void setPriority(String priority){
        this.priority = Priority.valueOf(priority);
    }
    /**
     * Updates the hours allocated for this task.
     *
     * @param hoursAllocated the number of hours to be allocated to the task
     */
    public void setHoursAllocated(int hoursAllocated){
        this.hoursAllocated = hoursAllocated;
    }

}

