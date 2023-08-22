package org.chap02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.chap02.FilteringApples.Color.GREEN;

public class FilteringApples {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80, GREEN),
                new Apple(90, GREEN),
                new Apple(100, Color.RED),
                new Apple(120, Color.RED),
                new Apple(150, Color.RED)
        );

        //1. for문 내에서 GREEN 필터링
        //[Apple{weight=80, color='GREEN'}, Apple{weight=90, color='GREEN'}]
        List<Apple> greenApples = filterGreenApples(inventory);
        System.out.println(greenApples);

    }

    //List를 받아 초록 사과 필터링
    // for문 내부에서 enum GREEN과 일치 여부를 따져서 필터링.
    //다른 색으로 필터링 하기위해서는 같은 형식의 함수 생성이 필요.
    public static List<Apple> filterGreenApples(List<Apple> apples) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (apple.getColor() == GREEN)
                result.add(apple);
        }
        return result;
    }

    enum Color {
        RED,
        GREEN
    }

    public static class Apple {
        private int weight;
        private Color color;

        public Apple(int weight, Color color) {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        @SuppressWarnings("boxing") //컴파일시 컴파일 경고를 사용하지 않는다.
        @Override
        public String toString() {
            return String.format("Apple{weight=%d, color='%s'}", weight, color);
        }
    }

}
