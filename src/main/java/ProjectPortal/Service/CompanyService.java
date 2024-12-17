package ProjectPortal.Service;

import ProjectPortal.Model.Company;
import ProjectPortal.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    /**
     * Retrieves a list of all companies from the repository.
     * @param companyId an integer representing the company's unique identifier, not used in the method logic
     * @param companyName a string representing the company's name, not used in the method logic
     * @return a list of Company objects representing all companies in the repository
     */
    public List<Company> readAllCompanies(int companyId, String companyName) {
        return companyRepository.readAllCompanies();
    }

    /**
     * Creates a new company with the specified name and ID.
     * @param companyName The name of the company to be created.
     * @param companyId The unique identifier for the company.
     */
    public void createCompany(String companyName, int companyId) {
        companyRepository.createCompany(companyName, companyId);
    }

    /**
     * Retrieves a company by its unique identifier.
     * @param CompanyId the unique identifier of the company to retrieve
     * @return the Company object corresponding to the specified ID, or null if no such company exists
     */
    public Company readCompanyById(int CompanyId) {
        return companyRepository.readCompanyById(CompanyId);
    }

    /**
     * Updates the company details in the repository.
     * @param companyName the new name of the company
     * @param companyId the unique identifier of the company to be updated
     */
    public void updateCompany(String companyName, int companyId) {
        companyRepository.updateCompany(companyName, companyId);
    }

    /**
     * Deletes a company from the repository based on the provided company ID.
     * @param companyId the ID of the company to be deleted
     */
    public void deleteCompanyById(int companyId) {
        companyRepository.deleteCompanyById(companyId);
    }
}