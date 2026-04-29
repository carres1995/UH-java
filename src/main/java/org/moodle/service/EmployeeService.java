package org.moodle.service;

import org.moodle.domain.Employee;
import org.moodle.persistence.ConfigDB;
import org.moodle.domain.records.PerformanceReport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Service refactorizado con funcionalidades SQL avanzadas.
 */
public class EmployeeService {

    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO employees (id, name, average_notes, salary) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConfigDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, employee.getId());
            ps.setString(2, employee.getName());
            ps.setDouble(3, employee.getAverageNotes());
            ps.setDouble(4, employee.getSalary());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("DB Error (Add): " + e.getMessage());
        }
    }

    public List<Employee> getAllEmployees() {
        return getListFromSQL("SELECT * FROM employees");
    }

    // Mejora: Obtener el primer empleado usando SQL (Eficiencia)
    public Employee getFirstEmployee() {
        List<Employee> list = getListFromSQL("SELECT * FROM employees ORDER BY id ASC LIMIT 1");
        return list.isEmpty() ? null : list.getFirst();
    }

    // Mejora: Obtener el último empleado usando SQL
    public Employee getLastEmployee() {
        List<Employee> list = getListFromSQL("SELECT * FROM employees ORDER BY id DESC LIMIT 1");
        return list.isEmpty() ? null : list.getFirst();
    }

    // Mejora: Lista en reversa usando SQL
    public List<Employee> getReverseList() {
        return getListFromSQL("SELECT * FROM employees ORDER BY id DESC");
    }

    public void deleteEmployee(String id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection conn = ConfigDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("DB Error (Delete): " + e.getMessage());
        }
    }

    public Employee findById(String id) {
        String sql = "SELECT * FROM employees WHERE id = ?";
        try (Connection conn = ConfigDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Employee(rs.getString("id"), rs.getString("name"), 
                                        rs.getDouble("average_notes"), rs.getDouble("salary"));
                }
            }
        } catch (SQLException e) {
            System.err.println("DB Error (Find): " + e.getMessage());
        }
        return null;
    }

    private List<Employee> getListFromSQL(String sql) {
        List<Employee> list = new ArrayList<>();
        try (Connection conn = ConfigDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Employee(rs.getString("id"), rs.getString("name"),
                        rs.getDouble("average_notes"), rs.getDouble("salary")));
            }
        } catch (SQLException e) {
            System.err.println("DB Query Error: " + e.getMessage());
        }
        return list;
    }

    public List<PerformanceReport> getPerformanceReport() {
        List<PerformanceReport> reports = new ArrayList<>();
        String sql = "SELECT id, name, average_notes FROM employees WHERE average_notes >= 4.0";
        try (Connection conn = ConfigDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                reports.add(new PerformanceReport(rs.getInt("id"), rs.getDouble("average_notes"), "High Performance"));
            }
        } catch (SQLException e) {
            System.err.println("Complex Query Error: " + e.getMessage());
        }
        return reports;
    }
}