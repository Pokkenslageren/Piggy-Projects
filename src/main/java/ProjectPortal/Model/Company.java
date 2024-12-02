package ProjectPortal.Model;

public class Company {
    private String companyName;
    private int companyId;
    public Company(String companyName, int companyId) {
        this.companyName = companyName;
        this.companyId = companyId;
    }
    public String getCompanyName() {
        return companyName;
    }
    public int getCompanyId() {
        return companyId;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}