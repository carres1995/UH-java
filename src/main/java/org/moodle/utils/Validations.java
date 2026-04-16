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
    public double NumberIsZero(double value){
        if(value <= 0){
            System.out.println("Must to be greater than zero");
        }
        return value;
    }
}
