package task6_1a;

import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)      // доступна во время выполнения через Reflection
@Target(ElementType.METHOD)              // применяется к методам
@interface TestInfo {
    String author();                      // String author()
    String date();                         // String date()
    String description() default "";       // String description() default ""
    int priority() default 5;              // int priority() default 5
}