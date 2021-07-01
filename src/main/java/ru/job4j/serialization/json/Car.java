package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Arrays;

public class Car {
	private String name;
	private String color;
	private Engine engine;
	private boolean frontWheelDrive;
	private int numberCar;
	private PersonCar[] crew;

	public Car(String name, String color, Engine engine,
			   boolean frontWheelDrive, int numberCar, PersonCar[] crew) {
		this.name = name;
		this.color = color;
		this.engine = engine;
		this.frontWheelDrive = frontWheelDrive;
		this.numberCar = numberCar;
		this.crew = crew;
	}

	public String getName() {
		return name;
	}

	public String getColor() {
		return color;
	}

	public Engine getEngine() {
		return engine;
	}

	public boolean isFrontWheelDrive() {
		return frontWheelDrive;
	}

	public int getNumberCar() {
		return numberCar;
	}

	public PersonCar[] getCrew() {
		return crew;
	}

	@Override
	public String toString() {
		return "Car{"
				+ "name='" + name + '\''
				+ ", color='" + color + '\''
				+ ", engine=" + engine
				+ ", frontWheelDrive=" + frontWheelDrive
				+ ", numberCar=" + numberCar
				+ ", crew=" + Arrays.toString(crew)
				+ '}';
	}

	public static void main(String[] args) {
		final Car carTeam = new Car("Happy", "black",
				new Engine(4, 2.0, 200),
				true, 36,
				new PersonCar[]{
						new PersonCar("Mike", "driver", 28),
						new PersonCar("Oleg", "navigator", 23)}
		);
		final Gson gson = new GsonBuilder().create();
		System.out.println(gson.toJson(carTeam));
		final String carTeam2 = "{\"name\":\"Happy\","
				+ "\"color\":\"black\","
				+ "\"engine\":{\"numberOfCylinders\":4,\"volume\":2.0,\"power\":200},"
				+ "\"frontWheelDrive\":true,"
				+ "\"numberCar\":36,"
				+ "\"crew\":["
				+ "{\"name\":\"Mike\",\"position\":\"driver\",\"age\":28},"
				+ "{\"name\":\"Oleg\",\"position\":\"navigator\",\"age\":23}"
				+ "]}";
		final Car carTeam2FromGson = gson.fromJson(carTeam2, Car.class);
		System.out.println(carTeam2FromGson);
	}
}
