package com.softnovo.algorithm.clazz;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimit {
    public enum TimeUnit { SECOND, MINUTE, HOUR, DAY, MONTH}
    String apiName();
    int limitCount();
    TimeUnit timeUnit() default TimeUnit.SECOND;
}
