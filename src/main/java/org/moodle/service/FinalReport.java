package org.moodle.service;

import org.moodle.domain.Employee;
import org.moodle.domain.records.PerformanceReport;
import java.util.List;

/**
 * Refactorización de FinalReport para trabajar con datos de la DB.
 */
public class FinalReport {
    private final EmployeeService service;

    public FinalReport(EmployeeService service){
        this.service = service;
    }

    public int totalEmployees(){
        return service.getAllEmployees().size();
    }

    public double salaryAverage() {
        List<Employee> list = service.getAllEmployees();
        if (list.isEmpty()) return 0;
        
        double sum = 0;
        for (Employee emp : list) {
            sum += emp.getSalary();
        }
        return sum / list.size();
    }

    public PerformanceReport generateMonthlyReport(Employee emp){
        var average = emp.getAverageNotes();
        var feedback = average >= 4.5 ? "Amazing Performance!!" : 
                      average >= 3.5 ? "Good performance, but you can improve" : 
                      "You have a bad performance";

        // Cambiamos a String el ID si el record lo permite, o manejamos el parseo con seguridad.
        try {
            return new PerformanceReport(Integer.parseInt(emp.getId()), average, feedback);
        } catch (NumberFormatException e) {
            // Si el ID no es numérico, usamos un valor por defecto para el reporte
            return new PerformanceReport(0, average, feedback);
        }
    }

    public void performanceByEmployee(String id){
        Employee employee = service.findById(id);
        if(employee == null){
            System.out.println("Employee not found");
            return;
        }

        var report = generateMonthlyReport(employee);

        System.out.printf("""
                -------------------------
                Report:
                Id Employee: %d
                Average: %.2f
                Feedback: %s
                -------------------------
                %n""", report.id(), report.average(), report.feedback());
    }
}
