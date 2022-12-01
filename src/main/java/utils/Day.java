package utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to pass information about the current advent day.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Day {

    /**
     * The day of the advent challenge.
     */
    int day();

    /**
     * Whether to use the test input.
     */
    boolean test() default false;
}