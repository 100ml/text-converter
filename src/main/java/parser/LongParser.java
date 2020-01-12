package parser;

import javax.annotation.Nonnull;

public class LongParser extends Parser<Long> {

    public Long parseString(@Nonnull String cell) {
        return Long.parseLong(cell);
    }

    public String toString(Long value) {
        return value.toString();
    }
}
