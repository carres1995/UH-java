package org.moodle.service;

import org.moodle.domain.Employee;
import org.moodle.domain.records.PerformanceReport;

public class FinalReport {
    private EmployeeService service;
    public FinalReport(EmployeeService service){
        this.service = service;
    }

    public int totalEmployes(){
        return service.employeesList().size();
    }
    public double salaryAverage() {
        if (totalEmployes() == 0) return 0;
        double sum = 0;
        for (Employee com : service.employeesList()) {
            sum += com.getSalary();

        }
        return sum / totalEmployes();
    }
    public PerformanceReport generateMonthlyReport(Employee emp){
        var average = emp.getAverageNotes();

        var feedback = average >=4.5 ? "Amazing Performance!!": average >= 3.5 ? "Good performance, but you can improve" : "you have a bad performance";

        return new PerformanceReport(Integer.parseInt(emp.getId()),average,feedback);
    }

    public void performanceByEmployee(String id){
        Employee employee = service.findById(id);
        if(employee == null){
            System.out.println("Employee not found");
            return;}

        var report = generateMonthlyReport(employee);

        System.out.printf("""
                Report:
                Id Empleyee: %s
                Average: %.2f
                Feedback: %s
                %n""", report.id(),report.average(),report.feedback());
    }

}
