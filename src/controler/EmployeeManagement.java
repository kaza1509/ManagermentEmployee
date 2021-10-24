package controler;

import model.Employee;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author kazaf
 */
public class EmployeeManagement {
    private final ArrayList<Employee> listEmployees = new ArrayList<>();

    public EmployeeManagement() {
        listEmployees.add(new Employee("HE153250", "Nguyen", "Tan", "03356629816", "e2@gmail.com", "Bo De",
                "23/6/1997", "Female", 1000, "Good"));
        listEmployees.add(new Employee("HE153287", "Le", "Lieu", "03675576459", "e3@gmail.com", "Boi Cau",
                "19/3/2000", "Male", 100, "Bad"));
        listEmployees.add(new Employee("HE154355", "Ha", "Lam", "0315644692", "e4@gmail.com", "An Ninh",
                "30/5/2001", "Female", 5000, "Undefined"));
        listEmployees.add(new Employee("HE156249", "Nguyen", "Khoi", "03136667682", "e1@gmail.com", "Ngoc Lu",
                "11/2/2001", "Male", 2000, "Better"));
    }

    /**
     * add employee
     *
     * @param e employee
     */
    public void addEmployee(Employee e) {
        listEmployees.add(e);
        Collections.sort(listEmployees);
    }

    /**
     * \
     * update employee
     *
     * @param id       id employee
     * @param emUpdate employee update
     */
    public void updateEmployee(String id, Employee emUpdate) {
        Employee e = getEmployeeById(id);

        e.setFirstName(emUpdate.getFirstName());
        e.setLastName(emUpdate.getLastName());
        e.setPhone(emUpdate.getPhone());
        e.setEmail(emUpdate.getEmail());
        e.setAddress(emUpdate.getAddress());
        e.setDob(emUpdate.getDob());
        e.setSalary(emUpdate.getSalary());
        e.setAgency(emUpdate.getAgency());
    }

    /**
     * remove an employee
     *
     * @param id employee id
     */
    public void removeExployee(String id) {
        int index = indexOfID(id);
        if (index != -1) {
            listEmployees.remove(index);
        }
    }

    /**
     * search employee by name
     *
     * @param name name search
     * @return list search
     */
    public ArrayList<Employee> searchEmployee(String name) {
        ArrayList<Employee> listSearch = new ArrayList<>();
        boolean isExist = false;


        for (Employee e : listEmployees) {
//            StringBuilder sb = null;
            StringBuilder sb;
            String fullName;
            sb = new StringBuilder(e.getFirstName()).append(" ").append(e.getLastName());
            fullName = sb.toString().toUpperCase();

            if (fullName.contains(name.toUpperCase())) {
                listSearch.add(e);
                isExist = true;
            }
        }

        if (isExist) return listSearch;
        return null;
    }

    /**
     * sort employee by salary
     */
    public void sortEmployee() {
        listEmployees.sort((Employee o1, Employee o2) -> {
            if (o1.getSalary() > o2.getSalary()) {
                return 1;
            } else {
                return -1;
            }
        });
    }

    /**
     * get list employee
     *
     * @return get list
     */
    public ArrayList<Employee> getDataEmployee() {
        return listEmployees;
    }

    //check id exist
    public int indexOfID(String id) {
        for (int i = 0; i < listEmployees.size(); i++) {
            if (listEmployees.get(i).getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    //get employee by id
    public Employee getEmployeeById(String id) {
        for (Employee listEmployee : listEmployees) {
            if (id.equalsIgnoreCase(listEmployee.getId()))
                return listEmployee;
        }
        return null;
    }

    //check duplicate id
    public boolean isDuplicateId(String id) {
        for (Employee eList : listEmployees) {
            if (eList.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }
}
