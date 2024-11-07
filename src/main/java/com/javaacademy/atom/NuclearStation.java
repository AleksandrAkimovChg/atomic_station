package com.javaacademy.atom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Slf4j
@Component
public class NuclearStation {
    private final ReactorDepartment reactorDepartment;
    private BigInteger energyTotalAmount = BigInteger.ZERO;
    private static final int DAY_IN_YEAR = 365;

    public NuclearStation(ReactorDepartment reactorDepartment) {
        this.reactorDepartment = reactorDepartment;
    }

    private void startYear() {
        BigInteger energyYearAmount = BigInteger.ZERO;
        log.info("Атомная станция начала работу");

        for (int i = 1; i <= DAY_IN_YEAR; i++) {
            BigInteger energyDay = BigInteger.ZERO;
            try {
                log.trace("День {}", i);
                energyDay = energyDay.add(reactorDepartment.run());
            } catch (NuclearFuelIsEmptyException e) {
                log.warn("Внимание! Происходят работы на атомной станции! Электричества нет!");
                log.debug(e.toString());
            } catch (ReactorWorkException e) {
                log.info(e.getMessage());
            }

            energyYearAmount = energyYearAmount.add(energyDay);
            energyTotalAmount = energyTotalAmount.add(energyDay);

            try {
                reactorDepartment.stop();
            } catch (ReactorWorkException e) {
                log.info(e.getMessage());
            }
        }
        log.info("Атомная станция закончила работу. За год Выработано {} киловатт/часов", energyYearAmount);
    }

    public void start(int year) {
        for (int i = 1; i <= year; i++) {
            startYear();
        }
    }
}
