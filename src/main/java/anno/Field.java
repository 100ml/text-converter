package anno;

import parser.FieldParser;

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
    Class<? extends FieldParser> parser() default FieldParser.class;
}
