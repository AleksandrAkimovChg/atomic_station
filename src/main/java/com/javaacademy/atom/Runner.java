package com.javaacademy.atom;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {
    public static final int YEAR_TO_TEST = 3;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("com.javaacademy.atom");
        NuclearStation station = context.getBean(NuclearStation.class);
        station.start(YEAR_TO_TEST);
    }
}
