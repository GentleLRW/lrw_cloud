package lrw.demo.lib.utils;

import org.apache.commons.lang3.RandomUtils;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

/**
 * @author fun
 * @date 2019/7/8
 */
public class SnowFlakeIdGenerator {
    private static final long BEGIN_TS = 1483200000000L;
    private static final int MAX_PROCESS_ID = (1 << 10) - 1;
    private static volatile long LAST_NEW_INSTANCE_TIME = currentTime();
    private static volatile Snowflake LAST_SNOWFLAKE_INSTANCE = Snowflake.newInstance();

    /**
     * 获取Snowflake算法形成的唯一Id
     *
     * @return id - 18位
     */
    public static long nextId() {
        return getInstance().nextId();
    }

    public static String format(Long id, DateTimeFormatter formatter) {
        return getTimeStamp(id).toLocalDateTime().format(formatter);
    }

    private static Timestamp getTimeStamp(Long id) {
        return new Timestamp(id / 4194304 + BEGIN_TS);
    }

    /**
     * 防重处理
     *
     * @return
     */
    private static Snowflake getInstance() {
        long l = currentTime();
        if (l > LAST_NEW_INSTANCE_TIME) {
            LAST_SNOWFLAKE_INSTANCE = Snowflake.newInstance();
        }
        LAST_NEW_INSTANCE_TIME = l;
        return LAST_SNOWFLAKE_INSTANCE;
    }

    private static long currentTime() {
        return System.currentTimeMillis();
    }

    /**
     * SnowFlake算法 64位Long类型生成唯一ID 第一位0，表明正数 2-42，41位，表示毫秒时间戳差值，起始值自定义
     * 43-52，10位，机器编号，5位数据中心编号，5位进程编号 53-64，12位，毫秒内计数器 本机内存生成，性能高
     * <p>
     * 主要就是三部分： 时间戳，进程id，序列号 时间戳41，id10位，序列号12位
     */
    private static class Snowflake {

        private long lastTs = 0L;
        private long processId;
        private int processIdBits = 10;
        private long sequence = 0L;
        private int sequenceBits = 12;

        /**
         * 构造器，输入10位进程Id标识
         *
         * @param processId 进程Id
         */
        private Snowflake(long processId) {
            if (processId > ((1 << processIdBits) - 1)) {
                throw new RuntimeException("Process Id is over the range, the maximum is " + ((1 << processIdBits) - 1));
            }
            this.processId = processId;
        }

        private static Snowflake newInstance() {
            return newInstance(RandomUtils.nextLong(0, MAX_PROCESS_ID));
        }

        private static Snowflake newInstance(long processId) {
            return new Snowflake(processId);
        }

        private synchronized long nextId() {
            long ts = currentTime();
            // 若生成时间比上次生成Id时间早，出错
            if (ts < lastTs) {
                throw new RuntimeException("Timestamp is error");
            }
            // 若时间一样，则生成一个序列号
            if (lastTs == ts) {
                // sequence 循环自增
                sequence = (sequence + 1) & ((1 << sequenceBits) - 1);
                // 如果sequence=0则需要重新生成时间戳
                if (sequence == 0) {
                    // 必须保证时间戳往后
                    ts = nextTs(ts);
                }
            } else {
                sequence = 0L;
            }
            lastTs = ts;
            return ((ts - BEGIN_TS) << (processIdBits + sequenceBits)) | (processId << sequenceBits) | sequence;
        }

        /**
         * 生成下一个时间戳
         *
         * @param lastTs 上一个时间戳
         * @return 时间戳
         */
        private long nextTs(long lastTs) {
            long ts = currentTime();
            while (ts <= lastTs) {
                ts = currentTime();
            }
            return ts;
        }
    }

}
