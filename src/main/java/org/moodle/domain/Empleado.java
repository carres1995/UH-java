package org.moodle.domain;

import org.moodle.utils.Validations;

import java.util.List;
import java.util.Map;

public class Empleado {
    static Validations validate = new Validations();

    //these are primitives dates
    /*
    byte smallerNumber = 1;
    short smallNumber = 200;
    int integerNumber = 100000;
    long bigNumber = 20000000L;
    float decimal = 2.50002f;
    double simpleDecimal = 2.3;
    char character = 'A';
    boolean trueFalse = true;

    //This date isn't a primitive date because is a class
    String text = "Hello, world";
    */


    private final String id;
    private final String name;
    private final double averageNotes;
    private final double salary;
    private static final List<String> TECNOLOGYS = List.of("Java", "Python", "JavaScript", "AWS");

    private static final Map<Integer, String> HEADQUATERS = Map.of(
            1, "Headquarter Norte",
            2, "Headquarter Sur",
            3, "Headquarter Virtual"
    );


    public Empleado(String id, String name, double averageNotes, double salary) {
        this.id = id;
        this.name = name;
        this. salary = salary;
        this.averageNotes = averageNotes;
    }


    public String getId() { return id; }
    public String getName() { return name; }
    public double getAverageNotes() { return averageNotes; }

    public double getSalary() {
        return salary;
    }

    public static double calculateFinalSalary(double salary, double bonus) {
        return (salary + bonus) - (salary * 0.05);
    }

    public boolean validateEligibility(int scoreTest, int age, int headquarters, boolean isActive) {
        var validHeadquarter = HEADQUATERS.containsKey(headquarters);

        if(!validHeadquarter){
            System.out.println("Error: the Headquarter " + headquarters + " don't register.");
            return false;
        }
        return (scoreTest > 85 && age < 30) || (headquarters == 1 && !isActive);
    }

    public static void getSalaryCategory(double salary, double bonus){
        var total = calculateFinalSalary(salary, bonus);
        var totalSalary = validate.NumberIsZero(total);
        var million = (int) totalSalary / 1_000_000;

        String category = switch (million){
            case 0,1 -> "Low range";
            case 2,3 -> "Medium range";
            default -> "High range";
        };
        System.out.println("Category result: " + category);
    }




}