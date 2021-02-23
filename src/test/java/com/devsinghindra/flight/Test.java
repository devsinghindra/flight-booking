package com.devsinghindra.flight;

import com.devsinghindra.flight.model.Flight;

import java.text.ParseException;

public class Test {
    public static void main(String[] args) {
        System.out.println();
        Long date = 1613675629 + 0L;
        String convertedString = Flight.epochToString(date);
        try {
            long convertedEpoch = Flight.toEpoch(convertedString);
            System.out.println(convertedEpoch);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        String[] strings=convertedString.split(" ");
//        Arrays.stream(strings).forEach(System.out::println);
        System.out.println(convertedString);
    }
}
