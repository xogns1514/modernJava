package org.chap06;

import org.chap04.Dish;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class Grouping {
    public static void main(String[] args) {
        System.out.println("Dishes grouped by type: " + groupDishesByType());

    }

    private static Map<Dish.Type, List<Dish>> groupDishesByType() {
        return Dish.menu.stream()
                .collect(groupingBy(Dish::getType));
        //{MEAT=[pork, beef, chicken], FISH=[prawns, salmon], OTHER=[french fries, rice, season fruit, pizza]}
    }
}
