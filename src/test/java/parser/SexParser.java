package parser;

import pojo.Sex;

public class SexParser implements FieldParser<Sex> {
    @Override
    public String toString(Sex sex) {
        return sex.name();
    }

    @Override
    public Sex toObject(String str) {
        return Sex.valueOf(str);
    }
}
