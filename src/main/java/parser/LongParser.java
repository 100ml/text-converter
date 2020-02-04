package parser;

public class LongParser implements FieldParser<Long> {
    @Override
    public String toString(Long aLong) {
        return aLong.toString();
    }

    @Override
    public Long toObject(String str) {
        return Long.parseLong(str);
    }
}
