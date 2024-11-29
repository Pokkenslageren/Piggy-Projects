package ProjectPlanner.Model;

public class Subproject {
    private int projectID;
    private String subprojectName;
    private int subprojectId;
    private int startDate;
    private double totalEstimatedCost;
    private double totalActualCost;
    private int totalAvailiableEmployees;
    private int totalAssignedEmployees;
    private int endDate;
    private boolean isComplete;
    private String subprojectDescription;

    public Subproject(int projectID, String subprojectName, int subprojectId, int startDate, int totalEstimatedCost, int totalActualCost, int totalAvailiableEmployees, int totalAssignedEmployees, int endDate, boolean isComplete) {
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
    public int getSubprojectId() {
        return subprojectId;
    }
    public int getStartDate() {
        return startDate;
    }
    public double getTotalEstimatedCost() {
        return totalEstimatedCost;
    }
    public double getTotalActualCost() {
        return totalActualCost;
    }
    public int getTotalAvailiableEmployees() {return totalAvailiableEmployees;}
    public int getTotalAssignedEmployees() {
        return totalAssignedEmployees;
    }
    public int getEndDate() {
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
    public void setSubprojectId(int subprojectId) {
        this.subprojectId = subprojectId;
    }
    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }
    public void setTotalEstimatedCost(double totalEstimatedCost) {
        this.totalEstimatedCost = totalEstimatedCost;
    }
    public void setTotalActualCost(double totalActualCost) {
        this.totalActualCost = totalActualCost;
    }
    public void setTotalAvailiableEmployees(int totalAvailiableEmployees) {
        this.totalAvailiableEmployees = totalAvailiableEmployees;
    }
    public void setTotalAssignedEmployees(int totalAssignedEmployees) {
        this.totalAssignedEmployees = totalAssignedEmployees;
    }
    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }
    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }
    public void setSubprojectDescription(String subprojectDescription) {
        this.subprojectDescription = subprojectDescription;
    }
}
