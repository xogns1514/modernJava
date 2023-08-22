package org.chap02;

public class MeaningOfThis {
    public final int value = 4;

    public void doIt() {
        int value = 6;
        Runnable r = new Runnable() {
            public final int value = 7;

            @Override
            public void run() {
                int value = 10;
                System.out.println(this.value);
            }
        };
        r.run();
    }

    public static void main(String[] args) {
        MeaningOfThis m = new MeaningOfThis();
        m.doIt(); //7
        //익명 클래스에서 this가 가르키는 것은 해당 익명 클래스 내부의 멤버 변수를 참조한다.
        //익명 클래스 안에 선언된 변수는 익명 클래스 내부의 스코프에서만 유효
    }
}
