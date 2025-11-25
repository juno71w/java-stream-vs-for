import org.openjdk.jmh.annotations.*;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
public class StringBenchmark {

    private final StringBuilder sb = new StringBuilder();
    private final int STRING_APPEND_COUNT = 100;
    private final String TARGET = "Hello, World! ";
    @Benchmark
    public void test1() {
        String string = "";
        for (int i = 0; i < STRING_APPEND_COUNT; i++) {
            string += TARGET;
        }
    }

    @Benchmark
    public void test2() {

        for (int i = 0; i < STRING_APPEND_COUNT; i++) {
            sb.append(TARGET);
        }
    }
}
