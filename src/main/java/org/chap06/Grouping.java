package org.chap06;

import org.chap04.Dish;

import java.util.*;

import static java.util.stream.Collectors.*;

public class Grouping {
    enum CaloricLevel { DIET, NORMAL, FAT };


    public static void main(String[] args) {
        System.out.println("Dishes grouped by type: " + groupDishesByType());
        System.out.println("Dishes grouped by caloric level: " + groupDishesByCaloricLevel());
        System.out.println("Dishes grouped by type and filter caloric level: " + groupDishedByTypeAndFilterCaloricLevel());
        System.out.println("Dish names grouped by type: " + groupDishNamesByType());
        System.out.println("Dish tags grouped by type: " + groupDishTagsByType());
        System.out.println("Dishes grouped by type and  caloric level: " + groupDishedByTypeAndCaloricLevel());
        System.out.println("Dishes typesCount: " + typesCount());
        System.out.println("mostCaloricByType: " + mostCaloricByType());
        System.out.println("mostCaloricByTypeWithCollectingAndThen: " + mostCaloricByType2());
        System.out.println("groupDishTypeAndCaloricLevel: " + groupDishTypeAndCaloricLevel());


    }

    private static Map<Dish.Type, List<Dish>> groupDishesByType() {
        return Dish.menu.stream()
                .collect(groupingBy(Dish::getType));
        //{MEAT=[pork, beef, chicken], FISH=[prawns, salmon], OTHER=[french fries, rice, season fruit, pizza]}
    }

    private static Map<CaloricLevel, List<Dish>> groupDishesByCaloricLevel() {
        return Dish.menu.stream().collect(
                groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                })
        );
    }

    private static Map<Dish.Type, List<Dish>> groupDishedByTypeAndFilterCaloricLevel() {
//        return Dish.menu.stream().filter(dish -> dish.getCalories() > 500).collect(groupingBy(Dish::getType));
        return Dish.menu.stream().collect(
                groupingBy(Dish::getType,
                        filtering(dish -> dish.getCalories() > 500, toList())));
    }

    private static Map<Dish.Type, List<String>> groupDishNamesByType() {
        return Dish.menu.stream().collect(
                groupingBy(Dish::getType,
                        mapping(Dish::getName, toList())));
    }

    private static Map<Dish.Type, Set<String>> groupDishTagsByType() {
        return Dish.menu.stream()
                .collect(groupingBy(Dish::getType,
                        flatMapping(dish -> Dish.dishTags.get(dish.getName()).stream(),
                                toSet())));
    }

    private static Map<Dish.Type, Set<CaloricLevel>> groupDishTypeAndCaloricLevel() {
        return Dish.menu.stream()
                .collect(groupingBy(Dish::getType,
                        mapping(dish -> {
                            if(dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;},
                                toCollection(HashSet::new))));
    }

    private static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishedByTypeAndCaloricLevel() {
        return Dish.menu.stream().collect(
                groupingBy(Dish::getType,
                        groupingBy(dish -> {
                            if (dish.getCalories() <= 400)
                                return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700)
                                return CaloricLevel.NORMAL;
                            else
                                return CaloricLevel.FAT;
                        }))
        );
    }


    public static Map<Dish.Type, Long> typesCount() {
        return Dish.menu.stream()
                .collect(groupingBy(Dish::getType, counting()));
    }

    public static Map<Dish.Type, Optional<Dish>> mostCaloricByType() {
        return Dish.menu.stream()
                .collect(groupingBy(Dish::getType,
                        maxBy(Comparator.comparingInt(Dish::getCalories))));
    }

    public static Map<Dish.Type, Dish> mostCaloricByType2() {
        return Dish.menu.stream()
                .collect(groupingBy(Dish::getType, collectingAndThen(
                        maxBy(Comparator.comparingInt(Dish::getCalories)),
                        Optional::get
                )));
    }


}
