package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "carTeam")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String color;
	private Engine engine;
	private boolean frontWheelDrive;
	private int numberCar;
	@XmlElementWrapper()
	@XmlElement(name = "person")
	private String[] crew;

	public Car() {
	}

	public Car(String name, String color, Engine engine,
			   boolean frontWheelDrive, int numberCar, String[] crew) {
		this.name = name;
		this.color = color;
		this.engine = engine;
		this.frontWheelDrive = frontWheelDrive;
		this.numberCar = numberCar;
		this.crew = crew;
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

	public static void main(String[] args) throws Exception {
		final Car carTeam = new Car(
				"Happy",
				"black",
				new Engine(4, 2.0, 200),
				true,
				36,
				new String[]{"Mike", "Igor", "Mary"}
		);

		// Получаем контекст для доступа к АПИ
		JAXBContext context = JAXBContext.newInstance(Car.class);
		// Создаем сериализатор
		Marshaller marshaller = context.createMarshaller();
		// Указываем, что нам нужно форматирование
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		String xml = "";
		try (StringWriter writer = new StringWriter()) {
			// Сериализуем
			marshaller.marshal(carTeam, writer);
			xml = writer.getBuffer().toString();
			System.out.println(xml);
		}
		// Для десериализации нам нужно создать десериализатор
		Unmarshaller unmarshaller = context.createUnmarshaller();
		try (StringReader reader = new StringReader(xml)) {
			// десериализуем
			Car carTeam2 = (Car) unmarshaller.unmarshal(reader);
			System.out.println(carTeam2);
		}
	}
}
