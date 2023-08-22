package org.chap01;

public class FilteringApples {
    public static void main(String[] args) {
    }
    public static class Apple {
        private int weight;

        private String color = "";

        public Apple(int weight, String color) {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        @SuppressWarnings("boxing") //컴파일시 컴파일 경고를 사용하지 않는다.
        @Override
        public String toString() {
            return String.format("Apple{weight=%d, color='%s'}", weight, color);
        }
    }
}
