package org.moodle.domain;

import org.moodle.Promotable;

public final class Developer extends Employee implements Promotable {
    private final String mainLanguage;

    public Developer(String id, String name, double averageNotes, double salary, String firstLanguage) {
        super(id, name, averageNotes, salary);
        this.mainLanguage = firstLanguage;
    }
    public String getMainLanguage(){
        return mainLanguage;
    }

    @Override
    public double calculatePromotionBonus() {
        return getSalary() * 0.20;
    }
}
