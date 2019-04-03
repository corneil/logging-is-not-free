# Logging performance

## The Code
```java
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

public class ExampleLogging {
    public static final XLogger LOGGER = XLoggerFactory.getXLogger(ExampleLogging.class);

    public static void logWithPlaceHoldersNoExt(SimplePOJO pojo) {
        LOGGER.info(logWithPlaceHoldersNoExt, pojo);
    }
    
    public static void logWithPlaceHolders(SimplePOJO pojo) {
        LOGGER.entry(pojo);
        LOGGER.info(logWithPlaceHoldersNoExt, pojo);
        LOGGER.exit();
    }
    
    public static void logWithPlaceHolderToString(SimplePOJO pojo) {
        LOGGER.entry(pojo);
        LOGGER.info("logWithPlaceHolderToString:{}", pojo.toString());
        LOGGER.exit();
    }
    
    public static void logWithStringCat(SimplePOJO pojo) {
        LOGGER.entry(pojo);
        LOGGER.info("logWithStringCat:" + pojo);
        LOGGER.exit();
    }

    public static void logWithStringCatToString(SimplePOJO pojo) {
        LOGGER.entry(pojo);
        LOGGER.info("logWithStringCatToString:" + pojo.toString());
        LOGGER.exit();
    }
}
```
```kotlin
import mu.KotlinLogging

class KExampleLogging {
    companion object {
        val logger = KotlinLogging.logger {}

        @JvmStatic
        fun logWithPlaceHoldersNoExtInterpolation(pojo: SimplePOJO) {
            logger.info { "logWithPlaceHoldersNoExtInterpolation:$pojo" }
        }

        @JvmStatic
        fun logWithPlaceHoldersNoExtInterpolationCheck(pojo: SimplePOJO) {
            if (logger.isInfoEnabled) {
                logger.info { "logWithPlaceHoldersNoExtInterpolationCheck:$pojo" }
            }
        }

        @JvmStatic
        fun logWithPlaceHoldersExtInterpolation(pojo: SimplePOJO) {
            logger.entry(pojo)
            logger.info { "logWithPlaceHoldersExtInterpolation:$pojo" }
            logger.exit()
        }
    }
}
```
## The Performance

### With loglevel disabled with Logback

|         **Method**            | **Time**  |
|-------------------------------|----------:|
| `KLogging`                    |     2.1ns |
| `logWithPlaceHoldersNoExt`    |     1.9ns |
| `logWithPlaceHoldersNoExtChk` |     2.0ns |
| `logWithPlaceHolders`         |     4.4ns |
| `logWithPlaceHolderToString`  |   320.0ns |
| `logWithStringCat`            |   397.0ns |
| `logWithStringCatToString`    |   388.0ns |
| `toString`                    |   319.0ns |

Other loggers provide similar performance when logging is disabled.

If someone can explain `EnabledLoggingBenchmark.benchmarkPlaceHoldersNoExtCheck` I would appreciate it very much. 

When logging is enable `java.util.logging` compares poorly since it doesn't provide a buffered appender.
```
Benchmark:JUL
Benchmark                                                 Mode  Cnt       Score      Error  Units
DisabledLoggingBenchmark.benchmarkAToString               avgt    5     319.085 ±   32.688  ns/op
DisabledLoggingBenchmark.benchmarkKLogging                avgt    5       2.121 ±    0.193  ns/op
DisabledLoggingBenchmark.benchmarkKLoggingCheck           avgt    5       2.187 ±    0.131  ns/op
DisabledLoggingBenchmark.benchmarkKLoggingExt             avgt    5       5.063 ±    0.322  ns/op
DisabledLoggingBenchmark.benchmarkPlaceHolderToString     avgt    5     320.622 ±   47.494  ns/op
DisabledLoggingBenchmark.benchmarkPlaceHolders            avgt    5       4.460 ±    0.128  ns/op
DisabledLoggingBenchmark.benchmarkPlaceHoldersNoExt       avgt    5       1.868 ±    0.084  ns/op
DisabledLoggingBenchmark.benchmarkPlaceHoldersNoExtCheck  avgt    5       1.967 ±    0.147  ns/op
DisabledLoggingBenchmark.benchmarkStringCat               avgt    5     397.333 ±   33.307  ns/op
DisabledLoggingBenchmark.benchmarkStringCatToString       avgt    5     388.336 ±   32.731  ns/op
EnabledLoggingBenchmark.benchmarkAToString                avgt    5     307.426 ±   28.002  ns/op
EnabledLoggingBenchmark.benchmarkKLogging                 avgt    5   38579.311 ± 4080.852  ns/op
EnabledLoggingBenchmark.benchmarkKLoggingCheck            avgt    5   38210.786 ± 2239.134  ns/op
EnabledLoggingBenchmark.benchmarkKLoggingExt              avgt    5  109078.512 ± 7366.749  ns/op
EnabledLoggingBenchmark.benchmarkPlaceHolderToString      avgt    5  107003.951 ± 3363.055  ns/op
EnabledLoggingBenchmark.benchmarkPlaceHolders             avgt    5  104548.334 ± 5305.424  ns/op
EnabledLoggingBenchmark.benchmarkPlaceHoldersNoExt        avgt    5   36536.956 ± 4121.395  ns/op
EnabledLoggingBenchmark.benchmarkPlaceHoldersNoExtCheck   avgt    5       1.895 ±    0.124  ns/op
EnabledLoggingBenchmark.benchmarkStringCat                avgt    5  104372.691 ± 9802.667  ns/op
EnabledLoggingBenchmark.benchmarkStringCatToString        avgt    5  105514.626 ± 2145.087  ns/op

Benchmark:LOG4J
Benchmark                                                 Mode  Cnt     Score     Error  Units
DisabledLoggingBenchmark.benchmarkAToString               avgt    5   297.967 ±   9.668  ns/op
DisabledLoggingBenchmark.benchmarkKLogging                avgt    5     3.810 ±   0.621  ns/op
DisabledLoggingBenchmark.benchmarkKLoggingCheck           avgt    5     3.659 ±   0.177  ns/op
DisabledLoggingBenchmark.benchmarkKLoggingExt             avgt    5     8.622 ±   0.250  ns/op
DisabledLoggingBenchmark.benchmarkPlaceHolderToString     avgt    5   307.602 ±   9.506  ns/op
DisabledLoggingBenchmark.benchmarkPlaceHolders            avgt    5     9.291 ±   0.534  ns/op
DisabledLoggingBenchmark.benchmarkPlaceHoldersNoExt       avgt    5     3.379 ±   0.317  ns/op
DisabledLoggingBenchmark.benchmarkPlaceHoldersNoExtCheck  avgt    5     3.248 ±   0.034  ns/op
DisabledLoggingBenchmark.benchmarkStringCat               avgt    5   395.379 ±   8.184  ns/op
DisabledLoggingBenchmark.benchmarkStringCatToString       avgt    5   386.691 ±   6.687  ns/op
EnabledLoggingBenchmark.benchmarkAToString                avgt    5   296.692 ±  37.388  ns/op
EnabledLoggingBenchmark.benchmarkKLogging                 avgt    5  3022.067 ± 225.548  ns/op
EnabledLoggingBenchmark.benchmarkKLoggingCheck            avgt    5  3104.794 ± 254.765  ns/op
EnabledLoggingBenchmark.benchmarkKLoggingExt              avgt    5  8507.866 ± 475.519  ns/op
EnabledLoggingBenchmark.benchmarkPlaceHolderToString      avgt    5  8598.615 ± 930.937  ns/op
EnabledLoggingBenchmark.benchmarkPlaceHolders             avgt    5  8563.165 ± 655.816  ns/op
EnabledLoggingBenchmark.benchmarkPlaceHoldersNoExt        avgt    5  3044.071 ±  92.754  ns/op
EnabledLoggingBenchmark.benchmarkPlaceHoldersNoExtCheck   avgt    5     3.311 ±   0.128  ns/op
EnabledLoggingBenchmark.benchmarkStringCat                avgt    5  8346.345 ± 465.123  ns/op
EnabledLoggingBenchmark.benchmarkStringCatToString        avgt    5  8426.884 ± 749.011  ns/op

Benchmark:LOGBACK
Benchmark                                                 Mode  Cnt     Score      Error  Units
DisabledLoggingBenchmark.benchmarkAToString               avgt    5   298.257 ±   15.041  ns/op
DisabledLoggingBenchmark.benchmarkKLogging                avgt    5     2.057 ±    0.019  ns/op
DisabledLoggingBenchmark.benchmarkKLoggingCheck           avgt    5     2.080 ±    0.020  ns/op
DisabledLoggingBenchmark.benchmarkKLoggingExt             avgt    5     4.548 ±    0.082  ns/op
DisabledLoggingBenchmark.benchmarkPlaceHolderToString     avgt    5   290.470 ±    5.450  ns/op
DisabledLoggingBenchmark.benchmarkPlaceHolders            avgt    5     4.899 ±    0.039  ns/op
DisabledLoggingBenchmark.benchmarkPlaceHoldersNoExt       avgt    5     2.102 ±    0.030  ns/op
DisabledLoggingBenchmark.benchmarkPlaceHoldersNoExtCheck  avgt    5     2.154 ±    0.081  ns/op
DisabledLoggingBenchmark.benchmarkStringCat               avgt    5   414.623 ±   23.907  ns/op
DisabledLoggingBenchmark.benchmarkStringCatToString       avgt    5   381.193 ±   12.581  ns/op
EnabledLoggingBenchmark.benchmarkAToString                avgt    5   303.537 ±   62.276  ns/op
EnabledLoggingBenchmark.benchmarkKLogging                 avgt    5  3185.870 ±  257.823  ns/op
EnabledLoggingBenchmark.benchmarkKLoggingCheck            avgt    5  3220.492 ±  190.128  ns/op
EnabledLoggingBenchmark.benchmarkKLoggingExt              avgt    5  9133.704 ±  465.849  ns/op
EnabledLoggingBenchmark.benchmarkPlaceHolderToString      avgt    5  9697.108 ± 1286.529  ns/op
EnabledLoggingBenchmark.benchmarkPlaceHolders             avgt    5  9370.804 ±  452.537  ns/op
EnabledLoggingBenchmark.benchmarkPlaceHoldersNoExt        avgt    5  3383.473 ±  132.233  ns/op
EnabledLoggingBenchmark.benchmarkPlaceHoldersNoExtCheck   avgt    5     2.113 ±    0.078  ns/op
EnabledLoggingBenchmark.benchmarkStringCat                avgt    5  9298.294 ±  505.990  ns/op
EnabledLoggingBenchmark.benchmarkStringCatToString        avgt    5  9520.823 ±  954.227  ns/op
```