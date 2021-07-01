package ru.job4j.serialization.xml;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Car {
	private String name;
	private String color;
	private Engine engine;
	private boolean frontWheelDrive;
	private int numberCar;
	private String[] crew;

	public Car(String name, String color, Engine engine,
			   boolean frontWheelDrive, int numberCar, String[] crew) {
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

	public String[] getCrew() {
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
		final Car carTeam = new Car(
				"Happy",
				"black",
				new Engine(4, 2.0, 200),
				true,
				36,
				new String[]{"Mike", "Igor", "Mary"}
		);
	}
}
