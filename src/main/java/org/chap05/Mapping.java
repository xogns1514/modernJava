package org.chap05;

import org.chap04.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Mapping {
    public static void main(String[] args) {

        List<String> dishNames = Dish.menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println("mapping dish to dishName: ");
        System.out.println(dishNames);
        System.out.println("----------------------------------------------------------------");

        List<String> words = Arrays.asList("Modern", "Java", "In", "Action");
        List<Integer> wordsLength = words.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println("mapping words to wordsLength: ");
        System.out.println(wordsLength);
        System.out.println("----------------------------------------------------------------");

        List<Integer> dishNamesLength = Dish.menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println("mapping dish to dishNamesLength: ");
        System.out.println(dishNamesLength);
        System.out.println("----------------------------------------------------------------");

        List<String> words1 = Arrays.asList("Hello", "World");
        List<String[]> distinctWords = words1.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(distinctWords);

        String[] arrayOfwords = {"Goodbye", "World"};
        Stream<String> streamOfwords = Arrays.stream(arrayOfwords);

        List<Stream<String>> distinctWords1 = words1.stream()
                .map(word -> word.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(distinctWords1);

        List<String> flatWords = words1.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(flatWords);
        System.out.println("----------------------------------------------------------------");

    }
}
