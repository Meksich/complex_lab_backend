package com.androsiuk.lab_patterns.observer;

import com.androsiuk.lab_patterns.entity.User;
import com.androsiuk.lab_patterns.repository.UserRepository;
import lombok.AllArgsConstructor;

import java.util.Date;


@AllArgsConstructor
public class UserObserver extends Observer{
    private UserRepository userRepository;
    private User user;

    @Override
    public void update(Date returnDate, Double pledgeCost, Double userBalanceDeduction) {
        Date currDate = new Date();
        if(returnDate.after(currDate)){
            user.setBalanceUah(user.getBalanceUah() - pledgeCost);
        } else {
            user.setBalanceUah(user.getBalanceUah() + pledgeCost - userBalanceDeduction);
        }
        if (userRepository.existsById(user.getId())){
            userRepository.save(user);
        }
    }
}