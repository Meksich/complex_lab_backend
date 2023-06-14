package com.androsiuk.lab_patterns.strategy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PenaltyStrategy implements Strategy{
    public double damagePenalty;

    @Override
    public double summarizeCost(double userBalanceDeduction) {
        double sumBalanceDeduction = userBalanceDeduction + damagePenalty;
        return sumBalanceDeduction;
    }
}
