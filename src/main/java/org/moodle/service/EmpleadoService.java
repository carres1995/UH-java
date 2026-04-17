package org.moodle.service;

import org.moodle.domain.Empleado;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EmpleadoService {

    private final ArrayList<Empleado> employeesList = new ArrayList<>();
    private final Map<String, Empleado> employessMap = new HashMap<>();

    public Map<String, Empleado> employeesMap(){
        // documentated in: src/main/java/org/moodle/readme.md#
        return this.employessMap;
    }

    public ArrayList<Empleado> employeesList(){
        // documentated in: src/main/java/org/moodle/readme.md#
        return this.employeesList;
    }



    public void addEmployee(Empleado employee) {
        employeesList().add(employee);
        employeesMap().put(employee.getId(), employee);
        System.out.println("Employee added successfully.");
    }


    public void listEmployees() {
        if (employeesList().isEmpty()) {
            System.out.println("No employees registered.");
            return;
        }

        for (Empleado emp : employeesList()) {
            System.out.println("ID: " + emp.getId() +
                    " | Name: " + emp.getName() +
                    " | Avg: " + emp.getAverageNotes());
        }
    }


    public Empleado findById(String id) {
        return employeesMap().get(id);
    }


    public void deleteEmployee(String id) {
        Empleado emp = employeesMap().remove(id);

        if (emp != null) {
            employeesList().remove(emp);
            System.out.println("Employee deleted.");
        } else {
            System.out.println("Employee not found.");
        }
    }
    public void firstEmployees(){
        if(employeesList().isEmpty()) return;
        System.out.println("The first employed of list is: " + employeesList().getFirst().getName());
    }

    public void lastEmployees(){
        if(employeesList().isEmpty()) return;
        System.out.println("The first employed of list is: " + employeesList().getLast().getName());
    }

    public void ReverseList(){
        if(employeesList().isEmpty())return;
        for(Empleado com: employeesList().reversed()){
        System.out.println("List in reverse: " + com.getId() + " | " + com.getName() + " | " + com.getAverageNotes());}
    }

    public void filterDeleted(){

        var deletes = employeesList().removeIf(n -> n.getAverageNotes() < 3);
        // documentated in: src/main/java/org/moodle/readme.md#removeif
        if(deletes){
            for(Empleado com : employeesList())
                System.out.println("Employees with a bad average: " + com.getName());
        }

    }
}