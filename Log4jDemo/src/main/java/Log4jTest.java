import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jTest {

    // 创建 Logger 实例
    private static final Logger Logger = LogManager.getLogger(Log4jTest.class);

    public static void main(String[] args) {
        // 潜在的安全风险：使用不受信任的数据作为日志消息
        String code = "${java:os}";

        // 将不受信任的数据作为日志消息传递给 Logger.error
        Logger.error("{}", code);
    }
}
