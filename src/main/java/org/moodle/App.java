package org.moodle;

import org.moodle.domain.Empleado;
import org.moodle.utils.Validations;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    Empleado com = new Empleado();
    Validations validate = new Validations();

    public void menu() {
        System.out.println("""
                Employers and Companys
                Welcome to Management System
                ---------------------------
                1. Employee (salary calculate)
                2. validate eligibility
                3. notes average - simplify score - promotion status
                4. Salir
                """);
    }

    public void start() {
        var scanner = new Scanner(System.in);
        int option;
        try{
            do {
                menu(); // 🔥 mostrar menú

                option = validate.scIsNumber(scanner); // 🔥 actualizar opción

                switch (option) {

                    case 1:
                        System.out.print("Enter salary: ");
                        var salary = Double.parseDouble(scanner.nextLine());

                        System.out.print("Enter bonus: ");
                        var bonus = Double.parseDouble(scanner.nextLine());

                        var total = Empleado.calculateFinalSalary(salary, bonus);
                        Empleado.obtenerCategoriaSalarial(salary,bonus);
                        System.out.println("Final salary: " + total);
                        break;


                    case 2:
                        System.out.print("Enter score: ");
                        var score = validate.scIsNumber(scanner);

                        System.out.print("Enter age: ");
                        var age = validate.scIsNumber(scanner);

                        System.out.print("Enter id: ");
                        var headquaters = validate.scIsNumber(scanner);

                        System.out.print("¿Está activo? (true/false): ");
                        var isActive = Boolean.parseBoolean(scanner.nextLine());

                        var result = com.validarElegibilidad(score, age, headquaters, isActive);
                        System.out.println("Elegible: " + result);
                        break;

                    case 3:
                        var average= Empleado.averageStudent();
                        var score2 = Empleado.simplifiedScore();
                        System.out.println(String.format("""
                                The average is: %f
                                The score is: %d
                                """,average,score2));
                        Empleado.promotionStatus();
                    case 4:
                        System.out.println("Saliendo...");
                        break;

                    default:
                        System.out.println("Invalid option.");
                }

            } while (option != 3);
        }catch (InputMismatchException e){
            System.out.println("Must enter a valid value" + e);
            /*
            See documentation:
            src/main/java/org/moodle/readme.md/lts-analisys-Evolution-of-Exception-Diagnostics
             */
        }

        scanner.close();
    }
}