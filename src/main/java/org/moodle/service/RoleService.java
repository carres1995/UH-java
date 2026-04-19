package org.moodle.service;

import org.moodle.domain.Developer;
import org.moodle.domain.Manager;
import org.moodle.domain.Person;

public class RoleService {
    public void validateRole(Person p){
        if(p instanceof Developer dev){
            System.out.println("Developer uses:" + dev.getMainLanguage() );
        } else if (p instanceof Manager manager) {
            System.out.println("Manager budget: " + manager.getMonthlyBuget());
        }
    }
}
