package lrw.demo.lib.zookeeper.lock;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author by lrw
 * @Classname ZkLockConfigProperties
 * @Description TODO
 * @Date 2020/9/29 15:57
 */
@Component
@Data
@ConfigurationProperties(value = "zookeeper.lock")
public class ZkLockConfigProperties {

    private String host;
    private String port;
    private String rootPath = "/lock";
    private int sessionTimeout = 30000;
    private long minWaitMillis = 20;
    private long maxWaitMillis = 100;

    public String getConnectString() {
        return this.host + ":" + this.port;
    }

}
