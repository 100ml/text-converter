package parser;

import com.google.gson.Gson;
import pojo.Car;

public class CarParser implements FieldParser<Car> {
    @Override
    public String toString(Car car) {
        Gson gson = new Gson();
        return gson.toJson(car);
    }

    @Override
    public Car toObject(String str) {
        Car car = new Car();
        Gson gson = new Gson();
        return gson.fromJson(str, Car.class);
    }
}
