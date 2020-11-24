package lrw.demo.lib.constant;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

/**
 * 时间段
 *
 * @author fun
 * @date 2019/7/18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class TimePeriod {

    private Timestamp start;

    private Timestamp end;

    public static TimePeriod today() {
        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = start.plusDays(1).minusNanos(1);
        return TimePeriod.of(Timestamp.valueOf(start), Timestamp.valueOf(end));
    }

    public static TimePeriod yesterday() {
        LocalDateTime start = LocalDate.now().minusDays(1).atStartOfDay();
        LocalDateTime end = start.plusDays(1).minusNanos(1);
        return TimePeriod.of(Timestamp.valueOf(start), Timestamp.valueOf(end));
    }

    /**
     * @param date yyyy-MM-dd
     * @return
     */
    public static TimePeriod from(String date) {
        LocalDateTime start = LocalDate.parse(date, TimeRegexFormatter.yMd.getFormatter()).atStartOfDay();
        LocalDateTime end = start.plusDays(1).minusNanos(1);
        return TimePeriod.of(Timestamp.valueOf(start), Timestamp.valueOf(end));
    }

    public static TimePeriod fromPeriodStr(String date) {
        String[] split = StringUtils.split(date, "~");
        if (split.length == 2) {
            Timestamp start = Timestamp.valueOf(LocalDate.parse(split[0], TimeRegexFormatter.yMd.getFormatter()).atStartOfDay());
            Timestamp end = Timestamp.valueOf(LocalDate.parse(split[1], TimeRegexFormatter.yMd.getFormatter()).atStartOfDay());
            return TimePeriod.of(start, end);
        }
        return TimePeriod.today();
    }

    public static TimePeriod from(LocalDate start, LocalDate end) {
        TimePeriod period = TimePeriod.of(Timestamp.valueOf(start.atStartOfDay()), Timestamp.valueOf(end.atStartOfDay().plusDays(1).minusNanos(1)));
        startRequireBeforeEnd(period);
        return period;
    }

    public static TimePeriod from(LocalDate date) {
        LocalDateTime dateTime = date.atStartOfDay();
        return TimePeriod.of(Timestamp.valueOf(dateTime), Timestamp.valueOf(dateTime.plusDays(1).minusNanos(1)));
    }

    public static TimePeriod from(long date) {
        return from(new Timestamp(date).toLocalDateTime().toLocalDate());
    }

    public static TimePeriod getMonth(long date) {
        LocalDate localDate = new Date(date).toLocalDate();
        return TimePeriod.from(localDate.with(TemporalAdjusters.firstDayOfMonth()), localDate.with(TemporalAdjusters.lastDayOfMonth()));
    }

    public static TimePeriod currentHour() {
        LocalDateTime now = LocalDateTime.now();
        now = now.minusNanos(now.getNano()).minusSeconds(now.getSecond()).minusMinutes(now.getMinute());
        return TimePeriod.of(Timestamp.valueOf(now), Timestamp.valueOf(now.plusHours(1).minusNanos(1)));
    }

    public static TimePeriod todayToNow() {
        LocalDateTime now = LocalDateTime.now();
        return TimePeriod.of(Timestamp.valueOf(now.toLocalDate().atStartOfDay()), Timestamp.valueOf(now));
    }

    private static void startRequireBeforeEnd(TimePeriod period) {
        if (period != null && period.isStartAfterEnd()) {
            throw new RuntimeException("error");
        }
    }

    @JSONField(serialize = false)
    public boolean isStartAfterEnd() {
        return start != null && end != null && start.getTime() > end.getTime();
    }

    /**
     * 开始结束时间在一天之内
     *
     * @return
     */
    @JSONField(serialize = false)
    public boolean isInOneDay() {
        return start != null && end != null &&
                start.toLocalDateTime().getYear() == end.toLocalDateTime().getYear() &&
                start.toLocalDateTime().getMonth() == end.toLocalDateTime().getMonth() &&
                start.toLocalDateTime().getDayOfMonth() == end.toLocalDateTime().getDayOfMonth();
    }

    @JSONField(serialize = false)
    public Date getStartDate() {
        return new Date(start.getTime());
    }

    @JSONField(serialize = false)
    public Date getEndDate() {
        return new Date(end.getTime());
    }

    @JSONField(serialize = false)
    public LocalDate getStartLocalDate() {
        return start.toLocalDateTime().toLocalDate();
    }

    @JSONField(serialize = false)
    public LocalDate getEndLocalDate() {
        return end.toLocalDateTime().toLocalDate();
    }

    public String formatToDay() {
        return getStartDate() + "~" + getEndDate();
    }
}
