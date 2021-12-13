package exam.demo.modulecommon.annotation;


import exam.demo.modulecommon.enums.EnumOperation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FullCommonField {
    EnumOperation operation() default EnumOperation.INSERT;
}
