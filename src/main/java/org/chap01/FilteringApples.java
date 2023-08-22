package org.chap01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FilteringApples {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(10, "red"),
                new Apple(20, "green"),
                new Apple(30, "red"),
                new Apple(40, "green"),
                new Apple(50, "red"),
                new Apple(110, "green"),
                new Apple(120, "red"),
                new Apple(140, "green"),
                new Apple(90, "red"),
                new Apple(100, "green")
        );

        //1. for문 이용한 초록사과 필터링
        //[Apple{weight=20, color='green'}, Apple{weight=40, color='green'}, Apple{weight=110, color='green'}, Apple{weight=140, color='green'}, Apple{weight=100, color='green'}]
        List<Apple> greenApples = filterGreenApples(inventory);
        System.out.println(greenApples);

        //2. for문 이용한 무거운 사과 필터링
        //[Apple{weight=110, color='green'}, Apple{weight=120, color='red'}, Apple{weight=140, color='green'}]
        List<Apple> heavyApples = filterHeavyApples(inventory);
        System.out.println(heavyApples);

        //3. Predicate와 static 메서드를 이용한 사과 필터링
        // 1번과 동일
        List<Apple> greenApples2 = filterApples(inventory, FilteringApples::isGreenApple);
        System.out.println(greenApples2);

        //4. Predicate와 static 메서드를 이용한 무거운 사과 필터링
        // 2번과 동일
        List<Apple> heavyApples2 = filterApples(inventory, FilteringApples::isHeavyApple);
        System.out.println(heavyApples2);

        //5. 람다를 이용한 필터링
        // 1번과 동일
        List<Apple> greenApples3 = filterApples(inventory, (apple) -> "green".equals(apple.getColor()));
        System.out.println(greenApples3);

        //6. 람다를 이용한 필터링
        // 2번과 동일
        List<Apple> heavyApples3 = filterApples(inventory, (apple) -> apple.getWeight() > 100);
        System.out.println(heavyApples3);
    }

    //for문을 이용한 초록 사과 필터링
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();//필터 사과 저장 리스트
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    //for문을 이용한 무거운 사과 필터링
    public static List<Apple> filterHeavyApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();//필터 사과 저장 리스트
        for (Apple apple : inventory) {
            if (apple.getWeight() > 100) {
                result.add(apple);
            }
        }
        return result;
    }

    public static boolean isGreenApple(Apple apple) {return "green".equals(apple.getColor());}

    public static boolean isHeavyApple(Apple apple) {return apple.getWeight() > 100;}

    //Predicate를 이용한 사과 필터링
    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();//필터 사과 저장 리스트
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static class Apple {
        private int weight;

        private String color = "";

        public Apple(int weight, String color) {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        @SuppressWarnings("boxing") //컴파일시 컴파일 경고를 사용하지 않는다.
        @Override
        public String toString() {
            return String.format("Apple{weight=%d, color='%s'}", weight, color);
        }
    }
}
