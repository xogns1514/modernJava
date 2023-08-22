package org.chap04;

import java.util.List;
import java.util.stream.Collectors;

public class HighCaloriesNames {
    public static void main(String[] args) {

        List<String> names = Dish.menu.stream()
                .filter(dish -> {
                    System.out.println("filtering " + dish.getName());
                    return dish.getCalories() > 300;
                }) //300 칼로리 넘는 dish 필터링
                .map(dish -> {
                    System.out.println("mapping " + dish.getName());
                    return dish.getName(); //Dish의 이름만 뽑아냄
                })
                .limit(3) //처음 3개만 선택한다.
                .collect(Collectors.toList());
        System.out.println(names);
        /*
        filtering pork
        mapping pork
        filtering beef
        mapping beef
        filtering chicken
        mapping chicken
        [pork, beef, chicken]
         */
        //스트림의 게으른 특성으로 인해 위와 같은 출력이 나온다.
    }
}
