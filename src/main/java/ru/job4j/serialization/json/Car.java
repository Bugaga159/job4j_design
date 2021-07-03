package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		JSONObject jsonEngine =
				new JSONObject("{\"numberOfCylinders\":4,\"volume\":2.0,\"power\":200}");

		List<PersonCar> crew = new ArrayList<>();
		crew.add(new PersonCar("Mike", "driver", 28));
		crew.add(new PersonCar("Oleg", "navigator", 23));
		JSONArray jsonCrew = new JSONArray(crew);
		final Car carTeam2 = new Car("Happy", "black",
				new Engine(4, 2.0, 200),
				true, 36,
				new PersonCar[]{
						new PersonCar("Mike", "driver", 28),
						new PersonCar("Oleg", "navigator", 23)}
		);
		JSONObject carTeam = new JSONObject();
		carTeam.put("name", carTeam2.getName());
		carTeam.put("color", carTeam2.getColor());
		carTeam.put("engine", jsonEngine);
		carTeam.put("frontWheelDrive", carTeam2.isFrontWheelDrive());
		carTeam.put("numberCar", carTeam2.getNumberCar());
		carTeam.put("crew", jsonCrew);

		System.out.println(carTeam.toString());

		System.out.println(new JSONObject(carTeam2).toString());
	}
}
