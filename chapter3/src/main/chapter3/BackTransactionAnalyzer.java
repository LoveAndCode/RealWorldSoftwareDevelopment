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
		System.out.println();
		callSummaryWithOCP(bankStatementProcessor);
	}

	private static void callSummaryWithOCP(BankStatementProcessor bankStatementProcessor) {
		System.out.println("------------------------------------------------------------");
		System.out.println(
			"Find total transactions in January is " + bankStatementProcessor.findTransactions(
				bankTransaction -> bankTransaction.getDate().getMonth() == Month.JANUARY));

		System.out.println("Find total transactions greater or equals than 2000 is "
			+ bankStatementProcessor.findTransactions(bankTransaction -> bankTransaction.getAmount() >= 2000));
		System.out.println("Find total transactions greater or equals than 2000 in January is " + bankStatementProcessor
			.findTransactions(bankTransaction -> bankTransaction.getAmount() >= 2000
				&& bankTransaction.getDate().getMonth() == Month.FEBRUARY));
		System.out.println("------------------------------------------------------------");
	}

	public static void callSummary(BankStatementProcessor bankStatementProcessor) {
		System.out.println("------------------------------------------------------------");
		System.out.println(
			"Find total transactions in January is " + bankStatementProcessor.findTransactionsInMonth(Month.JANUARY));
		System.out.println("Find total transactions greater or equals than 2000 is "
			+ bankStatementProcessor.findTransactionsGreaterThanEqual(2000));
		System.out.println("Find total transactions greater or equals than 2000 in January is " + bankStatementProcessor
			.findTransactionsInMonthAndGreater(Month.FEBRUARY, 2000));
		System.out.println("------------------------------------------------------------");
	}
}
