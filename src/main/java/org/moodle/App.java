package org.moodle;

import org.moodle.domain.Empleado;
import org.moodle.service.EmpleadoService;
import org.moodle.utils.Validations;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    Validations validate = new Validations();
    EmpleadoService service = new EmpleadoService();

    public void menu() {
        System.out.println("""
                Employees Management System
                ---------------------------
                1. Add Employee
                2. List Employees
                3. Delete Employee
                4. Salary calculation
                5. Validate eligibility
                6. Exit
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

                        service.addEmployee(new Empleado(id, name, avg));
                        break;

                    case 2:
                        service.listEmployees();
                        break;

                    case 3:
                        System.out.print("Enter ID to delete: ");
                        var idDelete = scanner.nextLine();
                        service.deleteEmployee(idDelete);
                        break;

                    case 4:
                        System.out.print("Enter salary: ");
                        var salary = Double.parseDouble(scanner.nextLine());

                        System.out.print("Enter bonus: ");
                        var bonus = Double.parseDouble(scanner.nextLine());

                        var total = Empleado.calculateFinalSalary(salary, bonus);
                        Empleado.getSalaryCategory(salary, bonus);

                        System.out.println("Final salary: " + total);
                        break;

                    case 5:
                        System.out.print("Enter score: ");
                        var score = validate.scIsNumber(scanner);

                        System.out.print("Enter age: ");
                        var age = validate.scIsNumber(scanner);

                        System.out.print("Enter headquarters ID: ");
                        var hq = validate.scIsNumber(scanner);

                        System.out.print("Is active? (true/false): ");
                        var isActive = Boolean.parseBoolean(scanner.nextLine());

                        var temp = new Empleado("0","temp",0);
                        var result = temp.validateEligibility(score, age, hq, isActive);

                        System.out.println("Eligible: " + result);
                        break;

                    case 6:
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