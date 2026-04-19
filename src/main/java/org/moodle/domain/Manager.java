package org.moodle.domain;

import org.moodle.Promotable;

public final class Manager extends Employee implements Promotable {
    private final double monthlyBuget;
    public Manager(String id, String name, double averageNotes, double salary, double monthlyBuget) {
        super(id, name, averageNotes, salary);
        this.monthlyBuget = monthlyBuget;
    }

    public double getMonthlyBuget() {
        return monthlyBuget;
    }

    @Override
    public double calculatePromotionBonus() {
        return getSalary() * 0.30;
    }
}
