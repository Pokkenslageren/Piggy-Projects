package ProjectPlanner.Service;

import ProjectPlanner.Repository.SubprojectRepository;
import org.springframework.jdbc.core.JdbcTemplate;

public class SubprojectService {

    private final SubprojectRepository subprojectRepository = new SubprojectRepository(new JdbcTemplate());

}
