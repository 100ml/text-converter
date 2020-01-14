package converter;

import pojo.Car;

public class CarConverter implements FieldConverter<Car> {
    @Override
    public String toString(Car car) {
        return car.toString();
    }

    @Override
    public Car toObject(String str) {
        Car car = new Car();
        String[] elements = str.split(",");
        int length = elements.length;
        if (length > 0) {
            if (length == 1) {
                car.setColor(elements[0]);
            }
            if (length == 2) {
                car.setSize(Double.parseDouble(elements[1]));
            }
        }
        return car;
    }
}
