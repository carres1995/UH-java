package org.moodle.service;

import org.moodle.domain.Developer;
import org.moodle.domain.Employee;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EmployeeService {

    private final ArrayList<Employee> employeesList = new ArrayList<>();
    private final Map<String, Employee> employessMap = new HashMap<>();

    public Map<String, Employee> employeesMap(){
        // documentated in: src/main/java/org/moodle/readme.md#
        return this.employessMap;
    }

    public ArrayList<Employee> employeesList(){
        // documentated in: src/main/java/org/moodle/readme.md#
        return this.employeesList;
    }



    public void addEmployee(Employee employee, String id) {
        if (employeesMap().containsKey(id)) {
            System.out.println("Id already exist.");
            return;
        }
        employeesList().add(employee);
        employeesMap().put(employee.getId(), employee);
        System.out.println("Employee added successfully.");

        listEmployees();
    }


    public void listEmployees() {
        if (employeesList().isEmpty()) {
            System.out.println("No employees registered.");
            return;
        }

        for (Employee emp : employeesList()) {
            System.out.println("ID: " + emp.getId() +
                    " | Name: " + emp.getName() +
                    " | Avg: " + emp.getAverageNotes());
        }
    }


    public Employee findById(String id) {
        return employeesMap().get(id);
    }


    public void deleteEmployee(String id) {
        Employee emp = employeesMap().remove(id);

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
        System.out.println("The Last employed of list is: " + employeesList().getLast().getName());
    }

    public void ReverseList(){
        if(employeesList().isEmpty())return;
        System.out.println("--- List in reverse: ");
        for(Employee com: employeesList().reversed()){

            System.out.println( com.getId() + " | " + com.getName() + " | " + com.getAverageNotes());}
    }

    public void filterDeleted(){

        var deletes = employeesList().removeIf(n -> n.getAverageNotes() < 3);
        // documentated in: src/main/java/org/moodle/readme.md#removeif
        if(deletes){
            for(Employee com : employeesList())
                System.out.println("Employees with a bad average: " + com.getName());
        }

    }

}