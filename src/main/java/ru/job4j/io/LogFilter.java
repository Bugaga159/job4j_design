package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
	public static List<String> filter(String file) {
		List<String> res = new ArrayList<>();
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			List<String> lines = new ArrayList<>();
			in.lines().forEach(lines::add);
			for (String line : lines) {
				String[] string = line.split(" ");
				if (string[string.length - 2].equals("404")) {
					res.add(line);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public static void main(String[] args) {
		List<String> log = filter("log.txt");
		log.forEach(System.out::println);
	}
}
