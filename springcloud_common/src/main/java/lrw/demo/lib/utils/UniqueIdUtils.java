package lrw.demo.lib.utils;

import java.util.UUID;

/**
 * @author by lrw
 * @Classname UniqueIdUtils
 * @Description TODO
 * @Date 2020/9/2 16:12
 */
public class UniqueIdUtils {
    /**
     * 生成UUID
     * @return
     */
    public static String generateUUID(){
        return UUID.randomUUID().toString();
    }
}
