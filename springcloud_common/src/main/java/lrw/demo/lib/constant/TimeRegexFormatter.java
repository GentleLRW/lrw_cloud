package lrw.demo.lib.constant;

import lombok.Getter;
import lrw.demo.lib.common.BaseErrorCode;
import lrw.demo.lib.common.exception.CommonException;
import org.springframework.util.StringUtils;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

/**
 * @author lrw
 * @date 2019/7/15
 */
public enum TimeRegexFormatter {
    //
    E(null, "EEEE"),
    HH("^\\d{2}$", "HH"),
    Hm("^\\d{2}:\\d{2}$", "HH:mm"),
    H0("^\\d{2}:00$", "HH:00"),
    Hms("^\\d{2}:\\d{2}:\\d{2}$", "HH:mm:ss"),
    H00("^\\d{2}:00:00$", "HH:00:00"),
    MdHm("^\\d{2}-\\d{2} \\d{2}:\\d{2}$", "MM-dd HH:mm"),
    yMd("^\\d{4}-\\d{2}-\\d{2}$", "yyyy-MM-dd"),
    yMdHms("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$", "yyyy-MM-dd HH:mm:ss"),
    yMdH00("^\\d{4}-\\d{2}-\\d{2} \\d{2}:00:00$", "yyyy-MM-dd HH:00:00"),
    yMdHm0("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:00$", "yyyy-MM-dd HH:mm:00");

    private String regex;

    @Getter
    private DateTimeFormatter formatter;

    @Getter
    private String pattern;

    TimeRegexFormatter(String regex, String pattern) {
        this.regex = regex;
        this.pattern = pattern;
        this.formatter = DateTimeFormatter.ofPattern(pattern, Locale.CHINESE);
    }

    public static TemporalAccessor matchAndParse(String time) {
        if (!StringUtils.isEmpty(time)) {
            for (TimeRegexFormatter value : TimeRegexFormatter.values()) {
                if (time.matches(value.regex)) {
                    return value.formatter.parse(time);
                }
            }
        }
        throw new CommonException(BaseErrorCode.BASE_FAILURE);
    }

}