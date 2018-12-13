# Logging performance


## LOG4J
```
Benchmark                                               Score Units
DisabledLoggingBenchmark.benchmarkPlaceHolders          8.944 ns/op
DisabledLoggingBenchmark.benchmarkStringCat           357.057 ns/op
DisabledLoggingBenchmark.benchmarkStringCatToString   359.773 ns/op
EnabledLoggingBenchmark.benchmarkPlaceHolders        8740.280 ns/op
EnabledLoggingBenchmark.benchmarkStringCat           8120.231 ns/op
EnabledLoggingBenchmark.benchmarkStringCatToString   8898.458 ns/op
```

## JUL
```
Benchmark                                               Score Units
DisabledLoggingBenchmark.benchmarkPlaceHolders          3.892 ns/op
DisabledLoggingBenchmark.benchmarkStringCat           349.113 ns/op
DisabledLoggingBenchmark.benchmarkStringCatToString   341.646 ns/op
EnabledLoggingBenchmark.benchmarkPlaceHolders       79930.225 ns/op
EnabledLoggingBenchmark.benchmarkStringCat          78698.360 ns/op
EnabledLoggingBenchmark.benchmarkStringCatToString  79292.005 ns/op
```

## LOGBACK
```
Benchmark                                               Score Units
DisabledLoggingBenchmark.benchmarkPlaceHolders          5.376 ns/op
DisabledLoggingBenchmark.benchmarkStringCat           347.354 ns/op
DisabledLoggingBenchmark.benchmarkStringCatToString   340.525 ns/op
EnabledLoggingBenchmark.benchmarkPlaceHolders        3153.081 ns/op
EnabledLoggingBenchmark.benchmarkStringCat           2924.083 ns/op
EnabledLoggingBenchmark.benchmarkStringCatToString   3042.592 ns/op
```