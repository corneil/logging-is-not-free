package com.github.corneil.demos.logging.jmh;

import com.github.corneil.demos.logging.ExampleLogging;
import com.github.corneil.demos.logging.KExampleLogging;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
public class DisabledLoggingBenchmark {
    @State(Scope.Thread)
    public static class ExecutionPlan extends BaseExecutionPlan {
        public ExecutionPlan() {
            super(false);
        }

        @Setup(Level.Trial)
        @Override
        public void setUp() {
            super.setUp();
        }
    }

    @Benchmark
    public void benchmarkAToString(ExecutionPlan plan, Blackhole blackhole) {
        blackhole.consume(plan.getPojo().toString());
    }

    @Benchmark
    public void benchmarkPlaceHolders(ExecutionPlan plan) {
        ExampleLogging.logWithPlaceHolders(plan.getPojo());
    }

    @Benchmark
    public void benchmarkFluentWithParam(EnabledLoggingBenchmark.ExecutionPlan plan) {
        ExampleLogging.logFluentWithParam(plan.getPojo());
    }
    @Benchmark
    public void benchmarkFluentWithLazy(EnabledLoggingBenchmark.ExecutionPlan plan) {
        ExampleLogging.logFluentWithLazy(plan.getPojo());
    }

    @Benchmark
    public void benchmarkPlaceHoldersNoExtCheck(ExecutionPlan plan) {
        ExampleLogging.logWithPlaceHoldersNoExtCheck(plan.getPojo());
    }

    @Benchmark
    public void benchmarkPlaceHoldersNoExt(ExecutionPlan plan) {
        ExampleLogging.logWithPlaceHoldersNoExt(plan.getPojo());
    }

    @Benchmark
    public void benchmarkStringCat(ExecutionPlan plan) {
        ExampleLogging.logWithStringCat(plan.getPojo());
    }

    @Benchmark
    public void benchmarkStringCatToString(ExecutionPlan plan) {
        ExampleLogging.logWithStringCatToString(plan.getPojo());
    }

    @Benchmark
    public void benchmarkPlaceHolderToString(ExecutionPlan plan) {
        ExampleLogging.logWithPlaceHolderToString(plan.getPojo());
    }

    @Benchmark
    public void benchmarkKLogging(ExecutionPlan plan) {
        KExampleLogging.logWithPlaceHoldersNoExtInterpolation(plan.getPojo());
    }

    @Benchmark
    public void benchmarkKLoggingCheck(ExecutionPlan plan) {
        KExampleLogging.logWithPlaceHoldersNoExtInterpolationCheck(plan.getPojo());
    }

    @Benchmark
    public void benchmarkKLoggingExt(ExecutionPlan plan) {
        KExampleLogging.logWithPlaceHoldersExtInterpolation(plan.getPojo());
    }
}
