package view;

import controler.EmployeeManagement;
import util.Validate;

/**
 * @author kazaf
 */
public class RunMenu {
    private EmployeeManagement em = new EmployeeManagement();
    private View v = new View();

    public void menu() {
        while (true) {
            System.out.println("=================== Menu ===================");
            System.out.println(
                    "1.Add employees \n"
                            + "2.Update employees\n"
                            + "3.Remove employees\n"
                            + "4.Search employees\n"
                            + "5.Sort employees by salary\n"
                            + "6.Exit");
            System.out.println("============================================");
            int choice;
            choice = Validate.getInt("Input choice: ", "Invalid choice[1-6]", 1, 6, null);
            switch (choice) {
                case 1:
                    System.out.println("------------Add employee---------------");
                    v.addAnEmployee();
                    v.displayEmployee();
                    break;
                case 2:
                    System.out.println("------------ Update employees ---------------");
                    v.updateAnEmployee();
                    v.displayEmployee();
                    break;
                case 3:
                    System.out.println("------------ Remove employees ---------------");
                    v.removeEmployeee();
                    v.displayEmployee();
                    break;
                case 4:
                    System.out.println("------------ Search employee ---------------");
                    v.searchEmployee();
                    break;
                case 5:
                    System.out.println("------------ Sort employee by salary ---------------");
                    v.sortEmployee();
                    v.displayEmployee();
                    break;
                case 6:
                    System.out.println("you are exit");
                    System.exit(0);
                    break;
            }
        }
    }
}
