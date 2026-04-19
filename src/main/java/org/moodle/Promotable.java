package org.moodle;

public abstract interface Promotable {
    double calculatePromotionBonus();

    default void logPromotion(){
        System.out.println("Promotion bonus calculated successfully.");
    }
}
