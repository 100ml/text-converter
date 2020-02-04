package parser;

import java.math.BigDecimal;

public class BigDecimalParser implements FieldParser<BigDecimal> {
    @Override
    public String toString(BigDecimal bigDecimal) {
        return bigDecimal.toPlainString();
    }

    @Override
    public BigDecimal toObject(String str) {
        return new BigDecimal(str);
    }
}
