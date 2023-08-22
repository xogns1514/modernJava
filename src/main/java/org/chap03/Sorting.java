package org.chap03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.*;

public class Sorting {
    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<Apple>();
        inventory.addAll(Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(100, Color.GREEN),
                new Apple(110, Color.RED),
                new Apple(90, Color.RED)
        ));

        //1. 미리 정의한 AppleComparator로 정렬
        //[Apple{color=GREEN, weight=80}, Apple{color=RED, weight=90}, Apple{color=GREEN, weight=100}, Apple{color=RED, weight=110}]
        inventory.sort(new AppleComparator());
        System.out.println(inventory);

        //데이터 변경
        inventory.set(1, new Apple(120, Color.GREEN));

        //2. 익명 클래스를 이용한 정렬
        //[Apple{color=GREEN, weight=80}, Apple{color=GREEN, weight=100}, Apple{color=RED, weight=110}, Apple{color=GREEN, weight=120}]
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight() - o2.getWeight();
            }
        });
        System.out.println(inventory);
        //데이터 변경
        inventory.set(1, new Apple(95, Color.GREEN));

        //3. 람다를 이용한 정렬
        //[Apple{color=GREEN, weight=80}, Apple{color=GREEN, weight=95}, Apple{color=RED, weight=110}, Apple{color=GREEN, weight=120}]
        inventory.sort((a1, a2) -> a1.getWeight() - a2.getWeight());
        System.out.println(inventory);

        //데이터 변경
        inventory.set(1, new Apple(30, Color.RED));

        //4. 메서드 참조를 이용한 정렬
        //[Apple{color=RED, weight=30}, Apple{color=GREEN, weight=80}, Apple{color=RED, weight=110}, Apple{color=GREEN, weight=120}]
        inventory.sort(comparing(Apple::getWeight));
        System.out.println(inventory);

    }

    //Comparator 구현
    static class AppleComparator implements Comparator<Apple> {
        @Override
        public int compare(Apple a, Apple b) {
            return a.getWeight() - b.getWeight();
        }
    }
}
