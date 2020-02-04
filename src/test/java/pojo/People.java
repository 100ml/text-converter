package pojo;

import anno.EnableHeader;
import anno.Field;
import anno.Strategy;
import parser.CarParser;
import parser.SexParser;
import parser.StringParser;
import lombok.Data;

@Data
@EnableHeader(strategy = Strategy.HEADER)
public class People {

    @Field(parser = StringParser.class)
    private String name;

    @Field(parser = SexParser.class)
    private Sex sex;

    @Field(parser = CarParser.class)
    private Car car;
}
