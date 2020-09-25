package lrw.demo.lib.redis.redission;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author by lrw
 * @Classname RedissionConfig
 * @Description TODO
 * @Date 2020/9/24 18:16
 */

@Component
@Data
@ConfigurationProperties("redisson.lock.server")
public class RedissonConfig {
    /**
     * redis主机地址，ip：port，有多个用半角逗号分隔
     */
    private String address;

    /**
     * 连接类型，支持standalone-单机节点，sentinel-哨兵，cluster-集群，masterslave-主从
     */
    private String type;

    /**
     * redis 连接密码
     */
    private String password;

    /**
     * 选取那个数据库
     */
    private int database;

    /**
     * 端口
     */
    private String port;

}
