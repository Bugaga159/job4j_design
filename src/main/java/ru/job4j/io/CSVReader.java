package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
	public static List<String> filter(Path path, String delimiter, String filter) {
		List<String> file = new ArrayList<>();
		try {
			var scanner = new Scanner(path);
			String[] filterCvs = filter.split(",");
			boolean firstLine = true;
			List<Integer> index = new ArrayList();
			while (scanner.hasNextLine()) {
				List<String> arg = Arrays.asList(scanner.next().split(delimiter));
				if (firstLine) {
					for (int i = 0; i < filterCvs.length; i++) {
						int k = arg.indexOf(filterCvs[i]);
						if (k != -1) {
							index.add(k);
						}
					}
					firstLine = false;
				}
				StringBuilder builder = new StringBuilder();
				if (arg.size() >= index.size()) {
					for (int i: index) {
						builder.append(arg.get(i));
						builder.append(delimiter);
					}
					file.add(builder.substring(0, builder.length() - 1).toString());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return file;
	}

	public static void writeFile(List<String> file, File out) {
		try (PrintWriter pw = new PrintWriter(new FileWriter(out))) {
			for (String str : file) {
				pw.println(str);
//				pw.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		if (args.length != 4) {
			throw new IllegalArgumentException("Invalid arguments");
		}
		ArgsName param = ArgsName.of(args);
		List<String> filteredIn = filter(Path.of(param.get("path")), param.get("delimiter"), param.get("filter"));
		if (param.get("out").equals("stdout")) {
			for (String s : filteredIn) {
				System.out.println(s);
			}
		} else {
			writeFile(filteredIn, new File(param.get("out")));
		}
	}
}
