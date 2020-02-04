package parser;

public class DoubleParser implements FieldParser<Double> {
    @Override
    public String toString(Double aDouble) {
        return aDouble.toString();
    }

    @Override
    public Double toObject(String str) {
        return Double.parseDouble(str);
    }
}
