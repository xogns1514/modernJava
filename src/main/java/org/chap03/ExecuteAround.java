package org.chap03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteAround {
    private static final String FILE = ExecuteAround.class.getResource("/data.txt").getFile();

    public static void main(String[] args) throws IOException {
        String result = processFileLimited();
        System.out.println(result); //JAVA

        System.out.println("---");

        //람다로 다양한 동작을 전달할 수 있다. 더 유연하게 사용가능하다
        String oneLine = processFile((BufferedReader b) -> b.readLine());
        System.out.println(oneLine); //JAVA

        String twoLines = processFile((BufferedReader b) -> b.readLine() + b.readLine());
        System.out.println(twoLines); //JAVA8

    }

    //FILE에서 한줄을 읽어오는 함수이다.
    public static String processFileLimited() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            return  br.readLine();
        }
    }

    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            return p.process(br);
        }
    }

    public interface BufferedReaderProcessor {
        String process(BufferedReader b) throws IOException;
    }
}
