package org.moodle;

import org.moodle.domain.Developer;
import org.moodle.domain.Employee;
import org.moodle.domain.Manager;
import org.moodle.service.EmployeeService;
import org.moodle.service.FinalReport;
import org.moodle.service.PromotionService;
import org.moodle.service.RoleService;
import org.moodle.utils.Validations;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    private final RoleService roleService = new RoleService();
    private final Validations validate = new Validations();
    private final EmployeeService service = new EmployeeService();
    private final FinalReport report = new FinalReport(service);
    private final PromotionService promotionService =
            new PromotionService();

    public void menu() {
        System.out.println("""
                Employees Management System
                ---------------------------
                1. Add Employee
                2. List Employees
                3. First and Last Employees and Reverse list
                4. Delete Employee
                5. Salary calculation
                6. Validate eligibility
                7. Deleted by Average
                8. Feedback employed
                9. Final report
                10. Validate role
                11. Promotion Bonus
                12. Exit
                """);
    }
    public void roleAdded() {
        System.out.println("""
            Select employee type
            -------------------
            1. Developer
            2. Manager
            """);
    }
    private void promotionFlow() {
        var scanner = new Scanner(System.in);
        service.listEmployees();

        System.out.print("Enter employee ID: ");
        var id = scanner.nextLine();

        var person = service.employeesMap().get(id);

        if(person == null){
            System.out.println("Employee not found.");
            return;
        }

        promotionService.processPromotion((Promotable) person);
    }
    public void startAdded(){
        var scanner = new Scanner(System.in);

        int roleOption = validate.scIsNumber(scanner);

        System.out.print("Enter ID: ");
        var id = scanner.nextLine();

        System.out.print("Enter name: ");
        var name = scanner.nextLine();

        switch (roleOption) {


            case 1:

                System.out.print("Enter average: ");
                var avgDev = validate.numberIsRange(scanner);

                System.out.print("Enter salary: ");
                var salaryDev = Double.parseDouble(scanner.nextLine());

                System.out.print("Enter main language: ");
                var language = scanner.nextLine();

                service.addEmployee(
                        new Developer(
                                id,
                                name,
                                avgDev,
                                salaryDev,
                                language
                        ),
                        id
                );
                break;

            case 2:

                System.out.print("Enter average: ");
                var avgMng = validate.numberIsRange(scanner);

                System.out.print("Enter salary: ");
                var salaryMng = Double.parseDouble(scanner.nextLine());

                System.out.print("Enter monthly budget: ");
                var budget = Double.parseDouble(scanner.nextLine());

                service.addEmployee(
                        new Manager(
                                id,
                                name,
                                avgMng,
                                salaryMng,
                                budget
                        ),
                        id
                );
                break;


        }
    }

    public void start() {
        var scanner = new Scanner(System.in);
        int option;

        try{
            do {
                menu();

                option = validate.scIsNumber(scanner);

                switch (option) {

                    case 1:
                        roleAdded();
                        startAdded();;
                        break;

                    case 2:
                        service.listEmployees();
                        break;

                    case 3:
                        service.firstEmployees();
                        service.lastEmployees();
                        service.ReverseList();
                        break;

                    case 4:
                        System.out.print("Enter ID to delete: ");
                        var idDelete = scanner.nextLine();
                        service.deleteEmployee(idDelete);
                        break;

                    case 5:
                        System.out.print("Enter salary: ");
                        var salary2 = Double.parseDouble(scanner.nextLine());

                        System.out.print("Enter bonus: ");
                        var bonus = Double.parseDouble(scanner.nextLine());

                        var total = Employee.calculateFinalSalary(salary2, bonus);
                        Employee.getSalaryCategory(salary2, bonus);

                        System.out.println("Final salary: " + total);
                        break;

                    case 6:
                        System.out.print("Enter score: ");
                        var score = validate.scIsNumber(scanner);

                        System.out.print("Enter age: ");
                        var age = validate.scIsNumber(scanner);

                        System.out.print("Enter headquarters ID: ");
                        var hq = validate.scIsNumber(scanner);

                        System.out.print("Is active? (true/false): ");
                        var isActive = Boolean.parseBoolean(scanner.nextLine());

                        var temp = new Employee("0","temp",0, 0);
                        var result = temp.validateEligibility(score, age, hq, isActive);

                        System.out.println("Eligible: " + result);
                        break;
                    case 7:
                        service.filterDeleted();
                        break;
                    case 8:
                        service.listEmployees();
                        System.out.println("Enter id Employee: ");
                        var idEmployee = scanner.nextLine();
                        report.performanceByEmployee(idEmployee);
                        break;
                    case 9:
                        System.out.printf("""
                                Final Report is:
                                    Employees Amount: %d
                                    Average salary Employees: %.2f
                                %n""",report.totalEmployes(),report.salaryAverage());
                        break;
                    case 10:

                        service.listEmployees();

                        System.out.print("Enter employee ID: ");
                        var roleId = scanner.nextLine();

                        var person = service.employeesMap().get(roleId);

                        if(person == null){
                            System.out.println("Employee not found.");
                            break;
                        }

                        roleService.validateRole(person);
                        break;
                    case 11:
                        promotionFlow();
                        break;
                    case 12:
                        System.out.println("Exiting...");
                        break;


                    default:
                        System.out.println("Invalid option.");
                }

            } while (option != 12);

        }catch (InputMismatchException e){
            System.out.println("Must enter a valid value " + e);
            /*
            See documentation:
            src/main/java/org/moodle/readme.md/lts-analysis-Evolution-of-Exception-Diagnostics
             */
        }

        scanner.close();
    }
}