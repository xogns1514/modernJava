package org.chap04;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamVsCollection {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Java8", "Lambdas", "In", "Action");
        Stream<String> s = names.stream();
        s.forEach(System.out::println);
        // 스트림은 한 번만 소비 할 수 있다. 따라서 다시 소비하기 위해서는 원래 소스에 스트림을 적용해야함
//        s.forEach(System.out::println); 이 코드 추가시 아래 컴파일 오류 발생
        //java.lang.IllegalStateException: stream has already been operated upon or closed
    }
}
