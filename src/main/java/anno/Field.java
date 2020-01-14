package anno;

import converter.FieldConverter;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Field {

    /**
     * Header name with default value of field name if headers are needed.
     * @return
     */
    String name() default "";

    /**
     * Field converter.
     * @return
     */
    Class<? extends FieldConverter> converter() default FieldConverter.class;
}
