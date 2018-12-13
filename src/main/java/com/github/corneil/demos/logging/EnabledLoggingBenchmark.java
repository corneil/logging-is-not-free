package com.github.corneil.demos.logging;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
public class EnabledLoggingBenchmark {

    @State(Scope.Thread)
    public static class ExecutionPlan extends BaseExecutionPlan {
        public ExecutionPlan() {
            super(true);
        }

        @Setup(Level.Trial)
        @Override
        public void setUp() {
            super.setUp();
        }
    }

    @Benchmark
    public void benchmarkToString(ExecutionPlan plan, Blackhole blackhole) {
        blackhole.consume(plan.getPojo().toString());
    }

    @Benchmark
    public void benchmarkPlaceHoldersNoExt(ExecutionPlan plan) {
        ExampleLogging.logWithPlaceHoldersNoExt(plan.getPojo());
    }

    @Benchmark
    public void benchmarkPlaceHolders(ExecutionPlan plan) {
        ExampleLogging.logWithPlaceHolders(plan.getPojo());
    }

    @Benchmark
    public void benchmarkPlaceHolderToString(ExecutionPlan plan) {
        ExampleLogging.logWithPlaceHolderToString(plan.getPojo());
    }
    @Benchmark
    public void benchmarkStringCat(ExecutionPlan plan) {
        ExampleLogging.logWithStringCat(plan.getPojo());
    }

    @Benchmark
    public void benchmarkStringCatToString(ExecutionPlan plan) {
        ExampleLogging.logWithStringCatToString(plan.getPojo());
    }
}
