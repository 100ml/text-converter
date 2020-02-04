import anno.EnableHeader;
import anno.Strategy;
import com.google.common.collect.Maps;
import lombok.Data;
import pojo.DicUnit;

import java.util.Map;

@Data
public abstract class TextHandler<T> {

    protected Class<T> clazz;
    protected String columnSplitSymbol = ",";
    protected String lineSplitSymbol = "\t";
    protected Map<String, DicUnit> dicMap = Maps.newHashMap();
    protected EnableHeader enableHeader;

//    public abstract Map<String, DicUnit> genDicMap();
}
