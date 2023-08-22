package org.chap04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamBasic {
    public static void main(String[] args) {
        getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);
        /*
        결과
        season fruit
        rice
         */
        System.out.println("------------------------");
        getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);
        //위와 결과 동일
    }

    public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes) {
        List<Dish> lowCaloricDishes = new ArrayList<>();
        //400 칼로리 이하 dish 추가
        for (Dish dish : dishes) {
            if (dish.getCalories() < 400) {
                lowCaloricDishes.add(dish);
            }
        }
        List<String> lowCaloricDishesNames = new ArrayList<>();
        //칼로리 오름 차순 정렬후, 이름 추가
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });
        for(Dish dish : lowCaloricDishes) {
            lowCaloricDishesNames.add(dish.getName());
        }
        return lowCaloricDishesNames;
    }

    //스트림을 이용
    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes) {
        return dishes.stream()
                .filter(dish -> dish.getCalories() < 400)
                .sorted(Comparator.comparingInt(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
    }
}
