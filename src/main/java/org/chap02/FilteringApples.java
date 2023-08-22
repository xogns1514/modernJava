package org.chap02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

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

        //2. 매개변수 받아 Color 필터링
        //1과 동일
        List<Apple> greenApples2 = filterGreenApples(inventory, GREEN);
        System.out.println(greenApples2);

        //3. 매개변수 받아 무거운 사과 필터링
        //[Apple{weight=120, color='RED'}, Apple{weight=150, color='RED'}]
        List<Apple> heavyApples = filterApplesByWeight(inventory, 100);
        System.out.println(heavyApples);

        //4. predicate을 이용한 무거운 사과 필터링
        //3과 동일
        List<Apple> heavyApples2 = filter(inventory, new AppleWeightPredicate());
        System.out.println(heavyApples2);

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

    //위 코드 개선안.
    //매개변수로 Color을 받아 필터링. 재사용 가능하다
    public static List<Apple> filterGreenApples(List<Apple> apples, Color color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (apple.getColor() == color)
                result.add(apple);
        }
        return result;
    }

    //매개변수로 weight 받아 무거운 사과 필터링
    //필터링 요구사항이 점점 많아 질수록 매개변수를 계속 추가해야 하는 일이 생긴다.
    public static  List<Apple> filterApplesByWeight(List<Apple> apples, int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (apple.getWeight() > weight)
                result.add(apple);
        }
        return result;
    }

    //predicate를 이용한 필터링
    //요구사항에 맞는 predicate을 전달하여 재사용 가능하다.
    public static List<Apple> filter(List<Apple> apples, ApplePredicate applePredicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (applePredicate.test(apple))
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

    interface ApplePredicate {
        boolean test(Apple apple);
    }

    static class AppleColorPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return apple.getColor() == Color.GREEN;
        }
    }
    static class AppleWeightPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return apple.getWeight() > 100;
        }
    }
    static class AppleWeightAndColorPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return apple.getWeight() > 100 && apple.getColor() == Color.GREEN;
        }
    }

}
