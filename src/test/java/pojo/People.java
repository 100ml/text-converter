package pojo;

import anno.EnableHeader;
import anno.Field;
import converter.CarConverter;
import converter.SexConverter;
import converter.StringConverter;
import lombok.Data;

@Data
@EnableHeader
public class People {

    @Field(converter = StringConverter.class)
    private String name;

    @Field(converter = SexConverter.class)
    private Sex sex;

    @Field(converter = CarConverter.class)
    private Car car;
}
