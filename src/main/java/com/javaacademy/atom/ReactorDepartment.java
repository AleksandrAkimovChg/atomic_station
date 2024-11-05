package com.javaacademy.atom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Slf4j
@Component
public class ReactorDepartment {
    private static final int COUNT_TO_FUEL_IS_EMPTY = 100;
    private static final BigInteger KILOWATT_HOURS_PER_DAY = BigInteger.valueOf(10_000_000_000L);

    private boolean isWork;
    private int countStart;

    public BigInteger start() {
        if (isWork) {
            throw new ReactorWorkException("Реактор уже работает");
        }
        countStart++;
        log.trace("Попытка старта {}", countStart);
        if (countStart % COUNT_TO_FUEL_IS_EMPTY == 0) {
            throw new NuclearFuelIsEmptyException("Топливо закончилось");
        }
        isWork = true;
        return KILOWATT_HOURS_PER_DAY;
    }

    public void stop() {
        if (!isWork) {
            throw new ReactorWorkException("Реактор уже выключен");
        }
        isWork = false;
    }



}
