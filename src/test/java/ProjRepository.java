import java.util.List;

public class ProjRepository {
    public int calculateTotalAvailableEmployees(List<Integer> taskEmployees){
        int totalEmployeesInUse = 0;
        for(Integer employee : taskEmployees){
            totalEmployeesInUse = totalEmployeesInUse + employee;
        }
        return 100 - totalEmployeesInUse;

    }
}
