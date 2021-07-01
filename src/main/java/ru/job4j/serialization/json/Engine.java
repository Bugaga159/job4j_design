package ru.job4j.serialization.json;

public class Engine {
	private int numberOfCylinders;
	private double volume;
	private int power;

	public Engine(int numberOfCylinders, double volume, int power) {
		this.numberOfCylinders = numberOfCylinders;
		this.volume = volume;
		this.power = power;
	}

	@Override
	public String toString() {
		return "Engine{"
				+ "numberOfCylinders=" + numberOfCylinders
				+ ", volume=" + volume
				+ ", power=" + power
				+ '}';
	}

	public int getNumberOfCylinders() {
		return numberOfCylinders;
	}

	public double getVolume() {
		return volume;
	}

	public int getPower() {
		return power;
	}
}
