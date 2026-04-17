package org.moodle.service;

import org.moodle.domain.Empleado;
import org.moodle.service.EmpleadoService;

public class FinalReport {
    private EmpleadoService service;
    public FinalReport(EmpleadoService service){
        this.service = service;
    }

    public int totalEmployes(){
        return service.employeesList().size();
    }
    public double salaryAverage() {
        if (totalEmployes() == 0) return 0;
        double sum = 0;
        for (Empleado com : service.employeesList()) {
            sum += com.getSalary();

        }
        return sum / totalEmployes();
    }
}
