package com.androsiuk.lab_patterns.observer;

import java.util.Date;

public abstract class Observer {
    public abstract void update(Date returnDate, Double pledgeCost, Double userBalanceDeduction);
}