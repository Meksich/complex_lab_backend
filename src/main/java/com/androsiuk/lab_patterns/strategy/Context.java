package com.androsiuk.lab_patterns.strategy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Context {
     public Strategy strategy;

    public double executeStrategy(double userBalanceDeduction){
        return strategy.summarizeCost(userBalanceDeduction);
    }
}
