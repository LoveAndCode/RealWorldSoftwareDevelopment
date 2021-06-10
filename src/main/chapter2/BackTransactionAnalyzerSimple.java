package main.chapter2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class BackTransactionAnalyzerSimple {
	private static final String RESOURCES = "src/main/resources/";

	public static void main(String[] args) throws IOException {
		final Path path = Paths.get(RESOURCES + args[0]);
		final List<String> lines = Files.readAllLines(path);
		double total = 0d;

		for (final String line : lines) {
			final String[] columns = line.split(",");
			System.out.println(Arrays.toString(columns));
			final double amount = Double.parseDouble(columns[1]);
			total += amount;
		}

		System.out.println("The total for all transactions is  = " + total);
	}


}
