package lrw.demo.lib.redis.redission;


/**
 * @author by lrw
 * @Classname GlobalConstant
 * @Description TODO
 * @Date 2020/9/24 18:22
 * @Description: 全局常量枚举 用来拼接完整的URL
 */
public enum GlobalConstant {

    REDIS_CONNECTION_PREFIX("redis://", "Redis地址配置前缀");

    private final String constant_value;
    private final String constant_desc;

    GlobalConstant(String constant_value, String constant_desc) {
        this.constant_value = constant_value;
        this.constant_desc = constant_desc;
    }

    public String getConstant_value() {
        return constant_value;
    }

    public String getConstant_desc() {
        return constant_desc;
    }
}
