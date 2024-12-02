package ProjectPortal.Model;

public class Project {
    private int companyId;
    private String projectName;
    private String projectId;
    private String startDate;
    private String endDate;
    private double totalEstimatedCost;
    private double actualCost;
    private String availableEmployees;
    private String assignedEmployees;
    private boolean isComplete;
    private String projectDescription = "";

    public Project(int companyId,String projectName,String projectId, String startDate, String endDate, double totalEstimatedCost, double actualCost, String availableEmployees, String assignedEmployees, boolean isComplete) {
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
    public String getProjectId() {
        return projectId;
    }
    public String getStartDate() {
        return startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public double getTotalEstimatedCost() {
        return totalEstimatedCost;
    }
    public double getActualCost() {
        return actualCost;
    }
    public String getAvailableEmployees() {
        return availableEmployees;
    }
    public String getAssignedEmployees() {
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
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public void setTotalEstimatedCost(double totalEstimatedCost) {
        this.totalEstimatedCost = totalEstimatedCost;
    }
    public void setActualCost(double actualCost) {
        this.actualCost = actualCost;
    }
    public void setAvailableEmployees(String availableEmployees) {
        this.availableEmployees = availableEmployees;
    }
    public void setAssignedEmployees(String assignedEmployees) {
        this.assignedEmployees = assignedEmployees;
    }
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }
    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }
    public void setProjectDescription() {
        this.projectDescription = "";
    }

}
