package console;

import domain.external.ExchangeService;
import domain.io.Logger;
import domain.repository.ConversionHistoryRepository;
import external.CurrConvExchangeService;
import external.StubbedExchangeService;
import repository.InMemoryConversionHistoryRepository;

import java.util.Scanner;

public class ConsoleRunner {

    public void run() {
        Scanner scanner = new Scanner(System.in);

        Logger logger = new ConsoleLogger();
        ExchangeService exchangeService = new CurrConvExchangeService();
        ConversionHistoryRepository conversionHistoryRepo = new InMemoryConversionHistoryRepository();

        ConsoleCommandExecutor executor = new ConsoleCommandExecutor(
                conversionHistoryRepo,
                logger,
                exchangeService
        );

        while (true) {
            String line = scanner.nextLine();
            String[] args = line.split("\\s+");
            executor.execute(args);
        }
    }


}
