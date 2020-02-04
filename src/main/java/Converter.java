import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

//@Builder
public class Converter<T> extends TextHandler<T> {

    @Nonnull
    public List convert(@Nonnull String text) {
        String[] lines = getLines(text);
        return parseLines(lines);
    }

    private List parseLines(String[] lines) {
        return Stream.of(lines).map(this::parseLine).collect(toList());
    }

    private Object parseLine(String line) {

        //TODO

        return null;
    }

    private String[] getLines(String text) {
        String[] tempArr = text.split(lineSplitSymbol);
        if (isHeaderEnabled(this.clazz)) {
            return Arrays.copyOfRange(tempArr, 1, tempArr.length);
        } else {
            return tempArr;
        }
    }

    public Converter(Class<T> tClass) {
        this.clazz = tClass;
    }
}
