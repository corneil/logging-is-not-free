# Logging performance

## The Code
```java
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

public class ExampleLogging {
    public static final XLogger LOGGER = XLoggerFactory.getXLogger(ExampleLogging.class);

    public static void logWithPlaceHoldersNoExt(SimplePOJO pojo) {
        LOGGER.info("logWithPlaceHolders:{}", pojo);
    }
    
    public static void logWithPlaceHolders(SimplePOJO pojo) {
        LOGGER.entry(pojo);
        LOGGER.info("logWithPlaceHolders:{}", pojo);
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
## The Performance

### With loglevel disabled with Logback

|         **Method**           | **Time**  |
|------------------------------|----------:|
| `logWithPlaceHoldersNoExt`   |       2ns |
| `logWithPlaceHolders`        |       5ns |
| _logWithPlaceHolderToString_ | 3&micro;s |
| `logWithStringCat`           |     330ns |
| `logWithStringCatToString`   |     335ns |
| `toString`                   |     280ns |

Other loggers provide similar performance when logging is disabled.

**_Cannot explain logWithPlaceHolderToString. Would have expected in same range as_** `logWithStringCatToString` 

### With loglevel enabled with Logback

|          **Method**          |  **Time**   |
|------------------------------|------------:|
| `logWithPlaceHoldersNoExt`   |   3&micro;s |
| `logWithPlaceHolders`        |   3&micro;s |
| `logWithPlaceHolderToString` |   3&micro;s |
| `logWithStringCat`           | 2.8&micro;s |
| `logWithStringCatToString`   | 2.8&micro;s |

### With loglevel enabled with Log4J

|          **Method**          |  **Time**   |
|------------------------------|------------:|
| `logWithPlaceHoldersNoExt`   |   3&micro;s |
| `logWithPlaceHolders`        | 7.8&micro;s |
| `logWithPlaceHolderToString` |   8&micro;s |
| `logWithStringCat`           | 9.5&micro;s |
| `logWithStringCatToString`   |  10&micro;s |


### With loglevel enabled with JUL

|          **Method**          |  **Time**   |
|------------------------------|------------:|
| `logWithPlaceHoldersNoExt`   | 2.5&micro;s |
| `logWithPlaceHolders`        | 7.8&micro;s |
| `logWithPlaceHolderToString` | 7.8&micro;s |
| `logWithStringCat`           |   8&micro;s |
| `logWithStringCatToString`   | 7.7&micro;s |

