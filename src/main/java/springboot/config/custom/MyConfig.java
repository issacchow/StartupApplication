package springboot.config.custom;

import org.springframework.boot.context.properties.ConfigurationProperties;
import springboot.util.BeanInitLogger;

/**
 * 配置映射
 */
@ConfigurationProperties(prefix = "myconfig")
public class MyConfig extends BeanInitLogger {

    private String url;
    private int port;
    private NestedConfig nestedConfig;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @ConfigurationProperties("nested")
    public NestedConfig getNestedConfig() {
        return nestedConfig;
    }

    public void setNestedConfig(NestedConfig nestedConfig) {
        this.nestedConfig = nestedConfig;
    }


    /**** class ****/
    public class NestedConfig extends MyConfig{
    }
}
