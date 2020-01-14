import anno.EnableHeader;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import converter.FieldConverter;

import javax.annotation.Nonnull;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

//@Builder
public class TextConverter {

    private Class clazz;
    private String columnSplitSymbol = ",";
    private String lineSplitSymbol = "\t";

    @Nonnull
    public <T> List<T> convert(@Nonnull String csvStr) {
        return null;
    }

    public String reverse(@Nonnull List<Object> list) {
        List<String> content = Lists.newArrayList();
        if (isHeaderEnabled(this.clazz)) content.add(genHeader(this.clazz));
        List<String> rows = list.stream().map(x -> genRow(x)).collect(toList());
        content.addAll(rows);
        return content.stream().collect(Collectors.joining(this.lineSplitSymbol));
    }

    private String genHeader(Class tClass) {
        Field[] fields = tClass.getDeclaredFields();
        return Stream.of(fields).map(this::getHeader).collect(joining(","));
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

    private String genRow(Object object) {
        Field[] fields = this.clazz.getDeclaredFields();
        Method[] methods = this.clazz.getDeclaredMethods();
        Map<String, Method> methodMap = Stream.of(methods).collect(toMap(x -> x.getName(), y -> y));
        Map<String, Class> fieldConvertMap = Stream.of(fields).collect(toMap(x -> x.getName(), y -> getFieldConverterClass(y)));
        List<String> cells = Lists.newArrayList();
        for (Field field : fields) {
            String tempMethodName = "get" + capitalize(field.getName());
            if (methodMap.containsKey(tempMethodName)) {
                try {
                    Object value = methodMap.get(tempMethodName).invoke(object, null);
                    Constructor constructor = fieldConvertMap.get(field.getName()).getConstructor();
                    FieldConverter converter = (FieldConverter) constructor.newInstance();
                    cells.add(converter.toString(value));
                } catch (Exception e) {
                    //TODO
                    e.printStackTrace();
                }
            } else {
                cells.add("");
            }
        }
        return String.join(columnSplitSymbol, cells);
    }

    private Class<? extends FieldConverter> getFieldConverterClass(Field field) {
        anno.Field field1 = field.getAnnotation(anno.Field.class);
        return field1.converter();
    }

    private Boolean isHeaderEnabled(Class tClass) {
        if (tClass.isAnnotationPresent(EnableHeader.class)) {
            EnableHeader enableHeader = (EnableHeader)tClass.getAnnotation(EnableHeader.class);
            return enableHeader.value();
        } else {
            //TODO is there any better way?
            return false;
        }
    }

    public TextConverter(Class clazz) {
        this.clazz = clazz;
    }

    @Nonnull
    private String capitalize(@Nonnull String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
