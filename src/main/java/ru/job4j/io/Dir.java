package ru.job4j.io;

import java.io.File;

public class Dir {
	public static void main(String[] args) {
		File file = new File("/Users/andrey/IdeaProjects/job4j_design");
		if (!file.exists()) {
			throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
		}
		if (!file.isDirectory()) {
			throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
		}
		for (File subfile : file.listFiles()) {
			if (subfile.isFile()) {
				System.out.println(subfile.getName()
				+ " -- " + subfile.length());
			}
		}
	}
}
