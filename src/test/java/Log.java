import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {

    private static final Logger logger
            = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        logger.info("Log from {}", Test.class.getSimpleName());

    }
}