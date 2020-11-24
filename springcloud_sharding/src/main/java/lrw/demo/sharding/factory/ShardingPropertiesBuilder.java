package lrw.demo.sharding.factory;

import java.util.Properties;

/**
 * @author fun
 * @date 2019/6/14
 */
public class ShardingPropertiesBuilder {

    private Properties properties = new Properties();


    public Properties build() {
        return properties;
    }
    public static ShardingPropertiesBuilder builder() {
        return new ShardingPropertiesBuilder();
    }

}
