package ru.job4j.serialization.json;

public class PersonCar {
	private String name;
	private String position;
	private int age;

	public PersonCar(String name, String position, int age) {
		this.name = name;
		this.position = position;
		this.age = age;
	}

	@Override
	public String toString() {
		return "PersonCar{"
				+ "name='" + name + '\''
				+ ", position='" + position + '\''
				+ ", age=" + age
				+ '}';
	}

	public String getName() {
		return name;
	}

	public String getPosition() {
		return position;
	}

	public int getAge() {
		return age;
	}
}
