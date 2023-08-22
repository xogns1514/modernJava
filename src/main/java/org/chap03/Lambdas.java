package org.chap03;

import java.util.ArrayList;
import java.util.List;

public class Lambdas {
    public static void main(String[] args) {
        Runnable r = () -> System.out.println("Hello World");
        r.run();

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
