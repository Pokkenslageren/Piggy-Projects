package ProjectPlanner.Model;

public class Subproject {
    private int projectID;
    private String subprojectName;
    private String subprojectId;
    private String startDate;
    private String totalEstimatedCost;
    private String totalActualCost;
    private String totalAvailiableEmployees;
    private String totalAssignedEmployees;
    private String endDate;
    private boolean isComplete;
    private String subprojectDescription;

    public Subproject(int projectID, String subprojectName, String subprojectId, String startDate,String totalEstimatedCost, String totalActualCost, String totalAvailiableEmployees, String totalAssignedEmployees, String endDate, boolean isComplete) {
        this.projectID = projectID;
        this.subprojectName = subprojectName;
        this.subprojectId = subprojectId;
        this.startDate = startDate;
        this.totalEstimatedCost = totalEstimatedCost;
        this.totalActualCost = totalActualCost;
        this.totalAvailiableEmployees = totalAvailiableEmployees;
        this.totalAssignedEmployees = totalAssignedEmployees;
        this.endDate = endDate;
        this.isComplete = isComplete;
        this.subprojectDescription = "";
    }
    public int getProjectID() {
        return projectID;
    }
    public String getSubprojectName() {
        return subprojectName;
    }
    public String getSubprojectId() {
        return subprojectId;
    }
    public String getStartDate() {
        return startDate;
    }
    public String getTotalEstimatedCost() {
        return totalEstimatedCost;
    }
    public String getTotalActualCost() {
        return totalActualCost;
    }
    public String getTotalAvailiableEmployees() {
        return totalAvailiableEmployees;
    }
    public String getTotalAssignedEmployees() {
        return totalAssignedEmployees;
    }
    public String getEndDate() {
        return endDate;
    }
    public boolean isComplete() {
        return isComplete;
    }
    public String getSubprojectDescription() {
        return subprojectDescription;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }
    public void setSubprojectName(String subprojectName) {
        this.subprojectName = subprojectName;
    }
    public void setSubprojectId(String subprojectId) {
        this.subprojectId = subprojectId;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public void setTotalEstimatedCost(String totalEstimatedCost) {
        this.totalEstimatedCost = totalEstimatedCost;
    }
    public void setTotalActualCost(String totalActualCost) {
        this.totalActualCost = totalActualCost;
    }
    public void setTotalAvailiableEmployees(String totalAvailiableEmployees) {
        this.totalAvailiableEmployees = totalAvailiableEmployees;
    }
    public void setTotalAssignedEmployees(String totalAssignedEmployees) {
        this.totalAssignedEmployees = totalAssignedEmployees;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }
    public void setSubprojectDescription(String subprojectDescription) {
        this.subprojectDescription = subprojectDescription;
    }
}
