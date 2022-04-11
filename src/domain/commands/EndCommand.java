package domain.commands;

import domain.commands.interfaces.Command;

public class EndCommand implements Command<EmptyInput> {

    @Override
    public void execute(EmptyInput input) {
        System.exit(0);
    }


}
