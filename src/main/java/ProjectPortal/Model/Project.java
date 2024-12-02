package ProjectPortal.Model;

public class Project {
    private int companyId;
    private String projectName;
    private int projectId;
    private int startDate;
    private int endDate;
    private double totalEstimatedCost;
    private double actualCost;
    private int availableEmployees;
    private int assignedEmployees;
    private boolean isComplete;
    private String projectDescription;

    public Project(int companyId,String projectName, int projectId, int startDate, int endDate, double totalEstimatedCost, double actualCost, int availableEmployees, int assignedEmployees, boolean isComplete, String projectDescription) {
        this.companyId = companyId;
        this.projectName = projectName;
        this.projectId = projectId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalEstimatedCost = totalEstimatedCost;
        this.actualCost = actualCost;
        this.availableEmployees = availableEmployees;
        this.assignedEmployees = assignedEmployees;
        this.isComplete = false;
        this.projectDescription = projectDescription;
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
        return actualCost;
    }
    public int getAvailableEmployees() {
        return availableEmployees;
    }
    public int getAssignedEmployees() {
        return assignedEmployees;
    }
    public boolean isComplete() {
        return isComplete;
    }
    public String getProjectDescription() {
        return projectDescription;
    }
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
        this.actualCost = actualCost;
    }
    public void setAvailableEmployees(int availableEmployees) {
        this.availableEmployees = availableEmployees;
    }
    public void setAssignedEmployees(int assignedEmployees) {
        this.assignedEmployees = assignedEmployees;
    }
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }
    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }
}
