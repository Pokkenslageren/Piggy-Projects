package PiggyProjects.Controller;

import PiggyProjects.Service.CompanyService;
import PiggyProjects.Model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class CompanyController {

    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/Company/{companyId}")
        public ResponseEntity<Integer> readCompanyById (int companyId){
            List<Company> Company = CompanyService.readCompanyById(companyId);
            return new ResponseEntity<>(companyId, HttpStatus.OK);
    }
}


