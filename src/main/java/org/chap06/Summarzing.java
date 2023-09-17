package org.chap06;

import org.chap04.Dish;

import static java.util.stream.Collectors.joining;

public class Summarzing {
    public static void main(String[] args) {
        System.out.println("Short menu: " + getShortMenu());

        System.out.println("Short menu comma separated: " + getShortMenuCommaSeparated());

    }
    private static String getShortMenu() {
        return Dish.menu.stream().map(Dish::getName).collect(joining());
    }

    private static String getShortMenuCommaSeparated() {
        return Dish.menu.stream().map(Dish::getName).collect(joining(", "));
    }

}
