package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contact")
@XmlAccessorType(XmlAccessType.FIELD)
public class Engine {
	private int numberOfCylinders;
	private double volume;
	private int power;

	public Engine() {
	}

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
}
