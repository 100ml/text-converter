import com.google.common.collect.Lists;
import org.junit.Test;
import pojo.Car;
import pojo.People;
import pojo.Sex;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ConverterTest {

    private final String columnSymbol = " ";

    @Test
    public void convert() throws Exception {
        People people = new People();
        Car car = new Car();
        car.setColor("green");
        car.setSize(2.0);
        people.setCar(car);
        people.setSex(Sex.Male);
        people.setName("张三");

        Reverser reverser = new Reverser<>(People.class);
        reverser.setColumnSplitSymbol(columnSymbol);
        String text = reverser.reverse(Lists.newArrayList(people));
        System.out.println(text);
        Converter converter = new Converter<>(People.class);
        converter.setColumnSplitSymbol(columnSymbol);
        List<People> list = converter.convert(text);
        assertNotNull(list);
        assertTrue(list.size() == 1);
    }
}