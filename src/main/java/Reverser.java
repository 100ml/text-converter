import anno.EnableHeader;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import parser.FieldParser;

import javax.annotation.Nonnull;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static util.StringUtil.capitalize;

public class Reverser<T> extends TextHandler<T> {

    public Reverser(Class<T> tClass) {
        this.clazz = tClass;
        this.enableHeader = clazz.getAnnotation(EnableHeader.class);
    }

    @Nonnull
    public String reverse(@Nonnull List<T> list) throws Exception {
        List<String> content = Lists.newArrayList();
        if (enableHeader.value()) content.add(genHeader(this.clazz));
        try {
            for (T t : list) {
                content.add(genRow(t));
            }
        } catch (Exception e) {
            throw e;
        }
        return content.stream().collect(Collectors.joining(this.lineSplitSymbol));
    }

    private String genHeader(Class tClass) {
        Field[] fields = tClass.getDeclaredFields();
        return Stream.of(fields).map(this::getHeader).collect(joining(columnSplitSymbol));
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

    private String genRow(Object object) throws Exception {
        Field[] fields = this.clazz.getDeclaredFields();
        Method[] methods = this.clazz.getDeclaredMethods();
        Map<String, Method> methodMap = Stream.of(methods).collect(toMap(x -> x.getName(), y -> y));
        Map<String, Class> fieldConvertMap = Stream.of(fields).collect(toMap(x -> x.getName(), y -> getFieldConverterClass(y)));
        List<String> cells = Lists.newArrayList();
        try {
            for (Field field : fields) {
                String tempMethodName = "get" + capitalize(field.getName());
                if (methodMap.containsKey(tempMethodName)) {
                    Object value = methodMap.get(tempMethodName).invoke(object, null);
                    Constructor constructor = fieldConvertMap.get(field.getName()).getConstructor();
                    FieldParser converter = (FieldParser) constructor.newInstance();
                    cells.add(converter.toString(value));
                } else {
                    cells.add("");
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return String.join(columnSplitSymbol, cells);
    }

    private Class<? extends FieldParser> getFieldConverterClass(Field field) {
        anno.Field field1 = field.getAnnotation(anno.Field.class);
        return field1.parser();
    }
}
