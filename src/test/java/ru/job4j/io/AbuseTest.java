package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AbuseTest {
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void drop() throws IOException {
		File source = folder.newFile("source.txt");
		File target = folder.newFile("target.txt");
		try (PrintWriter out = new PrintWriter(source)) {
			out.println("hello foolish dude ");
		}
		Abuse.drop(source.getAbsolutePath(), target.getAbsolutePath(), List.of("foolish"));
		StringBuilder rsl = new StringBuilder();
		try (BufferedReader in = new BufferedReader(new FileReader(target))) {
			in.lines().forEach(rsl::append);
		}
		assertThat(rsl.toString(), is("hello dude "));
	}
}