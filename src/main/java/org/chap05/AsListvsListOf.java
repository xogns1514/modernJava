package org.chap05;

import java.util.Arrays;
import java.util.List;

public class AsListvsListOf {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, null);
        list.set(1, 10); // OK

        List<Integer> list1 = List.of(1, 2, 3);
//        list1.set(1, 10); // Fails with UnsupportedOperationException

        List<Integer> list2 = Arrays.asList(1, 2, null); // OK
//        List<Integer> list3 = List.of(1, 2, null); // Fails with NullPointerException

        Integer[] array = {1,2};
        List<Integer> list4 = Arrays.asList(array);
        array[0] = 100;
        System.out.println(list4); // [100, 2]

        Integer[] array2 = {1,2};
        List<Integer> list5 = List.of(array2);
        array[0] = 100;
        System.out.println(list5); // [1, 2]
    }
}
