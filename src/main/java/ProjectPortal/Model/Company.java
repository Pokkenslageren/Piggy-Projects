package ProjectPortal.Model;

public class Company {

    private String companyName;
    private int companyId;

    /**
     * Constructor to create a new Company object with the specified name and ID.
     * @param companyName the name of the company
     * @param companyId the unique identifier of the company
     */
    public Company(String companyName, int companyId) {
        this.companyName = companyName;
        this.companyId = companyId;
    }

    /**
     * Default constructor for the Company class.
     * Initializes a new instance of the Company without setting any properties.
     */
    public Company() {}

    /**
     * Retrieves the name of the company.
     * @return the name of the company as a String
     */
    public String getCompanyName() {

        return companyName;
    }
    /**
     * Retrieves the unique identifier of the company.
     * @return the company ID as an integer
     */
    public int getCompanyId() {

        return companyId;
    }
    /**
     * Sets the name of the company.
     * @param companyName the name of the company to set
     */
    public void setCompanyName(String companyName) {

        this.companyName = companyName;
    }
    /**
     * Sets the company ID.
     * @param companyId the ID of the company to be assigned
     */
    public void setCompanyId(int companyId) {

        this.companyId = companyId;
    }
}