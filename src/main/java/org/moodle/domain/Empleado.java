package org.moodle.domain;

import org.moodle.utils.Validations;

public class Empleado {
    static Validations validate = new Validations();
    //these are primitives dates
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

    public static double calculateFinalSalary(double salary, double bonus) {

        return (salary + (bonus)) - (salary * 0.05);
    }

    public void bonusApply(int idEmployed, double extraBonus) {
        if (idEmployed % 2 == 0) {
            extraBonus += 50000;
            System.out.println("Extra bonus apply. " + extraBonus);
        }

    }

    public boolean validarElegibilidad(int scoreTest, int age, int headsquarter, boolean isActive) {

        return (scoreTest > 85 && age < 30) || (headsquarter == 1 && !isActive);
    }

    public static void obtenerCategoriaSalarial(double salary, double bonus){
        var total = calculateFinalSalary(salary,bonus);
        var totalSalary = validate.NumberIsZero(total);
        var million = (int) totalSalary / 1000000;

        String category = switch (million){
            case 0,1 -> "Low range";
            case 2,3 -> "Medium range";
            default -> "High range";
        };
        System.out.println("Category result: " + category);

    }
    public static double[][] gradesMatrix(){
        return new double[][]{
                {3.5,4,4.5},
                {5,2.8,3.5},
                {3,4.2,5}
        };
    }

    public static double averageStudent(){
        double addition = 0;
        for(int i = 0; i < gradesMatrix().length; i++){
            if(gradesMatrix().length == 0){
                System.out.println("Empty array.");
            }
            var notes = gradesMatrix()[i];
            for(double note:notes){
                addition += note;
            }
        }
        return addition/ gradesMatrix().length;

    }
    public static int simplifiedScore(){
        return (int) averageStudent();
        //documentation in:
        //org.moodle/readme.md#loss-of-precision
    }


}

