package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class SearchExe {
	public static void main(String[] args) throws IOException {
		if (args.length != 4) {
			throw new IllegalArgumentException("Incorrect parameters. "
					+ "Set parameter -d to define directory to search in,"
					+ "-n to define file name, mask or regular expression,"
					+ "-t to choose name, mask or regex,"
					+ "-o to define file to save search.");
		}
		ArgsName param = SearchExe.validateParameters(args);
		SearchFiles searcher = SearchExe.setPredicate(param.get("t"), param.get("n"));
		Files.walkFileTree(Path.of(param.get("d")), searcher);
		SearchExe.writeOut(searcher.getPaths(), new File(param.get("o")));
	}

	private static ArgsName validateParameters(String[] args) {
		ArgsName jvm = ArgsName.of(args);
		if (jvm.get("d") == null || jvm.get("n") == null || jvm.get("t") == null || jvm.get("o") == null) {
			throw new IllegalArgumentException("Need add: -d directory,"
					+ " -n file name, -o output, -t to choose name, mask or regex");
		}
		if (!Arrays.asList("name", "mask", "regex").contains(jvm.get("t"))) {
			throw new IllegalArgumentException("Need add: -t to choose name, mask or regex");
		}
		return jvm;
	}

	private static SearchFiles setPredicate(String findType, String s) {
		SearchFiles searchFiles = null;
		switch (findType) {
			case("name"):
				searchFiles = new SearchFiles(p -> p.toFile().getName().equals(s));
				break;
			case("mask"):
				String maskName = s.substring(0, s.indexOf(".")).replaceAll("\\*", "");
				String maskExt = s.substring(s.indexOf(".") + 1).replaceAll("\\*", "");
				searchFiles = new SearchFiles(p -> p.toFile().getName().contains(maskName)
						&& p.toFile().getName().contains(maskExt));
				break;
			case("regex"):
				Pattern pattern = Pattern.compile(s);
				searchFiles = new SearchFiles(p -> pattern.matcher(p.toFile().getName()).matches());
				break;
			default:
				break;
		}
		return searchFiles;
	}

	private static void writeOut(List<Path> fileList, File file) {
		try (PrintWriter printWriter = new PrintWriter(
				new BufferedWriter(
						new FileWriter(file)))) {
			fileList.forEach(printWriter::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
