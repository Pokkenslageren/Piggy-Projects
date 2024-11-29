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

    public List<Company> readAllCompanies(int companyId, String companyName) {
        return companyRepository.readAllCompanies();
    }

    public void createCompany(String companyName, int companyId) {
        companyRepository.createCompany(companyName, companyId);
    }

    public Company readCompanyById(int CompanyId) {
        return companyRepository.readCompanyById(CompanyId);
    }

    public void updateCompany(String companyName, int companyId) {
        companyRepository.updateCompany(companyName, companyId);
    }
    public void deleteCompanyById(int companyId) {
        companyRepository.deleteCompanyById(companyId);
    }

}

