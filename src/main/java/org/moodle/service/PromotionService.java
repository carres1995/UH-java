package org.moodle.service;

import org.moodle.Promotable;

public class PromotionService {
    public void processPromotion(Promotable promo){

        double bonus = promo.calculatePromotionBonus();

        promo.logPromotion();

        System.out.printf("""
                Promotion Bonus: %.2f
                %n""", bonus);
    }
}
