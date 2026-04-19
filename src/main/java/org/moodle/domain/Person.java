package org.moodle.domain;

public abstract sealed class Person permits Employee, ExternalConsultant {
    private final String id;
    private final String name;
    public Person(String id, String name){
        this.id = id;
        this.name = name;
        //relation documentation in: src/main/java/org/moodle/readme.md#sealed-security

    }
    public String getId() {return id;}

    public String getName(){return name;}
}
