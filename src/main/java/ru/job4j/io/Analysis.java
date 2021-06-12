package ru.job4j.io;

import java.io.*;

public class Analysis {
	public void unavailable(String source, String target) {
		try (BufferedReader in = new BufferedReader(new FileReader(source));
			 PrintWriter out = new PrintWriter(
					 new BufferedOutputStream(
							 new FileOutputStream(target)))) {
				StringBuilder sb = new StringBuilder();
			String line;
			while ((line = in.readLine()) != null) {
				String[] lines = line.split(" ");
				if (sb.length() == 0 && (lines[0].equals("400") || lines[0].equals("500"))) {
					sb.append(lines[1]).append(";");
				}
				if (sb.length() > 0 && (lines[0].equals("200") || lines[0].equals("300"))) {
					sb.append(lines[1]).append(";");
					out.println(sb);
					sb = new StringBuilder();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Analysis an = new Analysis();
		an.unavailable("./data/server.log", "./data/unavailable.csv");
	}
}
