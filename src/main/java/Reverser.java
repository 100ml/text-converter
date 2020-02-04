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

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static util.StringUtil.capitalize;

public class Reverser<T> extends TextHandler<T> {

    @Nonnull
    public String reverse(@Nonnull List<T> list) {

        List<String> content = Lists.newArrayList();
        if (isHeaderEnabled(this.clazz)) content.add(genHeader(this.clazz));
        List<String> rows = list.stream().map(x -> genRow(x)).collect(toList());
        content.addAll(rows);
        return content.stream().collect(Collectors.joining(this.lineSplitSymbol));
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

    public Reverser(Class<T> tClass) {
        this.clazz = tClass;
    }
}
