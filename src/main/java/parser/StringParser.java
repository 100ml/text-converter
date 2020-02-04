package parser;

public class StringParser implements FieldParser<String> {

    @Override
    public String toString(String s) {
        return s;
    }

    @Override
    public String toObject(String str) {
        return str;
    }
}
