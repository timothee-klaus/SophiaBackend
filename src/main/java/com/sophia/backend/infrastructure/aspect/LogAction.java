package com.sophia.backend.infrastructure.aspect;

import com.sophia.backend.domain.enums.ActionLog;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAction {
    ActionLog action();
    String entity();
    String description() default "";
}

