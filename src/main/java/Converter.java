import anno.EnableHeader;
import anno.Field;
import anno.Strategy;
import com.google.common.collect.Lists;
import parser.FieldParser;
import pojo.DicUnit;

import javax.annotation.Nonnull;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static util.StringUtil.capitalize;

public class Converter<T> extends TextHandler<T> {

    public Converter(Class<T> tClass) {
        this.clazz = tClass;
        this.enableHeader = clazz.getAnnotation(EnableHeader.class);
    }

    @Nonnull
    public List convert(@Nonnull String text) throws Exception {
        String[] lines = text.split(lineSplitSymbol);
        String[] content = null;
        if (enableHeader.strategy() == Strategy.HEADER) {
            String headers = lines[0];
            initHeaderDicMap(headers);
            content = Arrays.copyOfRange(lines, 1, lines.length);
        } else {
            initOrdinalDicMap();
            content = lines;
        }
        return parseLines(content);
    }

    private List<T> parseLines(String[] lines) throws Exception {
        List<T> list = Lists.newArrayList();
        try {
            for (String line : lines) {
                list.add(parseLine(line));
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    private T parseLine(String line) throws Exception {
        String[] columns = line.split(columnSplitSymbol);
        int length = columns.length;
        T t = clazz.newInstance();
        for (int i = 0; i < length; i++) {
            if (this.dicMap.containsKey(i + "")) {
                DicUnit dicUnit = this.dicMap.get(i + "");
                Field fieldAnno =  dicUnit.getField().getAnnotation(Field.class);
                Class<? extends FieldParser> parserClass = fieldAnno.parser();
                FieldParser parser = parserClass.newInstance();
                dicUnit.getMethod().invoke(t, parser.toObject(columns[i]));
            } else {
                throw new RuntimeException(String.format("key:%d does not exit in dicMap", i));
            }
        }
        return t;
    }

    private void initHeaderDicMap(String headers) throws Exception {
        String[] headerArr = headers.split(columnSplitSymbol);
        int length = headerArr.length;
        for (int i = 0; i < length; i++) {
            java.lang.reflect.Field field = this.clazz.getDeclaredField(headerArr[i]);
            //TODO
            Method method = Stream.of(this.clazz.getDeclaredMethods()).filter(x -> x.getName().equals("set" + capitalize(field.getName()))).findFirst().get();
            dicMap.put(i + "", new DicUnit(i, field, method));
        }
    }

    private void initOrdinalDicMap() {
        java.lang.reflect.Field[] fieldArr = clazz.getDeclaredFields();
        Integer ordinal = 0;
        for (java.lang.reflect.Field field : fieldArr) {
            if (field.isAnnotationPresent(Field.class)) {
                //TODO
                Method method = Stream.of(this.clazz.getDeclaredMethods()).filter(x -> x.getName().equals("set" + capitalize(field.getName()))).findFirst().get();
                dicMap.put(ordinal.toString(), new DicUnit(ordinal, field, method));
                ordinal++;
            }
        }
    }
}
