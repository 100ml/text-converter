import com.google.common.collect.Lists;
import org.junit.Test;
import pojo.Car;
import pojo.People;
import pojo.Sex;

import static org.junit.Assert.*;

public class TextConverterTest {

    @Test
    public void convert() {
        People people = new People();
//        System.out.println(new TextConverter().reverse(Lists.newArrayList(people), People.class));
    }

    @Test
    public void reverse() {
        People people = new People();
        Car car = new Car();
        car.setColor("green");
        car.setSize(2.0);
        people.setCar(car);
        people.setSex(Sex.Male);
        people.setName("张三");
        System.out.println(new TextConverter(People.class).reverse(Lists.newArrayList(people)));
    }
}