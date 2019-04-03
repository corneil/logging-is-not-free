package com.github.corneil.demos.logging;

import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

public class LoggingBenchmarkRunner {
    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder()
                .forks(1)
                .threads(1)
                .warmupTime(TimeValue.seconds(1))
                .warmupIterations(3)
                .measurementTime(TimeValue.seconds(2))
                .measurementIterations(5)
                .resultFormat(ResultFormatType.CSV)
                .detectJvmArgs()
                .build();
        new Runner(options).run();
    }
}
