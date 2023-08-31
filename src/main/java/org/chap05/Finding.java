package org.chap05;

import org.chap04.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Finding {
    public static void main(String[] args) {

        System.out.println("anyMatch for menu: ");
        if (Dish.menu.stream()
                .anyMatch(Dish::isVegetarian)) {
            System.out.println("Vegetarian friendly");
        }
        System.out.println("----------------------------------------------------------------");

        System.out.println("allMatch for menu: ");
        if(Dish.menu.stream()
                .allMatch(dish -> dish.getCalories() < 1000)) {
            System.out.println("Calories is less than 1000");
        }
        System.out.println("----------------------------------------------------------------");

        System.out.println("noneMatch for menu: ");
        if (Dish.menu.stream()
                .noneMatch(d -> d.getCalories() >= 1000)) {
            System.out.println("Calories is less than 1000");
        }
        System.out.println("----------------------------------------------------------------");

        System.out.println("findAny for dish: ");
        Optional<Dish> dishOptional = Dish.menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
        System.out.println(dishOptional);

        System.out.println("ifPresent for dish: ");
        Dish.menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(d -> System.out.println(d.getName()));
        System.out.println("----------------------------------------------------------------");

        System.out.println("findFirst: ");
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream()
                .map(n -> n * n)
                .filter(n -> n % 3 == 0)
                .findFirst();
        System.out.println(firstSquareDivisibleByThree);
        System.out.println("----------------------------------------------------------------");

        System.out.println("for-each:");
        int sum = 0;
        for(int x : someNumbers) {
            sum += x;
        }
        System.out.println(sum);

        System.out.println("reduce-lambda:");
        int sum2 = someNumbers.stream()
                .reduce(0, (a, b) -> a + b);
        System.out.println(sum2);

        System.out.println("reduce-method:");
        int sum3 = someNumbers.stream()
                .reduce(0, Integer::sum);
        System.out.println(sum3);
        System.out.println("----------------------------------------------------------------");

        System.out.println("max-lambda: ");
        Integer maxLambda = someNumbers.stream()
                .reduce(Integer.MIN_VALUE, (x, y) -> x > y ? x : y);
        System.out.println(maxLambda);

        System.out.println("max-method: ");
        Integer maxMethod = someNumbers.stream()
                .reduce(Integer.MIN_VALUE, Integer::max);
        System.out.println(maxMethod);

        Optional<Integer> max = someNumbers.stream()
                .max(Integer::compareTo);
        System.out.println(max);

    }

    private static boolean isVegetarianFriendlyMenu() {
        return Dish.menu.stream().anyMatch(Dish::isVegetarian);
    }

    private static boolean isHealthyMenu() {
        return Dish.menu.stream().allMatch(d -> d.getCalories() < 1000);
    }

}
