package org.moodle.repository;

import org.moodle.domain.Employee;
import org.moodle.domain.records.PerformanceReport;
import java.util.List;

/**
 * TASK 2: Interfaz para el acceso a datos.
 */
public interface EmployeeRepository {
    void insert(Employee employee);
    List<Employee> findAll();
    void update(Employee employee);
    void delete(String id);
    Employee findById(String id);
    List<PerformanceReport> getPerformanceReport();
}
