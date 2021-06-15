package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
	public void packFiles(List<Path> sources, Path target) {
		for (Path file : sources) {
			packSingleFile(file, target);
		}
	}

	public void packSingleFile(Path source, Path target) {
		try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toString())))) {
			zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
			try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toString()))) {
				zip.write(out.readAllBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ArgsName validateParameters(String[] args) {
		ArgsName jvm = ArgsName.of(args);
		if (jvm.get("d") == null || jvm.get("e") == null || jvm.get("o") == null) {
			throw new IllegalArgumentException("Need add: -d directory, -e exclude, -o output");
		}
		return jvm;
	}

	public static void main(String[] args) throws IOException {
		ArgsName param = Zip.validateParameters(args);
		List<Path> paths = Search.search(
				Path.of(param.get("d")),
				p -> !p.toFile().getName().endsWith(param.get("e")));
		new Zip().packFiles(paths, Path.of(param.get("o")));
	}
}
