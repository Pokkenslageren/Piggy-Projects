package ProjectPortal.Repository;

import ProjectPortal.Model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CompanyRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Get all companies
     * @return
     */
    public List<Company> readAllCompanies() {
        String query = "SELECT * FROM company";
        RowMapper rowMapper = new BeanPropertyRowMapper(Company.class);
        return jdbcTemplate.query(query, rowMapper);
    }

    /**
     * Get company by id
     * @param CompanyId
     * @return
     */
    public Company readCompanyById(int CompanyId) {
        String query = "SELECT * FROM company WHERE id = ?";
        RowMapper<Company> rowMapper = new BeanPropertyRowMapper(Company.class);
        return jdbcTemplate.queryForObject(query, rowMapper, CompanyId);
    }

    /**
     * Create company
     * @param companyName
     * @param companyId
     */
    public void createCompany(String companyName, int companyId){
        String query = "INSERT INTO company (company_name, company_id)" + "VALUES (?, ?)";
        jdbcTemplate.update(query, companyName, companyId);
    }

    /**
     * Update company
     * @param companyName
     * @param companyId
     */
    public void updateCompany(String companyName, int companyId){
        String query = "UPDATE company " +
                "SET company_name = ? " +
                "WHERE company_id = ?";
        jdbcTemplate.update(query, companyName, companyId);
    }

    public void deleteCompanyById(int companyId) {
        String query = "DELETE FROM company WHERE company_id = ?";
        jdbcTemplate.update(query, companyId);
    }
}