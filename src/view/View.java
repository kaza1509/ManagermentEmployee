package view;

import controler.EmployeeManagement;
import model.Employee;
import util.Validate;

/**
 * @author kazaf
 */
public class View {
    private EmployeeManagement em = new EmployeeManagement();

    /**
     * input employee without id
     *
     * @param isInput status null or nope
     * @return employee input
     */
    public Employee input(String isInput) {
        String firstName = Validate.getString("Enter first name: ", "First name invalid", "^[a-zA-Z ]+$", isInput);
        String lastName = Validate.getString("Enter last name: ", "Last name invalid", "^[a-zA-Z ]+$", isInput);
        String phone = Validate.getString("Enter phone: ", "Phone invalid(has 10 or 11 number)", "^0\\d{9,10}+$", isInput);
        String email = Validate.getString("Enter email: ", "Email invalid(name@gmail..com)", "^[a-zA-Z]\\w*@(\\w+[.])+\\w+$", isInput);
        String address = Validate.getString("Enter address: ", "Address invalid", "^[a-zA-Z0-9 ]+$", isInput);
        String dob = Validate.getDate("Enter dob: ", "DOB invalid(dd/MM/yyyy)", "dd/MM/yyyy", null);
        String sex = Validate.getString("Enter sex: ", "Sex(male,female)", "^(male|female)$", isInput);
        Double salary = Validate.getDouble("Enter salary: ", "Salary invalid", 0, Double.MAX_VALUE, isInput);
        String agency = Validate.getString("Enter agency: ", "Agency invalid", "^[a-zA-Z ]+$", isInput);
        return new Employee(firstName, lastName, phone, email, address, dob, sex, salary, agency);
    }

    /**
     * add employee
     */
    public void addAnEmployee() {
        String id;
        do {
            id = Validate.getString("Enter id: ", "Invalid id", "^[a-zA-Z0-9 ]+$", null);
            if (em.isDuplicateId(id))
                System.err.println("Add fail. Duplicate id");
            else break;
        }
        while (true);

        Employee e = input(null);
        Employee ob = new Employee(id, e.getFirstName(), e.getLastName(), e.getPhone(), e.getEmail(),
                e.getAddress(), e.getDob(), e.getSex(), e.getSalary(), e.getAgency());
        em.addEmployee(ob);
        System.out.println("Add sucessful id: " + id);
    }

    /**
     * update employee
     */
    public void updateAnEmployee() {
        if (em.getDataEmployee().isEmpty()) {
            System.out.println("List Employee Empty: ");
            return;
        }
        String id = getIDExist();
        Employee emUpdate = em.getEmployeeById(id);

        System.out.println("You can input 'nope' to keep old value");

        String firstName = Validate.getString("Enter first name: ", "First name invalid", "^[a-zA-Z ]+$", "nope");
        if (firstName != null) emUpdate.setFirstName(firstName);

        String lastName = Validate.getString("Enter last name: ", "Last name invalid", "^[a-zA-Z ]+$", "nope");
        if (lastName != null) emUpdate.setLastName(lastName);

        String phone = Validate.getString("Enter phone: ", "Phone invalid(has 10 or 11 number)", "^0\\d{9,10}+$", "nope");
        if (phone != null) emUpdate.setPhone(phone);

        String email = Validate.getString("Enter email: ", "Email invalid(name@gmail..com)", "^[a-zA-Z]\\w*@(\\w+[.])+\\w+$", "nope");
        if (email != null) emUpdate.setEmail(email);

        String address = Validate.getString("Enter address: ", "Address invalid", "^[a-zA-Z0-9 ]+$", "nope");
        if (address != null) emUpdate.setAddress(address);

        String dob = Validate.getDate("Enter dob: ", "DOB invalid(dd/MM/yyyy)", "dd/MM/yyyy", "nope");
        if (dob != null) emUpdate.setDob(dob);

        String sex = Validate.getString("Enter sex: ", "Sex(male,female)", "^(male|female)$", "nope");
        if (sex != null) emUpdate.setSex(sex);

        Double salary = Validate.getDouble("Enter salary: ", "Salary invalid", 0, Double.MAX_VALUE, "nope");
        if (salary != null) emUpdate.setSalary(salary);

        String agency = Validate.getString("Enter agency: ", "Agency invalid", "^[a-zA-Z ]+$", "nope");
        if (agency != null) emUpdate.setAgency(agency);

        em.updateEmployee(id, emUpdate);
    }

    /**
     * Remove employee
     */
    public void removeEmployeee() {
        if (em.getDataEmployee().isEmpty()) {
            System.out.println("List Employee Empty: ");
            return;
        }

        String id = Validate.getString("Enter id: ", "Invalid id", "^[a-zA-Z0-9 ]+$", null);
        if (em.indexOfID(id) == -1)
            System.out.println("Id not found. Remove fail");
        else {
            em.removeExployee(id);
            System.out.println("Remove successful");
        }
    }

    /**
     * search employee by name
     */
    public void searchEmployee() {
        if (em.getDataEmployee().isEmpty()) {
            System.out.println("List Employee Empty: ");
            return;
        }

        String name = Validate.getString("Enter name: ", "Invalid name", "^[a-zA-Z ]+$", null);

        if (em.searchEmployee(name) == null) {
            System.out.println("Name not found in the list");
            return;
        }
        System.out.println("Found [" + name + "] word in the list");

        System.out.format("%-10s%-25s%-15s%-15s%-15s%-15s%-15s%-10s%-15s",
                "ID", "Name", "Phone", "Email", "Address", "Dob", "Sex", "Salary", "Agency");
        System.out.println("");
        for (Employee employee : em.searchEmployee(name)) {
            System.out.println(employee);
        }
    }

    /**
     * sort employee by salary
     */
    public void sortEmployee() {
        if (em.getDataEmployee().isEmpty()) {
            System.out.println("List Employee Empty: ");
            return;
        }
        em.sortEmployee();
    }

    /**
     * display list
     */
    public void displayEmployee() {
        if (em.getDataEmployee().isEmpty()) {
            System.out.println("List Employee Empty: ");
            return;
        }

        System.out.format("%-10s%-25s%-15s%-15s%-15s%-15s%-15s%-10s%-15s",
                "ID", "Name", "Phone", "Email", "Address", "Dob", "Sex", "Salary", "Agency");
        System.out.println("");
        for (Employee employee : em.getDataEmployee()) {
            System.out.println(employee);
        }
    }

    /**
     * get id exist
     *
     * @return id
     */
    public String getIDExist() {
        while (true) {
            String id = Validate.getString("Enter id: ", "Invalid id", "^[a-zA-Z0-9 ]+$", null);
            if (em.indexOfID(id) != -1) {
                return id;//tìm thấy
            } else {
                System.out.println("Id is not exist, Input again");//không tìm thấy -> input again
            }
        }
    }
}
