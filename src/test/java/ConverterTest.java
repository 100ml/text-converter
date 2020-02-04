import com.google.common.collect.Lists;
import org.junit.Test;
import pojo.Car;
import pojo.People;
import pojo.Sex;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ConverterTest {

    @Test
    public void convert() {
        People people = new People();
        Car car = new Car();
        car.setColor("green");
        car.setSize(2.0);
        people.setCar(car);
        people.setSex(Sex.Male);
        people.setName("张三");
        String text = new Reverser<>(People.class).reverse(Lists.newArrayList(people));

        List<People> list = new Converter<>(People.class).convert(text);
        assertNotNull(list);
        assertTrue(list.size() == 1);
    }
}