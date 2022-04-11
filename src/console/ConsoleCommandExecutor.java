package console;

import domain.commands.*;
import domain.commands.interfaces.Command;
import domain.entities.Money;
import domain.external.ExchangeService;
import domain.io.Logger;
import domain.repository.ConversionHistoryRepository;

import java.math.BigDecimal;

public class ConsoleCommandExecutor {

    private ConversionHistoryRepository conversionHistoryRepo;
    private Logger logger;
    private ExchangeService exchangeService;

    public ConsoleCommandExecutor(ConversionHistoryRepository conversionHistoryRepo,
                                  Logger logger,
                                  ExchangeService exchangeService) {

        this.conversionHistoryRepo = conversionHistoryRepo;
        this.logger = logger;
        this.exchangeService = exchangeService;
    }

    public void execute(String[] args) {
        switch (args[0]) {
            case "END":
                end();
                break;
            case "CONVERT":
                convert(args);
                break;
            case "HISTORY":
                history(args);
                break;
            default:
                logger.logLine("Invalid command " + args[0] + "!");
        }
    }

    private void history(String[] args) {
        Command<HistoryCommand.Input> cmd = new HistoryCommand(conversionHistoryRepo, logger);
        cmd.execute(new HistoryCommand.Input(Integer.parseInt(args[1])));
    }

    private void convert(String[] args) {
        BigDecimal fromValue = new BigDecimal(args[1]);
        String fromCurrency = args[2];
        String toCurrency = args[3];

        ConvertCommand.Input input = new ConvertCommand.Input(
                new Money(fromValue, fromCurrency),
                toCurrency
        );
        new HistorySavingConvertCommand(exchangeService,
                logger,
                conversionHistoryRepo).execute(input);
    }

    private void end() {
        new EndCommand().execute(new EmptyInput());
    }
}
