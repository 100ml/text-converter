import com.google.common.collect.Lists;
import org.junit.Test;
import pojo.Car;
import pojo.People;
import pojo.Sex;

public class ReverserTest {

    @Test
    public void reverse() throws Exception {
        People people = new People();
        Car car = new Car();
        car.setColor("green");
        car.setSize(2.0);
        people.setCar(car);
        people.setSex(Sex.Male);
        people.setName("张三");
        System.out.println(new Reverser<>(People.class).reverse(Lists.newArrayList(people)));
    }
}