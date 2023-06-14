package com.androsiuk.lab_patterns.observer;

import com.androsiuk.lab_patterns.entity.UserCatalog;
import com.androsiuk.lab_patterns.repository.UserCatalogRepository;
;
import lombok.AllArgsConstructor;
import java.util.Date;


@AllArgsConstructor
public class UserCatalogObserver extends Observer{
    private UserCatalogRepository userCatalogRepository;
    private UserCatalog userCatalog;

    @Override
    public void update(Date returnDate, Double pledgeCost, Double userBalanceDeduction) {
        Date currDate = new Date();
        if(returnDate.after(currDate)){
            userCatalog.setUsersWithDueRent(userCatalog.getUsersWithDueRent() + 1);
        } else {
            userCatalog.setUsersWithDueRent(userCatalog.getUsersWithDueRent() - 1);
        }
        if (userCatalogRepository.existsById(userCatalog.getId())){
            userCatalogRepository.save(userCatalog);
        }
    }
}
