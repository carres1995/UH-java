package org.moodle.domain;

public class Empleado {
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

    public double calculateFinalSalary(double salary, double bonus) {
        double finalSalary = (salary + (bonus)) - (salary * 0.05);

        return finalSalary;
    }

    public void bonusApply(int idEmployed, double extraBonus) {
        if (idEmployed % 2 == 0) {
            extraBonus += 50000;
            System.out.println("Extra bonus apply.");
        }

    }

    public boolean validarElegibilidad(int puntajeTest, int edad, int idSede, boolean esActivo) {
        // Precedencia: ! (NOT) -> && (AND) -> || (OR)
        return (puntajeTest > 85 && edad < 30) || (idSede == 1 && !esActivo);
    }
}

