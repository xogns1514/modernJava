package org.chap01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        //[Apple{weight=20, color='green'}, Apple{weight=40, color='green'}, Apple{weight=110, color='green'}, Apple{weight=140, color='green'}, Apple{weight=100, color='green'}]
        List<Apple> greenApples = filterGreenApples(inventory);
        System.out.println(greenApples);

        //[Apple{weight=110, color='green'}, Apple{weight=120, color='red'}, Apple{weight=140, color='green'}]
        List<Apple> heavyApples = filterHeavyApples(inventory);
        System.out.println(heavyApples);

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
