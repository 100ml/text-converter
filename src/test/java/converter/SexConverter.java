package converter;

import pojo.Sex;

public class SexConverter implements FieldConverter<Sex> {
    @Override
    public String toString(Sex sex) {
        return sex.name();
    }

    @Override
    public Sex toObject(String str) {
        return Sex.valueOf(str);
    }
}
