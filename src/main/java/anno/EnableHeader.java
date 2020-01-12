package anno;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface EnableHeader {
    /**
     * Marks if headers enabled.
     * @return
     */
    boolean value() default true;

    Strategy strategy() default Strategy.ORDER;
}
