package org.moodle.repository.impl;

import org.moodle.domain.Employee;
import org.moodle.domain.records.PerformanceReport;
import org.moodle.persistence.ConfigDB;
import org.moodle.repository.EmployeeRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Override
    public void insert(Employee employee) {
        String sql = "INSERT INTO employees (id, name, average_notes, salary) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConfigDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, employee.getId());
            ps.setString(2, employee.getName());
            ps.setDouble(3, employee.getAverageNotes());
            ps.setDouble(4, employee.getSalary());
            ps.executeUpdate();
            System.out.println("Empleado insertado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar: " + e.getMessage());
        }
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try (Connection conn = ConfigDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Employee(rs.getString("id"), rs.getString("name"),
                        rs.getDouble("average_notes"), rs.getDouble("salary")));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar: " + e.getMessage());
        }
        return list;
    }

    @Override
    public void update(Employee employee) {
        String sql = "UPDATE employees SET name = ?, average_notes = ?, salary = ? WHERE id = ?";
        try (Connection conn = ConfigDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, employee.getName());
            ps.setDouble(2, employee.getAverageNotes());
            ps.setDouble(3, employee.getSalary());
            ps.setString(4, employee.getId());
            
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) System.out.println("Empleado actualizado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar: " + e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection conn = ConfigDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("Empleado eliminado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar: " + e.getMessage());
        }
    }

    @Override
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
            System.err.println("Error al buscar: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<PerformanceReport> getPerformanceReport() {
        List<PerformanceReport> reports = new ArrayList<>();
        String sql = "SELECT id, average_notes FROM employees WHERE average_notes >= 4.0";
        try (Connection conn = ConfigDB.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                reports.add(new PerformanceReport(rs.getInt("id"), rs.getDouble("average_notes"), "Alto Rendimiento"));
            }
        } catch (SQLException e) {
            System.err.println("Error en reporte complejo: " + e.getMessage());
        }
        return reports;
    }
}
