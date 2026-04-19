package org.moodle.utils;

import java.util.Scanner;

public class Validations {
    public int scIsNumber(Scanner sc) {
        while (true) {
            String input = sc.nextLine();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Error, ingresa un número entero válido: ");
            }
        }
    }
    public double numberIsRange(Scanner sc) {
        while (true){
            try{
            double input = Double.parseDouble(sc.nextLine());
            if (input >= 0 && input <= 5) {
                return input;
            }
            System.out.println("It isn't in range.");
            } catch (Exception e){
                System.out.println("Enter a valid number.");
            }
        }
    }
    public double NumberIsZero(double value){
        if(value <= 0){
            System.out.println("Must to be greater than zero");
        }
        return value;
    }


}
