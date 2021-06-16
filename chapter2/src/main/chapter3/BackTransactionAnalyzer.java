package main.chapter3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BackTransactionAnalyzer {
	private static final String RESOURCES = "chapter2/src/main/resources/";

	public static void main(String[] args) throws IOException {
		final BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();
		final Path path = Paths.get(RESOURCES + args[0]);
		final List<String> lines = Files.readAllLines(path);

		final List<BankTransaction> bankTransactions = bankStatementCSVParser.parseLinesFromCSV(lines);
		final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);


		callSummary(bankStatementProcessor);
	}

	public static void callSummary(BankStatementProcessor bankStatementProcessor) {
		System.out.println("------------------------------------------------------------");
		System.out.println("The total for all transactions is " + bankStatementProcessor.calculateTotalAmount());
		System.out.println("The total for transactions in January is " + bankStatementProcessor.calculateTOtalInMonth(Month.JANUARY));
		System.out.println("The total salary received is " + bankStatementProcessor.calculateTotalForCategory("Salary"));
		System.out.println("------------------------------------------------------------");
	}
}
