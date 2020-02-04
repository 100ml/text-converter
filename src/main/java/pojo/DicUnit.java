package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Data
@AllArgsConstructor
public class DicUnit {

    private Integer ordinal;
    private Field field;
    /**
     * used to assign value to field, whose name is setXX;
     */
    private Method method;
}
