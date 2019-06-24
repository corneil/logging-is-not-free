# Logging performance

## The Code
```java
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

public class ExampleLogging {
    private static final XLogger LOGGER = XLoggerFactory.getXLogger(ExampleLogging.class);
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

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
    public static void logFluentWithParam(final SimplePOJO pojo) {
        logger.atInfo().log("logFluentWithParam:%s", pojo);
    }
    
    public static void logFluentWithLazy(final SimplePOJO pojo) {
        logger.atInfo().log("logFluentWithLazy:%s", lazy(() -> pojo.toString()));
    }
}
```

```kotlin
import mu.KotlinLogging

class KExampleLogging {
    companion object {
        private val logger = KotlinLogging.logger {}

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
| `logBlank`                    |     1.1ns |
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

Fluent Logger does well with JUL logging but not with Logback or Log4J.
```
Benchmark:JUL
Benchmark                                                 Mode  Cnt       Score       Error  Units
DisabledLoggingBenchmark.benchmarkAToString               avgt    5     273.845 ±     8.728  ns/op
DisabledLoggingBenchmark.benchmarkFluentWithLazy          avgt    5       2.100 ±     0.156  ns/op
DisabledLoggingBenchmark.benchmarkFluentWithParam         avgt    5       2.088 ±     0.089  ns/op
DisabledLoggingBenchmark.benchmarkJavaBlank               avgt    5       1.015 ±     0.076  ns/op
DisabledLoggingBenchmark.benchmarkKLogging                avgt    5       1.857 ±     0.248  ns/op
DisabledLoggingBenchmark.benchmarkKLoggingCheck           avgt    5       1.749 ±     0.030  ns/op
DisabledLoggingBenchmark.benchmarkKLoggingExt             avgt    5       3.410 ±     0.237  ns/op
DisabledLoggingBenchmark.benchmarkKotlinBlank             avgt    5       1.129 ±     0.028  ns/op
DisabledLoggingBenchmark.benchmarkPlaceHolderToString     avgt    5     282.308 ±    16.588  ns/op
DisabledLoggingBenchmark.benchmarkPlaceHolders            avgt    5       3.711 ±     0.111  ns/op
DisabledLoggingBenchmark.benchmarkPlaceHoldersNoExt       avgt    5       1.729 ±     0.077  ns/op
DisabledLoggingBenchmark.benchmarkPlaceHoldersNoExtCheck  avgt    5       1.739 ±     0.093  ns/op
DisabledLoggingBenchmark.benchmarkStringCat               avgt    5     339.272 ±    16.245  ns/op
DisabledLoggingBenchmark.benchmarkStringCatToString       avgt    5     340.291 ±     6.745  ns/op
EnabledLoggingBenchmark.benchmarkAToString                avgt    5     279.436 ±     5.844  ns/op
EnabledLoggingBenchmark.benchmarkFluentWithLazy           avgt    5   28640.656 ±  7487.046  ns/op
EnabledLoggingBenchmark.benchmarkFluentWithParam          avgt    5   27996.848 ±  9082.948  ns/op
EnabledLoggingBenchmark.benchmarkJavaBlank                avgt    5       1.010 ±     0.009  ns/op
EnabledLoggingBenchmark.benchmarkKLogging                 avgt    5   40403.924 ±  2366.955  ns/op
EnabledLoggingBenchmark.benchmarkKLoggingCheck            avgt    5   41189.540 ± 13193.372  ns/op
EnabledLoggingBenchmark.benchmarkKLoggingExt              avgt    5  118765.296 ±  4353.175  ns/op
EnabledLoggingBenchmark.benchmarkKotlinBlank              avgt    5       1.144 ±     0.028  ns/op
EnabledLoggingBenchmark.benchmarkPlaceHolderToString      avgt    5  113550.900 ±  4936.266  ns/op
EnabledLoggingBenchmark.benchmarkPlaceHolders             avgt    5  115116.286 ±  3589.187  ns/op
EnabledLoggingBenchmark.benchmarkPlaceHoldersNoExt        avgt    5   39396.534 ± 12580.308  ns/op
EnabledLoggingBenchmark.benchmarkPlaceHoldersNoExtCheck   avgt    5       1.737 ±     0.099  ns/op
EnabledLoggingBenchmark.benchmarkStringCat                avgt    5  114439.182 ± 37428.181  ns/op
EnabledLoggingBenchmark.benchmarkStringCatToString        avgt    5  111569.280 ±  2301.574  ns/op
```

## Benchmark:LOG4J
```
Benchmark                                                 Mode  Cnt       Score       Error  Units
DisabledLoggingBenchmark.benchmarkAToString               avgt    5     281.114 ±    13.384  ns/op
DisabledLoggingBenchmark.benchmarkFluentWithLazy          avgt    5  274663.125 ± 23050.208  ns/op
DisabledLoggingBenchmark.benchmarkFluentWithParam         avgt    5  273585.349 ± 15421.686  ns/op
DisabledLoggingBenchmark.benchmarkJavaBlank               avgt    5       1.017 ±     0.030  ns/op
DisabledLoggingBenchmark.benchmarkKLogging                avgt    5       3.436 ±     0.166  ns/op
DisabledLoggingBenchmark.benchmarkKLoggingCheck           avgt    5       3.406 ±     0.107  ns/op
DisabledLoggingBenchmark.benchmarkKLoggingExt             avgt    5       9.371 ±     0.275  ns/op
DisabledLoggingBenchmark.benchmarkKotlinBlank             avgt    5       1.140 ±     0.049  ns/op
DisabledLoggingBenchmark.benchmarkPlaceHolderToString     avgt    5     287.269 ±    10.272  ns/op
DisabledLoggingBenchmark.benchmarkPlaceHolders            avgt    5       8.890 ±     0.347  ns/op
DisabledLoggingBenchmark.benchmarkPlaceHoldersNoExt       avgt    5       3.277 ±     0.141  ns/op
DisabledLoggingBenchmark.benchmarkPlaceHoldersNoExtCheck  avgt    5       3.273 ±     0.116  ns/op
DisabledLoggingBenchmark.benchmarkStringCat               avgt    5     359.720 ±    12.316  ns/op
DisabledLoggingBenchmark.benchmarkStringCatToString       avgt    5     340.073 ±    17.822  ns/op
EnabledLoggingBenchmark.benchmarkAToString                avgt    5     276.292 ±    12.642  ns/op
EnabledLoggingBenchmark.benchmarkFluentWithLazy           avgt    5  265862.096 ± 28442.800  ns/op
EnabledLoggingBenchmark.benchmarkFluentWithParam          avgt    5  269967.496 ± 22733.279  ns/op
EnabledLoggingBenchmark.benchmarkJavaBlank                avgt    5       1.013 ±     0.028  ns/op
EnabledLoggingBenchmark.benchmarkKLogging                 avgt    5   11673.157 ±  1038.203  ns/op
EnabledLoggingBenchmark.benchmarkKLoggingCheck            avgt    5   11842.847 ±  1117.611  ns/op
EnabledLoggingBenchmark.benchmarkKLoggingExt              avgt    5   33432.163 ±  2241.999  ns/op
EnabledLoggingBenchmark.benchmarkKotlinBlank              avgt    5       1.140 ±     0.038  ns/op
EnabledLoggingBenchmark.benchmarkPlaceHolderToString      avgt    5   34342.201 ±  1704.447  ns/op
EnabledLoggingBenchmark.benchmarkPlaceHolders             avgt    5   34565.342 ±  3464.779  ns/op
EnabledLoggingBenchmark.benchmarkPlaceHoldersNoExt        avgt    5   12753.840 ±  2277.417  ns/op
EnabledLoggingBenchmark.benchmarkPlaceHoldersNoExtCheck   avgt    5       3.439 ±     0.197  ns/op
EnabledLoggingBenchmark.benchmarkStringCat                avgt    5   34513.800 ±  6114.834  ns/op
EnabledLoggingBenchmark.benchmarkStringCatToString        avgt    5   34284.982 ±  6422.507  ns/op
```

## Benchmark:LOGBACK
```
Benchmark                                                 Mode  Cnt       Score         Error  Units
DisabledLoggingBenchmark.benchmarkAToString               avgt    5     292.400 ±      40.141  ns/op
DisabledLoggingBenchmark.benchmarkFluentWithLazy          avgt    5  299102.769 ±  104661.032  ns/op
DisabledLoggingBenchmark.benchmarkFluentWithParam         avgt    5  301647.339 ±   59016.826  ns/op
DisabledLoggingBenchmark.benchmarkJavaBlank               avgt    5       1.045 ±       0.063  ns/op
DisabledLoggingBenchmark.benchmarkKLogging                avgt    5       2.433 ±       0.515  ns/op
DisabledLoggingBenchmark.benchmarkKLoggingCheck           avgt    5       2.525 ±       1.084  ns/op
DisabledLoggingBenchmark.benchmarkKLoggingExt             avgt    5       8.799 ±       5.929  ns/op
DisabledLoggingBenchmark.benchmarkKotlinBlank             avgt    5       1.148 ±       0.035  ns/op
DisabledLoggingBenchmark.benchmarkPlaceHolderToString     avgt    5     278.206 ±      22.609  ns/op
DisabledLoggingBenchmark.benchmarkPlaceHolders            avgt    5       5.382 ±       0.664  ns/op
DisabledLoggingBenchmark.benchmarkPlaceHoldersNoExt       avgt    5       2.227 ±       0.151  ns/op
DisabledLoggingBenchmark.benchmarkPlaceHoldersNoExtCheck  avgt    5       2.588 ±       1.608  ns/op
DisabledLoggingBenchmark.benchmarkStringCat               avgt    5    2005.608 ±   10488.719  ns/op
DisabledLoggingBenchmark.benchmarkStringCatToString       avgt    5    1325.954 ±    5736.656  ns/op
EnabledLoggingBenchmark.benchmarkAToString                avgt    5     282.523 ±      11.025  ns/op
EnabledLoggingBenchmark.benchmarkFluentWithLazy           avgt    5  536439.774 ± 1869430.479  ns/op
EnabledLoggingBenchmark.benchmarkFluentWithParam          avgt    5  542063.579 ± 1608912.124  ns/op
EnabledLoggingBenchmark.benchmarkJavaBlank                avgt    5       1.761 ±       3.263  ns/op
EnabledLoggingBenchmark.benchmarkKLogging                 avgt    5   12136.299 ±    4679.513  ns/op
EnabledLoggingBenchmark.benchmarkKLoggingCheck            avgt    5   12456.234 ±    4792.358  ns/op
EnabledLoggingBenchmark.benchmarkKLoggingExt              avgt    5   34093.586 ±    6288.261  ns/op
EnabledLoggingBenchmark.benchmarkKotlinBlank              avgt    5       1.153 ±       0.016  ns/op
EnabledLoggingBenchmark.benchmarkPlaceHolderToString      avgt    5   34277.222 ±    2103.719  ns/op
EnabledLoggingBenchmark.benchmarkPlaceHolders             avgt    5   34042.421 ±    1351.917  ns/op
EnabledLoggingBenchmark.benchmarkPlaceHoldersNoExt        avgt    5   12321.277 ±    5673.173  ns/op
EnabledLoggingBenchmark.benchmarkPlaceHoldersNoExtCheck   avgt    5       2.183 ±       0.081  ns/op
EnabledLoggingBenchmark.benchmarkStringCat                avgt    5   33863.443 ±    1056.171  ns/op
EnabledLoggingBenchmark.benchmarkStringCatToString        avgt    5   34109.164 ±    1694.669  ns/op
```
