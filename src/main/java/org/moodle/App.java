package org.moodle;

import org.moodle.domain.Empleado;
import org.moodle.domain.records.CompanyRecord;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("""
                Employers and Companys
                Welcome to Management System
                ---------------------------
                """);
        Empleado company = new Empleado();
        CompanyRecord companyRecord1 = new CompanyRecord("SoftCol", 99999, 1995);

        String nullString = null;
        try{
            System.out.println(nullString);
        }catch (NullPointerException e){
            // Error registered in readme.md
            System.out.println("Detected error: " + e.getMessage());
        }

        CompanyRecord companyRecord2 = new CompanyRecord("SoftCol", 900-123-1, 2020);
        System.out.println(companyRecord1 == companyRecord2); //give it false
    }
}
