package org.chap05;

import org.chap04.Dish;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class Filtering {
    public static void main(String[] args) {
        System.out.println("<Filtering with a predicate>");
        List<Dish> vegetarianMenu = menu.stream()
                .filter(Dish::isVegetarian)// 채식 요리인지 확인하는 메서드 참조
                .collect(toList());
        vegetarianMenu.forEach(System.out::println);

        System.out.println("<Filtering with a lambda>");
        List<Dish> vegetarianMenu1 = menu.stream()
                .filter(d -> d.isVegetarian())// 채식 요리인지 확인하는 메서드 참조
                .collect(toList());
        vegetarianMenu.forEach(System.out::println);
        System.out.println("----------------------------------------------------------------");

        System.out.println("<Distinct - Numbers>");
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)// 짝수 필터링
                .distinct()
                .forEach(System.out::println);
        System.out.println("----------------------------------------------------------------");

        System.out.println("<Distinct - Dish>");
        List<Dish> distinctDishes = menu.stream()
                .distinct()
                .collect(toList());
        distinctDishes.forEach(System.out::println);

        System.out.println(menu.get(0).hashCode()); //Dish("pork", false, 800, Dish.Type.MEAT),
        System.out.println(menu.get(1).hashCode()); //Dish("pork", false, 900, Dish.Type.MEAT),
        System.out.println(menu.get(0).equals(menu.get(1)));

        System.out.println("----------------------------------------------------------------");

        Map<Dish, String> dishMap = new HashMap<Dish, String>();
        Dish pork = new Dish("pork", false, 800, Dish.Type.MEAT);
        dishMap.put(pork, "pork");

        String findPork = dishMap.get(new Dish("pork", false, 800, Dish.Type.MEAT));
        System.out.println(findPork);

    }
    public static final List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("pork", false, 900, Dish.Type.MEAT),
            new Dish("pork", false, 950, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 400, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH),
            new Dish("salmon", false, 460, Dish.Type.FISH),
            new Dish("salmon", false, 470, Dish.Type.FISH)


    );

}
