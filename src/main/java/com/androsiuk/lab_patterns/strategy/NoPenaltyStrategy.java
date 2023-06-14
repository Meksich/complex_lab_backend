package com.androsiuk.lab_patterns.strategy;

import com.androsiuk.lab_patterns.entity.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class NoPenaltyStrategy implements Strategy{
    User user;
    @Override
    public double summarizeCost(double userBalanceDeduction){
        double sumBalanceDeduction = userBalanceDeduction * user.getUserCategory().getDiscount();
        return sumBalanceDeduction;
    }
}
