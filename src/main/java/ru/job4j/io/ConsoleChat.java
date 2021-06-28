package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
	private final String path;
	private final String botAnswers;
	private static final String OUT = "закончить";
	private static final String STOP = "стоп";
	private static final String CONTINUE = "продолжить";
	private final List<String> answerBot = new ArrayList<>();
	private final List<String> log = new ArrayList<>();

	public ConsoleChat(String path, String botAnswers) {
		this.path = path;
		this.botAnswers = botAnswers;
	}

	private void readAnswerBot() {
		try (BufferedReader br = new BufferedReader(
				new FileReader(botAnswers, StandardCharsets.UTF_8))) {
			br.lines().forEach(answerBot::add);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeLogChat() {
		try (PrintWriter pw = new PrintWriter(
				new FileWriter(path, StandardCharsets.UTF_8))) {
			log.forEach(pw::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		boolean unblock = false;
		readAnswerBot();
		System.out.println("Hi user!");
		Scanner scanner = new Scanner(System.in);
		String word = "";
		while (!word.equals(OUT)) {
			word = scanner.nextLine();
			log.add("- User: " + word);
			if (!word.equals(OUT)
			&& !word.equals(STOP) && unblock || word.equals(CONTINUE)) {
				int rand = (int) (Math.random() * (answerBot.size() - 1));
				String answer = "- Robot: " + answerBot.get(rand);
				System.out.println(answer);
				log.add(answer);
			} else {
				unblock = false;
			}
			if (word.equals(CONTINUE)) {
				unblock = true;
			}
		}
		log.add(OUT);
		writeLogChat();
	}

	public static void main(String[] args) {
		String answerBot = "data/answerBot.txt";
		String path = "data/log.txt";
		ConsoleChat cc = new ConsoleChat(path, answerBot);
		cc.run();
	}
}