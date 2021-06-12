package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AnalysisTest {
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void analysisServerLogWithTemporaryFolder() throws IOException {
		File source = folder.newFile("source.txt");
		File target = folder.newFile("target.txt");
		try (PrintWriter out = new PrintWriter(source)) {
			out.println("200 10:56:01");
			out.println("500 10:57:01");
			out.println("400 10:58:01");
			out.println("200 10:59:01");
			out.println("500 11:01:02");
			out.println("200 12:02:02");
		}
		Analysis analysis = new Analysis();
		analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
		StringBuilder rsl = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
			reader.lines().forEach(rsl::append);
		}
		assertThat(rsl.toString(), is("10:57:01;10:59:01;11:01:02;12:02:02;"));
	}
}