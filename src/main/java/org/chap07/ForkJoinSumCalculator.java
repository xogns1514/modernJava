package org.chap07;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

import static org.chap07.ParallelStreamsHarness.FORK_JOIN_POOL;

public class ForkJoinSumCalculator extends RecursiveTask<Long> { //RecursiveTask를 상속받아 포크/조인 프레임워크에서 사용할 태스크

    public static final long THRESHOLD = 10_000; // 이 값 이하의 서브태스크는 더 이상 분할 할 수 없다.
    private final long[] numbers;
    private final int start;
    private final int end;

    public ForkJoinSumCalculator(long[] numbers) { // 메인 태스크를 생성할 때 사용할 공개 생성자
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculator(long[] numbers, int start, int end) { // 메인 태스크의 서브태스크를 재귀적으로 만들 때 사용할 비공개 생성자
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            return computeSequentially(); // 기준값과 같거나 작으면 순차적으로 결과를 계산한다.
        }
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        leftTask.fork();
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, end + length / 2, end);
        Long rightResult = rightTask.compute(); // 두 번째 서브태스크를 동기 실행한다. 추가로 분할 가능
        Long leftResult = leftTask.join(); // 첫 번째 서브태스크의 결과를 읽거나 아직 결과가 없으면 기다린다.
        return leftResult + rightResult; // 두 서브태스크의 결과를 조합한 값이 이 태스크의 결과다.
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return FORK_JOIN_POOL.invoke(task);
    }
}
