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
                .warmupTime(TimeValue.seconds(5))
                .measurementTime(TimeValue.seconds(10))
                .resultFormat(ResultFormatType.CSV)
                .detectJvmArgs()
                .build();
        new Runner(options).run();
    }
}
