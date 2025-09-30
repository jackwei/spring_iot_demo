package com.ykkj.test.delay;

import java.time.Instant;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayTask implements Delayed {

    // 延迟任务名称
    private String name;
    // 实际延迟时间的时间戳
    private long delayTimestamp;

    public DelayTask(int time, TimeUnit unit, String name) {
        this.delayTimestamp = Instant.now().toEpochMilli() +  (time > 0 ? unit.toMillis(time) : 0);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.delayTimestamp - Instant.now().toEpochMilli(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        DelayTask o1 = (DelayTask) o;
        long diff = this.delayTimestamp - o1.getDelay(TimeUnit.MILLISECONDS);
        if (diff == 0) {
            return 0;
        }
        return  diff > 0 ? 1 : -1;
    }



}
