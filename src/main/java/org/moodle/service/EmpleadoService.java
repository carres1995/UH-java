package org.moodle.service;

import org.moodle.domain.Empleado;


import java.util.ArrayList;
import java.util.HashMap;

public class EmpleadoService {


    private ArrayList<Empleado> employees = new ArrayList<>();


    private HashMap<String, Empleado> employeeMap = new HashMap<>();


    public void addEmployee(Empleado employee) {
        employees.add(employee);
        employeeMap.put(employee.getId(), employee);
        System.out.println("Employee added successfully.");
    }


    public void listEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees registered.");
            return;
        }

        for (Empleado emp : employees) {
            System.out.println("ID: " + emp.getId() +
                    " | Name: " + emp.getName() +
                    " | Avg: " + emp.getAverageNotes());
        }
    }


    public Empleado findById(String id) {
        return employeeMap.get(id);
    }


    public void deleteEmployee(String id) {
        Empleado emp = employeeMap.remove(id);

        if (emp != null) {
            employees.remove(emp);
            System.out.println("Employee deleted.");
        } else {
            System.out.println("Employee not found.");
        }
    }
}