package converter;

import anno.EnableHeader;
import anno.Field;

import java.util.List;

@EnableHeader
public class TestPojo {

    @Field(converter = FieldConverter.class)
    private List<String> pros;
}
