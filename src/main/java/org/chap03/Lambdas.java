package org.chap03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Lambdas {
    public static void main(String[] args) {
        Runnable r = () -> System.out.println("Hello World");
        r.run();

        List<Apple> inventory = Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(100, Color.RED),
                new Apple(120, Color.RED)
        );
        //[Apple{color=GREEN, weight=80}]
        List<Apple> greenApples = filter(inventory, a -> a.getColor() == Color.GREEN);
        System.out.println(greenApples);

        Comparator<Apple> c = (a1, a2) -> a1.getWeight() - a2.getWeight();

        inventory.sort(c);
        System.out.println(inventory);

    }


    public static List<Apple> filter(List<Apple> apples, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple a : apples) {
            if (p.test(a)) {
                result.add(a);
            }
        }
        return result;
    }

    interface ApplePredicate {
        boolean test(Apple a);
    }
}
