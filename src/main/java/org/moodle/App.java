package org.moodle;

import org.moodle.domain.Empleado;
import org.moodle.service.EmpleadoService;
import org.moodle.service.FinalReport;
import org.moodle.utils.Validations;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    Validations validate = new Validations();
    EmpleadoService service = new EmpleadoService();
    FinalReport report = new FinalReport(service);

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
                8. Final report
                9. Exit
                """);
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
                        System.out.print("Enter ID: ");
                        var id = scanner.nextLine();

                        System.out.print("Enter name: ");
                        var name = scanner.nextLine();

                        System.out.print("Enter average: ");
                        var avg = Double.parseDouble(scanner.nextLine());
                        System.out.println("Enter salary: ");
                        var salary = Double.parseDouble((scanner.nextLine()));

                        service.addEmployee(new Empleado(id, name, avg,salary));
                        break;

                    case 2:
                        service.listEmployees();
                        break;

                    case 3:
                        service.firstEmployees();
                        service.lastEmployees();
                        service.ReverseList();

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

                        var total = Empleado.calculateFinalSalary(salary2, bonus);
                        Empleado.getSalaryCategory(salary2, bonus);

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

                        var temp = new Empleado("0","temp",0, 0);
                        var result = temp.validateEligibility(score, age, hq, isActive);

                        System.out.println("Eligible: " + result);
                        break;
                    case 7:
                        service.filterDeleted();
                        break;
                    case 8:
                        System.out.printf("""
                                Final Report is:
                                    Employees Amount: %d
                                    Average Employees: %f
                                %n""",report.totalEmployes(),report.salaryAverage());
                        break;
                    case 9:
                        System.out.println("Exiting...");
                        break;


                    default:
                        System.out.println("Invalid option.");
                }

            } while (option != 6);

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