package org.moodle.domain;

import org.moodle.Promotable;

/**
 * Entidad Employee (POJO Tradicional)
 * 
 * Análisis Moderno (TASK 4):
 * A diferencia de los Records, esta clase POJO requiere definición explícita de campos,
 * constructores y métodos de acceso. Mientras que los Records son inmutables por defecto,
 * esta clase permite mutabilidad si se definen setters.
 */
public non-sealed class Employee extends Person implements Promotable {
    
    private final double averageNotes;
    private final double salary;

    public Employee(String id, String name, double averageNotes, double salary) {
        super(id, name);
        this.salary = salary;
        this.averageNotes = averageNotes;
    }

    public double getAverageNotes() { return averageNotes; }
    public double getSalary() { return salary; }

    @Override
    public double calculatePromotionBonus() {
        return getSalary() * 0.10;
    }

    @Override
    public String toString() {
        return "ID: " + getId() + " | Name: " + getName() + " | Avg: " + averageNotes + " | Salary: " + salary;
    }
}