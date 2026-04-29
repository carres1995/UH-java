package org.moodle;

import org.moodle.controller.EmployeeController;
import org.moodle.domain.Employee;
import org.moodle.utils.Validations;

import java.util.Scanner;

/**
 * Vista Final - Cumplimiento 100% de Tareas.
 */
public class App {
    private final EmployeeController controller = new EmployeeController();
    private final Validations validate = new Validations();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        int option;
        do {
            System.out.println("""
                
                =========================================
                |     SISTEMA DE GESTIÓN (MVC + JDBC)   |
                =========================================
                1. Registrar Empleado
                2. Listar Empleados
                3. Actualizar Empleado
                4. Eliminar Empleado
                5. Reporte de Alto Rendimiento (Records)
                6. Salir
                =========================================
                Seleccione una opción: """);

            option = validate.scIsNumber(scanner);

            switch (option) {
                case 1 -> createFlow();
                case 2 -> listFlow();
                case 3 -> updateFlow();
                case 4 -> deleteFlow();
                case 5 -> reportFlow();
                case 6 -> System.out.println("Finalizando sistema...");
                default -> System.out.println("Opción no válida.");
            }
        } while (option != 6);
    }

    private void createFlow() {
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Nombre: ");
        String name = scanner.nextLine();
        System.out.print("Promedio (0-5): ");
        double avg = validate.numberIsRange(scanner);
        System.out.print("Salario: ");
        double salary = Double.parseDouble(scanner.nextLine());
        controller.saveEmployee(id, name, avg, salary);
    }

    private void listFlow() {
        System.out.println("\n--- LISTADO DE EMPLEADOS ---");
        controller.listAll().forEach(System.out::println);
    }

    private void updateFlow() {
        System.out.print("ID del empleado a actualizar: ");
        String id = scanner.nextLine();
        Employee existing = controller.getById(id);
        
        if (existing == null) {
            System.out.println("Empleado no encontrado.");
            return;
        }

        System.out.print("Nuevo Nombre (" + existing.getName() + "): ");
        String name = scanner.nextLine();
        System.out.print("Nuevo Promedio (" + existing.getAverageNotes() + "): ");
        double avg = validate.numberIsRange(scanner);
        System.out.print("Nuevo Salario (" + existing.getSalary() + "): ");
        double salary = Double.parseDouble(scanner.nextLine());

        controller.updateEmployee(id, name, avg, salary);
    }

    private void deleteFlow() {
        System.out.print("ID del empleado a eliminar: ");
        String id = scanner.nextLine();
        controller.removeEmployee(id);
    }

    private void reportFlow() {
        var reports = controller.generateComplexReport();
        if (reports.isEmpty()) {
            System.out.println("Sin datos para reporte.");
            return;
        }

        String header = """
            
            **************************************************
            *      REPORTES DE RENDIMIENTO (JAVA 21)        *
            **************************************************
            ID         | PROMEDIO   | COMENTARIO
            --------------------------------------------------
            """;
        System.out.print(header);
        reports.forEach(r -> System.out.printf("%-10d | %-10.2f | %-20s\n", r.id(), r.average(), r.feedback()));
        System.out.println("**************************************************");
    }
}