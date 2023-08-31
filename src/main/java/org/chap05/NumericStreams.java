package org.chap05;

import org.chap04.Dish;

import java.util.ArrayList;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumericStreams {
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        int reduceCalories = Dish.menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        System.out.println("Number of calories:" + reduceCalories);
        long endTime = System.currentTimeMillis();
        long testTime = endTime - startTime;

        System.out.println("reduce testTime: " + testTime);

//        int reduceCalories2 = Dish.menu.stream()
//                .map(Dish::getCalories)
//                .sum();

        startTime = System.currentTimeMillis();
        int calories = Dish.menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println("Number of calories:" + calories);
        endTime = System.currentTimeMillis();
        testTime = endTime - startTime;

        System.out.println("sum testTime: " + testTime);
        System.out.println("----------------------------------------------------------------");

        double averageCalories = Dish.menu.stream()
                .mapToInt(Dish::getCalories)
                .average().orElse(0);
        System.out.println("averageCalories: " + averageCalories);
        System.out.println("----------------------------------------------------------------");

        IntStream intStream = Dish.menu.stream()
                .mapToInt(Dish::getCalories); //스트림을 숫자 스트림으로 변환
        Stream<Integer> stream = intStream.boxed(); //숫자 스트림을 스트림으로 변환

        OptionalInt maxCalories = Dish.menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        int max = maxCalories.orElse(1);
        System.out.println(max);
        System.out.println("------------------------------------------------------------------");

        System.out.println("rangeClosed: ");
        IntStream evenNumbers = IntStream.rangeClosed(1, 100) //1~100
                .filter(n -> n % 2 == 0);
        System.out.println(evenNumbers.count()); //50

        System.out.println("range: ");
        IntStream evenNumber1 = IntStream.range(1, 100) //2~99
                .filter(n -> n % 2 == 0);
        System.out.println(evenNumber1.count()); //49
    }
}
