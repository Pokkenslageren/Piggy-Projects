package ProjectPlanner.Model;

public class Task {
    private String projectId;
    private String subprojectId;
    private String taskId;
    private String taskName;
    private String assignedEmployees;
    private double estimatedCost;
    private String startDate;
    private String endDate;
    private boolean isComplete;
    private String taskDescription;

    public Task(String projectId, String subprojectId, String taskId, String taskName, String assignedEmployees, double estimatedCost, String startDate, String endDate, boolean isComplete, String taskDescription) {
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
    }
    public String getProjectId() {
        return projectId;
    }
    public String subprojectId() {
        return subprojectId;
    }
    public String taskId() {
        return taskId;
    }
    public String taskName() {
        return taskName;
    }
    public String assignedEmployees() {
        return assignedEmployees;
    }
    public double estimatedCost() {
        return estimatedCost;
    }
    public String startDate() {
        return startDate;
    }
    public String endDate() {
        return endDate;
    }
    public boolean isComplete() {
        return isComplete;
    }
    public String taskDescription() {
        return taskDescription;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    public void setSubprojectId(String subprojectId) {
        this.subprojectId = subprojectId;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public void setAssignedEmployees(String assignedEmployees) {
        this.assignedEmployees = assignedEmployees;
    }
    public void setEstimatedCost(double estimatedCost) {
        this.estimatedCost = estimatedCost;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
}
