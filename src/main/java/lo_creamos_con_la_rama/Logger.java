package lo_creamos_con_la_rama;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static final Logger INSTANCE = new Logger();
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private Logger() {

    }

    public static Logger getInstance() {
        return INSTANCE;
    }

    public void info(String msg) {
        System.out.println("[" + LocalDateTime.now().format(fmt) + "] [INFO] " + msg);
    }

    public void warn(String msg) {
        System.out.println("[" + LocalDateTime.now().format(fmt) + "] [WARN] " + msg);
    }

    public void error(String msg) {
        System.err.println("[" + LocalDateTime.now().format(fmt) + "] [ERROR] " + msg);
    }
}
