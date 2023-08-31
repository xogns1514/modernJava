package org.chap05;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BuildingStreams {
    public static void main(String[] args) {
        //Stream.of
        System.out.println("Stream.of(): ");
        Stream<String> stream = Stream.of("Modern", "Java", "In", "Action"); //스트림 생성
        stream.map(String::toUpperCase)
                .forEach(System.out::println);

        Stream<String> emptyStream = Stream.empty(); //빈 스트림 생성
        System.out.println("----------------------------------------------------------------");

        String homeValue = System.getProperty("user.home");
        Stream<String> homeValueStream =
                homeValue == null? Stream.empty() : Stream.of(homeValue);

        System.out.println("Stream.ofNullable(): ");
        Stream<String> homeValueStream1
                = Stream.ofNullable(System.getProperty("user.home"));
        System.out.println(homeValueStream1.collect(Collectors.toList()));

        Stream<String> values =
                Stream.of("config", "home", "user")
                        .flatMap(key -> Stream.ofNullable(System.getProperty(key)));
        System.out.println("----------------------------------------------------------------");

        System.out.println("Arrays.stream(): ");
        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum(); //합계는 41
        System.out.println(sum);
        System.out.println("----------------------------------------------------------------");

        System.out.println("IO streams: ");
        long uniqueWords = 0;
        try (Stream<String> lines =
                     Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        } catch (IOException e) {
            //파일을 열다가 예외가 발생하면 처리한다.
        }
        System.out.println("----------------------------------------------------------------");

        System.out.println("Stream.iterate: ");
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

        System.out.println("Stream.iterate with predicate: ");
        IntStream.iterate(0, n -> n < 100, n -> n + 4)
                .forEach(System.out::println);
//
//        System.out.println("Stream.iterate with filter: ");
//        IntStream.iterate(0, n -> n + 4)
//                .filter(n -> n < 100)
//                .forEach(System.out::println);

        System.out.println("Stream.iterate with takeWhile: ");
        IntStream.iterate(0, n -> n + 4)
                .takeWhile(n -> n < 100)
                .forEach(System.out::println);

        System.out.println("Stream.generate: ");
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

        //lambda를 전달한 generate
        IntStream ones = IntStream.generate(() -> 1);
        //getAsInt를 구현하는 객체를 명시적으로 전달한 generate
        IntStream twos = IntStream.generate(new IntSupplier() {
            @Override
            public int getAsInt() {
                return 2;
            }
        });

        //피보나치 커스텀
        IntSupplier fib = new IntSupplier() {

            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int nextValue = previous + current;
                previous = current;
                current = nextValue;
                return previous;
            }

        };
        System.out.println("custom IntSupplier to fib:");
        IntStream.generate(fib).limit(10).forEach(System.out::println);

        //iterate를 이용한 피보나치
        System.out.println("fib with iterate:");
        Stream.iterate(new int[] { 0, 1 }, t -> new int[] { t[1], t[0] + t[1] })
                .limit(10)
                .map(t -> t[0])
                .forEach(System.out::println);
    }
}
