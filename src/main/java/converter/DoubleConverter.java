package converter;

public class DoubleConverter implements FieldConverter<Double> {
    @Override
    public String toString(Double aDouble) {
        return aDouble.toString();
    }

    @Override
    public Double toObject(String str) {
        return Double.parseDouble(str);
    }
}
