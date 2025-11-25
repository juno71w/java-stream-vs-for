package src;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
public class IntegerBenchmark {

    @Param({"100", "10000", "100000"})
    int size;

    List<Integer> boxedList;
    int[] primitiveArray;

    @Setup(Level.Iteration)
    public void setup() {
        boxedList = new ArrayList<>();
        primitiveArray = new int[size];
        Random r = new Random();

        for (int i = 0; i < size; i++) {
            int val = r.nextInt();
            boxedList.add(val);
            primitiveArray[i] = val;
        }
    }

    @Benchmark
    public int boxedForLoop() {
        int sum = 0;
        for (Integer v : boxedList) {
            if (v % 7 == 0) continue;
            sum += (v * 13) / 23;
        }
        return sum;
    }

    @Benchmark
    public int boxedStream() {
        return boxedList.stream()
                .filter(v -> v % 7 != 0)
                .map(v -> v * 13)
                .map(v -> v / 23)
                .reduce(0, Integer::sum);
    }

    @Benchmark
    public int primitiveForLoop() {
        int sum = 0;
        for (int v : primitiveArray) {
            if (v % 7 == 0) continue;
            sum += (v * 13) / 23;
        }
        return sum;
    }

    @Benchmark
    public int primitiveStream() {
        return Arrays.stream(primitiveArray)
                .filter(v -> v % 7 != 0)
                .map(v -> v * 13)
                .map(v -> v / 23)
                .sum();
    }
}