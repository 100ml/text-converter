import anno.EnableHeader;
import com.google.common.base.Strings;

import java.lang.reflect.Field;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class TextHandler<T> {

    protected Class<T> clazz;
    protected String columnSplitSymbol = ",";
    protected String lineSplitSymbol = "\t";

    public String genHeader(Class tClass) {
        Field[] fields = tClass.getDeclaredFields();
        return Stream.of(fields).map(this::getHeader).collect(joining(","));
    }

    public Boolean isHeaderEnabled(Class tClass) {
        if (tClass.isAnnotationPresent(EnableHeader.class)) {
            EnableHeader enableHeader = (EnableHeader)tClass.getAnnotation(EnableHeader.class);
            return enableHeader.value();
        } else {
            //TODO is there any better way?
            return false;
        }
    }

    private String getHeader(Field field) {
        String header;
        anno.Field field1 = field.getAnnotation(anno.Field.class);
        if (field1 != null && !Strings.isNullOrEmpty(field1.name())) {
            header = field1.name();
        } else {
            header = field.getName();
        }
        return header;
    }
}
