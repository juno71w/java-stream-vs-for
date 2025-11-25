package src;

import org.openjdk.jmh.annotations.*;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
public class UUIDBenchmark {

    @Param({"100", "10000", "100000"})
    int size;

    List<UUID> data;

    @Setup(Level.Iteration)
    public void setup() {
        data = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            data.add(UUID.randomUUID());
        }
    }

    @Benchmark
    public List<String> forLoop() {
        List<String> result = new ArrayList<>();
        for (UUID uuid : data) {
            if (uuid.hashCode() % 7 == 0) continue;

            String[] parts = uuid.toString().split("-");
            result.add(parts[2]);
        }
        return result;
    }

    @Benchmark
    public List<String> streamSequential() {
        return data.stream()
                .filter(uuid -> uuid.hashCode() % 7 != 0)
                .map(UUID::toString)
                .map(s -> s.split("-")[2])
                .toList();
    }

    @Benchmark
    public List<String> streamParallel() {
        return data.parallelStream()
                .filter(uuid -> uuid.hashCode() % 7 != 0)
                .map(UUID::toString)
                .map(s -> s.split("-")[2])
                .toList();
    }
}