package anno;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface EnableHeader {
    /**
     * Marks if headers are needed.
     * @return
     */
    boolean value() default true;

    /**
     * Used when converting text to list.
     * @return
     */
    Strategy strategy() default Strategy.ORDINAL;
}
