package com.androsiuk.lab_patterns.strategy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Context {
     public Strategy strategy;

    public void executeStrategy(double userBalanceDeduction){
        strategy.summarizeCost(userBalanceDeduction);
    }
}
