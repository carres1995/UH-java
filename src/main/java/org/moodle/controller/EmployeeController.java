package org.moodle.controller;

import org.moodle.domain.Employee;
import org.moodle.repository.EmployeeRepository;
import org.moodle.repository.impl.EmployeeRepositoryImpl;
import java.util.List;

/**
 * TASK 3: Controlador Refactorizado.
 * Coordina las acciones usando el Repositorio (Capa de datos).
 */
public class EmployeeController {
    private final EmployeeRepository repository;

    public EmployeeController() {
        this.repository = new EmployeeRepositoryImpl();
    }

    public void saveEmployee(String id, String name, double avg, double salary) {
        repository.insert(new Employee(id, name, avg, salary));
    }

    public List<Employee> listAll() {
        return repository.findAll();
    }

    public void updateEmployee(String id, String name, double avg, double salary) {
        repository.update(new Employee(id, name, avg, salary));
    }

    public void removeEmployee(String id) {
        repository.delete(id);
    }

    public Employee getById(String id) {
        return repository.findById(id);
    }

    public List<org.moodle.domain.records.PerformanceReport> generateComplexReport() {
        return repository.getPerformanceReport();
    }
}
