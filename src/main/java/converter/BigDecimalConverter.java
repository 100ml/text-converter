package converter;

import java.math.BigDecimal;

public class BigDecimalConverter implements FieldConverter<BigDecimal> {
    @Override
    public String toString(BigDecimal bigDecimal) {
        return bigDecimal.toPlainString();
    }

    @Override
    public BigDecimal toObject(String str) {
        return new BigDecimal(str);
    }
}
